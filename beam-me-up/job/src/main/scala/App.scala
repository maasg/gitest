
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
sparkConf.set("spark.app.name", sparkConf.get("spark.app.name", "beam-me-up"))

// Set project Jars

val libDir = new java.io.File(".", "lib")
val currentProjectJars = Array[String]( "com.example-beam-me-up.beam-me-up-0.0.1.jar" ).map{j => new java.io.File(libDir, j).getAbsolutePath}
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


// no custom variables 
/* -- Code Cell: Some(A83FA95CEB5345578AA71DC2FF4DFFDE) -- */ 

  val v=sparkContext.range(1L, 1000L, 10)
/****************/


  /* -- Code Cell: Some(BE09518B7C26458E8E7EC4DF4FC38596) -- */ 

  import scala.util.Random
  val rdd = v.map(e => Random.nextInt())
/****************/


  /* -- Code Cell: Some(ED671E39045C4DBB82551E1E5D725044) -- */ 

  rdd.reduce(_ max _)
/****************/


  /* -- Code Cell: Some(E8F4CC3BDB1A401288A9A71740F1FBB4) -- */ 

  rdd.reduce(_ min _)
/****************/


  /* -- Code Cell: Some(87F6E075876C4B6681457D0F02571467) -- */ 

  rdd.sum / rdd.count
/****************/


  /* -- Code Cell: Some(4C7AAEDD4ACE40008CF40AC53C1A1233) -- */

}
