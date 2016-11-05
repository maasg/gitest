#!/bin/bash

echo "Start service/gen.sh"
echo $(date)

export JAVA_HOME=/home/maasg/Dev/java/jdk1.8.0_20
export JDK_HOME=/home/maasg/Dev/java/jdk1.8.0_20
export PATH=/home/maasg/Dev/java/sbt/bin:/home/maasg/Dev/java/jdk1.8.0_20/bin:$PATH

echo "enter /home/maasg/testground/sne/projects/decisiontreefied/svc/com.datafellas.g3nerator.ModelOutput-0"

cd /home/maasg/testground/sne/projects/decisiontreefied/svc/com.datafellas.g3nerator.ModelOutput-0

echo "building project"
/home/maasg/testground/sne/projects/decisiontreefied/sbt/bin/sbt "common/publish"
/home/maasg/testground/sne/projects/decisiontreefied/sbt/bin/sbt "server/publish"
/home/maasg/testground/sne/projects/decisiontreefied/sbt/bin/sbt "client/publish"
// tellLibraryToCatalog(groupId, "client_"+scalaVersion.split("\.").take(2).mkString("."), project.config.version)

${loadAdalog}
// GM Change this!
val uuid = "1234"
val tpe ="service"
val variable = "foo"
val groupId="com.datafellas"
val artifactId="fooService"
val version = "0.0.1"
val packageRoot = "com.datafellas"
val projecConfigVersion = "0.0.1"

val auth = for {
    username <- adalogUser
    password <- adalogPassword
    } yield (s"-u $username:$password")

val credentials = auth.getOrElse("")

echo "Telling catalog which client library to use ($groupId:client:{$projectConfigVersion})"
curl $credentials -X POST "$adalogUrl/adalog/output/service/library?uuid=$uuid&tpe=$tpe&variable=$variable&groupId=$groupId&artifactId=$artifactId&version=$version&pck=$packageRoot"



echo "building docker"
/home/maasg/testground/sne/projects/decisiontreefied/sbt/bin/sbt "server/docker:publishLocal"



echo "pushing docker"
docker push localhost:5000/decisiontreefied-com.datafellas.g3nerator.modeloutput-0:0.0.1-SNAPSHOT



echo "showing docker images"

docker images

echo "posting marathon"


curl -X POST -H "Content-type: application/json" http://172.17.0.6:8080/v2/apps -d @/home/maasg/testground/sne/projects/decisiontreefied/svc/com.datafellas.g3nerator.ModelOutput-0/server/src/main/resources/marathon.json
                  

echo "End service/gen.sh"
echo $(date)