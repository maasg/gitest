
package io.kensu


// no custom variables 
/* -- Code Cell: Some(F0709DE78AC34E2C862448C94A644899) -- */ 

  import org.apache.spark.ml.Transformer
  import org.apache.spark.sql.DataFrame
  import org.apache.spark.ml.util.Identifiable
  import org.apache.spark.ml.param.ParamMap
  import org.apache.spark.sql.types.{StructType, DoubleType}
  import org.apache.spark.ml.attribute.NominalAttribute
  import org.apache.spark.sql.functions._
  import org.apache.spark.SparkContext
  import org.apache.spark.sql.SQLContext
  
  class PrepareTransformer(fillStr: Map[String,String], fillNum: Map[String,Int]) extends Transformer {
  
    val sqlContext = new SQLContext(SparkContext.getOrCreate())
    import sqlContext.implicits._
  
    val uid: String = Identifiable.randomUID("prepareTransformer")
  
    override def transformSchema(schema: StructType) = schema.add("cs_k_hash", DoubleType)
                                                    .add("Postal_Code_hash", DoubleType)
                                                    .add("Neighbourhood_Code_hash", DoubleType)
                                                    .add("label", DoubleType)
    
    override def transform(dfm: DataFrame) : DataFrame = {
                            
                        val meta = NominalAttribute
                        .defaultAttr
                        .withName("churned")
                        .withValues("0.0", "1.0")
                        .toMetadata
      
                        
                        val  hc : String => Double = _.hashCode()
                        val hash = udf(hc)
    
                        dfm.withColumn("churned", when($"any_churn_target" === false , 0.0).otherwise(1.0)) // any_churn_target => churned, not_churned
                          .na.fill(fillStr)
                          .na.fill(fillNum)
                          .withColumn("cs_k_hash" , hash($"cs_k"))  
                          .withColumn("Postal_Code_hash" , hash($"Postal_Code"))
                          .withColumn("Neighbourhood_Code_hash", hash($"Neighbourhood_Code"))
                          .withColumn("label", $"churned".as("label", meta))
                          //.drop(df("cs_k"))
                          //.drop(df("Postal_Code"))
                          //.drop(df("Neighbourhood_Code"))
                           
    } 
      
    override def copy( extra : ParamMap) = defaultCopy(extra)
  }


