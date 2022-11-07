package com.microsoft.appcenter.analytics.ingestion.models;

import com.microsoft.appcenter.ingestion.models.LogWithProperties;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

public class PageLog extends LogWithProperties {
  public static final String TYPE = "page";
  
  private String name;
  
  public boolean equals(Object paramObject) {
    boolean bool = true;
    if (this == paramObject)
      return true; 
    if (paramObject == null || getClass() != paramObject.getClass())
      return false; 
    if (!super.equals(paramObject))
      return false; 
    PageLog pageLog = (PageLog)paramObject;
    paramObject = this.name;
    String str = pageLog.name;
    if (paramObject != null) {
      bool = paramObject.equals(str);
    } else if (str != null) {
      bool = false;
    } 
    return bool;
  }
  
  public String getName() {
    return this.name;
  }
  
  public String getType() {
    return "page";
  }
  
  public int hashCode() {
    byte b;
    int i = super.hashCode();
    String str = this.name;
    if (str != null) {
      b = str.hashCode();
    } else {
      b = 0;
    } 
    return i * 31 + b;
  }
  
  public void read(JSONObject paramJSONObject) throws JSONException {
    super.read(paramJSONObject);
    setName(paramJSONObject.getString("name"));
  }
  
  public void setName(String paramString) {
    this.name = paramString;
  }
  
  public void write(JSONStringer paramJSONStringer) throws JSONException {
    super.write(paramJSONStringer);
    paramJSONStringer.key("name").value(getName());
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/microsoft/appcenter/analytics/ingestion/models/PageLog.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */