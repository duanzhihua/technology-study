package cn.guxiangfly.sql

import org.apache.spark.sql.{DataFrame, SparkSession}
import org.apache.spark.SparkConf

object SparkSQL01_Demo {
  def main(args: Array[String]): Unit = {
    val config : SparkConf = new SparkConf().setMaster("local[1]").setAppName("SparkSQL01_Demo")
    val spark: SparkSession = SparkSession.builder().config(config).getOrCreate()

    val dataFrame: DataFrame = spark.read.json("data/input/user.json")

    dataFrame.show()


    dataFrame.createOrReplaceTempView("user")
    spark.sql("select * from user").show()


    spark.stop()
  }
}
