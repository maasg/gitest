/**
 * Autogenerated by Avro
 * 
 * DO NOT EDIT DIRECTLY
 */
package com.example.pipeline_dt_model_final_com.datafellas.g3nerator.modeloutput_0.model;  
@SuppressWarnings("all")
@org.apache.avro.specific.AvroGenerated
public class Domain extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"Domain\",\"namespace\":\"com.example.pipeline_dt_model_final_com.datafellas.g3nerator.modeloutput_0.model\",\"fields\":[{\"name\":\"output\",\"type\":\"double\"}]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }
  @Deprecated public double output;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>. 
   */
  public Domain() {}

  /**
   * All-args constructor.
   */
  public Domain(java.lang.Double output) {
    this.output = output;
  }

  public org.apache.avro.Schema getSchema() { return SCHEMA$; }
  // Used by DatumWriter.  Applications should not call. 
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return output;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }
  // Used by DatumReader.  Applications should not call. 
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: output = (java.lang.Double)value$; break;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  /**
   * Gets the value of the 'output' field.
   */
  public java.lang.Double getOutput() {
    return output;
  }

  /**
   * Sets the value of the 'output' field.
   * @param value the value to set.
   */
  public void setOutput(java.lang.Double value) {
    this.output = value;
  }

  /** Creates a new Domain RecordBuilder */
  public static com.example.pipeline_dt_model_final_com.datafellas.g3nerator.modeloutput_0.model.Domain.Builder newBuilder() {
    return new com.example.pipeline_dt_model_final_com.datafellas.g3nerator.modeloutput_0.model.Domain.Builder();
  }
  
  /** Creates a new Domain RecordBuilder by copying an existing Builder */
  public static com.example.pipeline_dt_model_final_com.datafellas.g3nerator.modeloutput_0.model.Domain.Builder newBuilder(com.example.pipeline_dt_model_final_com.datafellas.g3nerator.modeloutput_0.model.Domain.Builder other) {
    return new com.example.pipeline_dt_model_final_com.datafellas.g3nerator.modeloutput_0.model.Domain.Builder(other);
  }
  
  /** Creates a new Domain RecordBuilder by copying an existing Domain instance */
  public static com.example.pipeline_dt_model_final_com.datafellas.g3nerator.modeloutput_0.model.Domain.Builder newBuilder(com.example.pipeline_dt_model_final_com.datafellas.g3nerator.modeloutput_0.model.Domain other) {
    return new com.example.pipeline_dt_model_final_com.datafellas.g3nerator.modeloutput_0.model.Domain.Builder(other);
  }
  
  /**
   * RecordBuilder for Domain instances.
   */
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<Domain>
    implements org.apache.avro.data.RecordBuilder<Domain> {

    private double output;

    /** Creates a new Builder */
    private Builder() {
      super(com.example.pipeline_dt_model_final_com.datafellas.g3nerator.modeloutput_0.model.Domain.SCHEMA$);
    }
    
    /** Creates a Builder by copying an existing Builder */
    private Builder(com.example.pipeline_dt_model_final_com.datafellas.g3nerator.modeloutput_0.model.Domain.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.output)) {
        this.output = data().deepCopy(fields()[0].schema(), other.output);
        fieldSetFlags()[0] = true;
      }
    }
    
    /** Creates a Builder by copying an existing Domain instance */
    private Builder(com.example.pipeline_dt_model_final_com.datafellas.g3nerator.modeloutput_0.model.Domain other) {
            super(com.example.pipeline_dt_model_final_com.datafellas.g3nerator.modeloutput_0.model.Domain.SCHEMA$);
      if (isValidValue(fields()[0], other.output)) {
        this.output = data().deepCopy(fields()[0].schema(), other.output);
        fieldSetFlags()[0] = true;
      }
    }

    /** Gets the value of the 'output' field */
    public java.lang.Double getOutput() {
      return output;
    }
    
    /** Sets the value of the 'output' field */
    public com.example.pipeline_dt_model_final_com.datafellas.g3nerator.modeloutput_0.model.Domain.Builder setOutput(double value) {
      validate(fields()[0], value);
      this.output = value;
      fieldSetFlags()[0] = true;
      return this; 
    }
    
    /** Checks whether the 'output' field has been set */
    public boolean hasOutput() {
      return fieldSetFlags()[0];
    }
    
    /** Clears the value of the 'output' field */
    public com.example.pipeline_dt_model_final_com.datafellas.g3nerator.modeloutput_0.model.Domain.Builder clearOutput() {
      fieldSetFlags()[0] = false;
      return this;
    }

    @Override
    public Domain build() {
      try {
        Domain record = new Domain();
        record.output = fieldSetFlags()[0] ? this.output : (java.lang.Double) defaultValue(fields()[0]);
        return record;
      } catch (Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }
}
