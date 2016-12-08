
package io.kensu.classtest
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
sparkConf.set("spark.app.name", sparkConf.get("spark.app.name", "class_test"))

// Set project Jars

val libDir = new java.io.File(s"/usr/share/class-test", "lib")
val currentProjectJars = Array("io.kensu.class-test-0.0.1-SNAPSHOT.jar").map{j => new java.io.File(libDir, j).getAbsolutePath}
val sparkLibDir = new java.io.File(s"/usr/share/class-test", "spark-lib")
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
/* -- Code Cell: Some(D7C3019AC2054904A36B549CD872D3B8) -- */ 

  val genres = Seq("horror", "drama", "western").map(Genre(_))
/****************/


  /* -- Code Cell: Some(F92966D76F8E4432BFDA2B0424171822) -- */ 

  val movies = Seq("rain", "murder", "killing", "humpy jumpy", "boem boem").map(m => Movie(genres(scala.util.Random.nextInt(genres.size)), m))
/****************/


  /* -- Code Cell: Some(0B56D7FDF0FB4AD38C4A185AA4C45376) -- */ 

  import org.apache.spark.sql.SQLContext
/****************/


  /* -- Code Cell: Some(F5D1E62E668045678087AFE488C10BE8) -- */ 

  val sqlCtx = new SQLContext(sc)
/****************/


  /* -- Code Cell: Some(ECC37B415E504865AB2A11FD776805D7) -- */ 

  import sqlCtx.implicits._
/****************/


  /* -- Code Cell: Some(924FE0CD9CD94963ACF8CBA6E6708980) -- */ 

  val movieDF = sqlCtx.createDataFrame(movies)
/****************/


  /* -- Code Cell: Some(7787A0DA5E7E41DE8A6EB3968C570F8E) -- */ 

  movieDF.show()
/****************/


  /* -- Code Cell: Some(F7B662D74FEF4A368B77E682BC519FBE) -- */

sparkContext.stop


}
}
