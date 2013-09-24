name := "numa08_net"

version := "1.0-SNAPSHOT"

libraryDependencies ++= Seq(
  jdbc,
  anorm,
  cache
)     

play.Project.playScalaSettings

libraryDependencies += "commons-daemon" % "commons-daemon" % "1.0.15"

