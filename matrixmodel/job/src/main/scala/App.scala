
package io.kensu.matrixmodel
/**
  Outputs
  -------
  > ParquetOutput(CodeCell(CellMetadata(Some(true),Some(false),None,Some(false),None,Some(BC42BE58D98940C389B7712F417423AA),Some({"schema":{"type":"struct","fields":[{"name":"id","type":"integer","nullable":true,"metadata":{}},{"name":"any_churn_target","type":"boolean","nullable":true,"metadata":{}},{"name":"full_churn_target","type":"boolean","nullable":true,"metadata":{}},{"name":"A_1_count_last","type":"integer","nullable":true,"metadata":{}},{"name":"A_2_count_last","type":"integer","nullable":true,"metadata":{}},{"name":"A_3_count_last","type":"integer","nullable":true,"metadata":{}},{"name":"Postal_Code","type":"string","nullable":true,"metadata":{}},{"name":"cs_k","type":"string","nullable":true,"metadata":{}},{"name":"Neighbourhood_Code","type":"string","nullable":true,"metadata":{}}]},"inputs":{"resolved":["/home/maasg/playground/data/decision_tree.csv"],"unresolved":[]},"saveMode":"org.apache.spark.sql.SaveMode.Overwrite"})),output,parquetFile,None,None,Some({"type":"parquet","var":"matrixDF","extra":{"value":"Overwrite","source":null}}),Some(List(ScalaStream(stdout,stream,Parquet
Located: /home/maasg/playground/data/decision_tree.parquet
DataFrame: matrixDF
output-BC42BE58D98940C389B7712F417423AA: String = /home/maasg/playground/data/decision_tree.parquet
res5: notebook.front.widgets.adst.ParquetOutputWidget = <ParquetOutputWidget widget>
), ScalaExecuteResult(ExecuteResultMetadata(None),Map(text/html -> <div>
      <script data-this="{&quot;dfSchema&quot;:{&quot;type&quot;:&quot;struct&quot;,&quot;fields&quot;:[{&quot;name&quot;:&quot;id&quot;,&quot;type&quot;:&quot;integer&quot;,&quot;nullable&quot;:true,&quot;metadata&quot;:{}},{&quot;name&quot;:&quot;any_churn_target&quot;,&quot;type&quot;:&quot;boolean&quot;,&quot;nullable&quot;:true,&quot;metadata&quot;:{}},{&quot;name&quot;:&quot;full_churn_target&quot;,&quot;type&quot;:&quot;boolean&quot;,&quot;nullable&quot;:true,&quot;metadata&quot;:{}},{&quot;name&quot;:&quot;A_1_count_last&quot;,&quot;type&quot;:&quot;integer&quot;,&quot;nullable&quot;:true,&quot;metadata&quot;:{}},{&quot;name&quot;:&quot;A_2_count_last&quot;,&quot;type&quot;:&quot;integer&quot;,&quot;nullable&quot;:true,&quot;metadata&quot;:{}},{&quot;name&quot;:&quot;A_3_count_last&quot;,&quot;type&quot;:&quot;integer&quot;,&quot;nullable&quot;:true,&quot;metadata&quot;:{}},{&quot;name&quot;:&quot;Postal_Code&quot;,&quot;type&quot;:&quot;string&quot;,&quot;nullable&quot;:true,&quot;metadata&quot;:{}},{&quot;name&quot;:&quot;cs_k&quot;,&quot;type&quot;:&quot;string&quot;,&quot;nullable&quot;:true,&quot;metadata&quot;:{}},{&quot;name&quot;:&quot;Neighbourhood_Code&quot;,&quot;type&quot;:&quot;string&quot;,&quot;nullable&quot;:true,&quot;metadata&quot;:{}}]},&quot;dfInputs&quot;:{&quot;resolved&quot;:[&quot;/home/maasg/playground/data/decision_tree.csv&quot;],&quot;unresolved&quot;:[]},&quot;saveMode&quot;:&quot;org.apache.spark.sql.SaveMode.Overwrite&quot;}" type="text/x-scoped-javascript">/*<![CDATA[*/req(['../javascripts/notebook/adst/output/parquetOutput'], 
      function(parquetOutput) {
        parquetOutput.call(data, this);
      }
    );/*]]>*/</script>
    </div>),None,execute_result,4,None)))),parquetFile,parquetFile,matrixDF,org.apache.spark.sql.SaveMode.Overwrite,List(ParquetMappingElement(id,integer), ParquetMappingElement(any_churn_target,boolean), ParquetMappingElement(full_churn_target,boolean), ParquetMappingElement(A_1_count_last,integer), ParquetMappingElement(A_2_count_last,integer), ParquetMappingElement(A_3_count_last,integer), ParquetMappingElement(Postal_Code,string), ParquetMappingElement(cs_k,string), ParquetMappingElement(Neighbourhood_Code,string)),0)

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
sparkConf.set("spark.app.name", sparkConf.get("spark.app.name", "matrixmodel"))

// Set project Jars

val libDir = new java.io.File(s"/usr/share/matrixmodel", "lib")
val currentProjectJars = Array("io.kensu.matrixmodel-0.0.1-SNAPSHOT.jar").map{j => new java.io.File(libDir, j).getAbsolutePath}
val sparkLibDir = new java.io.File(s"/usr/share/matrixmodel", "spark-lib")
val fromProjectJars = Array[String]( "spark-csv_2.10-1.5.0.jar" , "scala-library-2.10.5.jar" , "commons-csv-1.1.jar" , "univocity-parsers-1.5.1.jar" ).map{j => new java.io.File(sparkLibDir, j).getAbsolutePath}
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
/* -- Code Cell: Some(AD1BF54A1C6948AD8A510C7D51C8F40F) -- */ 

  val sqlContext = new org.apache.spark.sql.SQLContext(sc)
  
  import org.apache.spark.sql.types._
  import org.apache.spark.sql.functions._
  import sqlContext.implicits._
  import org.apache.spark.sql.DataFrame
/****************/


  /* -- Code Cell: Some(4EE2C215F2CB42E880D6B3A6DB70ED3B) -- */ 

  val folder = "/home/maasg/playground/data/"
  val csvFile =  folder + "decision_tree.csv"
  
  val matrixDF = sqlContext.read
                          .format("com.databricks.spark.csv")
                          .option("header", "true")
                          .option("inferSchema", "true")
                          .load(csvFile)
                          .persist(org.apache.spark.storage.StorageLevel.MEMORY_AND_DISK_SER)
                          .repartition(2)
/****************/


  /* -- Code Cell: Some(D9BA002C3CF14B4887E0AD78FADC6581) -- */ 

  val parquetFile = folder + "decision_tree.parquet"
  //matrixDF.write.format("parquet").saveMode(SaveMode.Overwrite).save("namesAndAges.parquet")
/****************/


  /* -- Code Cell: Some(BC42BE58D98940C389B7712F417423AA) -- */ 

  
val `output-BC42BE58D98940C389B7712F417423AA` = {
  parquetFile
  }
  
{
  matrixDF.write.mode(org.apache.spark.sql.SaveMode.Overwrite).parquet(`output-BC42BE58D98940C389B7712F417423AA`)
  val ps = matrixDF.rdd.partitions.size
  val desc = scala.util.Try(matrixDF.describe().toJSON.collect.mkString("[", ",", "]")).toOption.getOrElse("null")
  val json = s""" { "partitions": $ps , "description": $desc} """
  
  {
    // adding output instance information into catalog
    import scalaj.http._
    import  java.net.URL
    val path = "/adalog/output?uuid=fa0e94a3-88f5-45e4-8334-7a15953a0ea3&tpe=parquet&variable=matrixDF&location="+`output-BC42BE58D98940C389B7712F417423AA`
    val httpReq = adalogUrl.map(url => Http(new URL(new URL(url), path).toString))
    val credentials = for (u <-adalogUser; p <- adalogPassword ) yield (u,p)
    val authReq = credentials.foldLeft(httpReq){case (req, (u,p)) =>  req.map(_.auth(u, p))}
    val data = json
    val res = authReq.map(_.postData(data).header("content-type", "application/json").asString.body)
    println(res)
  }
  
}

   // Output without variable
/****************/


  /* -- Code Cell: Some(67A6F29D372642CC8F6DCE1EBBFB54C5) -- */

sparkContext.stop


}
}
