import sbt._
import sbt.Keys._

object deps {

  lazy val common = Seq(
    scalaVersion := "2.10.5",
    organization := "io.kensu.decisiontreefied_com.datafellas.g3nerator.modeloutput_0",
    libraryDependencies += "com.google.guava" % "guava" % "16.0.1" force(),
    version := "0.0.1-SNAPSHOT",
    publishMavenStyle := true,
    resolvers += "Adastyx Artifactory" at "http://artifactory-node:8082/artifactory/list/remote-repos/",credentials += Credentials("Artifactory Realm", "artifactory-node", "adastyx", "artifactory-password"),// no assembly deployed for service ATM
// addArtifact(artifact in (Compile, assembly), assembly).settings

publishTo := Some("Artifactory Realm" at "http://artifactory-node:8082/artifactory/datafellas-jobs/")
  )

  lazy val avro = Seq(
                    "org.apache.avro" % "avro"          % "1.7.7" excludeAll(
                      ExclusionRule(organization = "javax.servlet"),
                      ExclusionRule(organization = "org.mortbay.jetty")
                    ),
                    "org.apache.avro" % "avro-ipc"      % "1.7.7" excludeAll(
                      ExclusionRule(organization = "javax.servlet"),
                      ExclusionRule(organization = "org.mortbay.jetty")
                    ),
                    "org.apache.avro" % "avro-compiler" % "1.7.7" excludeAll(
                      ExclusionRule(organization = "javax.servlet"),
                      ExclusionRule(organization = "org.mortbay.jetty")
                    )
                  )

  lazy val server = Seq(
"org.apache.spark" %% "spark-core" % "1.6.1" excludeAll(
    ExclusionRule(organization = "javax.servlet"),
    ExclusionRule(organization = "org.mortbay.jetty"),
    ExclusionRule(organization = "org.apache.hadoop"),
    ExclusionRule("org.apache.ivy", "ivy")
  )
      ,
  
"org.apache.spark" %% "spark-sql" % "1.6.1" excludeAll(
    ExclusionRule(organization = "javax.servlet"),
    ExclusionRule(organization = "org.mortbay.jetty"),
    ExclusionRule(organization = "org.apache.hadoop")
)
      ,
  
"org.apache.spark" %% "spark-mllib" % "1.6.1" excludeAll(
    ExclusionRule(organization = "javax.servlet"),
    ExclusionRule(organization = "org.mortbay.jetty"),
    ExclusionRule("org.apache.hadoop")
)
      ,
  
"org.apache.spark" %% "spark-yarn" % "1.6.1" excludeAll(
    ExclusionRule(organization = "javax.servlet"),
    ExclusionRule(organization = "org.mortbay.jetty"),
    ExclusionRule("org.apache.hadoop"),
    ExclusionRule("org.apache.ivy", "ivy")
  )
      ,
  
"org.apache.hadoop" % "hadoop-client" % "2.7.2" excludeAll(
    ExclusionRule("org.apache.commons", "commons-exec"),
    ExclusionRule("commons-codec", "commons-codec"),
    ExclusionRule(organization = "javax.servlet"),
    ExclusionRule(organization = "org.mortbay.jetty"),
    ExclusionRule("com.google.guava", "guava")
  )
      ,
  
"org.apache.hadoop" % "hadoop-yarn-server-web-proxy" % "2.7.2" excludeAll(
    ExclusionRule("org.apache.commons", "commons-exec"),
    ExclusionRule("commons-codec", "commons-codec"),
    ExclusionRule(organization = "javax.servlet"),
    ExclusionRule(organization = "org.mortbay.jetty"),
    ExclusionRule("com.google.guava", "guava")
  )
      )

}
