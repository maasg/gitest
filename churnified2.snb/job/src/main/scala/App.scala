
package com.example
/**
  Outputs
  -------
  > ModelOutput(CodeCell(CellMetadata(Some(true),Some(false),None,Some(false),None,Some(8018A45B21564247B501733A718D5370),Some({"fittedPipeline":"org.apache.spark.ml.tuning.CrossValidatorModel","inputs":{"resolved":[],"unresolved":[]}})),output,model_output+(System.currentTimeMillis.toString.drop(5)),None,None,Some({"type":"model","var":"fittedPipeline","extra":{"value":"org.apache.spark.ml.tuning.CrossValidatorModel"}}),Some(List(ScalaStream(stdout,stream,Model
Located: /tmp/adalog/modelout10640347
Model:  fittedPipeline (org.apache.spark.ml.tuning.CrossValidatorModel)
{"type":"model","var":"fittedPipeline","extra":{"value":"org.apache.spark.ml.tuning.CrossValidatorModel"}}
None
output-8018A45B21564247B501733A718D5370: String = /tmp/adalog/modelout10640347
res26: notebook.front.widgets.adst.ModelOutputWidget = <ModelOutputWidget widget>
), ScalaExecuteResult(ExecuteResultMetadata(None),Map(text/html -> <div>
      <script data-this="{&quot;modelVar&quot;:&quot;fittedPipeline&quot;,&quot;inputs&quot;:{&quot;resolved&quot;:[],&quot;unresolved&quot;:[]},&quot;modelName&quot;:&quot;org.apache.spark.ml.tuning.CrossValidatorModel&quot;}" type="text/x-scoped-javascript">/*<![CDATA[*/req(['../javascripts/notebook/adst/output/modelOutput'], 
      function(modelOutput) {
        modelOutput.call(data, this);
      }
    );/*]]>*/</script>
    </div>),execute_result,20)))),model_output+(System.currentTimeMillis.toString.drop(5)),model_output+(System.currentTimeMillis.toString.drop(5)),fittedPipeline,com.datafellas.DefaultModelHandlers$ML$Classification$$anon$3@442d9efb,0)

 */
object Main extends App {

import org.apache.spark.{SparkContext, SparkConf}
import org.apache.spark.SparkContext._
import org.apache.spark.rdd._
import com.typesafe.config._
import scala.collection.JavaConverters._

// Create spark configuration holder
val sparkConf = new SparkConf()

// Set configuration
val config = ConfigFactory.load()
val sparkConfig = config.getConfig("spark")
                        .atPath("spark").entrySet.asScala.map(e => e.getKey -> config.getString(e.getKey))

sparkConf.setAll(sparkConfig)

sparkConf.setMaster(sparkConf.get("spark.master", "local[*]"))
sparkConf.set("spark.app.name", sparkConf.get("spark.app.name", "churnified2.snb"))

// Set project Jars

val libDir = new java.io.File(".", "lib")
val currentProjectJars = Array[String]( "com.example-churnified2.snb.churnified2.snb-0.0.1-SNAPSHOT.jar" ).map{j => new java.io.File(libDir, j).getAbsolutePath}
val sparkLibDir = new java.io.File(".", "spark-lib")
val fromProjectJars = Array[String]( "spark-csv_2.10-1.5.0.jar" , "scala-library-2.10.5.jar" , "commons-csv-1.1.jar" , "univocity-parsers-1.5.1.jar" ).map{j => new java.io.File(sparkLibDir, j).getAbsolutePath}
val jarsArray = (sparkConf.get("spark.jars", "").split(",").toArray ++ currentProjectJars ++ fromProjectJars).distinct.filter(!_.isEmpty)
println("Add Jars: \n" + jarsArray.mkString("\n"))
sparkConf.setJars(jarsArray)



// Create Spark Context
val sparkContext = new SparkContext(sparkConf)
println("Debug Conf:" + sparkContext.getConf.toDebugString)

// Spark Context alias
val sc = sparkContext






  def getStringConfig(path: String) : Option[String] = {
    if (config.hasPath(path)) {
      Some(config.getString(path))
    } else {
      None
    }
  }

  val adalogUrl: Option[String] = getStringConfig("adalog.url")
  val adalogUser: Option[String] = getStringConfig("adalog.authentication.username")
  val adalogPassword: Option[String] = getStringConfig("adalog.authentication.password")


// no custom variables 
/* -- Code Cell: Some(A0B79C1E9ADE4E1F89022CAB6A0297F0) -- */ 

  val sqlContext = new org.apache.spark.sql.SQLContext(sc)
  import sqlContext.implicits._
  
  import org.apache.spark.sql.types._
/****************/


