
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
sparkConf.set("spark.app.name", sparkConf.get("spark.app.name", "newbum"))

// Set project Jars

val libDir = new java.io.File(".", "lib")
val currentProjectJars = Array[String]( "com.example-newbum.newbum-0.0.1-SNAPSHOT.jar" ).map{j => new java.io.File(libDir, j).getAbsolutePath}
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
/* -- Code Cell: Some(A3F5EC37A1EC48A58084F2D58EFB6BF0) -- */ 

  val rdd = sparkContext.parallelize(1 to 1000)
/****************/


  /* -- Code Cell: Some(ACCFAE7A462B49FD8C975A7F7262BDE4) -- */ 

  rdd.sum
/****************/


  /* -- Code Cell: Some(EAE05D512F744BE58A557DD4AB4B9233) -- */ 

  val nr = System.currentTimeMillis % (24*60*60)
/****************/


  /* -- Code Cell: Some(2FC76FA0178C4C638A125AE4F4370086) -- */ 

  rdd.saveAsTextFile(s"/tmp/newbum.rdd-$nr.txt")
/****************/


  /* -- Code Cell: Some(781AAC4B3CDF4CA697F9B24E4B69570D) -- */ 

  val ranTable = rdd.map(e => (e, scala.util.Random.nextInt(100)))
/****************/


  /* -- Code Cell: Some(3F7DD103D6614D408A6D34676BE10B43) -- */ 

  val localRanTable = ranTable.collect.take(100)
  localRanTable
/****************/


  /* -- Code Cell: Some(DAC8B24B6EF34912ACA275C92B02AF22) -- */ 

   localRanTable.foreach(e=> println(e))
/****************/


  /* -- Code Cell: Some(D07EFC328BE14C34A5CE3D6217AD1129) -- */ 

  sc.stop

}
