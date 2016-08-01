
package com.example
/**
  Outputs
  -------
  > 

 */
object Main extends App {

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
sparkConf.set("spark.app.name", sparkConf.get("spark.app.name", "sertest"))

// Set project Jars

val libDir = new java.io.File(".", "lib")
val currentProjectJars = Array[String]( "com.example-sertest.sertest-0.0.1-SNAPSHOT.jar" ).map{j => new java.io.File(libDir, j).getAbsolutePath}
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






val adalogUrl:Option[String] = None

val adalogUser:Option[String] = None
val adalogPassword:Option[String] = None


// no custom variables 
/* -- Code Cell: Some(AFC7C939A9F14FECB7004090F887513E) -- */ 

  val serTest = 1 to 100
/****************/


  /* -- Code Cell: Some(FFFE925F97354270908107C25768F3B6) -- */ 

  serTest.map(e => (e, scala.util.Random.nextInt(100)))
/****************/


  /* -- Code Cell: Some(C46587982C7B49EFB30C8C4142717B99) -- */ 

  import org.joda.time.DateTime
  val moreStuff = new DateTime()
/****************/


  /* -- Code Cell: Some(761F4B94598F4BED8BD3B0C22D29E31B) -- */

}
