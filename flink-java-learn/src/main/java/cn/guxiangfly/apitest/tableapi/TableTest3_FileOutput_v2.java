package cn.guxiangfly.apitest.tableapi;/**
 * Copyright (c) 2018-2028 尚硅谷 All Rights Reserved
 * <p>
 * Project: FlinkTutorial
 * Package: cn.guxiangfly.apitest.tableapi
 * Version: 1.0
 * <p>
 * Created by wushengran on 2020/11/13 11:54
 */

import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.table.api.DataTypes;
import org.apache.flink.table.api.Table;
import org.apache.flink.table.api.java.StreamTableEnvironment;
import org.apache.flink.table.descriptors.Csv;
import org.apache.flink.table.descriptors.FileSystem;
import org.apache.flink.table.descriptors.Schema;
import org.apache.flink.types.Row;

/**
 * @ClassName: TableTest3_FileOutput
 * @Description:
 * @Author: wushengran on 2020/11/13 11:54
 * @Version: 1.0
 */
public class TableTest3_FileOutput_v2 {
    public static void main(String[] args) throws Exception {
        // 1. 创建环境
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setParallelism(1);

        StreamTableEnvironment tableEnv = StreamTableEnvironment.create(env);

        // 2. 表的创建：连接外部系统，读取数据
        // 读取文件
        String filePath = "/Users/mtdp/dev/ideaworkspace/guxiangwork/technology-study/flink-java-learn/src/main/resources/sensor.txt";
        tableEnv.connect( new FileSystem().path(filePath))
                .withFormat( new Csv())
                .withSchema( new Schema()
                        .field("id", DataTypes.STRING())
                        .field("timestamp", DataTypes.BIGINT())
                        .field("temp", DataTypes.DOUBLE())
                )
                .createTemporaryTable("inputTable");

        Table inputTable = tableEnv.from("inputTable");
//        inputTable.printSchema();
//        tableEnv.toAppendStream(inputTable, Row.class).print();

        // 3. 查询转换
        // 3.1 Table API
        // 简单转换
        Table resultTable = inputTable.select("id, temp")
                .filter("id === 'sensor_6'");

        // 聚合统计
        Table aggTable = inputTable.groupBy("id")
                .select("id, id.count as count, temp.avg as avgTemp");

        // 3.2 SQL
        tableEnv.sqlQuery("select id, temp from inputTable where id = 'senosr_6'");
        Table sqlAggTable = tableEnv.sqlQuery("select id, count(id) as cnt, avg(temp) as avgTemp from inputTable group by id");

        // 4. 输出到文件
        // 连接外部文件注册输出表
        String outputPath = "/Users/mtdp/dev/ideaworkspace/guxiangwork/technology-study/flink-java-learn/src/main/resources/out.txt";
        tableEnv.connect( new FileSystem().path(outputPath))
                .withFormat( new Csv())
                .withSchema( new Schema()
                        .field("id", DataTypes.STRING())
                        .field("temperature", DataTypes.DOUBLE())
                )
                .createTemporaryTable("outputTable");

        resultTable.insertInto("outputTable");
//        aggTable.insertInto("outputTable");

        DataStream<Row> resultAppendStream = tableEnv.toAppendStream(resultTable, Row.class);
        DataStream<Tuple2<Boolean, Row>> resultRetractStream = tableEnv.toRetractStream(resultTable, Row.class);

        env.execute();
    }
}
