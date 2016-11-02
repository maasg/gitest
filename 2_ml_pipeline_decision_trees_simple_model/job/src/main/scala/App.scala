
package com.example
/**
  Outputs
  -------
  > 

 */
object Main {

def main(args:Array[String]):Unit = {

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
sparkConf.set("spark.app.name", sparkConf.get("spark.app.name", "2_ml_pipeline_decision_trees_simple_model"))

// Set project Jars

val libDir = new java.io.File(s"/usr/share/2_ml_pipeline_decision_trees_simple_model", "lib")
val currentProjectJars = Array[String]( "com.example-2_ml_pipeline_decision_trees_simple_model.2_ml_pipeline_decision_trees_simple_model-0.0.1-SNAPSHOT.jar" ).map{j => new java.io.File(libDir, j).getAbsolutePath}
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
/* -- Code Cell: Some(C35F6EC713D449979E3D06E0DF125A8F) -- */ 

  val sqlContext = new org.apache.spark.sql.SQLContext(sc)
  
  import org.apache.spark.sql.types._
  import org.apache.spark.sql.functions._
  import sqlContext.implicits._
  import org.apache.spark.sql.DataFrame
/****************/


  /* -- Code Cell: Some(ED04F99D331A4028910ADA204ED63917) -- */ 

  val sample =  "/home/maasg/playground/data/decision_tree.data"
  
  val matrixDF = sqlContext.read
                          .format("com.databricks.spark.csv")
                          .option("header", "true")
  .option("inferSchema", "true")
                     
                     .load(sample)
                     .persist(org.apache.spark.storage.StorageLevel.MEMORY_AND_DISK_SER)
                     .repartition(4)
  val schema = matrixDF.schema
/****************/


  /* -- Code Cell: Some(466C85782D15432BBAC4CBBB561CF60C) -- */ 

  matrixDF.groupBy("any_churn_target", "full_churn_target").count
/****************/


  /* -- Code Cell: Some(77E1BBFD352E4F029255E54EA05CF6E8) -- */ 

  val distinctCsk = matrixDF.select("cs_k").distinct.count
/****************/


  /* -- Code Cell: Some(CA42CE0CA79744F38A1BB21026D5B025) -- */ 

  val distinctNeighborhoodCodes = matrixDF.select("Neighbourhood_Code").distinct.count
/****************/


  /* -- Code Cell: Some(C01DBEEA03D54C3E8DF6DCC6ADEBF26E) -- */ 

  val dictinctPostalCodes = matrixDF.select("Postal_Code").distinct.count 
/****************/


  /* -- Code Cell: Some(32D320FCD4504224824094B70A3B370B) -- */ 

  val stringedColumns = matrixDF.schema.toSeq.filter(col => (col.dataType == StringType)  && (col.name != "any_churn_target") && (col.name != "full_churn_target")).map(_.name)   
  
  val categoricals = matrixDF.schema.toSeq.filter(col => (col.dataType == StringType) && (col.name != "Postal_Code") && (col.name != "cs_k") && (col.name != "Neighbourhood_Code")
                                                               && (col.name != "any_churn_target") && (col.name != "full_churn_target") )
                                               .map(_.name)   
  
  val numCols = matrixDF.schema.toSeq.filter(col => ((col.dataType == DoubleType) || (col.dataType == IntegerType)) || (col.dataType == LongType)) 
                                 .map (_.name)  
  
  
  (stringedColumns.length + numCols.length)  == (matrixDF.schema.toSeq.length -2)
/****************/


  /* -- Code Cell: Some(503EAE0B270B4AA383A13BC37AB6E508) -- */ 

  val fillStrMap = stringedColumns.map (s => ( s, "unknown")).toMap   //Replace empty strings with a full string so Encoders and Indexers don't explode
  val fillNumMap = numCols.map ( s => (s, 0)).toMap                   // TODO ==> compute averages and look how it behaves instead of zeros
/****************/


  /* -- Code Cell: Some(F0709DE78AC34E2C862448C94A644899) -- */ 

  import org.apache.spark.ml.Transformer
  import org.apache.spark.sql.DataFrame
  import org.apache.spark.ml.util.Identifiable
  import org.apache.spark.ml.param.ParamMap
  import org.apache.spark.sql.types.{StructType, DoubleType}
  import org.apache.spark.ml.attribute.NominalAttribute
  import org.apache.spark.sql.functions._
  
  
  class PrepareTransformer() extends Transformer {
    
    val uid: String = Identifiable.randomUID("prepareTransformer")
  
    override def transformSchema(schema: StructType) = schema.add("cs_k_hash", DoubleType)
                                                    .add("Postal_Code_hash", DoubleType)
                                                    .add("Neighbourhood_Code_hash", DoubleType)
                                                    .add("label", DoubleType)
    
    override def transform(df: DataFrame) : DataFrame = {
                        import sqlContext.implicits._
      
                        val meta = NominalAttribute
                        .defaultAttr
                        .withName("churned")
                        .withValues("0.0", "1.0")
                        .toMetadata
                        
                        val  hc : String => Double = _.hashCode()
                        val hash = udf(hc)
    
                        df.withColumn("churned", when($"any_churn_target" === false , 0.0).otherwise(1.0)) // any_churn_target => churned, not_churned
                          .na.fill(fillStrMap)
                          .na.fill(fillNumMap)
                          .withColumn("cs_k_hash" , hash($"cs_k"))  
                          .withColumn("Postal_Code_hash" , hash($"Postal_Code"))
                          .withColumn("Neighbourhood_Code_hash", hash($"Neighbourhood_Code"))
                          .withColumn("label", $"churned".as("label", meta))  
    } 
      
    override def copy( extra : ParamMap) = defaultCopy(extra)
  }
/****************/


  /* -- Code Cell: Some(92612E5FBA084F7589906D6EE8BAEE65) -- */ 

  import org.apache.spark.ml.feature.{StringIndexer, VectorIndexer, VectorAssembler}
  import org.apache.spark.ml.{Pipeline, PipelineStage, PipelineModel}
  import org.apache.spark.ml.classification.DecisionTreeClassifier
  import org.apache.spark.ml.classification.DecisionTreeClassificationModel
  import org.apache.spark.ml.evaluation.MulticlassClassificationEvaluator
/****************/


  /* -- Code Cell: Some(56BD6B71E1DD4543826A81E51FD73101) -- */ 

  
  // First let's index categoricals features
  val catIndexer: Array[org.apache.spark.ml.PipelineStage] = categoricals.map(
    cname => new StringIndexer()
      .setInputCol(cname)
      .setOutputCol(s"${cname}_index")
      .setHandleInvalid("skip")
  ).toArray
  
  //Then we assemble all the features into one Vector that contains the Indexed categoricals and the continuousfeatures
  
  val vectorCols = (categoricals.map(cname => s"${cname}_index") ++ numCols ++ Array("cs_k_hash", "Postal_Code_hash" , "Neighbourhood_Code_hash" )).toArray
  
  val assembler = new VectorAssembler()
                             .setInputCols(vectorCols)
                             .setOutputCol("features")
  
  //In order for the decision tree to automatically detect categorical and improve performance, we index the assembled vectors before feeding it to a Decision tree. We set a maximal number
  // categories at 4 so it will consider features with more than 4 categories as continuous features
  
  val vectorIndexer = new VectorIndexer()
                            .setInputCol("features")
                            .setOutputCol("indexedFeatures")
                            .setMaxCategories(1500)
/****************/


  /* -- Code Cell: Some(B829133ABCA94607877E38F5CC155AA7) -- */ 

  val dt = new DecisionTreeClassifier()
            .setFeaturesCol("features")
            .setLabelCol("label")
            .setPredictionCol("predictions")
            .setImpurity("gini") //Entropy ??
            .setMaxDepth(30)
/****************/


  /* -- Code Cell: Some(E6DE621A739E49A28967A04FF8BB6BF9) -- */ 

  //We assemble all the above stages into one single pipeline 
  
  val dtPipeline = new Pipeline().setStages( Array(new PrepareTransformer()) ++ catIndexer ++ Array(assembler, vectorIndexer, dt))
/****************/


  /* -- Code Cell: Some(C8180A0CB4674200B05573B9B6C9037D) -- */ 

  val Array(trainingSet, testSet) = matrixDF.randomSplit(Array(0.8, 0.2))
/****************/


  /* -- Code Cell: Some(9C4AB4C606E64008BAF8DA881668559E) -- */ 

  val model = dtPipeline.fit(trainingSet)
/****************/


  /* -- Code Cell: Some(0FBEE70305CB418E82F97243A985F584) -- */ 

  
  val model_output = "hdfs://lhvbdab8.axa-be.intraxa:9000/playground/projects/churn_auto/out/trained_model/dt_pipeline"
  
  //sparkContext.parallelize(Seq(model), 1).saveAsObjectFile(model_output)
  
  //Note : to reload the model : sparkContext.objectFile[orgapache.spark.ml.PipelineModel](model_output).first 
/****************/


  /* -- Code Cell: Some(202CB011520746DA8A2884A5B524732D) -- */ 

  val modelOut = "/tmp/models/dt_"+(System.currentTimeMillis.toString.drop(5).take(5))
  sparkContext.parallelize(Seq(model), 1).saveAsObjectFile(modelOut)
/****************/


  /* -- Code Cell: Some(5B2F4BD6F9DF4CDE8EBF11D5CE3C3BB3) -- */ 

  // Let's get predictions from the testSet applied to pipelinedModel
  
  val predictionsDF = model.transform(testSet)
                           .select( $"rawPrediction", $"probability", $"predictions", $"label")  
/****************/


  /* -- Code Cell: Some(F7305C927E1E4F0688E361B2B1B0F282) -- */ 

  import org.apache.spark.ml.evaluation.BinaryClassificationEvaluator
  
  val evaluator = new BinaryClassificationEvaluator()
  
  println("areaUnderPR :" + evaluator.setMetricName("areaUnderPR").evaluate(predictionsDF))
  println("areaUnderROC :" + evaluator.setMetricName("areaUnderROC").evaluate(predictionsDF))
/****************/


  /* -- Code Cell: Some(DE8E5BBA7DBB4CBD80CE8D5C49AE8BFC) -- */ 

  // Using MulclassMetrics to assess the model
  import org.apache.spark.mllib.evaluation.MulticlassMetrics
  
  val predictionsRDD = predictionsDF.select("predictions", "label").rdd.map(x => (x.getDouble(0), x.getDouble(1)))
  
  val metrics = new MulticlassMetrics(predictionsRDD)
/****************/


  /* -- Code Cell: Some(B1F3136609CA4149AB6ED2993D9F90B2) -- */ 

  println("Precision of True : "+  metrics.precision(1.0))
  println("Precision of False: "+ metrics.precision(0.0))
  println("Recall of True    :"+ metrics.recall(1.0))
  println("Recall of False   :"+ metrics.recall(0.0))
  println("F-1 Score         :"+ metrics.fMeasure)
/****************/


  /* -- Code Cell: Some(BE15184DEE1145F987DA83136CC5CAE4) -- */ 

  println("Confusion Matrix\n:"+ metrics.confusionMatrix )
/****************/


  /* -- Code Cell: Some(7578120C08364817B4A41442FDC9D48A) -- */ 

  import org.apache.spark.sql.DataFrame
  import org.apache.spark.ml.PipelineModel
  
  // We take the data we want predictions from as dataframe, we featurize the dataframe ( fill Empty records, hash some columns ) by calling prepare() defined above 
  //then feed it into the pipelineModel
  // Here make sure that your data has the same schema as ChurnedDF
  
  def predict ( input : DataFrame, model : PipelineModel) = {
     model.transform (input).select("id", "rawPrediction", "probability", "predictions")
  }
/****************/


  /* -- Code Cell: Some(4B6B0F6EDA9041D888C4EE554D820D88) -- */ 

  // Example. we want to predictions for the following record
  val sample = matrixDF.sample(false, 0.00001)
  
  // We Get the following predictions.
  predict (sample, model)
/****************/


  /* -- Code Cell: Some(ECB2BF0CE9E64CAE8081844B75867F5A) -- */

sparkContext.stop


}
}