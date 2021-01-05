package cn.guxiangfly.apitest.state;

import cn.guxiangfly.apitest.beans.SensorReading;
import cn.guxiangfly.apitest.beans.SensorReadingCount;
import org.apache.flink.api.common.functions.RichMapFunction;
import org.apache.flink.api.common.state.ListState;
import org.apache.flink.api.common.state.ListStateDescriptor;
import org.apache.flink.api.common.state.MapState;
import org.apache.flink.api.common.state.MapStateDescriptor;
import org.apache.flink.api.common.state.ReducingState;
import org.apache.flink.api.common.state.ValueState;
import org.apache.flink.api.common.state.ValueStateDescriptor;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;


public class StateTest2_KeyedState_v2 {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setParallelism(1);

        // socket文本流
        DataStream<String> inputStream = env.socketTextStream("localhost", 7777);

        // 转换成SensorReading类型
        DataStream<SensorReading> dataStream = inputStream.map(line -> {
            String[] fields = line.split(",");
            return new SensorReading(fields[0], new Long(fields[1]), new Double(fields[2]));
        });

        // 定义一个有状态的map操作，统计当前sensor数据个数
        DataStream<SensorReadingCount> countStream = dataStream.keyBy("id").map(new MyKeyCountMapper());

        countStream.print("countStream");

        // resultStream.print();

        env.execute();
    }

    public static class MyKeyCountMapper extends RichMapFunction<SensorReading, SensorReadingCount> {

        private ValueState<Integer> keyCountState;

        @Override
        public void open(Configuration parameters) throws Exception {
            keyCountState = getRuntimeContext().getState(new ValueStateDescriptor<Integer>("key-count", Integer.class, 0));
        }

        @Override
        public SensorReadingCount map(SensorReading value) throws Exception {

            SensorReadingCount sensorReadingCount = new SensorReadingCount();

            Integer countStateValue = keyCountState.value();
            if (countStateValue == null){
                countStateValue = 1;
            }else {
                countStateValue++;
            }
            keyCountState.update(countStateValue);
            sensorReadingCount.setCount(countStateValue);
            sensorReadingCount.setKey(value.getId());
            return sensorReadingCount;
        }
    }
}
