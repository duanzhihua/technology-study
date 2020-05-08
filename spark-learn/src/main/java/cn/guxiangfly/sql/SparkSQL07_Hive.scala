package cn.guxiangfly.sql

import java.io.File

import org.apache.spark.SparkConf
import org.apache.spark.sql.{DataFrame, SparkSession}

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
    spark.sql("select  singer_type,count(*) from singer_info group by singer_type").show()

    spark.sql("select * from music_info ").show()

    

    spark.stop()
  }
}
