#!/bin/bash

echo "Start job/gen.sh"
echo $(date)

if [ ! -d "/tmp/adastyx-projects/beam-me-up/jdk" ]; then
  echo "Folder /tmp/adastyx-projects/beam-me-up/jdk doesn't exist"
  cd /tmp/adastyx-projects/beam-me-up
  wget http://repo-node/jdk-7u79-linux-x64.tar.gz
  tar xvzf jdk-7u79-linux-x64.tar.gz
  mv jdk1.7.0_79 jdk
  rm jdk-7u79-linux-x64.tar.gz
fi

if [ ! -d "/tmp/adastyx-projects/beam-me-up/sbt" ]; then
  echo "Folder /tmp/adastyx-projects/beam-me-up/sbt doesn't exist"
  cd /tmp/adastyx-projects/beam-me-up
  wget http://repo-node/sbt-0.13.9.tgz
  tar xvzf sbt-0.13.9.tgz
  rm sbt-0.13.9.tgz
fi

export JAVA_HOME=/tmp/adastyx-projects/beam-me-up/jdk
export JDK_HOME=/tmp/adastyx-projects/beam-me-up/jdk
export PATH=/tmp/adastyx-projects/beam-me-up/sbt/bin:/tmp/adastyx-projects/beam-me-up/jdk/bin:$PATH

echo "enter /tmp/adastyx-projects/beam-me-up/job"

cd /tmp/adastyx-projects/beam-me-up/job

echo "publishing project"
/tmp/adastyx-projects/beam-me-up/sbt/bin/sbt -Dspark.version=1.5.0 -Dhadoop.version=2.6.0 publish

echo "building docker"
/tmp/adastyx-projects/beam-me-up/sbt/bin/sbt docker:publishLocal



echo "pushing docker"
docker push docker-repo:5000/beam-me-up:0.0.1



echo "showing docker images"

docker images

echo "posting marathon"


curl -X POST -H "Content-type: application/json" http://chronos-note:4400/scheduler/iso8601 -d @/tmp/adastyx-projects/beam-me-up/job/src/main/resources/chronos.json
                    

echo "End job/gen.sh"
echo $(date)