  /* -- Code Cell: Some(9A7C4B1B2CC74CC588A355344AD4CFB2) -- */ 

  //val model_matrix_uri = "hdfs://lhvbdab8.axa-be.intraxa:9000/playground/projects/churn_auto/out/modelMatrix/"
  //val model_matrix_uri_output = "hdfs://lhvbdab8.axa-be.intraxa:9000/playground/projects/churn_auto/out/modelMatrix/"
  
  val sample =  "/home/maasg/playground/data/random_forest.data"
  
  val matrixDF = sqlContext.read
                          .format("com.databricks.spark.csv")
                          .option("header", "true")
  .option("inferSchema", "true")
                     
                     .load(sample)
                     .persist(org.apache.spark.storage.StorageLevel.MEMORY_AND_DISK_SER)
                     .repartition(4)
/****************/


  /* -- Code Cell: Some(CE0477855D3741E5924933D8FD31BA86) -- */ 

  matrixDF.show
/****************/


  /* -- Code Cell: Some(CDD53E52F14C4E62BA50847F9094F1B6) -- */ 

  matrixDF.schema
/****************/


  /* -- Code Cell: Some(8C1F13860F174AF88CAA9DC08100070C) -- */ 

  // matrixDF.take(50)
/****************/


  /* -- Code Cell: Some(045E46A55615443B85869C46E7134013) -- */ 

  import org.apache.spark.sql.functions._
  
  val churnedDF_tmp = matrixDF.withColumn("churned", when($" any_churn_target_boolean" === false , 0.0).otherwise(1.0)) // any_churn_target => churned, not_churned
                          .drop(" full_churn_target_boolean")
  .drop(" any_churn_target_boolean")
  
  
  //TODO Deal with these features : either by augmenting the number of categoricals
/****************/


  /* -- Code Cell: Some(32D320FCD4504224824094B70A3B370B) -- */ 

  val stringedColumns = churnedDF_tmp.schema.toSeq.filter(col => (col.dataType == StringType) ).map(_.name)   
  
  val numCols = churnedDF_tmp.schema.toSeq.filter(col => ( (col.name != "churned") && ((col.dataType == DoubleType) || (col.dataType == IntegerType)) || (col.dataType == LongType))) 
                                 .map (_.name)  
  
  
  (stringedColumns.length + numCols.length)  == (churnedDF_tmp.schema.toSeq.length -1)
/****************/


  /* -- Code Cell: Some(503EAE0B270B4AA383A13BC37AB6E508) -- */ 

  val fillStrMap = stringedColumns.map (s => ( s, "unknown")).toMap   //Replace empty strings with a full string so Encoders and Indexers don't explode
  val fillNumMap = numCols.map ( s => (s, 0)).toMap                   // TODO ==> compute averages instead of zeros
/****************/


  /* -- Code Cell: Some(B028D7AF5FB845698678874090D9EBF8) -- */ 

  val churnedDF = churnedDF_tmp.na.fill(fillStrMap)
                               .na.fill(fillNumMap)
/****************/


  /* -- Code Cell: Some(92612E5FBA084F7589906D6EE8BAEE65) -- */ 

  import org.apache.spark.ml.feature.{StringIndexer, IndexToString, VectorIndexer, VectorSlicer, StandardScaler, VectorAssembler, OneHotEncoder}
  import org.apache.spark.ml.{Pipeline, PipelineStage}
  import org.apache.spark.mllib.util.MLUtils
  import org.apache.spark.ml.evaluation.MulticlassClassificationEvaluator
/****************/


  /* -- Code Cell: Some(490EB66BE7A0411285629874B6BD1EEB) -- */ 

  import org.apache.spark.ml.attribute.NominalAttribute
  
  val meta = NominalAttribute
    .defaultAttr
    .withName("churned")
    .withValues("0.0", "1.0")
    .toMetadata
  
  //Manually adds metadata to the label columns 
  val churnedWithMetadata = churnedDF.withColumn("label", $"churned".as("label", meta)).drop("churned")
/****************/


  /* -- Code Cell: Some(3576D11CD45A476692EB73459760D951) -- */ 

  churnedWithMetadata.take(50)
/****************/


  /* -- Code Cell: Some(56BD6B71E1DD4543826A81E51FD73101) -- */ 

  val catIndexer: Array[org.apache.spark.ml.PipelineStage] = stringedColumns.map(
    cname => new StringIndexer()
      .setInputCol(cname)
      .setOutputCol(s"${cname}_index")
      .setHandleInvalid("skip")
  ).toArray
  
  val vectorCols = (stringedColumns.map(cname => s"${cname}_index") ++ numCols).toArray
  
