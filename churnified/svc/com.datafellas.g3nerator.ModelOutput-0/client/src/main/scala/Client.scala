package com.example.churnified_com.datafellas.g3nerator.modeloutput_0.client
import org.apache.avro.ipc.NettyTransceiver
import java.net.InetSocketAddress

import org.apache.avro.ipc.specific.SpecificRequestor
object Client {

  
import com.typesafe.config._
import scala.collection.JavaConverters._

val config = ConfigFactory.load()



  

val adalogUrl:Option[String] = Some(config.getString("adastyx.adalog.url"))

val adalogUser:Option[String] = None
val adalogPassword:Option[String] = None



  def serviceInfo = {
    
def askDeployedInfoToCatalog() = {
  // asking last added output instance information from catalog
  import scalaj.http._

  val h = Http(adalogUrl.get + "/adalog/output/service?uuid=6797f88d-19bd-45c7-89d2-631e0df7b717&tpe=model&variable=fittedPipeline")
  val ah = adalogUser.map { _ => h.auth(adalogUser.get, adalogPassword.get) }.getOrElse(h)
  ah.asString.body
}
askDeployedInfoToCatalog()


  }

  def from(host:String, port:Int=50179) = {
    val transport = new NettyTransceiver(new InetSocketAddress(host, port))
    val client = SpecificRequestor.getClient(classOf[com.example.churnified_com.datafellas.g3nerator.modeloutput_0.server.Methods], transport)
    transport -> client
  }

  def get() = {
    val List(host, port) = serviceInfo.split(":").toList
    from(host, port.toInt)
  }
}
