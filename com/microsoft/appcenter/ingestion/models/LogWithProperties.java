package com.microsoft.appcenter.ingestion.models;

import com.microsoft.appcenter.ingestion.models.json.JSONUtils;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

public abstract class LogWithProperties extends AbstractLog {
  private static final String PROPERTIES = "properties";
  
  private Map<String, String> properties;
  
  public boolean equals(Object<String, String> paramObject) {
    boolean bool = true;
    if (this == paramObject)
      return true; 
    if (paramObject == null || getClass() != paramObject.getClass())
      return false; 
    if (!super.equals(paramObject))
      return false; 
    LogWithProperties logWithProperties = (LogWithProperties)paramObject;
    paramObject = (Object<String, String>)this.properties;
    Map<String, String> map = logWithProperties.properties;
    if (paramObject != null) {
      bool = paramObject.equals(map);
    } else if (map != null) {
      bool = false;
    } 
    return bool;
  }
  
  public Map<String, String> getProperties() {
    return this.properties;
  }
  
  public int hashCode() {
    byte b;
    int i = super.hashCode();
    Map<String, String> map = this.properties;
    if (map != null) {
      b = map.hashCode();
    } else {
      b = 0;
    } 
    return i * 31 + b;
  }
  
  public void read(JSONObject paramJSONObject) throws JSONException {
    super.read(paramJSONObject);
    setProperties(JSONUtils.readMap(paramJSONObject, "properties"));
  }
  
  public void setProperties(Map<String, String> paramMap) {
    this.properties = paramMap;
  }
  
  public void write(JSONStringer paramJSONStringer) throws JSONException {
    super.write(paramJSONStringer);
    JSONUtils.writeMap(paramJSONStringer, "properties", getProperties());
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/microsoft/appcenter/ingestion/models/LogWithProperties.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */