package com.atguigu.networkflow_analysis;/**
 * Copyright (c) 2018-2028 尚硅谷 All Rights Reserved
 * <p>
 * Project: UserBehaviorAnalysis
 * Package: com.atguigu.networkflow_analysis
 * Version: 1.0
 * <p>
 * Created by wushengran on 2020/11/16 15:28
 */

import com.atguigu.networkflow_analysis.beans.PageViewCount;
import com.atguigu.networkflow_analysis.beans.UserBehavior;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.TimeCharacteristic;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.timestamps.AscendingTimestampExtractor;
import org.apache.flink.streaming.api.functions.windowing.ProcessAllWindowFunction;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.streaming.api.windowing.triggers.Trigger;
import org.apache.flink.streaming.api.windowing.triggers.TriggerResult;
import org.apache.flink.streaming.api.windowing.windows.TimeWindow;
import org.apache.flink.util.Collector;
import redis.clients.jedis.Jedis;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ClassName: UvWithBloomFilter
 * @Description:
 * @Author: wushengran on 2020/11/16 15:28
 * @Version: 1.0
 */
public class UvWithBloomFilter_v2 {
    public static void main(String[] args) throws Exception {
        // 1. 创建执行环境
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setParallelism(1);
        env.setStreamTimeCharacteristic(TimeCharacteristic.EventTime);

        // 2. 读取数据，创建DataStream
        URL resource = UniqueVisitor.class.getResource("/UserBehavior.csv");
        DataStream<String> inputStream = env.readTextFile(resource.getPath());

        // 3. 转换为POJO，分配时间戳和watermark
        DataStream<UserBehavior> dataStream = inputStream
                .map(line -> {
                    String[] fields = line.split(",");
                    return new UserBehavior(new Long(fields[0]), new Long(fields[1]), new Integer(fields[2]), fields[3], new Long(fields[4]));
                })
                .assignTimestampsAndWatermarks(new AscendingTimestampExtractor<UserBehavior>() {
                    @Override
                    public long extractAscendingTimestamp(UserBehavior element) {
                        return element.getTimestamp() * 1000L;
                    }
                });

        // 开窗统计uv值
        SingleOutputStreamOperator<PageViewCount> uvStream = dataStream
                .filter(data -> "pv".equals(data.getBehavior()))
                .timeWindowAll(Time.hours(1))
                .trigger( new MyTrigger() )
                .process( new UvCountResultWithBloomFliter() );

        uvStream.print();

        env.execute("uv count with bloom filter job");
    }

    // 自定义触发器
    public static class MyTrigger extends Trigger<UserBehavior,TimeWindow>{

        /**
         * 来了一条数据，处理这条数据
         * @param element
         * @param timestamp
         * @param window
         * @param ctx
         * @return
         * @throws Exception
         */
        @Override
        public TriggerResult onElement(UserBehavior element, long timestamp, TimeWindow window, TriggerContext ctx) throws Exception {

            return TriggerResult.FIRE_AND_PURGE;
        }

        /**
         *  处理时间定时器时，要处理啥
         * @param time
         * @param window
         * @param ctx
         * @return
         * @throws Exception
         */
        @Override
        public TriggerResult onProcessingTime(long time, TimeWindow window, TriggerContext ctx) throws Exception {
            return TriggerResult.CONTINUE;
        }

        /**
         * 事件时间改变， 类似watermark往前推移了
         * @param time
         * @param window
         * @param ctx
         * @return
         * @throws Exception
         */
        @Override
        public TriggerResult onEventTime(long time, TimeWindow window, TriggerContext ctx) throws Exception {
            return TriggerResult.CONTINUE;
        }

        @Override
        public void clear(TimeWindow window, TriggerContext ctx) throws Exception {

        }
    }


    public static class MyBloomFilter{
        //定义位图的大小,一般需要定义为2的整次幂
        private Integer cap;

        public MyBloomFilter(Integer cap){
            this.cap = cap;
        }

        public Long hashCode (String value, Integer seed){
            Long result = 0L;
            for (int i = 0; i < value.length(); i++) {
                result = result * seed + value.charAt(i);
            }
            return result & (cap -1);
        }
    }


    public static class UvCountResultWithBloomFliter extends ProcessAllWindowFunction<UserBehavior, PageViewCount, TimeWindow>{

        //定义 jedis连接和布隆过滤器
        Jedis jedis;

        MyBloomFilter myBloomFilter;

        @Override
        public void open(Configuration parameters) throws Exception {
            jedis = new Jedis("localhost",6379 );
            myBloomFilter = new MyBloomFilter(1<<29); // 要处理一
            super.open(parameters);
        }

        @Override
        public void process(Context context, Iterable<UserBehavior> elements, Collector<PageViewCount> out) throws Exception {

            //将位图的count也存入redis
            Long windowEnd = context.window().getEnd();
            String bitmapKey = windowEnd.toString();

            String countHashName = "uv_count";
            String countKey = windowEnd.toString();

            UserBehavior next = elements.iterator().next();
            Long userId =next.getUserId();
            Long nowTime = next.getTimestamp() * 1000L;

            Long offset = myBloomFilter.hashCode(userId.toString(), 61);

            Boolean isExist = jedis.getbit(bitmapKey, offset);
            if (!isExist){
                //如果不存在，对应位图设置为1
                jedis.setbit(bitmapKey,offset,true);

                Long uvCount = 0L;
                String uvCountString = jedis.hget(countHashName, countKey);
                if (uvCountString !=null && !"".equals(uvCountString)){
                    uvCount = Long.valueOf(uvCountString);
                }
                jedis.hset(countHashName, countKey, String.valueOf(uvCount + 1));

                PageViewCount uv = new PageViewCount("uv", windowEnd, uvCount + 1);

                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String format = simpleDateFormat.format(new Date(nowTime));
                uv.setNowTime(format);
                out.collect(uv);
            }
        }


        @Override
        public void close() throws Exception {
            jedis.close();
        }
    }

}
