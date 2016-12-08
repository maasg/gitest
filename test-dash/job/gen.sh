#!/bin/bash

echo "Start job/gen.sh"
echo $(date)
echo "At directory `pwd`"

export JAVA_HOME=/home/maasg/Dev/java/jdk1.8.0_20
export JDK_HOME=/home/maasg/Dev/java/jdk1.8.0_20
export PATH=/home/maasg/Dev/java/sbt/bin:/home/maasg/Dev/java/jdk1.8.0_20/bin:$PATH

echo "enter /home/maasg/testground/sne/projects/test-dash/job"

cd /home/maasg/testground/sne/projects/test-dash/job

echo "publishing project"
/home/maasg/Dev/java/sbt/bin/sbt -Dspark.version=1.6.1 -Dhadoop.version=2.6.0 publish


echo "Telling catalog which notebok library (io.kensu:test-dash_2.10:0.0.5})"
curl -u 'username:password' -X POST "http://localhost:9002/adalog/12408da4-3763-480b-841a-4d4b7d3eeb13/library?uuid=12408da4-3763-480b-841a-4d4b7d3eeb13&groupId=io.kensu&artifactId=test-dash_2.10&version=0.0.5&pkg=io.kensu"



echo "building debian"
/home/maasg/Dev/java/sbt/bin/sbt -Dspark.version=1.6.1 -Dhadoop.version=2.6.0 debian:packageBin
echo "package: /home/maasg/testground/sne/projects/test-dash/job/target/test-dash_0.0.5_all.deb "

echo "pushing deb package to Adalog UI"
[[ -d /tmp/sne/downloads ]] || /bin/mkdir -p /tmp/sne/downloads
[[ -f /home/maasg/testground/sne/projects/test-dash/job/target/test-dash_0.0.5_all.deb ]] && /bin/cp /home/maasg/testground/sne/projects/test-dash/job/target/test-dash_0.0.5_all.deb /tmp/sne/downloads || echo "package file not found !!"

echo "Submitting Job"


echo POSTing to Chronos
curl -i -X POST  -H "Content-type: application/json" http://172.17.0.2:4400/scheduler/iso8601 -d @/home/maasg/testground/sne/projects/test-dash/job/src/main/resources/chronos.json
           

echo "End job/gen.sh"
echo $(date)