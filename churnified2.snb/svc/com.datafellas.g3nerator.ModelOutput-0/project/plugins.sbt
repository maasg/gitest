resolvers += "sbt-plugin-releases" at "http://repo.scala-sbt.org/scalasbt/sbt-plugin-releases"

scalaVersion := "2.10.5"

libraryDependencies ++= Seq(
  "org.apache.avro" % "avro" % "1.7.7" force(),
  "org.apache.avro" % "avro-compiler" % "1.7.7" force()
)

addSbtPlugin("com.typesafe.sbt" % "sbt-native-packager" % "1.0.2")

addSbtPlugin("com.cavorite" % "sbt-avro" % "0.3.2")

addSbtPlugin("com.eed3si9n" % "sbt-assembly" % "0.14.3")
