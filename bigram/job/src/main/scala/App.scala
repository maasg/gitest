
package io.kensu.bigram
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
sparkConf.set("spark.app.name", sparkConf.get("spark.app.name", "bigram"))

// Set project Jars

val libDir = new java.io.File(s"/usr/share/bigram", "lib")
val currentProjectJars = Array("io.kensu.bigram-0.0.1-SNAPSHOT.jar").map{j => new java.io.File(libDir, j).getAbsolutePath}
val sparkLibDir = new java.io.File(s"/usr/share/bigram", "spark-lib")
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


// no custom variables 
/* -- Code Cell: Some(6B74DA4CB3764875B584D84761477DB1) -- */ 

  
  val dataStr="""love, new, truck, present
  environment, save, trying, stop, destroying
  great, environment, save, money, animals, zoo, daughter, fun
  impressive, loved, speech, inspiration
  Happy Birthday, brother, years, old
  save, money, stop, spending
  new, haircut, love, check it out
  """
  val data = sparkContext.parallelize(dataStr.split("\n"))
/****************/


  /* -- Code Cell: Some(36C45ECD3CEA4129A88EDF2B242E9D96) -- */ 

  val phrases = data.map(line=> line.split(",").map(_.trim))
/****************/


  /* -- Code Cell: Some(E21CC7C53EDB4B5C897A962FD0730FD0) -- */ 

  phrases.take(2)
/****************/


  /* -- Code Cell: Some(C06827926D654A31928AF0613D0C3F0E) -- */ 

  val bigrams = phrases.flatMap(phrase => phrase.sliding(2).map{
      case Array(x,y) => (x,y)
      case _ => ("","")
  }.toSeq)
/****************/


  /* -- Code Cell: Some(FD153382608E4311A5BF83A137C0CC1E) -- */ 

  bigrams.take(2)
/****************/


  /* -- Code Cell: Some(6C4AC0748B6C4E578B5A5D1E6CDE1D83) -- */ 

  val bigramCount = bigrams.map(bigram => (bigram, 1)).reduceByKey(_+_) 
/****************/


  /* -- Code Cell: Some(9F0B5C9BC6C54D1A9A12636F2142835A) -- */ 

  bigramCount.collect
/****************/


  /* -- Code Cell: Some(80483A7B39C34F49993813F22305A166) -- */ 

  
/****************/


  /* -- Code Cell: Some(E3161ED635DB439B82496D6797EFE80F) -- */

sparkContext.stop


}
}
