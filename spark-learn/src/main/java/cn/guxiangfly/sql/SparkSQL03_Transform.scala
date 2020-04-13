package cn.guxiangfly.sql

import org.apache.spark.SparkConf
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.{DataFrame, Dataset, Row, SparkSession}

object SparkSQL03_Transform {

  case class User(id:Int, name:String , age:Int)

  def main(args: Array[String]): Unit = {
    val config : SparkConf = new SparkConf().setMaster("local[1]").setAppName("SparkSQL01_Demo")
    val spark: SparkSession = SparkSession.builder().config(config).getOrCreate()
    import spark.implicits._

    //创建RDD
    val rdd: RDD[(Int, String, Int)] = spark.sparkContext.makeRDD(List((1, "zhangsan", 20), (2, "zhangsan2", 20), (3, "zhangsan3", 20)))

    //RDD转DataFrame
    val frame: DataFrame = rdd.toDF("id","name","age")
    frame.show()

    //DataFrame转 dataSet
    val ds: Dataset[User] = frame.as[User]
    ds.show()

    //dataSet转 dataframe
    val frame2: DataFrame = ds.toDF()
    frame2.show()

    val rdd1: RDD[Row] = frame2.rdd

    rdd1.foreach(row => {
      println(row.getString(1))
    })


    spark.stop()
  }
}

