package cn.guxiangfly.core

import java.io.File

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Spark001RDD {
  def main(args: Array[String]): Unit = {
    val config : SparkConf = new SparkConf().setMaster("local[1]").setAppName("wordCount")
    val sc: SparkContext = new SparkContext(config)
    val listRDD: RDD[Int] = sc.makeRDD(List(1, 2, 3, 4, 5),2)
    val listRDD2: RDD[Int] = sc.makeRDD(List(1, 2, 3, 4, 5,6,7),3)
    new File("data/output").deleteOnExit()
    listRDD2.saveAsTextFile("data/output")


    val sales = Seq(
      ("Beijing", 2016, 100),
      ("Beijing", 2017, 200),
      ("shanghai", 2015, 50),
      ("shanghai", 2016, 100),
      ("guangzhou", 2017, 50)
    ).toDF("city", "year","amount")

  }
}
