import com.typesafe.sbt.SbtNativePackager.autoImport._
import com.typesafe.sbt.SbtNativePackager.packageArchetype
import com.typesafe.sbt.packager.MappingsHelper._
import com.typesafe.sbt.packager.docker._

resolvers += Resolver.mavenLocal

resolvers += Resolver.typesafeRepo("releases")

resolvers += "cloudera" at "https://repository.cloudera.com/artifactory/cloudera-repos"

credentials += Credentials(Path.userHome / ".bintray" / ".credentials")

resolvers += Resolver.url("bintray-data-fellas-maven", url("http://dl.bintray.com/data-fellas/maven"))(Resolver.ivyStylePatterns)

version := "0.0.1-SNAPSHOT"

lazy val svc = Project(id="svc", base = file("."))
  .aggregate(common, server, client)
  .dependsOn(common, server, client)
  .settings(
    deps.common: _*
  )
  .settings(
    name := "svc",
    crossPaths := false
  )

lazy val common = Project(id="common", base = file("common"))
  .settings(
    deps.common: _*
  )
  .settings(
    libraryDependencies ++= deps.avro
  )
  .settings(
    sbtavro.SbtAvro.avroSettings: _*
  )
  .settings(
    (stringType in avroConfig) := "String",
    (sourceDirectory in avroConfig) := file("common/src/main/resources/avro/"),
    (javaSource in avroConfig)  <<= baseDirectory( (base: File) => base /"src/main/java/" )
  )

lazy val server = Project(id="server", base = file("server"))
  .dependsOn(common)
  .settings(
    deps.common: _*
  )
  .settings(
    libraryDependencies ++= deps.avro,
    libraryDependencies ++= deps.server
  )
  .settings( //make http call (adalog)
    libraryDependencies +=  "org.scalaj" %% "scalaj-http" % "2.3.0",
    libraryDependencies +=  "org.json4s" %% "json4s-jackson" % "3.2.10" excludeAll( //although... imported transitively by spark mllib
      ExclusionRule(organization = "org.scala-lang")
    )
  )
  .settings(
    packageArchetype.java_server: _*
  )
  .settings(
    mainClass in Compile := Some("com.example.decisiontreefied_com.datafellas.g3nerator.modeloutput_0.Main")
  )
  .settings(
    bashScriptExtraDefines += "addJava \"-Dsun.io.serialization.extendedDebugInfo=true\"",
    bashScriptExtraDefines += "addJava \"-Dsun.io.serialization.extendedDebugInfo=true\""
  )
  .settings(
    dockerBaseImage := "data-fellas-docker-public.bintray.io/base-adst:0.0.1",
    dockerExposedPorts := Seq(55471),
    daemonUser in Docker := "root",
    mappings in Docker ++= directory("spark-lib"),
    mappings in Universal ++= directory("spark-lib"),
    dockerCommands ++= Seq(Cmd("ENV", "SPARK_HOME \"\"")),
    packageName in Docker := "decisiontreefied-com.datafellas.g3nerator.modeloutput-0",
    dockerRepository := Some("localhost:5000") //Docker
  )

lazy val client = Project(id="client", base = file("client"))
  .dependsOn(common)
  .settings(
    deps.common: _*
  )
  .settings(
    libraryDependencies ++= deps.avro,
    libraryDependencies += "com.typesafe" % "config" % "1.2.1"
  )
  .settings( //make http call (adalog)
    libraryDependencies +=  "org.scalaj" %% "scalaj-http" % "2.3.0"
  )
