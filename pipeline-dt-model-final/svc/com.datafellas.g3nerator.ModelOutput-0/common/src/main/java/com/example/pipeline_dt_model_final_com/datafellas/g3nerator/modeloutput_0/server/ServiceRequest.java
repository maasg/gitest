/**
 * Autogenerated by Avro
 * 
 * DO NOT EDIT DIRECTLY
 */
package com.example.pipeline_dt_model_final_com.datafellas.g3nerator.modeloutput_0.server;  
@SuppressWarnings("all")
@org.apache.avro.specific.AvroGenerated
public class ServiceRequest extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"ServiceRequest\",\"namespace\":\"com.example.pipeline_dt_model_final_com.datafellas.g3nerator.modeloutput_0.server\",\"fields\":[{\"name\":\"id\",\"type\":\"int\"},{\"name\":\"any_churn_target\",\"type\":\"boolean\"},{\"name\":\"full_churn_target\",\"type\":\"boolean\"},{\"name\":\"A_1_count_last\",\"type\":\"int\"},{\"name\":\"A_2_count_last\",\"type\":\"int\"},{\"name\":\"A_3_count_last\",\"type\":\"int\"},{\"name\":\"Postal_Code\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}},{\"name\":\"cs_k\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}},{\"name\":\"Neighbourhood_Code\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}}]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }
  @Deprecated public int id;
  @Deprecated public boolean any_churn_target;
  @Deprecated public boolean full_churn_target;
  @Deprecated public int A_1_count_last;
  @Deprecated public int A_2_count_last;
  @Deprecated public int A_3_count_last;
  @Deprecated public java.lang.String Postal_Code;
  @Deprecated public java.lang.String cs_k;
  @Deprecated public java.lang.String Neighbourhood_Code;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>. 
   */
  public ServiceRequest() {}

  /**
   * All-args constructor.
   */
  public ServiceRequest(java.lang.Integer id, java.lang.Boolean any_churn_target, java.lang.Boolean full_churn_target, java.lang.Integer A_1_count_last, java.lang.Integer A_2_count_last, java.lang.Integer A_3_count_last, java.lang.String Postal_Code, java.lang.String cs_k, java.lang.String Neighbourhood_Code) {
    this.id = id;
    this.any_churn_target = any_churn_target;
    this.full_churn_target = full_churn_target;
    this.A_1_count_last = A_1_count_last;
    this.A_2_count_last = A_2_count_last;
    this.A_3_count_last = A_3_count_last;
    this.Postal_Code = Postal_Code;
    this.cs_k = cs_k;
    this.Neighbourhood_Code = Neighbourhood_Code;
  }

  public org.apache.avro.Schema getSchema() { return SCHEMA$; }
  // Used by DatumWriter.  Applications should not call. 
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return id;
    case 1: return any_churn_target;
    case 2: return full_churn_target;
    case 3: return A_1_count_last;
    case 4: return A_2_count_last;
    case 5: return A_3_count_last;
    case 6: return Postal_Code;
    case 7: return cs_k;
    case 8: return Neighbourhood_Code;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }
  // Used by DatumReader.  Applications should not call. 
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: id = (java.lang.Integer)value$; break;
    case 1: any_churn_target = (java.lang.Boolean)value$; break;
    case 2: full_churn_target = (java.lang.Boolean)value$; break;
    case 3: A_1_count_last = (java.lang.Integer)value$; break;
    case 4: A_2_count_last = (java.lang.Integer)value$; break;
    case 5: A_3_count_last = (java.lang.Integer)value$; break;
    case 6: Postal_Code = (java.lang.String)value$; break;
    case 7: cs_k = (java.lang.String)value$; break;
    case 8: Neighbourhood_Code = (java.lang.String)value$; break;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  /**
   * Gets the value of the 'id' field.
   */
  public java.lang.Integer getId() {
    return id;
  }

  /**
   * Sets the value of the 'id' field.
   * @param value the value to set.
   */
  public void setId(java.lang.Integer value) {
    this.id = value;
  }

  /**
   * Gets the value of the 'any_churn_target' field.
   */
  public java.lang.Boolean getAnyChurnTarget() {
    return any_churn_target;
  }

  /**
   * Sets the value of the 'any_churn_target' field.
   * @param value the value to set.
   */
  public void setAnyChurnTarget(java.lang.Boolean value) {
    this.any_churn_target = value;
  }

  /**
   * Gets the value of the 'full_churn_target' field.
   */
  public java.lang.Boolean getFullChurnTarget() {
    return full_churn_target;
  }

  /**
   * Sets the value of the 'full_churn_target' field.
   * @param value the value to set.
   */
  public void setFullChurnTarget(java.lang.Boolean value) {
    this.full_churn_target = value;
  }

  /**
   * Gets the value of the 'A_1_count_last' field.
   */
  public java.lang.Integer getA1CountLast() {
    return A_1_count_last;
  }

  /**
   * Sets the value of the 'A_1_count_last' field.
   * @param value the value to set.
   */
  public void setA1CountLast(java.lang.Integer value) {
    this.A_1_count_last = value;
  }

  /**
   * Gets the value of the 'A_2_count_last' field.
   */
  public java.lang.Integer getA2CountLast() {
    return A_2_count_last;
  }

  /**
   * Sets the value of the 'A_2_count_last' field.
   * @param value the value to set.
   */
  public void setA2CountLast(java.lang.Integer value) {
    this.A_2_count_last = value;
  }

  /**
   * Gets the value of the 'A_3_count_last' field.
   */
  public java.lang.Integer getA3CountLast() {
    return A_3_count_last;
  }

  /**
   * Sets the value of the 'A_3_count_last' field.
   * @param value the value to set.
   */
  public void setA3CountLast(java.lang.Integer value) {
    this.A_3_count_last = value;
  }

  /**
   * Gets the value of the 'Postal_Code' field.
   */
  public java.lang.String getPostalCode() {
    return Postal_Code;
  }

  /**
   * Sets the value of the 'Postal_Code' field.
   * @param value the value to set.
   */
  public void setPostalCode(java.lang.String value) {
    this.Postal_Code = value;
  }

  /**
   * Gets the value of the 'cs_k' field.
   */
  public java.lang.String getCsK() {
    return cs_k;
  }

  /**
   * Sets the value of the 'cs_k' field.
   * @param value the value to set.
   */
  public void setCsK(java.lang.String value) {
    this.cs_k = value;
  }

  /**
   * Gets the value of the 'Neighbourhood_Code' field.
   */
  public java.lang.String getNeighbourhoodCode() {
    return Neighbourhood_Code;
  }

  /**
   * Sets the value of the 'Neighbourhood_Code' field.
   * @param value the value to set.
   */
  public void setNeighbourhoodCode(java.lang.String value) {
    this.Neighbourhood_Code = value;
  }

  /** Creates a new ServiceRequest RecordBuilder */
  public static com.example.pipeline_dt_model_final_com.datafellas.g3nerator.modeloutput_0.server.ServiceRequest.Builder newBuilder() {
    return new com.example.pipeline_dt_model_final_com.datafellas.g3nerator.modeloutput_0.server.ServiceRequest.Builder();
  }
  
  /** Creates a new ServiceRequest RecordBuilder by copying an existing Builder */
  public static com.example.pipeline_dt_model_final_com.datafellas.g3nerator.modeloutput_0.server.ServiceRequest.Builder newBuilder(com.example.pipeline_dt_model_final_com.datafellas.g3nerator.modeloutput_0.server.ServiceRequest.Builder other) {
    return new com.example.pipeline_dt_model_final_com.datafellas.g3nerator.modeloutput_0.server.ServiceRequest.Builder(other);
  }
  
  /** Creates a new ServiceRequest RecordBuilder by copying an existing ServiceRequest instance */
  public static com.example.pipeline_dt_model_final_com.datafellas.g3nerator.modeloutput_0.server.ServiceRequest.Builder newBuilder(com.example.pipeline_dt_model_final_com.datafellas.g3nerator.modeloutput_0.server.ServiceRequest other) {
    return new com.example.pipeline_dt_model_final_com.datafellas.g3nerator.modeloutput_0.server.ServiceRequest.Builder(other);
  }
  
  /**
   * RecordBuilder for ServiceRequest instances.
   */
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<ServiceRequest>
    implements org.apache.avro.data.RecordBuilder<ServiceRequest> {

    private int id;
    private boolean any_churn_target;
    private boolean full_churn_target;
    private int A_1_count_last;
    private int A_2_count_last;
    private int A_3_count_last;
    private java.lang.String Postal_Code;
    private java.lang.String cs_k;
    private java.lang.String Neighbourhood_Code;

    /** Creates a new Builder */
    private Builder() {
      super(com.example.pipeline_dt_model_final_com.datafellas.g3nerator.modeloutput_0.server.ServiceRequest.SCHEMA$);
    }
    
    /** Creates a Builder by copying an existing Builder */
    private Builder(com.example.pipeline_dt_model_final_com.datafellas.g3nerator.modeloutput_0.server.ServiceRequest.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.id)) {
        this.id = data().deepCopy(fields()[0].schema(), other.id);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.any_churn_target)) {
        this.any_churn_target = data().deepCopy(fields()[1].schema(), other.any_churn_target);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.full_churn_target)) {
        this.full_churn_target = data().deepCopy(fields()[2].schema(), other.full_churn_target);
        fieldSetFlags()[2] = true;
      }
      if (isValidValue(fields()[3], other.A_1_count_last)) {
        this.A_1_count_last = data().deepCopy(fields()[3].schema(), other.A_1_count_last);
        fieldSetFlags()[3] = true;
      }
      if (isValidValue(fields()[4], other.A_2_count_last)) {
        this.A_2_count_last = data().deepCopy(fields()[4].schema(), other.A_2_count_last);
        fieldSetFlags()[4] = true;
      }
      if (isValidValue(fields()[5], other.A_3_count_last)) {
        this.A_3_count_last = data().deepCopy(fields()[5].schema(), other.A_3_count_last);
        fieldSetFlags()[5] = true;
      }
      if (isValidValue(fields()[6], other.Postal_Code)) {
        this.Postal_Code = data().deepCopy(fields()[6].schema(), other.Postal_Code);
        fieldSetFlags()[6] = true;
      }
      if (isValidValue(fields()[7], other.cs_k)) {
        this.cs_k = data().deepCopy(fields()[7].schema(), other.cs_k);
        fieldSetFlags()[7] = true;
      }
      if (isValidValue(fields()[8], other.Neighbourhood_Code)) {
        this.Neighbourhood_Code = data().deepCopy(fields()[8].schema(), other.Neighbourhood_Code);
        fieldSetFlags()[8] = true;
      }
    }
    
    /** Creates a Builder by copying an existing ServiceRequest instance */
    private Builder(com.example.pipeline_dt_model_final_com.datafellas.g3nerator.modeloutput_0.server.ServiceRequest other) {
            super(com.example.pipeline_dt_model_final_com.datafellas.g3nerator.modeloutput_0.server.ServiceRequest.SCHEMA$);
      if (isValidValue(fields()[0], other.id)) {
        this.id = data().deepCopy(fields()[0].schema(), other.id);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.any_churn_target)) {
        this.any_churn_target = data().deepCopy(fields()[1].schema(), other.any_churn_target);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.full_churn_target)) {
        this.full_churn_target = data().deepCopy(fields()[2].schema(), other.full_churn_target);
        fieldSetFlags()[2] = true;
      }
      if (isValidValue(fields()[3], other.A_1_count_last)) {
        this.A_1_count_last = data().deepCopy(fields()[3].schema(), other.A_1_count_last);
        fieldSetFlags()[3] = true;
      }
      if (isValidValue(fields()[4], other.A_2_count_last)) {
        this.A_2_count_last = data().deepCopy(fields()[4].schema(), other.A_2_count_last);
        fieldSetFlags()[4] = true;
      }
      if (isValidValue(fields()[5], other.A_3_count_last)) {
        this.A_3_count_last = data().deepCopy(fields()[5].schema(), other.A_3_count_last);
        fieldSetFlags()[5] = true;
      }
      if (isValidValue(fields()[6], other.Postal_Code)) {
        this.Postal_Code = data().deepCopy(fields()[6].schema(), other.Postal_Code);
        fieldSetFlags()[6] = true;
      }
      if (isValidValue(fields()[7], other.cs_k)) {
        this.cs_k = data().deepCopy(fields()[7].schema(), other.cs_k);
        fieldSetFlags()[7] = true;
      }
      if (isValidValue(fields()[8], other.Neighbourhood_Code)) {
        this.Neighbourhood_Code = data().deepCopy(fields()[8].schema(), other.Neighbourhood_Code);
        fieldSetFlags()[8] = true;
      }
    }

    /** Gets the value of the 'id' field */
    public java.lang.Integer getId() {
      return id;
    }
    
    /** Sets the value of the 'id' field */
    public com.example.pipeline_dt_model_final_com.datafellas.g3nerator.modeloutput_0.server.ServiceRequest.Builder setId(int value) {
      validate(fields()[0], value);
      this.id = value;
      fieldSetFlags()[0] = true;
      return this; 
    }
    
    /** Checks whether the 'id' field has been set */
    public boolean hasId() {
      return fieldSetFlags()[0];
    }
    
    /** Clears the value of the 'id' field */
    public com.example.pipeline_dt_model_final_com.datafellas.g3nerator.modeloutput_0.server.ServiceRequest.Builder clearId() {
      fieldSetFlags()[0] = false;
      return this;
    }

    /** Gets the value of the 'any_churn_target' field */
    public java.lang.Boolean getAnyChurnTarget() {
      return any_churn_target;
    }
    
    /** Sets the value of the 'any_churn_target' field */
    public com.example.pipeline_dt_model_final_com.datafellas.g3nerator.modeloutput_0.server.ServiceRequest.Builder setAnyChurnTarget(boolean value) {
      validate(fields()[1], value);
      this.any_churn_target = value;
      fieldSetFlags()[1] = true;
      return this; 
    }
    
    /** Checks whether the 'any_churn_target' field has been set */
    public boolean hasAnyChurnTarget() {
      return fieldSetFlags()[1];
    }
    
    /** Clears the value of the 'any_churn_target' field */
    public com.example.pipeline_dt_model_final_com.datafellas.g3nerator.modeloutput_0.server.ServiceRequest.Builder clearAnyChurnTarget() {
      fieldSetFlags()[1] = false;
      return this;
    }

    /** Gets the value of the 'full_churn_target' field */
    public java.lang.Boolean getFullChurnTarget() {
      return full_churn_target;
    }
    
    /** Sets the value of the 'full_churn_target' field */
    public com.example.pipeline_dt_model_final_com.datafellas.g3nerator.modeloutput_0.server.ServiceRequest.Builder setFullChurnTarget(boolean value) {
      validate(fields()[2], value);
      this.full_churn_target = value;
      fieldSetFlags()[2] = true;
      return this; 
    }
    
    /** Checks whether the 'full_churn_target' field has been set */
    public boolean hasFullChurnTarget() {
      return fieldSetFlags()[2];
    }
    
    /** Clears the value of the 'full_churn_target' field */
    public com.example.pipeline_dt_model_final_com.datafellas.g3nerator.modeloutput_0.server.ServiceRequest.Builder clearFullChurnTarget() {
      fieldSetFlags()[2] = false;
      return this;
    }

    /** Gets the value of the 'A_1_count_last' field */
    public java.lang.Integer getA1CountLast() {
      return A_1_count_last;
    }
    
    /** Sets the value of the 'A_1_count_last' field */
    public com.example.pipeline_dt_model_final_com.datafellas.g3nerator.modeloutput_0.server.ServiceRequest.Builder setA1CountLast(int value) {
      validate(fields()[3], value);
      this.A_1_count_last = value;
      fieldSetFlags()[3] = true;
      return this; 
    }
    
    /** Checks whether the 'A_1_count_last' field has been set */
    public boolean hasA1CountLast() {
      return fieldSetFlags()[3];
    }
    
    /** Clears the value of the 'A_1_count_last' field */
    public com.example.pipeline_dt_model_final_com.datafellas.g3nerator.modeloutput_0.server.ServiceRequest.Builder clearA1CountLast() {
      fieldSetFlags()[3] = false;
      return this;
    }

    /** Gets the value of the 'A_2_count_last' field */
    public java.lang.Integer getA2CountLast() {
      return A_2_count_last;
    }
    
    /** Sets the value of the 'A_2_count_last' field */
    public com.example.pipeline_dt_model_final_com.datafellas.g3nerator.modeloutput_0.server.ServiceRequest.Builder setA2CountLast(int value) {
      validate(fields()[4], value);
      this.A_2_count_last = value;
      fieldSetFlags()[4] = true;
      return this; 
    }
    
    /** Checks whether the 'A_2_count_last' field has been set */
    public boolean hasA2CountLast() {
      return fieldSetFlags()[4];
    }
    
    /** Clears the value of the 'A_2_count_last' field */
    public com.example.pipeline_dt_model_final_com.datafellas.g3nerator.modeloutput_0.server.ServiceRequest.Builder clearA2CountLast() {
      fieldSetFlags()[4] = false;
      return this;
    }

    /** Gets the value of the 'A_3_count_last' field */
    public java.lang.Integer getA3CountLast() {
      return A_3_count_last;
    }
    
    /** Sets the value of the 'A_3_count_last' field */
    public com.example.pipeline_dt_model_final_com.datafellas.g3nerator.modeloutput_0.server.ServiceRequest.Builder setA3CountLast(int value) {
      validate(fields()[5], value);
      this.A_3_count_last = value;
      fieldSetFlags()[5] = true;
      return this; 
    }
    
    /** Checks whether the 'A_3_count_last' field has been set */
    public boolean hasA3CountLast() {
      return fieldSetFlags()[5];
    }
    
    /** Clears the value of the 'A_3_count_last' field */
    public com.example.pipeline_dt_model_final_com.datafellas.g3nerator.modeloutput_0.server.ServiceRequest.Builder clearA3CountLast() {
      fieldSetFlags()[5] = false;
      return this;
    }

    /** Gets the value of the 'Postal_Code' field */
    public java.lang.String getPostalCode() {
      return Postal_Code;
    }
    
    /** Sets the value of the 'Postal_Code' field */
    public com.example.pipeline_dt_model_final_com.datafellas.g3nerator.modeloutput_0.server.ServiceRequest.Builder setPostalCode(java.lang.String value) {
      validate(fields()[6], value);
      this.Postal_Code = value;
      fieldSetFlags()[6] = true;
      return this; 
    }
    
    /** Checks whether the 'Postal_Code' field has been set */
    public boolean hasPostalCode() {
      return fieldSetFlags()[6];
    }
    
    /** Clears the value of the 'Postal_Code' field */
    public com.example.pipeline_dt_model_final_com.datafellas.g3nerator.modeloutput_0.server.ServiceRequest.Builder clearPostalCode() {
      Postal_Code = null;
      fieldSetFlags()[6] = false;
      return this;
    }

    /** Gets the value of the 'cs_k' field */
    public java.lang.String getCsK() {
      return cs_k;
    }
    
    /** Sets the value of the 'cs_k' field */
    public com.example.pipeline_dt_model_final_com.datafellas.g3nerator.modeloutput_0.server.ServiceRequest.Builder setCsK(java.lang.String value) {
      validate(fields()[7], value);
      this.cs_k = value;
      fieldSetFlags()[7] = true;
      return this; 
    }
    
    /** Checks whether the 'cs_k' field has been set */
    public boolean hasCsK() {
      return fieldSetFlags()[7];
    }
    
    /** Clears the value of the 'cs_k' field */
    public com.example.pipeline_dt_model_final_com.datafellas.g3nerator.modeloutput_0.server.ServiceRequest.Builder clearCsK() {
      cs_k = null;
      fieldSetFlags()[7] = false;
      return this;
    }

    /** Gets the value of the 'Neighbourhood_Code' field */
    public java.lang.String getNeighbourhoodCode() {
      return Neighbourhood_Code;
    }
    
    /** Sets the value of the 'Neighbourhood_Code' field */
    public com.example.pipeline_dt_model_final_com.datafellas.g3nerator.modeloutput_0.server.ServiceRequest.Builder setNeighbourhoodCode(java.lang.String value) {
      validate(fields()[8], value);
      this.Neighbourhood_Code = value;
      fieldSetFlags()[8] = true;
      return this; 
    }
    
    /** Checks whether the 'Neighbourhood_Code' field has been set */
    public boolean hasNeighbourhoodCode() {
      return fieldSetFlags()[8];
    }
    
    /** Clears the value of the 'Neighbourhood_Code' field */
    public com.example.pipeline_dt_model_final_com.datafellas.g3nerator.modeloutput_0.server.ServiceRequest.Builder clearNeighbourhoodCode() {
      Neighbourhood_Code = null;
      fieldSetFlags()[8] = false;
      return this;
    }

    @Override
    public ServiceRequest build() {
      try {
        ServiceRequest record = new ServiceRequest();
        record.id = fieldSetFlags()[0] ? this.id : (java.lang.Integer) defaultValue(fields()[0]);
        record.any_churn_target = fieldSetFlags()[1] ? this.any_churn_target : (java.lang.Boolean) defaultValue(fields()[1]);
        record.full_churn_target = fieldSetFlags()[2] ? this.full_churn_target : (java.lang.Boolean) defaultValue(fields()[2]);
        record.A_1_count_last = fieldSetFlags()[3] ? this.A_1_count_last : (java.lang.Integer) defaultValue(fields()[3]);
        record.A_2_count_last = fieldSetFlags()[4] ? this.A_2_count_last : (java.lang.Integer) defaultValue(fields()[4]);
        record.A_3_count_last = fieldSetFlags()[5] ? this.A_3_count_last : (java.lang.Integer) defaultValue(fields()[5]);
        record.Postal_Code = fieldSetFlags()[6] ? this.Postal_Code : (java.lang.String) defaultValue(fields()[6]);
        record.cs_k = fieldSetFlags()[7] ? this.cs_k : (java.lang.String) defaultValue(fields()[7]);
        record.Neighbourhood_Code = fieldSetFlags()[8] ? this.Neighbourhood_Code : (java.lang.String) defaultValue(fields()[8]);
        return record;
      } catch (Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }
}
