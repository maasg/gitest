#!/bin/bash

echo "Start job/gen.sh"
echo $(date)

if [ ! -d "/tmp/adastyx-new-projects/newbum/jdk" ]; then
  echo "Folder /tmp/adastyx-new-projects/newbum/jdk doesn't exist"
  cd /tmp/adastyx-new-projects/newbum
  wget http://repo-node/jdk-7u79-linux-x64.tar.gz
  tar xvzf jdk-7u79-linux-x64.tar.gz
  mv jdk1.7.0_79 jdk
  rm jdk-7u79-linux-x64.tar.gz
fi

if [ ! -d "/tmp/adastyx-new-projects/newbum/sbt" ]; then
  echo "Folder /tmp/adastyx-new-projects/newbum/sbt doesn't exist"
  cd /tmp/adastyx-new-projects/newbum
  wget http://repo-node/sbt-0.13.9.tgz
  tar xvzf sbt-0.13.9.tgz
  rm sbt-0.13.9.tgz
fi

export JAVA_HOME=/tmp/adastyx-new-projects/newbum/jdk
export JDK_HOME=/tmp/adastyx-new-projects/newbum/jdk
export PATH=/tmp/adastyx-new-projects/newbum/sbt/bin:/tmp/adastyx-new-projects/newbum/jdk/bin:$PATH

echo "enter /tmp/adastyx-new-projects/newbum/job"

cd /tmp/adastyx-new-projects/newbum/job

echo "publishing project"
/tmp/adastyx-new-projects/newbum/sbt/bin/sbt -Dspark.version=1.6.1 -Dhadoop.version=2.6.0 publish

echo "building docker"
/tmp/adastyx-new-projects/newbum/sbt/bin/sbt docker:publishLocal



echo "pushing docker"
docker push docker-repo:5000/newbum:0.0.1-SNAPSHOT



echo "showing docker images"

docker images

echo "posting marathon"


curl -X POST -H "Content-type: application/json" http://172.17.0.2:4400//scheduler/iso8601 -d @/tmp/adastyx-new-projects/newbum/job/src/main/resources/chronos.json
                    

echo "End job/gen.sh"
echo $(date)