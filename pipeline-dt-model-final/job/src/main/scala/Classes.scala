
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
  }()

}
