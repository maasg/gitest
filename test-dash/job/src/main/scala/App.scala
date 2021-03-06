
package io.kensu.testdash
/**
  Outputs
  -------
  > 

 */
object Main {

def main(args:Array[String]):Unit = {
// spark context

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
sparkConf.set("spark.app.name", sparkConf.get("spark.app.name", "test-dash"))

// Set project Jars

val libDir = new java.io.File(s"/usr/share/test-dash", "lib")
val currentProjectJars = Array("io.kensu.test-dash-0.0.1-SNAPSHOT.jar").map{j => new java.io.File(libDir, j).getAbsolutePath}
val sparkLibDir = new java.io.File(s"/usr/share/test-dash", "spark-lib")
val fromProjectJars = Array[String]().map{j => new java.io.File(sparkLibDir, j).getAbsolutePath}
val jarsArray = (sparkConf.get("spark.jars", "").split(",").toArray ++ currentProjectJars ++ fromProjectJars).distinct.filter(!_.isEmpty)
println("Add Jars: \n" + jarsArray.mkString("\n"))
sparkConf.setJars(jarsArray)



// Create Spark Context
val sparkContext = new SparkContext(sparkConf)
println("Debug Conf:" + sparkContext.getConf.toDebugString)

// Spark Context alias
val sc = sparkContext



// main code



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



val customVars = config.getConfig("notebook.custom.vars")
// custom variables
  val rootDir = customVars.getString("rootDir") 

     
/* -- Code Cell: Some(DA127C236725455A8C0BCD108BA70123) -- */ 

  println(1 to 100)
/****************/


  /* -- Code Cell: Some(6559727CE4D44896BFC00A885E4114CD) -- */ 

  println("my____notebook".toLowerCase.split("[\\W_]+").map(_.capitalize).mkString(""))
/****************/


  /* -- Code Cell: Some(0F641608E7F54C3381CAEECA4776D8AC) -- */

sparkContext.stop


}
}
