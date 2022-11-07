package com.microsoft.appcenter.ingestion.models;

import com.microsoft.appcenter.ingestion.models.json.JSONUtils;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

public class StartServiceLog extends AbstractLog {
  private static final String SERVICES = "services";
  
  public static final String TYPE = "startService";
  
  private List<String> services;
  
  public boolean equals(Object<String> paramObject) {
    boolean bool = true;
    if (this == paramObject)
      return true; 
    if (paramObject == null || getClass() != paramObject.getClass())
      return false; 
    if (!super.equals(paramObject))
      return false; 
    StartServiceLog startServiceLog = (StartServiceLog)paramObject;
    paramObject = (Object<String>)this.services;
    List<String> list = startServiceLog.services;
    if (paramObject != null) {
      bool = paramObject.equals(list);
    } else if (list != null) {
      bool = false;
    } 
    return bool;
  }
  
  public List<String> getServices() {
    return this.services;
  }
  
  public String getType() {
    return "startService";
  }
  
  public int hashCode() {
    byte b;
    int i = super.hashCode();
    List<String> list = this.services;
    if (list != null) {
      b = list.hashCode();
    } else {
      b = 0;
    } 
    return i * 31 + b;
  }
  
  public void read(JSONObject paramJSONObject) throws JSONException {
    super.read(paramJSONObject);
    setServices(JSONUtils.readStringArray(paramJSONObject, "services"));
  }
  
  public void setServices(List<String> paramList) {
    this.services = paramList;
  }
  
  public void write(JSONStringer paramJSONStringer) throws JSONException {
    super.write(paramJSONStringer);
    JSONUtils.writeStringArray(paramJSONStringer, "services", getServices());
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/microsoft/appcenter/ingestion/models/StartServiceLog.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */