package cn.guxiangfly.wc;

import org.apache.flink.api.java.DataSet;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.api.java.utils.ParameterTool;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

/**
 * @Author guxiang02
 * @Date 2020/12/21
 **/
public class StreamWordCountFile {
    public static void main(String[] args) throws Exception{
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        // 从文件中读取数据
//        String inputPath = "/Users/mtdp/dev/ideaworkspace/guxiangwork/technology-study/flink-java-learn/src/main/resources/hello.txt";
//        DataStreamSource<String> inputDataStream = env.readTextFile(inputPath);
//        // 对流数据，没有groupby  只有 keyby，（group 本身就只应该出现在批处理中， 流处理中，来一个key，处理一个key， keyby是按照key的hashcode，进行一个重分区，将所有key都在同一个分区中，那么内部计算也比较容易）
//        DataStream<Tuple2<String, Integer>> resultStream = inputDataStream.flatMap(new WordCount.MyFlatMapper()).keyBy(0).sum(1);
        // resultStream.print();
        //env.execute();


//        ParameterTool parameterTool = ParameterTool.fromArgs(args);
//        String host = parameterTool.get("host");
//        int port = parameterTool.getInt("port");


        /**
         * 使用工具： nc -lk 7777     注：7777为端口号
         *
         */
        DataStream<String> inputDataStream = env.socketTextStream("127.0.0.1", 7777);

        DataStream<Tuple2<String, Integer>> resultStream = inputDataStream.flatMap(new WordCount.MyFlatMapper()).keyBy(0).sum(1).setParallelism(2).slotSharingGroup("red");

        resultStream.print().setParallelism(1);

        env.execute();
    }
}
