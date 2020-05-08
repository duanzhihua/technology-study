package cn.guxiangfly.sql

import org.apache.spark.SparkConf
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.{DataFrame, Dataset, Row, SparkSession}

object SparkSQL03_Transform02 {

  case class User(id:Int, name:String , age:Int)

  def main(args: Array[String]): Unit = {
    val config : SparkConf = new SparkConf().setMaster("local[1]").setAppName("SparkSQL01_Demo")
    val spark: SparkSession = SparkSession.builder().config(config).getOrCreate()
    import spark.implicits._

    //创建RDD
    val rdd: RDD[(Int, String, Int)] = spark.sparkContext.makeRDD(List((1, "zhangsan", 20), (2, "zhangsan2", 20), (3, "zhangsan3", 20)))

    //RDD转DataSet
    val userRDD: RDD[User] = rdd.map {
      case (id, name, age) => {
        User(id, name, age)
      }
    }
    val userDataSet: Dataset[User] = userRDD.toDS()
    userDataSet.show()

    val rdd1: RDD[User] = userDataSet.rdd

    spark.stop()
  }
}

