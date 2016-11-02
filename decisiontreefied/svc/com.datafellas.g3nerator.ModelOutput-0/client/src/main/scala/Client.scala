package com.example.decisiontreefied_com.datafellas.g3nerator.modeloutput_0.client
import org.apache.avro.ipc.NettyTransceiver
import java.net.InetSocketAddress

import org.apache.avro.ipc.specific.SpecificRequestor
object Client {

  
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



  def serviceInfo = {
    
def askDeployedInfoToCatalog() = {
  // asking last added output instance information from catalog
  import scalaj.http._

  val req = Http(adalogUrl + "/adalog/output/service?uuid=fdeed9c0-3bed-4fc5-b923-ef788b8b7d80&tpe=model&variable=model")
  val authReq = adalogUser.map { case (user, pwd) => req.auth(user, pwd) }.getOrElse(req)
  authReq.asString.body
}
askDeployedInfoToCatalog()


  }

  def from(host:String, port:Int=50835) = {
    val transport = new NettyTransceiver(new InetSocketAddress(host, port))
    val client = SpecificRequestor.getClient(classOf[com.example.decisiontreefied_com.datafellas.g3nerator.modeloutput_0.server.Methods], transport)
    transport -> client
  }

  def get() = {
    val Array(host, port) = serviceInfo.split(":")
    from(host, port.toInt)
  }
}
