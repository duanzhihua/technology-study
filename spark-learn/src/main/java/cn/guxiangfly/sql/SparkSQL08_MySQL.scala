package cn.guxiangfly.sql

import cn.guxiangfly.common.MusicInfo
import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession

object SparkSQL08_MySQL {
  def main(args: Array[String]): Unit = {

    val config : SparkConf = new SparkConf().setMaster("local[1]").setAppName("SparkSQL_HIVE_Demo")

    val spark: SparkSession = SparkSession
      .builder()
      .appName("Spark Hive Example")
      .config(config)
      .getOrCreate()

    val jdbcDF = spark.read
      .format("jdbc")
      .option("url", "jdbc:mysql://localhost:3306/kuwomusic?useUnicode=true&characterEncoding=utf8")
      .option("dbtable", "music_info")
      .option("user", "root")
      .option("password", "root")
      .load()
    import spark.implicits._
    jdbcDF.as[MusicInfo]
    var musicInfoListRdd = jdbcDF.as[MusicInfo].rdd
    musicInfoListRdd.foreach(
      item =>{
        println(item.music_lyric)
      }
    )
    

    spark.stop()
  }
}
