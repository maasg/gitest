#!/bin/bash

echo "Start job/gen.sh"
echo $(date)
echo "At directory `pwd`"

export JAVA_HOME=/home/maasg/Dev/java/jdk1.8.0_20
export JDK_HOME=/home/maasg/Dev/java/jdk1.8.0_20
export PATH=/home/maasg/Dev/java/sbt/bin:/home/maasg/Dev/java/jdk1.8.0_20/bin:$PATH

echo "enter /home/maasg/testground/sne/projects/pipeline-dt-model-final/job"

cd /home/maasg/testground/sne/projects/pipeline-dt-model-final/job

echo "publishing project"
/home/maasg/Dev/java/sbt/bin/sbt -Dspark.version=1.6.1 -Dhadoop.version=2.6.0 publish

echo "building debian"
/home/maasg/Dev/java/sbt/bin/sbt -Dspark.version=1.6.1 -Dhadoop.version=2.6.0 debian:packageBin
export PKG_NAME=pipeline-dt-model-final_0.0.1-SNAPSHOT_all.deb
echo "package: /home/maasg/testground/sne/projects/pipeline-dt-model-final/job/target/pipeline-dt-model-final_0.0.1-SNAPSHOT_all.deb"

echo "pushing deb package to Adalog UI"
/bin/mkdir -p /tmp/sne/downloads
/bin/cp /home/maasg/testground/sne/projects/pipeline-dt-model-final/job/target/pipeline-dt-model-final_0.0.1-SNAPSHOT_all.deb /tmp/sne/downloads


echo "Submitting Job"


echo POSTing to Chronos
curl -X POST  -H "Content-type: application/json" http://172.17.0.2:4400/scheduler/iso8601 -d @/home/maasg/testground/sne/projects/pipeline-dt-model-final/job/src/main/resources/chronos.json
           

echo "End job/gen.sh"
echo $(date)