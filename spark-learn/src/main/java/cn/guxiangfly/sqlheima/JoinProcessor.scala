package cn.guxiangfly.sqlheima

import org.apache.spark.sql.SparkSession
import org.junit.Test


class JoinProcessor {

  val spark = SparkSession.builder().master("local[6]").appName("join").getOrCreate();

  import spark.implicits._

  @Test
  def introJoin():Unit = {
    val person = Seq(0,"lucy")
  }
}
