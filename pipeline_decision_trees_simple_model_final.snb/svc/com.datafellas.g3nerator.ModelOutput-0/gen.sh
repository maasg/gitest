#!/bin/bash

echo "Start service/gen.sh"
echo $(date)

export JAVA_HOME=/home/maasg/Dev/java/jdk1.8.0_20
export JDK_HOME=/home/maasg/Dev/java/jdk1.8.0_20
export PATH=/home/maasg/Dev/java/sbt/bin:/home/maasg/Dev/java/jdk1.8.0_20/bin:$PATH

echo "enter /home/maasg/testground/sne/projects/pipeline_decision_trees_simple_model_final.snb/svc/com.datafellas.g3nerator.ModelOutput-0"

cd /home/maasg/testground/sne/projects/pipeline_decision_trees_simple_model_final.snb/svc/com.datafellas.g3nerator.ModelOutput-0

echo "building project"
/home/maasg/Dev/java/sbt/bin/sbt "common/publish"
/home/maasg/Dev/java/sbt/bin/sbt "server/publish"
/home/maasg/Dev/java/sbt/bin/sbt "client/publish"

echo "Telling catalog which client library to use (com.example.pipeline_decision_trees_simple_model_final.snb_com.datafellas.g3nerator.modeloutput_0:client:0.0.1-SNAPSHOT})"
curl -u 'username:password' -X POST "http://localhost:9001//adalog/output/service/library?uuid=bbe7efb6-db5f-483f-abe7-341e536f0b34&tpe=model&variable=model&groupId=com.example.pipeline_decision_trees_simple_model_final.snb_com.datafellas.g3nerator.modeloutput_0&artifactId=client_2.10&version=0.0.1-SNAPSHOT&pck=com.example.pipeline_decision_trees_simple_model_final.snb_com.datafellas.g3nerator.modeloutput_0"



echo "building docker"
/home/maasg/Dev/java/sbt/bin/sbt "server/docker:publishLocal"

## 

echo "pushing docker"
docker push localhost:5000/pipeline_decision_trees_simple_model_final.snb-com.datafellas.g3nerator.modeloutput-0:0.0.1-SNAPSHOT

 ## Not needed now -> Replace by Canister

echo "showing docker images"

## docker images

echo "posting marathon"


curl -X POST -H "Content-type: application/json" http://172.17.0.6:8080/v2/apps -d @/home/maasg/testground/sne/projects/pipeline_decision_trees_simple_model_final.snb/svc/com.datafellas.g3nerator.ModelOutput-0/server/src/main/resources/marathon.json
                  

echo "End service/gen.sh"
echo $(date)