  val vectorAssembler = new VectorAssembler()
                             .setInputCols(vectorCols)
                             .setOutputCol("features")
  
  val vectorIndexer = new VectorIndexer()
                             .setInputCol("features")
                             .setOutputCol("indexedFeatures")
                             .setMaxCategories(10)
/****************/


  /* -- Code Cell: Some(A443CDE48DB445DA9FB945382800C79C) -- */ 

  import org.apache.spark.ml.classification.{RandomForestClassifier, RandomForestClassificationModel}
  import scala.util.Random
  
  val classifier = new RandomForestClassifier()
           .setSeed(Random.nextLong())
           .setFeaturesCol("features")
           .setLabelCol("label")
           .setPredictionCol("prediction")
           .setNumTrees(48)
           .setImpurity("entropy")
           .setFeatureSubsetStrategy("auto")
                    
  val rfPipeline = new Pipeline().setStages(catIndexer ++ Array(vectorAssembler, classifier))
/****************/


  /* -- Code Cell: Some(A145EF3CD68148FD95084B2B2819641B) -- */ 

  import org.apache.spark.ml.tuning.{CrossValidator, ParamGridBuilder}
  import org.apache.spark.ml.evaluation.BinaryClassificationEvaluator
  
  
  // We use a ParamGridBuilder to construct a grid of parameters to search over
  val paramGrid = new ParamGridBuilder()
    .addGrid(classifier.maxBins, Array(25, 28, 32))
    .addGrid(classifier.maxDepth, Array(4, 8, 16))
    .addGrid(classifier.impurity, Array("entropy", "gini"))
    .build()
  
  val evaluator = new BinaryClassificationEvaluator()
    .setLabelCol("label")
    //.setMetricName("areaUnderPR")
/****************/


  /* -- Code Cell: Some(4A68D670F4484C8F81856ABF8ED6099F) -- */ 

  val cv = new CrossValidator().setEstimator(rfPipeline)
                               .setEvaluator(evaluator)
                               .setEstimatorParamMaps(paramGrid)
                               .setNumFolds(10)
/****************/


  /* -- Code Cell: Some(C8180A0CB4674200B05573B9B6C9037D) -- */ 

  val Array(trainingSet, testSet) = churnedWithMetadata.randomSplit(Array(0.8, 0.2))
/****************/


  /* -- Code Cell: Some(9C4AB4C606E64008BAF8DA881668559E) -- */ 

  val fittedPipeline = cv.fit(trainingSet)
/****************/


  /* -- Code Cell: Some(0E30F6A5768B4DE881250218705CE9A5) -- */ 

  val model_output = "/tmp/adalog/modelout"
/****************/


  /* -- Code Cell: Some(8018A45B21564247B501733A718D5370) -- */ 

  
val `output-8018A45B21564247B501733A718D5370` = {
  model_output+(System.currentTimeMillis.toString.drop(5))
  }
  


  
  org.apache.spark.SparkContext.getOrCreate.parallelize(Seq(fittedPipeline), 1).saveAsObjectFile(`output-8018A45B21564247B501733A718D5370`)
  
  


  
  {
    // adding output instance information into catalog
    import scalaj.http._
  
    val h = Http(adalogUrl.get + "/adalog/output?uuid=399f473c-4c02-460d-a96f-f11a3093d599&tpe=model&variable=fittedPipeline&location="+`output-8018A45B21564247B501733A718D5370`)
    val ah = adalogUser.map { _ => h.auth(adalogUser.get, adalogPassword.get) }.getOrElse(h)
    ah.postForm(Nil).asString.body
  }
  
  


   // Output without variable
/****************/


  /* -- Code Cell: Some(CC4E5A07AE9A4C5D8916109B2F8A3D4D) -- */ 

  val predictions = fittedPipeline.transform(testSet)
  val accuracy = evaluator.evaluate(predictions)
/****************/


  /* -- Code Cell: Some(FD3A048F937640B4922A8880F6D8E8EC) -- */ 

  println ("accuracy : " + accuracy)
/****************/


  /* -- Code Cell: Some(40F206F6FB73480880AA3AC6EE57BB20) -- */ 

  import org.apache.spark.mllib.evaluation.BinaryClassificationMetrics
  
  val predictionsAndLabels = predictions.select($"prediction", $"label").rdd.map( k => (k.getDouble(0), k.getDouble(1)))
  val metrics_rdd = new BinaryClassificationMetrics(predictionsAndLabels)
/****************/


  /* -- Code Cell: Some(F7305C927E1E4F0688E361B2B1B0F282) -- */ 

  println ( "ROC under PR : " + metrics_rdd.areaUnderPR)
  println ("ROC Curve : " + metrics_rdd.areaUnderROC)

sparkContext.stop


}
