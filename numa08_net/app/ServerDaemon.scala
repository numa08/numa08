package net.numa08.numa08_net

import org.apache.commons.daemon._
import play.core.server.NettyServer
import java.io.File
import play.core.StaticApplication

class ServerDaemon extends Daemon {

  var nettyServer : NettyServer = _
  var applicationPath : File = _

  override def init(context : DaemonContext) : Unit = {
    applicationPath = new File(context.getArguments()(0))
  }

  override def start() : Unit = {
    nettyServer = new NettyServer(
      new StaticApplication(applicationPath),
      Option(System.getProperty("http.port").toInt)
    )
  }

  override def stop() : Unit = {
    Option(nettyServer) match {
      case Some(server) => {server.stop()}
      case _ => {}
    }
  }

  override def destroy() : Unit = {
    nettyServer = null
  }
}
