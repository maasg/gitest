
package io.kensu
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
sparkConf.set("spark.app.name", sparkConf.get("spark.app.name", "generateclass"))

// Set project Jars

val libDir = new java.io.File(s"/usr/share/generateclass", "lib")
val currentProjectJars = Array[String]( "io.kensu-generateclass.generateclass-0.0.1-SNAPSHOT.jar" ).map{j => new java.io.File(libDir, j).getAbsolutePath}
val sparkLibDir = new java.io.File(s"/usr/share/generateclass", "spark-lib")
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
/* -- Code Cell: Some(3F5D5253B0154E97A5410FB29717A692) -- */ 

  val starter = 1 to 100
  val ender = 1 to 50
  val seq  = for { 
    i <- starter
    j <- ender
  } yield {
    Caser(i,j)
  }
/****************/


  /* -- Code Cell: Some(4860FADC962F420485BA5EDBF48D4735) -- */ 

  val df = sparkContext.parallelize(seq)
/****************/


  /* -- Code Cell: Some(D1AAA5417F0749DF8A9EF06D5227BCAA) -- */ 

  val summed = df.map{case Caser(x,y) => x+y}.sum
/****************/


  /* -- Code Cell: Some(68CE547B73C34FD78D0C2AB40F24B72F) -- */ 

  println(s"summed: $summed")
/****************/


  /* -- Code Cell: Some(F32839153A34427AB8565A4EA91BAB09) -- */

sparkContext.stop


}
}
