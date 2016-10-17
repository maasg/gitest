#!/bin/bash

echo "Start service/gen.sh"
echo $(date)

if [ ! -d "/tmp/adastyx-new-projects/churnified/jdk" ]; then
  echo "Folder /tmp/adastyx-new-projects/churnified/jdk doesn't exist"
  cd /tmp/adastyx-new-projects/churnified
  wget http://localhost:8000/jdk-8u20-linux-x64.tar.gz
  tar xvzf jdk-7u79-linux-x64.tar.gz
  mv jdk1.7.0_79 jdk
  rm jdk-7u79-linux-x64.tar.gz
fi

if [ ! -d "/tmp/adastyx-new-projects/churnified/sbt" ]; then
  echo "Folder /tmp/adastyx-new-projects/churnified/sbt doesn't exist"
  cd /tmp/adastyx-new-projects/churnified
  wget http://localhost:8000/sbt-0.13.12.tgz
  tar xvzf sbt-0.13.9.tgz
  rm sbt-0.13.9.tgz
fi

export JAVA_HOME=/tmp/adastyx-new-projects/churnified/jdk
export JDK_HOME=/tmp/adastyx-new-projects/churnified/jdk
export PATH=/tmp/adastyx-new-projects/churnified/sbt/bin:/tmp/adastyx-new-projects/churnified/jdk/bin:$PATH

echo "enter /tmp/adastyx-new-projects/churnified/svc/com.datafellas.g3nerator.ModelOutput-0"

cd /tmp/adastyx-new-projects/churnified/svc/com.datafellas.g3nerator.ModelOutput-0

echo "building project"
/tmp/adastyx-new-projects/churnified/sbt/bin/sbt "common/publish"
/tmp/adastyx-new-projects/churnified/sbt/bin/sbt "server/publish"
/tmp/adastyx-new-projects/churnified/sbt/bin/sbt "client/publish"



echo "Telling catalog which client library to use (com.example.churnified_com.datafellas.g3nerator.modeloutput_0:client:0.0.2-SNAPSHOT)"
curl  -X POST "http://localhost:9001/adalog/output/service/library?uuid=6797f88d-19bd-45c7-89d2-631e0df7b717&tpe=model&variable=fittedPipeline&groupId=com.example.churnified_com.datafellas.g3nerator.modeloutput_0&artifactId=client_2.10&version=0.0.2-SNAPSHOT&pck=com.example.churnified_com.datafellas.g3nerator.modeloutput_0"



echo "building docker"
/tmp/adastyx-new-projects/churnified/sbt/bin/sbt "server/docker:publishLocal"



echo "pushing docker"
docker push localhost:5000/churnified-com.datafellas.g3nerator.modeloutput-0:0.0.2-SNAPSHOT



echo "showing docker images"

docker images

echo "posting marathon"


curl -X POST -H "Content-type: application/json" http://172.17.0.6:8080/v2/apps -d @/tmp/adastyx-new-projects/churnified/svc/com.datafellas.g3nerator.ModelOutput-0/server/src/main/resources/marathon.json
                  

echo "End service/gen.sh"
echo $(date)