package com.example.decisiontreefied_com.datafellas.g3nerator.modeloutput_0.server.impl

import java.net.InetSocketAddress

import scala.collection.JavaConverters._

import org.apache.avro.AvroRemoteException

import org.apache.avro.ipc.NettyServer
import org.apache.avro.ipc.specific.SpecificResponder

import com.example.decisiontreefied_com.datafellas.g3nerator.modeloutput_0.server._

object Server extends Methods {
  private [this] val _ipc = new NettyServer(
                                             new SpecificResponder(classOf[Methods], this),
                                             new InetSocketAddress(50835)
                                           )
  
import com.typesafe.config._
import scala.collection.JavaConverters._

val config = ConfigFactory.load()



  
  def getStringConfig(path: String) : Option[String] = {
    if (config.hasPath(path)) {
      Some(config.getString(path))
    } else {
      None
    }
  }

  val adalogUrl: String = getStringConfig("adalog.url").getOrElse(throw new RuntimeException("Adalog config missing"))
  val adalogUser: Option[String] = getStringConfig("adalog.authentication.username")
  val adalogPassword: Option[String] = getStringConfig("adalog.authentication.password")



  

@volatile var catalogTold = false

def tellCatalog():Unit = new Thread { override def run:Unit = {
  if (catalogTold) return ()

  // publishing current IP and port
  import scalaj.http._

  val marathonUrl = "http://172.17.0.6:8080/v2/apps/decisiontreefied/service/com.datafellas.g3nerator.modeloutput-0"
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
      val ch = Http(adalogUrl.get + s"/adalog/output/service?uuid=fdeed9c0-3bed-4fc5-b923-ef788b8b7d80&tpe=model&variable=model&host=${host}&port=50835")
      val ach = adalogUser.map { _ => ch.auth(adalogUser.get, adalogPassword.get) }.getOrElse(ch)
      ach.postForm(Nil).asString.body
      catalogTold = true
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
sparkConf.set("spark.app.name", sparkConf.get("spark.app.name", "decisiontreefied"))




val libDir = new java.io.File(".", "lib")
val currentProjectJars = Array[String]( "com.example.decisiontreefied_com.datafellas.g3nerator.modeloutput_0.common-0.0.1-SNAPSHOT.jar" , "com.example.decisiontreefied_com.datafellas.g3nerator.modeloutput_0.server-0.0.1-SNAPSHOT.jar" ).map{j => new java.io.File(libDir, j).getAbsolutePath}
val sparkLibDir = new java.io.File(".", "spark-lib")
val fromProjectJars = Array[String]( "commons-collections-3.2.1.jar" , "netty-3.4.0.Final.jar" , "xz-1.0.jar" , "snappy-java-1.0.5.jar" , "slf4j-api-1.6.4.jar" , "commons-lang-2.6.jar" , "jackson-mapper-asl-1.9.13.jar" , "velocity-1.7.jar" , "avro-compiler-1.7.7.jar" , "avro-1.7.7.jar" , "commons-compress-1.4.1.jar" , "jackson-core-asl-1.9.13.jar" , "paranamer-2.3.jar" , "avro-ipc-1.7.7.jar" ).map{j => new java.io.File(sparkLibDir, j).getAbsolutePath}
val jarsArray = (sparkConf.get("spark.jars", "").split(",").toArray ++ currentProjectJars ++ fromProjectJars).distinct.filter(!_.isEmpty)
println("Add Jars: \n" + jarsArray.mkString("\n"))
sparkConf.setJars(jarsArray)



@transient private[this] val sparkContext = new org.apache.spark.SparkContext(sparkConf)
@transient private[this] val sqlContext = new org.apache.spark.sql.SQLContext(sparkContext)
import sqlContext.implicits._

  def predict(@transient request:ServiceRequest):ServiceResponse = {
    
{
  val modelPath = askOutputToCatalog().get

  
// Load Model
val ctx = org.apache.spark.SparkContext.getOrCreate
val model =  ctx.objectFile[org.apache.spark.ml.PipelineModel](modelPath).first

           

  
val ds = request.input.asScala.toArray.map(_.toDouble)
val input = org.apache.spark.mllib.linalg.Vectors.dense(ds)
              

  
 // Implement here the correct estimation function
 val output = 3.1415
           

  val d = new com.example.decisiontreefied_com.datafellas.g3nerator.modeloutput_0.model.Domain()
   d.setOutput(output) 

  new ServiceResponse(d)
}

  }

  def notebook(request:MetadataRequest):MetadataResponse = {
    val notebookContent = scala.io.Source.fromInputStream(Server.getClass.getResourceAsStream("/notebook.snb")).getLines.mkString("\n")
new MetadataResponse(notebookContent)
  }
}
