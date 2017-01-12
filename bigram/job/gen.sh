#!/bin/bash

echo "Start job/gen.sh"
echo $(date)
echo "At directory `pwd`"

export JAVA_HOME=/home/maasg/Dev/java/jdk1.8.0_20
export JDK_HOME=/home/maasg/Dev/java/jdk1.8.0_20
export PATH=/home/maasg/Dev/java/sbt/bin:/home/maasg/Dev/java/jdk1.8.0_20/bin:$PATH

echo "enter /home/maasg/testground/sne/projects/bigram/job"

cd /home/maasg/testground/sne/projects/bigram/job

echo "publishing project"
/home/maasg/Dev/java/sbt/bin/sbt -Dspark.version=1.6.1 -Dhadoop.version=2.6.0 publish


echo "Telling catalog which notebok library (io.kensu:bigram_2.10:0.0.1-SNAPSHOT})"
curl -u 'username:password' -X POST "http://localhost:9002/adalog/88c4279e-aa76-411f-ac0e-c0bb334f5c1e/library?uuid=88c4279e-aa76-411f-ac0e-c0bb334f5c1e&groupId=io.kensu&artifactId=bigram_2.10&version=0.0.1-SNAPSHOT&pkg=io.kensu"



echo "building debian"
/home/maasg/Dev/java/sbt/bin/sbt -Dspark.version=1.6.1 -Dhadoop.version=2.6.0 debian:packageBin
echo "package: /home/maasg/testground/sne/projects/bigram/job/target/bigram_0.0.1-SNAPSHOT_all.deb "

echo "pushing deb package to Adalog UI"
[[ -d /tmp/sne/downloads ]] || /bin/mkdir -p /tmp/sne/downloads
[[ -f /home/maasg/testground/sne/projects/bigram/job/target/bigram_0.0.1-SNAPSHOT_all.deb ]] && /bin/cp /home/maasg/testground/sne/projects/bigram/job/target/bigram_0.0.1-SNAPSHOT_all.deb /tmp/sne/downloads || echo "package file not found !!"

echo "Submitting Job"


echo POSTing to Chronos
curl -i -X POST  -H "Content-type: application/json" http://172.17.0.2:4400/scheduler/iso8601 -d @/home/maasg/testground/sne/projects/bigram/job/src/main/resources/chronos.json
           

echo "End job/gen.sh"
echo $(date)