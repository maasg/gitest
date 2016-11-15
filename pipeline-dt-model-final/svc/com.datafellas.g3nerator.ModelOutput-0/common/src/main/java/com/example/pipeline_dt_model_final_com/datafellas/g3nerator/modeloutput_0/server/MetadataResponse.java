/**
 * Autogenerated by Avro
 * 
 * DO NOT EDIT DIRECTLY
 */
package com.example.pipeline_dt_model_final_com.datafellas.g3nerator.modeloutput_0.server;  
@SuppressWarnings("all")
@org.apache.avro.specific.AvroGenerated
public class MetadataResponse extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"MetadataResponse\",\"namespace\":\"com.example.pipeline_dt_model_final_com.datafellas.g3nerator.modeloutput_0.server\",\"fields\":[{\"name\":\"notebook\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}}]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }
  @Deprecated public java.lang.String notebook;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>. 
   */
  public MetadataResponse() {}

  /**
   * All-args constructor.
   */
  public MetadataResponse(java.lang.String notebook) {
    this.notebook = notebook;
  }

  public org.apache.avro.Schema getSchema() { return SCHEMA$; }
  // Used by DatumWriter.  Applications should not call. 
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return notebook;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }
  // Used by DatumReader.  Applications should not call. 
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: notebook = (java.lang.String)value$; break;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  /**
   * Gets the value of the 'notebook' field.
   */
  public java.lang.String getNotebook() {
    return notebook;
  }

  /**
   * Sets the value of the 'notebook' field.
   * @param value the value to set.
   */
  public void setNotebook(java.lang.String value) {
    this.notebook = value;
  }

  /** Creates a new MetadataResponse RecordBuilder */
  public static com.example.pipeline_dt_model_final_com.datafellas.g3nerator.modeloutput_0.server.MetadataResponse.Builder newBuilder() {
    return new com.example.pipeline_dt_model_final_com.datafellas.g3nerator.modeloutput_0.server.MetadataResponse.Builder();
  }
  
  /** Creates a new MetadataResponse RecordBuilder by copying an existing Builder */
  public static com.example.pipeline_dt_model_final_com.datafellas.g3nerator.modeloutput_0.server.MetadataResponse.Builder newBuilder(com.example.pipeline_dt_model_final_com.datafellas.g3nerator.modeloutput_0.server.MetadataResponse.Builder other) {
    return new com.example.pipeline_dt_model_final_com.datafellas.g3nerator.modeloutput_0.server.MetadataResponse.Builder(other);
  }
  
  /** Creates a new MetadataResponse RecordBuilder by copying an existing MetadataResponse instance */
  public static com.example.pipeline_dt_model_final_com.datafellas.g3nerator.modeloutput_0.server.MetadataResponse.Builder newBuilder(com.example.pipeline_dt_model_final_com.datafellas.g3nerator.modeloutput_0.server.MetadataResponse other) {
    return new com.example.pipeline_dt_model_final_com.datafellas.g3nerator.modeloutput_0.server.MetadataResponse.Builder(other);
  }
  
  /**
   * RecordBuilder for MetadataResponse instances.
   */
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<MetadataResponse>
    implements org.apache.avro.data.RecordBuilder<MetadataResponse> {

    private java.lang.String notebook;

    /** Creates a new Builder */
    private Builder() {
      super(com.example.pipeline_dt_model_final_com.datafellas.g3nerator.modeloutput_0.server.MetadataResponse.SCHEMA$);
    }
    
    /** Creates a Builder by copying an existing Builder */
    private Builder(com.example.pipeline_dt_model_final_com.datafellas.g3nerator.modeloutput_0.server.MetadataResponse.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.notebook)) {
        this.notebook = data().deepCopy(fields()[0].schema(), other.notebook);
        fieldSetFlags()[0] = true;
      }
    }
    
    /** Creates a Builder by copying an existing MetadataResponse instance */
    private Builder(com.example.pipeline_dt_model_final_com.datafellas.g3nerator.modeloutput_0.server.MetadataResponse other) {
            super(com.example.pipeline_dt_model_final_com.datafellas.g3nerator.modeloutput_0.server.MetadataResponse.SCHEMA$);
      if (isValidValue(fields()[0], other.notebook)) {
        this.notebook = data().deepCopy(fields()[0].schema(), other.notebook);
        fieldSetFlags()[0] = true;
      }
    }

    /** Gets the value of the 'notebook' field */
    public java.lang.String getNotebook() {
      return notebook;
    }
    
    /** Sets the value of the 'notebook' field */
    public com.example.pipeline_dt_model_final_com.datafellas.g3nerator.modeloutput_0.server.MetadataResponse.Builder setNotebook(java.lang.String value) {
      validate(fields()[0], value);
      this.notebook = value;
      fieldSetFlags()[0] = true;
      return this; 
    }
    
    /** Checks whether the 'notebook' field has been set */
    public boolean hasNotebook() {
      return fieldSetFlags()[0];
    }
    
    /** Clears the value of the 'notebook' field */
    public com.example.pipeline_dt_model_final_com.datafellas.g3nerator.modeloutput_0.server.MetadataResponse.Builder clearNotebook() {
      notebook = null;
      fieldSetFlags()[0] = false;
      return this;
    }

    @Override
    public MetadataResponse build() {
      try {
        MetadataResponse record = new MetadataResponse();
        record.notebook = fieldSetFlags()[0] ? this.notebook : (java.lang.String) defaultValue(fields()[0]);
        return record;
      } catch (Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }
}
