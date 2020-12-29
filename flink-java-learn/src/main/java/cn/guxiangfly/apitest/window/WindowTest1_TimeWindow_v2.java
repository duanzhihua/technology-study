package cn.guxiangfly.apitest.window;/**
 * Copyright (c) 2018-2028 尚硅谷 All Rights Reserved
 * <p>
 * Project: FlinkTutorial
 * Package: cn.guxiangfly.apitest.window
 * Version: 1.0
 * <p>
 * Created by wushengran on 2020/11/9 14:37
 */

import cn.guxiangfly.apitest.beans.SensorReading;
import org.apache.commons.collections.IteratorUtils;
import org.apache.flink.api.common.functions.AggregateFunction;
import org.apache.flink.api.java.tuple.Tuple;
import org.apache.flink.api.java.tuple.Tuple3;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.windowing.WindowFunction;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.streaming.api.windowing.windows.TimeWindow;
import org.apache.flink.util.Collector;
import org.apache.flink.util.OutputTag;

/**
 * @ClassName: WindowTest1_TimeWindow
 * @Description:
 * @Author: wushengran on 2020/11/9 14:37
 * @Version: 1.0
 */
public class WindowTest1_TimeWindow_v2 {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setParallelism(1);

//        // 从文件读取数据
//        DataStream<String> inputStream = env.readTextFile("/Users/mtdp/dev/ideaworkspace/guxiangwork/technology-study/flink-java-learn/src/main/resources/sensor.txt");

        // socket文本流
        DataStream<String> inputStream = env.socketTextStream("localhost", 7777);

        DataStream<SensorReading> dataStream = inputStream.map(line -> {
            System.out.println("input:" + line);
            String[] fields = line.split(",");
            return new SensorReading(fields[0], new Long(fields[1]), new Double(fields[2]));
        });

        DataStream<Integer> resultStream = dataStream.keyBy("id").timeWindow(Time.seconds(5)).aggregate(new AggregateFunction<SensorReading, Integer, Integer>() {
            @Override
            public Integer createAccumulator() {
                return 0;
            }

            @Override
            public Integer add(SensorReading value, Integer accumulator) {
                return accumulator + 1;
            }

            @Override
            public Integer getResult(Integer accumulator) {
                return accumulator;
            }

            // merge 基本不用，主要session window
            @Override
            public Integer merge(Integer a, Integer b) {
                return a + b;
            }
        });
        resultStream.print("result");

        SingleOutputStreamOperator<Integer> resultStream2 = dataStream.keyBy("id").timeWindow(Time.seconds(5)).apply(new WindowFunction<SensorReading, Integer, Tuple, TimeWindow>() {
            @Override
            public void apply(Tuple tuple, TimeWindow window, Iterable<SensorReading> input, Collector<Integer> out) throws Exception {
                int count = IteratorUtils.toList(input.iterator()).size();
                out.collect(count);
            }

        });

        resultStream2.print("result2");

        env.execute();
    }
}
