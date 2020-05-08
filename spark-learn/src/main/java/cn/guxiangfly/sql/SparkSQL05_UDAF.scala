package cn.guxiangfly.sql

import org.apache.spark.SparkConf
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.expressions.{MutableAggregationBuffer, UserDefinedAggregateFunction}
import org.apache.spark.sql.types.{DataType, DoubleType, LongType, StructType}
import org.apache.spark.sql.{DataFrame, Dataset, Row, SparkSession}

object SparkSQL06_UDAF {

  case class User(id:Int, name:String , age:Int)

  def main(args: Array[String]): Unit = {
    val config : SparkConf = new SparkConf().setMaster("local[1]").setAppName("SparkSQL01_Demo")
    val spark: SparkSession = SparkSession.builder().config(config).getOrCreate()
    import spark.implicits._

    //创建RDD
    val udaf = new MyAgeAvgFunction

    spark.udf.register("avgAge", udaf)

    //使用聚合函数
    val dataFrame: DataFrame = spark.read.json("data/input/user.json")

    dataFrame.createOrReplaceTempView("user")

    spark.sql("select avgAge(age) from user").show




    spark.stop()
  }
}


class MyAgeAvgFunction extends UserDefinedAggregateFunction {

  //函数输入的数据结构
  override def inputSchema: StructType = {
    new StructType().add("age",LongType)
  }

  //函数计算的数据类型
  override def bufferSchema: StructType = {
    new StructType().add("sum",LongType).add("count",LongType)
  }

  //函数返回的数据类型
  override def dataType: DataType = DoubleType



  override def deterministic: Boolean = true
  //计算之前的缓冲区初始化
  override def initialize(buffer: MutableAggregationBuffer): Unit = {
    buffer(0) = 0L //sum
    buffer(1) = 0L //count
  }

  //根据查询的结果 输入的值  来更新我们的缓存区
  override def update(buffer: MutableAggregationBuffer, input: Row): Unit = {
    //sum
    buffer(0) =  buffer.getLong(0) + input.getLong(0)
    //count
    buffer(1) = buffer.getLong(1) +1
  }

  override def merge(buffer1: MutableAggregationBuffer, buffer2: Row): Unit = {
    //sum
   buffer1(0) =  buffer1.getLong(0) + buffer2.getLong(0)
    //count
    buffer1(1) = buffer1.getLong(1) + buffer2.getLong(1)
  }

  override def evaluate(buffer: Row): Any = {
    val result =  buffer.getLong(0)/ buffer.getLong(1)
    return result.toDouble
  }
}

