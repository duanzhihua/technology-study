package cn.guxiangfly.apitest.transform;/**
 * Copyright (c) 2018-2028 尚硅谷 All Rights Reserved
 * <p>
 * Project: FlinkTutorial
 * Package: cn.guxiangfly.apitest.transform
 * Version: 1.0
 * <p>
 * Created by wushengran on 2020/11/7 15:09
 */

import cn.guxiangfly.apitest.beans.SensorReading;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.java.functions.KeySelector;
import org.apache.flink.api.java.tuple.Tuple;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.KeyedStream;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

/**
 * @ClassName: TransformTest2_RollingAggregation
 * @Description:
 * @Author: wushengran on 2020/11/7 15:09
 * @Version: 1.0
 */
public class TransformTest2_RollingAggregation_v2 {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setParallelism(2);

        // 从文件读取数据
        DataStream<String> inputStream = env.readTextFile("/Users/mtdp/dev/ideaworkspace/guxiangwork/technology-study/flink-java-learn/src/main/resources/sensor.txt");

        // 转换成SensorReading类型
        DataStream<SensorReading> dataStream = inputStream.map((MapFunction<String, SensorReading>) value -> {
            String[] fields = value.split(",");
            return new SensorReading(fields[0], new Long(fields[1]), new Double(fields[2]));
        });

        KeyedStream<SensorReading, Tuple> keyedStream = dataStream.keyBy("id");

        KeyedStream<SensorReading, String> keyedStream1 = dataStream.keyBy(key -> key.getId());

        // max 的实现方式， 每次将读取下来的最大的一条数据，存储在内存中，然后
        SingleOutputStreamOperator<SensorReading> resultStream = keyedStream1.maxBy("temperature");


        resultStream.print();
        env.execute();
    }
}
