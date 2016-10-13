
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
sparkConf.set("spark.app.name", sparkConf.get("spark.app.name", "scala"))

// Set project Jars

val libDir = new java.io.File(".", "lib")
val currentProjectJars = Array[String]( "com.example-scala.scala-0.0.1-SNAPSHOT.jar" ).map{j => new java.io.File(libDir, j).getAbsolutePath}
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






val adalogUrl:Option[String] = Some(config.getString("adastyx.adalog.url"))

val adalogUser:Option[String] = None
val adalogPassword:Option[String] = None


// no custom variables 
/* -- Code Cell: Some(EC93FFA5CF19476CA5C0FDBF42AEE786) -- */ 

  trait T[M] {
    def t:M
  }
/****************/


  /* -- Code Cell: Some(F1E517EEEEC1455D8F02899661A42BE6) -- */ 

  (1 to 100).foreach(println(_))
/****************/


  /* -- Code Cell: Some(74DE5179010A47BAB539C8988DF16A58) -- */

}
