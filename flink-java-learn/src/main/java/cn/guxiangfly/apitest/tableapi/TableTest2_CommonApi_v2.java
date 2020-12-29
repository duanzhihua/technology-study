package cn.guxiangfly.apitest.tableapi;/**
 * Copyright (c) 2018-2028 尚硅谷 All Rights Reserved
 * <p>
 * Project: FlinkTutorial
 * Package: cn.guxiangfly.apitest.tableapi
 * Version: 1.0
 * <p>
 * Created by wushengran on 2020/11/13 10:29
 */

import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.table.api.DataTypes;
import org.apache.flink.table.api.EnvironmentSettings;
import org.apache.flink.table.api.Table;
import org.apache.flink.table.api.TableEnvironment;
import org.apache.flink.table.api.java.BatchTableEnvironment;
import org.apache.flink.table.api.java.StreamTableEnvironment;
import org.apache.flink.table.descriptors.Csv;
import org.apache.flink.table.descriptors.FileSystem;
import org.apache.flink.table.descriptors.Schema;
import org.apache.flink.types.Row;

/**
 * @ClassName: TableTest2_CommonApi
 * @Description:
 * @Author: wushengran on 2020/11/13 10:29
 * @Version: 1.0
 */
public class TableTest2_CommonApi_v2 {
    public static void main(String[] args) throws Exception{
        // 1. 创建环境
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setParallelism(1);

        StreamTableEnvironment tableEnv = StreamTableEnvironment.create(env);

        //老版本
        EnvironmentSettings.Builder builder = EnvironmentSettings.newInstance();


        String filePath = "/Users/mtdp/dev/ideaworkspace/guxiangwork/technology-study/flink-java-learn/src/main/resources/sensor.txt";

        tableEnv.connect( new FileSystem().path(filePath)).withFormat(new Csv()).withSchema(new Schema().field("id", DataTypes.STRING())
                .field("timestamp", DataTypes.BIGINT())
                .field("temp", DataTypes.DOUBLE())
        ).createTemporaryTable("inputTable");


        Table inputTable = tableEnv.from("inputTable");

        tableEnv.toAppendStream(inputTable,Row.class).print("inputTable");


        Table sqlAggTable = tableEnv.sqlQuery("select id, count(id) as cnt, avg(temp) as avgTemp from inputTable group by id");


        tableEnv.toRetractStream(sqlAggTable,Row.class).print("sqlAggTable");

        // 1.1 基于老版本planner的流处理


        env.execute();
    }
}
