package cn.guxiangfly.apitest.processfunction;

import cn.guxiangfly.apitest.beans.SensorReading;
import org.apache.flink.api.common.state.ValueState;
import org.apache.flink.api.common.state.ValueStateDescriptor;
import org.apache.flink.api.java.tuple.Tuple;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.KeyedProcessFunction;
import org.apache.flink.util.Collector;


public class ProcessTest1_KeyedProcessFunction_v2 {
    public static void main(String[] args) throws Exception{
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setParallelism(1);

        // socket文本流
        DataStream<String> inputStream = env.socketTextStream("localhost", 7777);

        // 转换成SensorReading类型
        DataStream<SensorReading> dataStream = inputStream.map(line -> {
            String[] fields = line.split(",");
            return new SensorReading(fields[0], new Long(fields[1]), new Double(fields[2]));
        });

        // 测试KeyedProcessFunction，先分组然后自定义处理
       // dataStream.keyBy("id").process()

        env.execute();
    }


    public static class MyProcess extends KeyedProcessFunction<Tuple,SensorReading,Integer>{

        ValueState<Long> tsTimerState;

        @Override
        public void open(Configuration parameters) throws Exception {
            super.open(parameters);
            tsTimerState =  getRuntimeContext().getState(new ValueStateDescriptor<Long>("ts-timer", Long.class));
        }

        @Override
        public void processElement(SensorReading value, Context ctx, Collector<Integer> out) throws Exception {

            out.collect(value.getId().length());
            Tuple currentKey = ctx.getCurrentKey();
            Long timestamp = ctx.timestamp();

            //设置侧输出流
          //  ctx.output();
            long processingTime = ctx.timerService().currentProcessingTime();
            long currentWatermark = ctx.timerService().currentWatermark();

        }

        @Override
        public void onTimer(long timestamp, OnTimerContext ctx, Collector<Integer> out) throws Exception {

        }
    }


}
