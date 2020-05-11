package cn.guxiangfly.sql

import java.io.File
import java.util

import org.apache.spark.SparkConf
import org.apache.spark.sql.{DataFrame, Row, SparkSession}

object SparkSQL07_Hive {
  def main(args: Array[String]): Unit = {

    val config : SparkConf = new SparkConf().setMaster("local[1]").setAppName("SparkSQL_HIVE_Demo")

    val spark: SparkSession = SparkSession
      .builder()
      .appName("Spark Hive Example")
      .config(config)
      .enableHiveSupport()
      .getOrCreate()

    spark.sql("show tables").show()
    val frame: DataFrame = spark.sql("select  singer_type,count(*) as likeWeight from singer_info group by singer_type")
    frame.show()
    val rows = new util.ArrayList[Row]()

    val units: Array[Row] = frame.rdd.map(item => {
      println(item)
      rows.add(item)
      item
    }).collect()
    val list: List[Row] = units.toList


    spark.stop()
  }
}
