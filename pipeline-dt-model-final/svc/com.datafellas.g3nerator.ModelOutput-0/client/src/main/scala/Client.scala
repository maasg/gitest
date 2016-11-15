package com.example.pipeline_dt_model_final_com.datafellas.g3nerator.modeloutput_0.client
import org.apache.avro.ipc.NettyTransceiver
import java.net.InetSocketAddress

import org.apache.avro.ipc.specific.SpecificRequestor
object Client {

  
import com.typesafe.config._
import java.net.URL
import scala.collection.JavaConverters._
import scala.util.{Try, Success, Failure}
import scalaj.http._
     

  
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
  def postFunc: HttpRequest => HttpRequest = req.postForm
  def adalogPost(path:String) : Try[String] = adalogRequest(path, Some(postFunc)
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


  def serviceInfo: (String,Int) = {
    
def askServiceToCatalog(): Try[(String, Int)] = {
    val path = Http(adalogUrl("/adalog/output/service?uuid=bbe7efb6-db5f-483f-abe7-341e536f0b34&tpe=model&variable=model"))
    adalogGet(path).map{ content =>
           val Array(host,strPort) = content.split(":")
           (host, strPort.toInt)
     }
}

    askServiceToCatalog().get //force failure case
  }

  def from(host:String, port:Int=19917) = {
    val transport = new NettyTransceiver(new InetSocketAddress(host, port))
    val client = SpecificRequestor.getClient(classOf[com.example.pipeline_dt_model_final_com.datafellas.g3nerator.modeloutput_0.server.Methods], transport)
    transport -> client
  }

  def get() = {
    val (host, port) = serviceInfo
    from(host, port)
  }
}
