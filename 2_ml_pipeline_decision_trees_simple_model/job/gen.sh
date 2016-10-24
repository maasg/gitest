#!/bin/bash

echo "Start job/gen.sh"
echo $(date)
echo "At directory `pwd`"
if [ ! -d "/home/maasg/testground/sne/projects/2_ml_pipeline_decision_trees_simple_model/jdk" ]; then
  echo "Folder /home/maasg/testground/sne/projects/2_ml_pipeline_decision_trees_simple_model/jdk doesn't exist"
  cd /home/maasg/testground/sne/projects/2_ml_pipeline_decision_trees_simple_model
  wget -q http://localhost:8000/jdk-7u79-linux-x64.tar.gz
  tar xvzf jdk-7u79-linux-x64.tar.gz*
  find . -type d -maxdepth 1 -name "jdk" -exec mv "{}" jdk \;
  rm jdk-7u79-linux-x64.tar.gz
fi

if [ ! -d "/home/maasg/testground/sne/projects/2_ml_pipeline_decision_trees_simple_model/sbt" ]; then
  echo "Folder /home/maasg/testground/sne/projects/2_ml_pipeline_decision_trees_simple_model/sbt doesn't exist"
  cd /home/maasg/testground/sne/projects/2_ml_pipeline_decision_trees_simple_model
  wget -q http://localhost:8000/sbt-0.13.12.tgz
  tar xvzf sbt-0.13.12.tgz*
  rm sbt-0.13.12.tgz*
fi

export JAVA_HOME=/home/maasg/testground/sne/projects/2_ml_pipeline_decision_trees_simple_model/jdk
export JDK_HOME=/home/maasg/testground/sne/projects/2_ml_pipeline_decision_trees_simple_model/jdk
export PATH=/home/maasg/testground/sne/projects/2_ml_pipeline_decision_trees_simple_model/sbt/bin:/home/maasg/testground/sne/projects/2_ml_pipeline_decision_trees_simple_model/jdk/bin:$PATH

echo "enter /home/maasg/testground/sne/projects/2_ml_pipeline_decision_trees_simple_model/job"

cd /home/maasg/testground/sne/projects/2_ml_pipeline_decision_trees_simple_model/job

echo "publishing project"
/home/maasg/testground/sne/projects/2_ml_pipeline_decision_trees_simple_model/sbt/bin/sbt -Dspark.version=1.6.1 -Dhadoop.version=2.6.0 publish

echo "building debian"
/home/maasg/testground/sne/projects/2_ml_pipeline_decision_trees_simple_model/sbt/bin/sbt -Dspark.version=1.6.1 -Dhadoop.version=2.6.0 debian:packageBin
export PKG_NAME=2_ml_pipeline_decision_trees_simple_model_0.0.1-SNAPSHOT_all.deb
echo "package: /home/maasg/testground/sne/projects/2_ml_pipeline_decision_trees_simple_model/job/target/$PKG_NAME"

echo "pushing deb package to Adalog UI"
/bin/mkdir /srv/tmp/debian_packages/
/bin/cp /home/maasg/testground/sne/projects/2_ml_pipeline_decision_trees_simple_model/job/target/$PKG_NAME /srv/tmp/debian_packages/


echo "posting marathon/chronos"

echo 
curl -X POST -H "Content-type: application/json" http://172.17.0.2:4400/scheduler/iso8601 -d @/home/maasg/testground/sne/projects/2_ml_pipeline_decision_trees_simple_model/job/src/main/resources/chronos.json
           

echo "End job/gen.sh"
echo $(date)