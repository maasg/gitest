
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
sparkConf.set("spark.app.name", sparkConf.get("spark.app.name", "helloworld-shortcut"))

// Set project Jars

val libDir = new java.io.File(".", "lib")
val currentProjectJars = Array[String]( "com.example-helloworld-shortcut.helloworld-shortcut-0.0.1-SNAPSHOT.jar" ).map{j => new java.io.File(libDir, j).getAbsolutePath}
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
/* -- Code Cell: Some(4D106591454A485E8B3177808537A13C) -- */ 

  val salutation = Seq("hello", "hi", "hola", "bonjour", "hallo", "ciao", "saluton", "hei", "hoi", "kaixo", "zdravo")
  val subjects = Seq("world", "mundo", "mundi", "monde", "wereld")
/****************/


  /* -- Code Cell: Some(D2664A3E55C74AAA832A8B0BD098C69E) -- */ 

  val isFrench:String => Boolean = s => s=="bonjour" || s=="monde"
/****************/


  /* -- Code Cell: Some(9C4DA32CF7EB420095408BC96847FEBE) -- */ 

  val r1 = sparkContext.parallelize(salutation)
  val r2 = sparkContext.parallelize(subjects)
/****************/


  /* -- Code Cell: Some(12C6EBE0DA13447C878CA4F21663056C) -- */ 

  //sanity check
  val elements = r1.collect()
  val haveSameElements = (elements.toSet -- salutation.toSet).isEmpty
  println(s"Data should equal: $haveSameElements") 
/****************/


  /* -- Code Cell: Some(CA473EFBE90C496187B144D69E1F650A) -- */ 

  val combs = r1.cartesian(r2)
/****************/


  /* -- Code Cell: Some(359C42C4B9774EAF9AC40D0889A929C7) -- */ 

  println("=== Combinations ===")
  println(combs.toDebugString)
/****************/


  /* -- Code Cell: Some(A5737A7CF8524978933D129311CEF302) -- */ 

  val frenchCombo = combs.filter{case (sal,sub) => isFrench(sal) && isFrench(sub)}
/****************/


  /* -- Code Cell: Some(B905F6D0CB4A40EABB23E190B077C44E) -- */ 

  println("=== French Combo ===")
  println(frenchCombo.toDebugString)
/****************/


  /* -- Code Cell: Some(E44B742ED3824E158F54E370571F6375) -- */ 

  val result = frenchCombo.collect()
/****************/


  /* -- Code Cell: Some(84613BE29C2D4BB397C45313D0D0A502) -- */ 

  println("Result")
  println(result.mkString(","))

sparkContext.stop


}
