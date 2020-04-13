package scalastudy

object ImplicitConvertTest {

  def main(args: Array[String]): Unit = {

    implicit def f1(b:Double): Int ={
      return b.toInt
    }

    val a:Int = 3.5
  //var a:Int = f1$1(3.5)

    implicit def addDelete(msql:MySQL): DB = {
      new DB
    }

    val mySQL = new MySQL
    mySQL.insert();
    mySQL.delete();
    // mySQL这个对象符合addDelete这个隐式函数的形参类型MySQL, 于是包装了一个 addDelete$1(mySQL)
    // 这个函数的返回值DB类型符合delete
    // 于是做了以下的包装
    // addDelete$1(mySQL).delete()
  }

  class MySQL {
    def insert(): Unit = {
      println("insert")
    }
  }

  class DB {
    def delete(): Unit = {
      println("delete")
    }
  }
}
