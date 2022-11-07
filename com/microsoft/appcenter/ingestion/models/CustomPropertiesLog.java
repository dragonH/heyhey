package com.microsoft.appcenter.ingestion.models;

import com.microsoft.appcenter.ingestion.models.json.JSONDateUtils;
import com.microsoft.appcenter.ingestion.models.json.JSONUtils;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

public class CustomPropertiesLog extends AbstractLog {
  private static final String PROPERTIES = "properties";
  
  private static final String PROPERTY_NAME = "name";
  
  private static final String PROPERTY_TYPE = "type";
  
  private static final String PROPERTY_TYPE_BOOLEAN = "boolean";
  
  private static final String PROPERTY_TYPE_CLEAR = "clear";
  
  private static final String PROPERTY_TYPE_DATETIME = "dateTime";
  
  private static final String PROPERTY_TYPE_NUMBER = "number";
  
  private static final String PROPERTY_TYPE_STRING = "string";
  
  private static final String PROPERTY_VALUE = "value";
  
  public static final String TYPE = "customProperties";
  
  private Map<String, Object> properties;
  
  private static Map<String, Object> readProperties(JSONObject paramJSONObject) throws JSONException {
    JSONArray jSONArray = paramJSONObject.getJSONArray("properties");
    HashMap<Object, Object> hashMap = new HashMap<Object, Object>();
    for (byte b = 0; b < jSONArray.length(); b++) {
      JSONObject jSONObject = jSONArray.getJSONObject(b);
      hashMap.put(jSONObject.getString("name"), readPropertyValue(jSONObject));
    } 
    return (Map)hashMap;
  }
  
  private static Object readPropertyValue(JSONObject paramJSONObject) throws JSONException {
    String str = paramJSONObject.getString("type");
    if (str.equals("clear")) {
      paramJSONObject = null;
    } else {
      Boolean bool;
      if (str.equals("boolean")) {
        bool = Boolean.valueOf(paramJSONObject.getBoolean("value"));
      } else {
        Object object;
        if (str.equals("number")) {
          object = bool.get("value");
          if (!(object instanceof Number))
            throw new JSONException("Invalid value type"); 
        } else if (str.equals("dateTime")) {
          object = JSONDateUtils.toDate(object.getString("value"));
        } else {
          if (str.equals("string"))
            return object.getString("value"); 
          throw new JSONException("Invalid value type");
        } 
      } 
    } 
    return paramJSONObject;
  }
  
  private static void writeProperties(JSONStringer paramJSONStringer, Map<String, Object> paramMap) throws JSONException {
    if (paramMap != null) {
      paramJSONStringer.key("properties").array();
      for (Map.Entry<String, Object> entry : paramMap.entrySet()) {
        paramJSONStringer.object();
        JSONUtils.write(paramJSONStringer, "name", entry.getKey());
        writePropertyValue(paramJSONStringer, entry.getValue());
        paramJSONStringer.endObject();
      } 
      paramJSONStringer.endArray();
      return;
    } 
    JSONException jSONException = new JSONException("Properties cannot be null");
    throw jSONException;
  }
  
  private static void writePropertyValue(JSONStringer paramJSONStringer, Object paramObject) throws JSONException {
    if (paramObject == null) {
      JSONUtils.write(paramJSONStringer, "type", "clear");
    } else if (paramObject instanceof Boolean) {
      JSONUtils.write(paramJSONStringer, "type", "boolean");
      JSONUtils.write(paramJSONStringer, "value", paramObject);
    } else if (paramObject instanceof Number) {
      JSONUtils.write(paramJSONStringer, "type", "number");
      JSONUtils.write(paramJSONStringer, "value", paramObject);
    } else if (paramObject instanceof Date) {
      JSONUtils.write(paramJSONStringer, "type", "dateTime");
      JSONUtils.write(paramJSONStringer, "value", JSONDateUtils.toString((Date)paramObject));
    } else {
      if (paramObject instanceof String) {
        JSONUtils.write(paramJSONStringer, "type", "string");
        JSONUtils.write(paramJSONStringer, "value", paramObject);
        return;
      } 
      throw new JSONException("Invalid value type");
    } 
  }
  
  public boolean equals(Object<String, Object> paramObject) {
    boolean bool = true;
    if (this == paramObject)
      return true; 
    if (paramObject == null || getClass() != paramObject.getClass())
      return false; 
    if (!super.equals(paramObject))
      return false; 
    CustomPropertiesLog customPropertiesLog = (CustomPropertiesLog)paramObject;
    paramObject = (Object<String, Object>)this.properties;
    Map<String, Object> map = customPropertiesLog.properties;
    if (paramObject != null) {
      bool = paramObject.equals(map);
    } else if (map != null) {
      bool = false;
    } 
    return bool;
  }
  
  public Map<String, Object> getProperties() {
    return this.properties;
  }
  
  public String getType() {
    return "customProperties";
  }
  
  public int hashCode() {
    byte b;
    int i = super.hashCode();
    Map<String, Object> map = this.properties;
    if (map != null) {
      b = map.hashCode();
    } else {
      b = 0;
    } 
    return i * 31 + b;
  }
  
  public void read(JSONObject paramJSONObject) throws JSONException {
    super.read(paramJSONObject);
    setProperties(readProperties(paramJSONObject));
  }
  
  public void setProperties(Map<String, Object> paramMap) {
    this.properties = paramMap;
  }
  
  public void write(JSONStringer paramJSONStringer) throws JSONException {
    super.write(paramJSONStringer);
    writeProperties(paramJSONStringer, getProperties());
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/microsoft/appcenter/ingestion/models/CustomPropertiesLog.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */