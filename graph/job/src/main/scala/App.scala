
package com.example
/**
  Outputs
  -------
  > 

 */
object Main {

def main(args:Array[String]):Unit = {

import org.apache.spark.{SparkContext, SparkConf}
import org.apache.spark.SparkContext._
import org.apache.spark.rdd._
import com.typesafe.config._
import scala.collection.JavaConverters._

// Create spark configuration holder
val sparkConf = new SparkConf()

// Set configuration
val config = ConfigFactory.load()
val sparkConfig = config.getConfig("spark")
                        .atPath("spark").entrySet.asScala.map(e => e.getKey -> config.getString(e.getKey))

sparkConf.setAll(sparkConfig)

sparkConf.setMaster(sparkConf.get("spark.master", "local[*]"))
sparkConf.set("spark.app.name", sparkConf.get("spark.app.name", "graph"))

// Set project Jars

val libDir = new java.io.File(s"/usr/share/graph", "lib")
val currentProjectJars = Array[String]( "com.example-graph.graph-0.0.1-SNAPSHOT.jar" ).map{j => new java.io.File(libDir, j).getAbsolutePath}
val sparkLibDir = new java.io.File(".", "spark-lib")
val fromProjectJars = Array[String]().map{j => new java.io.File(sparkLibDir, j).getAbsolutePath}
val jarsArray = (sparkConf.get("spark.jars", "").split(",").toArray ++ currentProjectJars ++ fromProjectJars).distinct.filter(!_.isEmpty)
println("Add Jars: \n" + jarsArray.mkString("\n"))
sparkConf.setJars(jarsArray)



// Create Spark Context
val sparkContext = new SparkContext(sparkConf)
println("Debug Conf:" + sparkContext.getConf.toDebugString)

// Spark Context alias
val sc = sparkContext






  def getStringConfig(path: String) : Option[String] = {
    if (config.hasPath(path)) {
      Some(config.getString(path))
    } else {
      None
    }
  }

  val adalogUrl: Option[String] = getStringConfig("adalog.url")
  val adalogUser: Option[String] = getStringConfig("adalog.authentication.username")
  val adalogPassword: Option[String] = getStringConfig("adalog.authentication.password")


// no custom variables 
/* -- Code Cell: Some(1AEB0CD768CD4BB6B8F439D716386C53) -- */ 

  val users = Seq("Alice" , "Bob", "Charly", "Dean", "Eve", "Flor", "Greta")
  val pages = (1 to 10).map(i => s"page_$i")
/****************/


  /* -- Code Cell: Some(400F854404244C3696727C29258B7F12) -- */ 

  import scala.util.Random
  
  val userPages = for {
    user <- users
    page <- pages
    maybePage <- if (Random.nextInt(100)<70) Some(page) else None
  } yield (user,maybePage)
/****************/


  /* -- Code Cell: Some(613BC2CC4C8041B48BBBAFE269B4D0F7) -- */ 

  case class User(name:String) 
  case class Page(id:String) 
/****************/


  /* -- Code Cell: Some(CFE0BEEEB6AD48F48E269D7D77525E27) -- */ 

  val List(p1,p2,p3,p4,p5,p6) = (1 to 6).map(i=> Page(i.toString)).toList
/****************/


  /* -- Code Cell: Some(2380E474F7A54B368B4FF9930C7AB3CA) -- */ 

  val customUsers = Seq(User("Alice") -> Seq(p1,p2,p5), User("Bob") -> Seq(p2, p4,p5), User("Cloe") -> Seq(p1,p2,p4,p6), User("Danny") -> Seq(p6))
  val customUsersPages = customUsers.flatMap{case (u,pages) => pages.map(p=>(u,p))}
/****************/


  /* -- Code Cell: Some(0C7D3E9819E544388F157A17A781548A) -- */ 

  val userByPage = sparkContext.parallelize(customUsersPages)
/****************/


  /* -- Code Cell: Some(706BF97C49A94D67BFB2445D67186E6F) -- */ 

  userByPage.take(5)
/****************/


  /* -- Code Cell: Some(D419FFE67288480785F2155EBCAEC6F4) -- */ 

  val pageByUser = userByPage.map{case (k,v) => v.id -> k}
/****************/


  /* -- Code Cell: Some(5DAF010E677448F688459355E6888966) -- */ 

  val d1 = pageByUser.join(pageByUser).filter{case (p,(u1,u2)) => u1.name != u2.name}
/****************/


  /* -- Code Cell: Some(C38E6EE0197A49CD8647D3DDEEB132BF) -- */ 

  d1.take(10)
/****************/


  /* -- Code Cell: Some(5D00D19E9DEE43458B96DDA365C6D7E8) -- */ 

  val pagesPerUser = pageAndUser.groupByKey.mapValues(_.toArray)
/****************/


  /* -- Code Cell: Some(7A52509D7C4D40188A8B6D3871F23DD1) -- */ 

  pagesPerUser.take(3)
/****************/


  /* -- Code Cell: Some(2A3BCFA4F2C847E080AB64C6C23F2D14) -- */ 

  val pageAndUsers = pagesPerUser.map(_._2)
/****************/


  /* -- Code Cell: Some(774DA843517C4351A914BCE383CC3503) -- */ 

  pageAndUsers.take(3)
/****************/


  /* -- Code Cell: Some(A75FE947E879456E8D784D8FCFB27359) -- */ 

  pageAndUsers.count
/****************/


  /* -- Code Cell: Some(5069427CE56B45DE85BA40332DA8E74D) -- */ 

  val commonUsers = pageAndUsers.flatMap(users => users.map(user => (user,users)))
/****************/


  /* -- Code Cell: Some(4B95D49007C64D88866601A16CFE371D) -- */ 

  commonUsers.take(3)
/****************/


  /* -- Code Cell: Some(A2E82CCF12424C329836238E6BBCE822) -- */ 

  val commonUsersRed = commonUsers.reduceByKey(_ ++ _)
/****************/


  /* -- Code Cell: Some(C345DDED770F4EC68374B91DFC4877B0) -- */ 

  commonUsersRed.take(3)
/****************/


  /* -- Code Cell: Some(B1D073A95048498B8BDA9E55808F693E) -- */ 

  val commonUsersRedMap = commonUsersRed.map(users => (users._1, users._2.distinct))
/****************/


  /* -- Code Cell: Some(4FEBDF0A23D247EDAA2C291BE7710B00) -- */ 

  commonUsersRedMap.take(3)
/****************/


  /* -- Code Cell: Some(E404B2DA42EC4D12B76BA70973A7EB6C) -- */ 

  val page2user = pageAndUser.map(_.swap).groupByKey
/****************/


  /* -- Code Cell: Some(9FAE3EDD61D546F88542F61C234EA013) -- */ 

  page2user.take(3)
/****************/


  /* -- Code Cell: Some(AA92AEDFD86441168BFD06AE1C52F516) -- */ 

  pageAndUser.take(3)
/****************/


  /* -- Code Cell: Some(D3C3808715DE45F2BCEBA381868CAF3F) -- */ 

  val oneC= pageAndUser.map(_.swap).groupByKey
/****************/


  /* -- Code Cell: Some(6D248C6A94FD4FC281491403271436B3) -- */ 

  val nextC = oneC.flatMap{case (page, users) => users.headOption.map(head => (head,users.tail))}
/****************/


  /* -- Code Cell: Some(671035F15C5F47769C350B0A1B09F19E) -- */ 

  nextC.take(5)
/****************/


  /* -- Code Cell: Some(45B3A4822406472787FFE65F017AA92A) -- */ 

  val revC = nextC.map{case(from, toList) => toList.map(u => (u,from))}
/****************/


  /* -- Code Cell: Some(9BFADB0C742346AD800A212F65BFCA92) -- */ 

  revC.take(5)
/****************/


  /* -- Code Cell: Some(201C17A0E022490381D4942F8E14D5C2) -- */ 

  val pnu = pageAndUser.groupByKey.mapValues(_.toArray).map(line => line._2)
/****************/


  /* -- Code Cell: Some(C41BEE0B7FE64207B4999FD7113336EB) -- */ 

  pnu.take(5)
/****************/


  /* -- Code Cell: Some(42200AE0586A43B09CF9A7776305983D) -- */ 

  pageAndUser
/****************/


  /* -- Code Cell: Some(981B791FC1374D9E92CC69993CC7AB51) -- */ 

  pageAndUser
/****************/


  /* -- Code Cell: Some(8E3F9FFBAE5F40FE8AB7205DB51501DA) -- */ 

  val pageAndUsers = pageAndUser.map(_.swap).groupByKey.mapValues(_.toArray)
      .map(line => line._2)
  val commonUsers = pageAndUsers.flatMap(users => users.map(user => (user, users)))
      .reduceByKey(_ ++ _).cache()
      .map(users => (users._1, users._2.distinct))
/****************/


  /* -- Code Cell: Some(4A571CC5F8AA4666A27EF7F081CBAFE3) -- */ 

  commonUsers.take(5)
/****************/


  /* -- Code Cell: Some(823F1C67A6DE448EA84F5BE897D3E1AE) -- */

sparkContext.stop


}
}
