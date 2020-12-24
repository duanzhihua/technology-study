package cn.guxiangfly.core
package cn.guxiangfly.sql

import org.apache.spark.SparkConf
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.{DataFrame, Dataset, Row, SparkSession}

class RollupStudy {

  def rollup(): Unit = {
    import org.apache.spark.sql.functions._

    val sales = Seq(
      ("Beijing", 2016, 100),
      ("Beijing", 2017, 200),
      ("shanghai", 2015, 50),
      ("shanghai", 2016, 100),
      ("guangzhou", 2017, 50)
    ).toDF("city", "year","amount")


  }
}
