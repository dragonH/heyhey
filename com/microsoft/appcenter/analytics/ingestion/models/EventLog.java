package com.microsoft.appcenter.analytics.ingestion.models;

import com.microsoft.appcenter.ingestion.models.LogWithProperties;
import java.util.UUID;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

public class EventLog extends LogWithProperties {
  public static final String TYPE = "event";
  
  private UUID id;
  
  private String name;
  
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
    if ((uUID != null) ? !uUID.equals(((EventLog)paramObject).id) : (((EventLog)paramObject).id != null))
      return false; 
    String str = this.name;
    paramObject = ((EventLog)paramObject).name;
    if (str != null) {
      bool = str.equals(paramObject);
    } else if (paramObject != null) {
      bool = false;
    } 
    return bool;
  }
  
  public UUID getId() {
    return this.id;
  }
  
  public String getName() {
    return this.name;
  }
  
  public String getType() {
    return "event";
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
    String str = this.name;
    if (str != null)
      j = str.hashCode(); 
    return (i * 31 + b) * 31 + j;
  }
  
  public void read(JSONObject paramJSONObject) throws JSONException {
    super.read(paramJSONObject);
    setId(UUID.fromString(paramJSONObject.getString("id")));
    setName(paramJSONObject.getString("name"));
  }
  
  public void setId(UUID paramUUID) {
    this.id = paramUUID;
  }
  
  public void setName(String paramString) {
    this.name = paramString;
  }
  
  public void write(JSONStringer paramJSONStringer) throws JSONException {
    super.write(paramJSONStringer);
    paramJSONStringer.key("id").value(getId());
    paramJSONStringer.key("name").value(getName());
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/microsoft/appcenter/analytics/ingestion/models/EventLog.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */