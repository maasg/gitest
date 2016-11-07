
package com.example
/**
  Outputs
  -------
  > ParquetOutput(CodeCell(CellMetadata(Some(true),Some(false),None,Some(false),None,Some(251A626CFF1844A485288DFE95BE81A7),Some({"schema":{"type":"struct","fields":[{"name":"id","type":"integer","nullable":false,"metadata":{}},{"name":"category","type":"string","nullable":true,"metadata":{}},{"name":"impressions","type":"long","nullable":false,"metadata":{}},{"name":"rate","type":"double","nullable":false,"metadata":{}}]},"inputs":{"resolved":[],"unresolved":[]},"saveMode":"org.apache.spark.sql.SaveMode.ErrorIfExists"})),output,"/tmp/df/data/sample"+(System.currentTimeMillis.toString.drop(6).take(4)),None,None,Some({"type":"parquet","var":"dataset","extra":{"value":"\"\"","source":null}}),Some(List(ScalaStream(stdout,stream,Parquet
Located: /tmp/df/data/sample2841
DataFrame: dataset
output-251A626CFF1844A485288DFE95BE81A7: String = /tmp/df/data/sample2841
res11: notebook.front.widgets.adst.ParquetOutputWidget = <ParquetOutputWidget widget>
), ScalaExecuteResult(ExecuteResultMetadata(None),Map(text/html -> <div>
      <script data-this="{&quot;dfSchema&quot;:{&quot;type&quot;:&quot;struct&quot;,&quot;fields&quot;:[{&quot;name&quot;:&quot;id&quot;,&quot;type&quot;:&quot;integer&quot;,&quot;nullable&quot;:false,&quot;metadata&quot;:{}},{&quot;name&quot;:&quot;category&quot;,&quot;type&quot;:&quot;string&quot;,&quot;nullable&quot;:true,&quot;metadata&quot;:{}},{&quot;name&quot;:&quot;impressions&quot;,&quot;type&quot;:&quot;long&quot;,&quot;nullable&quot;:false,&quot;metadata&quot;:{}},{&quot;name&quot;:&quot;rate&quot;,&quot;type&quot;:&quot;double&quot;,&quot;nullable&quot;:false,&quot;metadata&quot;:{}}]},&quot;dfInputs&quot;:{&quot;resolved&quot;:[],&quot;unresolved&quot;:[]},&quot;saveMode&quot;:&quot;org.apache.spark.sql.SaveMode.ErrorIfExists&quot;}" type="text/x-scoped-javascript">/*<![CDATA[*/req(['../javascripts/notebook/adst/output/parquetOutput'], 
      function(parquetOutput) {
        parquetOutput.call(data, this);
      }
    );/*]]>*/</script>
    </div>),execute_result,11)))),"/tmp/df/data/sample"+(System.currentTimeMillis.toString.drop(6).take(4)),"/tmp/df/data/sample"+(System.currentTimeMillis.toString.drop(6).take(4)),dataset,org.apache.spark.sql.SaveMode."",List(),0)

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
sparkConf.set("spark.app.name", sparkConf.get("spark.app.name", "datagenerator"))

// Set project Jars

val libDir = new java.io.File(s"/usr/share/datagenerator", "lib")
val currentProjectJars = Array[String]( "com.example-datagenerator.datagenerator-0.0.1-SNAPSHOT.jar" ).map{j => new java.io.File(libDir, j).getAbsolutePath}
val sparkLibDir = new java.io.File(s"/usr/share/datagenerator", "spark-lib")
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
/* -- Code Cell: Some(0D920AFE13674E669B190122F9C0338D) -- */ 

  case class Schema(id:Int, category:String, impressions: Long, rate: Double)
/****************/


  /* -- Code Cell: Some(D3D3D5FAE276451F9292235007FA2391) -- */ 

  val ids = (9999 to 19999)
  val categories = Seq("horror", "drama", "comic", "docu","humor")
  def impressions = Seq(scala.util.Random.nextInt(99999))
  def rates = Seq(scala.util.Random.nextDouble)
/****************/


  /* -- Code Cell: Some(2867C3A65F884FD8864AF314E25AF521) -- */ 

  val sample = for {
    id <- ids
    category <- categories
    impression <- impressions
    rate <- rates
  } yield Schema(id,category, impression, rate)
/****************/


  /* -- Code Cell: Some(85BC45E7FB494CCC826043DF4402EA5B) -- */ 

  val sqlContext = new org.apache.spark.sql.SQLContext(sc)
  
  import org.apache.spark.sql.types._
  import org.apache.spark.sql.functions._
  import sqlContext.implicits._
  import org.apache.spark.sql.DataFrame
/****************/


  /* -- Code Cell: Some(1C0BA39B45CA4AA78226D70F1699EA4A) -- */ 

  val dataset = sqlContext.createDataFrame(sample)
/****************/


  /* -- Code Cell: Some(251A626CFF1844A485288DFE95BE81A7) -- */ 

  
val `output-251A626CFF1844A485288DFE95BE81A7` = {
  "/tmp/df/data/sample"+(System.currentTimeMillis.toString.drop(6).take(4))
  }
  

dataset.write.mode(org.apache.spark.sql.SaveMode."").parquet(`output-251A626CFF1844A485288DFE95BE81A7`)


  {
    // adding output instance information into catalog
    import scalaj.http._
    import  java.net.URL
    val path = "/adalog/output?uuid=dd625b10-8710-4325-b946-bb65ffde51b2&tpe=parquet&variable=dataset&location="+`output-251A626CFF1844A485288DFE95BE81A7`
    val httpReq = adalogUrl.map(url => Http(new URL(new URL(url), path).toString))
    val credentials = for (u <-adalogUser; p <- adalogPassword ) yield (u,p)
    val authReq = credentials.foldLeft(httpReq){case (req, (u,p)) =>  req.map(_.auth(u, p))}
    val res = authReq.map(_.postForm(Nil).asString.body)
    println(res)
  }
  

   // Output without variable
/****************/


  /* -- Code Cell: Some(843BE47A55664C7B8E6BFB701FD1518B) -- */

sparkContext.stop


}
}
