name := "numa08_net"

version := "2.0"

libraryDependencies ++= Seq(
  jdbc,
  anorm,
  cache
)     

play.Project.playScalaSettings

libraryDependencies += "org.jsoup" % "jsoup" % "1.7.3"

