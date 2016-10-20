#!/bin/bash

echo "Start job/gen.sh"
echo $(date)
echo "At directory `pwd`"
if [ ! -d "/tmp/adastyx-new-projects/decisiontreefied/jdk" ]; then
  echo "Folder /tmp/adastyx-new-projects/decisiontreefied/jdk doesn't exist"
  cd /tmp/adastyx-new-projects/decisiontreefied
  wget http://localhost:8000/jdk-7u79-linux-x64.tar.gz
  tar xvzf jdk-7u79-linux-x64.tar.gz*
  find . -type d -maxdepth 1 -name "jdk" -exec mv "{}" jdk \;
  rm jdk-7u79-linux-x64.tar.gz
fi

if [ ! -d "/tmp/adastyx-new-projects/decisiontreefied/sbt" ]; then
  echo "Folder /tmp/adastyx-new-projects/decisiontreefied/sbt doesn't exist"
  cd /tmp/adastyx-new-projects/decisiontreefied
  wget http://localhost:8000/sbt-0.13.12.tgz
  tar xvzf sbt-0.13.12.tgz*
  rm sbt-0.13.12.tgz*
fi

export JAVA_HOME=/tmp/adastyx-new-projects/decisiontreefied/jdk
export JDK_HOME=/tmp/adastyx-new-projects/decisiontreefied/jdk
export PATH=/tmp/adastyx-new-projects/decisiontreefied/sbt/bin:/tmp/adastyx-new-projects/decisiontreefied/jdk/bin:$PATH

echo "enter /tmp/adastyx-new-projects/decisiontreefied/job"

cd /tmp/adastyx-new-projects/decisiontreefied/job

echo "publishing project"
/tmp/adastyx-new-projects/decisiontreefied/sbt/bin/sbt -Dspark.version=1.6.1 -Dhadoop.version=2.6.0 publish

echo "building docker"
/tmp/adastyx-new-projects/decisiontreefied/sbt/bin/sbt -Dspark.version=1.6.1 -Dhadoop.version=2.6.0 docker:publishLocal



echo "pushing docker"
docker push localhost:5000/decisiontreefied:0.0.1-SNAPSHOT



echo "showing docker images"

docker images

echo "posting marathon"


curl -X POST -H "Content-type: application/json" http://172.17.0.2:4400/scheduler/iso8601 -d @/tmp/adastyx-new-projects/decisiontreefied/job/src/main/resources/chronos.json
           

echo "End job/gen.sh"
echo $(date)