#!/bin/bash

echo "Start job/gen.sh"
echo $(date)
echo "At directory `pwd`"

export JAVA_HOME=/home/maasg/Dev/java/jdk1.8.0_20
export JDK_HOME=/home/maasg/Dev/java/jdk1.8.0_20
export PATH=/home/maasg/Dev/java/sbt/bin:/home/maasg/Dev/java/jdk1.8.0_20/bin:$PATH

echo "enter /home/maasg/testground/sne/projects/decisiontreefied/job"

cd /home/maasg/testground/sne/projects/decisiontreefied/job

echo "publishing project"
/home/maasg/testground/sne/projects/decisiontreefied/sbt/bin/sbt -Dspark.version=1.6.1 -Dhadoop.version=2.6.0 publish

echo "building debian"
/home/maasg/testground/sne/projects/decisiontreefied/sbt/bin/sbt -Dspark.version=1.6.1 -Dhadoop.version=2.6.0 debian:packageBin
export PKG_NAME=decisiontreefied_0.0.1-SNAPSHOT_all.deb
echo "package: /home/maasg/testground/sne/projects/decisiontreefied/job/target/$PKG_NAME"

echo "pushing deb package to Adalog UI"
/bin/mkdir /tmp/sne/downloads
/bin/cp /home/maasg/testground/sne/projects/decisiontreefied/job/target/$PKG_NAME /tmp/sne/downloads


echo "posting marathon/chronos"

echo 
curl -X POST  -H "Content-type: application/json" http://172.17.0.2:4400/scheduler/iso8601 -d @/home/maasg/testground/sne/projects/decisiontreefied/job/src/main/resources/chronos.json
           

echo "End job/gen.sh"
echo $(date)