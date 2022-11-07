package com.microsoft.appcenter.ingestion.models;

import android.support.annotation.VisibleForTesting;
import com.microsoft.appcenter.ingestion.models.json.JSONDateUtils;
import com.microsoft.appcenter.ingestion.models.json.JSONUtils;
import java.util.Date;
import java.util.UUID;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

public abstract class AbstractLog implements Log {
  @VisibleForTesting
  static final String DEVICE = "device";
  
  @VisibleForTesting
  static final String SID = "sid";
  
  @VisibleForTesting
  static final String TIMESTAMP = "timestamp";
  
  private Device device;
  
  private UUID sid;
  
  private Date timestamp;
  
  public boolean equals(Object paramObject) {
    boolean bool = true;
    if (this == paramObject)
      return true; 
    if (paramObject == null || getClass() != paramObject.getClass())
      return false; 
    paramObject = paramObject;
    Date date = this.timestamp;
    if ((date != null) ? !date.equals(((AbstractLog)paramObject).timestamp) : (((AbstractLog)paramObject).timestamp != null))
      return false; 
    UUID uUID = this.sid;
    if ((uUID != null) ? !uUID.equals(((AbstractLog)paramObject).sid) : (((AbstractLog)paramObject).sid != null))
      return false; 
    Device device = this.device;
    paramObject = ((AbstractLog)paramObject).device;
    if (device != null) {
      bool = device.equals(paramObject);
    } else if (paramObject != null) {
      bool = false;
    } 
    return bool;
  }
  
  public Device getDevice() {
    return this.device;
  }
  
  public UUID getSid() {
    return this.sid;
  }
  
  public Date getTimestamp() {
    return this.timestamp;
  }
  
  public int hashCode() {
    byte b1;
    byte b2;
    Date date = this.timestamp;
    int i = 0;
    if (date != null) {
      b1 = date.hashCode();
    } else {
      b1 = 0;
    } 
    UUID uUID = this.sid;
    if (uUID != null) {
      b2 = uUID.hashCode();
    } else {
      b2 = 0;
    } 
    Device device = this.device;
    if (device != null)
      i = device.hashCode(); 
    return (b1 * 31 + b2) * 31 + i;
  }
  
  public void read(JSONObject paramJSONObject) throws JSONException {
    if (paramJSONObject.getString("type").equals(getType())) {
      setTimestamp(JSONDateUtils.toDate(paramJSONObject.getString("timestamp")));
      if (paramJSONObject.has("sid"))
        setSid(UUID.fromString(paramJSONObject.getString("sid"))); 
      if (paramJSONObject.has("device")) {
        Device device = new Device();
        device.read(paramJSONObject.getJSONObject("device"));
        setDevice(device);
      } 
      return;
    } 
    throw new JSONException("Invalid type");
  }
  
  public void setDevice(Device paramDevice) {
    this.device = paramDevice;
  }
  
  public void setSid(UUID paramUUID) {
    this.sid = paramUUID;
  }
  
  public void setTimestamp(Date paramDate) {
    this.timestamp = paramDate;
  }
  
  public void write(JSONStringer paramJSONStringer) throws JSONException {
    JSONUtils.write(paramJSONStringer, "type", getType());
    paramJSONStringer.key("timestamp").value(JSONDateUtils.toString(getTimestamp()));
    JSONUtils.write(paramJSONStringer, "sid", getSid());
    if (getDevice() != null) {
      paramJSONStringer.key("device").object();
      getDevice().write(paramJSONStringer);
      paramJSONStringer.endObject();
    } 
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/microsoft/appcenter/ingestion/models/AbstractLog.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */