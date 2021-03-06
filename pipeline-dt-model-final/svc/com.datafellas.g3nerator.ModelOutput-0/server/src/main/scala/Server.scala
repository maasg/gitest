package com.example.pipeline_dt_model_final_com.datafellas.g3nerator.modeloutput_0.server.impl

import java.net.InetSocketAddress

import scala.collection.JavaConverters._

import org.apache.avro.AvroRemoteException

import org.apache.avro.ipc.NettyServer
import org.apache.avro.ipc.specific.SpecificResponder

import com.typesafe.config._
import java.net.URL
import scala.collection.JavaConverters._
import scala.util.{Try, Success, Failure}
import scalaj.http._
     

import com.example.pipeline_dt_model_final_com.datafellas.g3nerator.modeloutput_0.server._

object Server extends Methods {
  private [this] val _ipc = new NettyServer(
                                             new SpecificResponder(classOf[Methods], this),
                                             new InetSocketAddress(59391)
                                           )

  
  val config = ConfigFactory.load()
  def getStringConfig(path: String) : Option[String] = {
    if (config.hasPath(path)) {
      Some(config.getString(path))
    } else {
      None
    }
  }


  

  val adalogHost: URL = getStringConfig("adalog.url").map(s => new URL(s)).getOrElse(throw new RuntimeException("Adalog config missing"))
  val adalogUser: Option[String] = getStringConfig("adalog.authentication.username")
  val adalogPassword: Option[String] = getStringConfig("adalog.authentication.password")
  def adalogGet(path:String) : Try[String] = adalogRequest(path, None)
  def postFunc: HttpRequest => HttpRequest = req => req.postForm
  def adalogPost(path:String) : Try[String] = adalogRequest(path, Some(postFunc))
  def adalogRequest(path:String, func: Option[HttpRequest => HttpRequest]) : Try[String] = {
      val url = new URL(adalogHost, path).toString
      val req = Http(url)
      val optAuthReq = for {
                         user <- adalogUser
                         pwd <- adalogPassword
                       } yield req.auth(user, pwd)
      val authReq = optAuthReq.getOrElse(req)
      val authReqProc = func.map(f => f(authReq)).getOrElse(authReq)
      val response = authReqProc.asString
      val responseCode = response.code
      val responseContent = response.body
      if (responseCode == 200) {
         Success(responseContent)
      } else {
        Failure(new RuntimeException(s"Could not contact Adalog. Reason: HTTP/$responseCode [$responseContent]"))
      }
  }


  
def askOutputToCatalog(): Try[String] = {
  val path = "/adalog/output?uuid=bbe7efb6-db5f-483f-abe7-341e536f0b34&tpe=model&variable=model"
  adalogGet(path)
}


  

@volatile var catalogTold = true

def tellCatalog():Unit = new Thread { override def run:Unit = {
  if (catalogTold) return ()

  // publishing current IP and port
  import scalaj.http._

  val marathonUrl = "http://172.17.0.6:8080/v2/apps/pipeline-dt-model-final/service/com.datafellas.g3nerator.modeloutput-0"
  val appJson = Http(marathonUrl).asString.body

  import org.json4s._
  import org.json4s.jackson.JsonMethods._
  val j = parse(appJson, true)

  val h = (j \ "app" \ "tasks" \ "host").toOption.collect {
    case org.json4s.JsonAST.JString(host) => host
    case org.json4s.JsonAST.JArray(host::_) if host.isInstanceOf[org.json4s.JsonAST.JString] =>
      val org.json4s.JsonAST.JString(h) = host
      h
  }
  h match {
    case Some(host) =>
      // publishing current host and port to adalog
      val url = s"/adalog/output/service?uuid=bbe7efb6-db5f-483f-abe7-341e536f0b34&tpe=model&variable=model&host=${host}&port=59391"
      val res = adalogPost(url)
      val msg = "Adalog response on service update: "
      res match {
        case Success(result) => println(msg + result)
            catalogTold = true
        case Failure(ex) => println(msg + ex.getMessage)
          ex.printStackTrace
          catalogTold = false
      }
    case None =>
      Thread.sleep(1000)
      tellCatalog()
  }
}}.start
tellCatalog()


  // no custom variables 

  import org.apache.spark.{SparkContext, SparkConf}
import org.apache.spark.SparkContext._
import org.apache.spark.rdd._
private[this] val sparkConf = new SparkConf()

val sparkConfig = config.getConfig("spark")
                        .atPath("spark").entrySet.asScala.map(e => e.getKey -> config.getString(e.getKey))

sparkConf.setAll(sparkConfig)

sparkConf.setMaster(sparkConf.get("spark.master", "local[*]"))
sparkConf.set("spark.app.name", sparkConf.get("spark.app.name", "pipeline-dt-model-final"))




//##val libDir = new java.io.File("/usr/share/server", "lib")
val libDir = new java.io.File("/home/maasg/testground/sne/projects/pipeline-dt-model-final/svc/com.datafellas.g3nerator.ModelOutput-0/server/lib")
//val currentProjectJars = Array[String]( "com.example.pipeline_dt_model_final_com.datafellas.g3nerator.modeloutput_0.common-0.0.1-SNAPSHOT.jar" , "com.example.pipeline_dt_model_final_com.datafellas.g3nerator.modeloutput_0.server-0.0.1-SNAPSHOT.jar" ).map{j => new java.io.File(libDir, j).getAbsolutePath}
val currentProjectJars = Array("pipeline-dt-model-final_2.10-0.0.1-SNAPSHOT.jar").map{j => new java.io.File(libDir, j).getAbsolutePath}

  //##val sparkLibDir = new java.io.File("/usr/share/server", "spark-lib")
  val sparkLibDir = new java.io.File("/home/maasg/testground/sne/projects/pipeline-dt-model-final/svc/com.datafellas.g3nerator.ModelOutput-0/server/spark-lib")
val fromProjectJars = Array[String]( "commons-collections-3.2.1.jar" , "commons-compress-1.4.1.jar" , "commons-lang-2.6.jar" , "avro-ipc-1.7.7.jar" , "xz-1.0.jar" , "slf4j-api-1.6.4.jar" , "snappy-java-1.0.5.jar" , "avro-compiler-1.7.7.jar" , "jackson-mapper-asl-1.9.13.jar" , "jackson-core-asl-1.9.13.jar" , "netty-3.4.0.Final.jar" , "avro-1.7.7.jar" , "paranamer-2.3.jar" , "velocity-1.7.jar" ).map{j => new java.io.File(sparkLibDir, j).getAbsolutePath}
val jarsArray = (sparkConf.get("spark.jars", "").split(",").toArray ++ currentProjectJars ++ fromProjectJars).distinct.filter(!_.isEmpty)
println("Add Jars: \n" + jarsArray.mkString("\n"))
sparkConf.setJars(jarsArray)



@transient private[this] val sparkContext = new org.apache.spark.SparkContext(sparkConf)
@transient private[this] val sqlContext = new org.apache.spark.sql.SQLContext(sparkContext)
import sqlContext.implicits._

  def predict(@transient request:ServiceRequest):ServiceResponse = {
    
{
  //val modelPath = askOutputToCatalog().get
  val modelPath = "/tmp/pipeline/df-final2"

  
// Load Model
val ctx = org.apache.spark.SparkContext.getOrCreate
val model =  ctx.objectFile[org.apache.spark.ml.PipelineModel](modelPath).first


//  int id;
//  boolean any_churn_target;
//  boolean full_churn_target;
//  int A_1_count_last;
//  int A_2_count_last;
//  int A_3_count_last;
//  string Postal_Code;
//  string cs_k;
//  string Neighbourhood_Code;

  //  int id;
  val id = request.getId

  //  boolean any_churn_target;
  val any_churn_target = request.getAnyChurnTarget

  //  boolean full_churn_target;
  val full_churn_target = request.getFullChurnTarget

  //  int A_1_count_last;
  val A_1_count_last = request.getA1CountLast

  //  int A_2_count_last;
  val A_2_count_last = request.getA2CountLast

  //  int A_3_count_last;
  val A_3_count_last = request.getA3CountLast

  //  string Postal_Code;
  val Postal_Code = request.getPostalCode

  //  string cs_k;
  val cs_k = request.getCsK

  //  string Neighbourhood_Code;
  val Neighbourhood_Code = request.getNeighbourhoodCode

  val datapoint = (id, any_churn_target, full_churn_target, A_1_count_last, A_2_count_last, A_3_count_last, Postal_Code, cs_k, Neighbourhood_Code)

  val df = sqlContext.createDataFrame(Seq(datapoint)).toDF("id", "any_churn_target", "full_churn_target", "A_1_count_last", "A_2_count_last", "A_3_count_last", "Postal_Code", "cs_k", "Neighbourhood_Code")


// ## wrong val ds = request.input.asScala.toArray.map(_.toDouble)
// ## wrong val input = org.apache.spark.mllib.linalg.Vectors.dense(ds)
              

  
 // Implement here the correct estimation function
 val output = model.transform(df).select( $"rawPrediction", $"probability", $"predictions", $"label")  
 import org.apache.spark.sql.Row
 import org.apache.spark.mllib.linalg.DenseVector
 val Row(rawPrediction:DenseVector, probability:DenseVector, predictions:Double,label:Double) = output.head

 val d = new com.example.pipeline_dt_model_final_com.datafellas.g3nerator.modeloutput_0.model.Domain(predictions)
  
 new ServiceResponse(d)
}

  }

  def notebook(request:MetadataRequest):MetadataResponse = {
    val notebookContent = scala.io.Source.fromInputStream(Server.getClass.getResourceAsStream("/notebook.snb")).getLines.mkString("\n")
new MetadataResponse(notebookContent)
  }
}
