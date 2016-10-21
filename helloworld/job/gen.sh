#!/bin/bash

echo "Start job/gen.sh"
echo $(date)
echo "At directory `pwd`"
if [ ! -d "/home/maasg/testground/sne/projects/helloworld/jdk" ]; then
  echo "Folder /home/maasg/testground/sne/projects/helloworld/jdk doesn't exist"
  cd /home/maasg/testground/sne/projects/helloworld
  wget http://localhost:8000/jdk-7u79-linux-x64.tar.gz
  tar xvzf jdk-7u79-linux-x64.tar.gz*
  find . -type d -maxdepth 1 -name "jdk" -exec mv "{}" jdk \;
  rm jdk-7u79-linux-x64.tar.gz
fi

if [ ! -d "/home/maasg/testground/sne/projects/helloworld/sbt" ]; then
  echo "Folder /home/maasg/testground/sne/projects/helloworld/sbt doesn't exist"
  cd /home/maasg/testground/sne/projects/helloworld
  wget http://localhost:8000/sbt-0.13.12.tgz
  tar xvzf sbt-0.13.12.tgz*
  rm sbt-0.13.12.tgz*
fi

export JAVA_HOME=/home/maasg/testground/sne/projects/helloworld/jdk
export JDK_HOME=/home/maasg/testground/sne/projects/helloworld/jdk
export PATH=/home/maasg/testground/sne/projects/helloworld/sbt/bin:/home/maasg/testground/sne/projects/helloworld/jdk/bin:$PATH

echo "enter /home/maasg/testground/sne/projects/helloworld/job"

cd /home/maasg/testground/sne/projects/helloworld/job

echo "publishing project"
/home/maasg/testground/sne/projects/helloworld/sbt/bin/sbt -Dspark.version=1.6.1 -Dhadoop.version=2.6.0 publish

echo "building docker"
/home/maasg/testground/sne/projects/helloworld/sbt/bin/sbt -Dspark.version=1.6.1 -Dhadoop.version=2.6.0 docker:publishLocal



echo "pushing docker"
docker push localhost:5000/helloworld:0.0.1-SNAPSHOT



echo "showing docker images"

docker images

echo "posting marathon"


curl -X POST -H "Content-type: application/json" http://172.17.0.2:4400/scheduler/iso8601 -d @/home/maasg/testground/sne/projects/helloworld/job/src/main/resources/chronos.json
           

echo "End job/gen.sh"
echo $(date)