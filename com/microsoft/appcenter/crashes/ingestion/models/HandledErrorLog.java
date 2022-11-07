package com.microsoft.appcenter.crashes.ingestion.models;

import com.microsoft.appcenter.ingestion.models.AbstractLog;
import java.util.UUID;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

public class HandledErrorLog extends AbstractLog {
  private static final String EXCEPTION = "exception";
  
  public static final String TYPE = "handledError";
  
  private Exception exception;
  
  private UUID id;
  
  public boolean equals(Object paramObject) {
    boolean bool = true;
    if (this == paramObject)
      return true; 
    if (paramObject == null || getClass() != paramObject.getClass())
      return false; 
    if (!super.equals(paramObject))
      return false; 
    paramObject = paramObject;
    UUID uUID = this.id;
    if ((uUID != null) ? !uUID.equals(((HandledErrorLog)paramObject).id) : (((HandledErrorLog)paramObject).id != null))
      return false; 
    Exception exception = this.exception;
    paramObject = ((HandledErrorLog)paramObject).exception;
    if (exception != null) {
      bool = exception.equals(paramObject);
    } else if (paramObject != null) {
      bool = false;
    } 
    return bool;
  }
  
  public Exception getException() {
    return this.exception;
  }
  
  public UUID getId() {
    return this.id;
  }
  
  public String getType() {
    return "handledError";
  }
  
  public int hashCode() {
    byte b;
    int i = super.hashCode();
    UUID uUID = this.id;
    int j = 0;
    if (uUID != null) {
      b = uUID.hashCode();
    } else {
      b = 0;
    } 
    Exception exception = this.exception;
    if (exception != null)
      j = exception.hashCode(); 
    return (i * 31 + b) * 31 + j;
  }
  
  public void read(JSONObject paramJSONObject) throws JSONException {
    super.read(paramJSONObject);
    setId(UUID.fromString(paramJSONObject.getString("id")));
    if (paramJSONObject.has("exception")) {
      JSONObject jSONObject = paramJSONObject.getJSONObject("exception");
      Exception exception = new Exception();
      exception.read(jSONObject);
      setException(exception);
    } 
  }
  
  public void setException(Exception paramException) {
    this.exception = paramException;
  }
  
  public void setId(UUID paramUUID) {
    this.id = paramUUID;
  }
  
  public void write(JSONStringer paramJSONStringer) throws JSONException {
    super.write(paramJSONStringer);
    paramJSONStringer.key("id").value(getId());
    if (getException() != null) {
      paramJSONStringer.key("exception").object();
      this.exception.write(paramJSONStringer);
      paramJSONStringer.endObject();
    } 
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/microsoft/appcenter/crashes/ingestion/models/HandledErrorLog.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */