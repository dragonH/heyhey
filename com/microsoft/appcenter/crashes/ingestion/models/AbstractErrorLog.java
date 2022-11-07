package com.microsoft.appcenter.crashes.ingestion.models;

import com.microsoft.appcenter.ingestion.models.AbstractLog;
import com.microsoft.appcenter.ingestion.models.json.JSONDateUtils;
import com.microsoft.appcenter.ingestion.models.json.JSONUtils;
import java.util.Date;
import java.util.UUID;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

public abstract class AbstractErrorLog extends AbstractLog {
  private static final String APP_LAUNCH_TIMESTAMP = "appLaunchTimestamp";
  
  private static final String ARCHITECTURE = "architecture";
  
  private static final String ERROR_THREAD_ID = "errorThreadId";
  
  private static final String ERROR_THREAD_NAME = "errorThreadName";
  
  private static final String FATAL = "fatal";
  
  private static final String PARENT_PROCESS_ID = "parentProcessId";
  
  private static final String PARENT_PROCESS_NAME = "parentProcessName";
  
  private static final String PROCESS_ID = "processId";
  
  private static final String PROCESS_NAME = "processName";
  
  private Date appLaunchTimestamp;
  
  private String architecture;
  
  private Long errorThreadId;
  
  private String errorThreadName;
  
  private Boolean fatal;
  
  private UUID id;
  
  private Integer parentProcessId;
  
  private String parentProcessName;
  
  private Integer processId;
  
  private String processName;
  
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
    if ((uUID != null) ? !uUID.equals(((AbstractErrorLog)paramObject).id) : (((AbstractErrorLog)paramObject).id != null))
      return false; 
    Integer integer2 = this.processId;
    if ((integer2 != null) ? !integer2.equals(((AbstractErrorLog)paramObject).processId) : (((AbstractErrorLog)paramObject).processId != null))
      return false; 
    String str4 = this.processName;
    if ((str4 != null) ? !str4.equals(((AbstractErrorLog)paramObject).processName) : (((AbstractErrorLog)paramObject).processName != null))
      return false; 
    Integer integer1 = this.parentProcessId;
    if ((integer1 != null) ? !integer1.equals(((AbstractErrorLog)paramObject).parentProcessId) : (((AbstractErrorLog)paramObject).parentProcessId != null))
      return false; 
    String str3 = this.parentProcessName;
    if ((str3 != null) ? !str3.equals(((AbstractErrorLog)paramObject).parentProcessName) : (((AbstractErrorLog)paramObject).parentProcessName != null))
      return false; 
    Long long_ = this.errorThreadId;
    if ((long_ != null) ? !long_.equals(((AbstractErrorLog)paramObject).errorThreadId) : (((AbstractErrorLog)paramObject).errorThreadId != null))
      return false; 
    String str2 = this.errorThreadName;
    if ((str2 != null) ? !str2.equals(((AbstractErrorLog)paramObject).errorThreadName) : (((AbstractErrorLog)paramObject).errorThreadName != null))
      return false; 
    Boolean bool1 = this.fatal;
    if ((bool1 != null) ? !bool1.equals(((AbstractErrorLog)paramObject).fatal) : (((AbstractErrorLog)paramObject).fatal != null))
      return false; 
    Date date = this.appLaunchTimestamp;
    if ((date != null) ? !date.equals(((AbstractErrorLog)paramObject).appLaunchTimestamp) : (((AbstractErrorLog)paramObject).appLaunchTimestamp != null))
      return false; 
    String str1 = this.architecture;
    paramObject = ((AbstractErrorLog)paramObject).architecture;
    if (str1 != null) {
      bool = str1.equals(paramObject);
    } else if (paramObject != null) {
      bool = false;
    } 
    return bool;
  }
  
  public Date getAppLaunchTimestamp() {
    return this.appLaunchTimestamp;
  }
  
  public String getArchitecture() {
    return this.architecture;
  }
  
  public Long getErrorThreadId() {
    return this.errorThreadId;
  }
  
  public String getErrorThreadName() {
    return this.errorThreadName;
  }
  
  public Boolean getFatal() {
    return this.fatal;
  }
  
  public UUID getId() {
    return this.id;
  }
  
  public Integer getParentProcessId() {
    return this.parentProcessId;
  }
  
  public String getParentProcessName() {
    return this.parentProcessName;
  }
  
  public Integer getProcessId() {
    return this.processId;
  }
  
  public String getProcessName() {
    return this.processName;
  }
  
  public int hashCode() {
    byte b1;
    byte b2;
    byte b3;
    byte b4;
    byte b5;
    byte b6;
    byte b7;
    byte b8;
    byte b9;
    int i = super.hashCode();
    UUID uUID = this.id;
    int j = 0;
    if (uUID != null) {
      b1 = uUID.hashCode();
    } else {
      b1 = 0;
    } 
    Integer integer2 = this.processId;
    if (integer2 != null) {
      b2 = integer2.hashCode();
    } else {
      b2 = 0;
    } 
    String str4 = this.processName;
    if (str4 != null) {
      b3 = str4.hashCode();
    } else {
      b3 = 0;
    } 
    Integer integer1 = this.parentProcessId;
    if (integer1 != null) {
      b4 = integer1.hashCode();
    } else {
      b4 = 0;
    } 
    String str3 = this.parentProcessName;
    if (str3 != null) {
      b5 = str3.hashCode();
    } else {
      b5 = 0;
    } 
    Long long_ = this.errorThreadId;
    if (long_ != null) {
      b6 = long_.hashCode();
    } else {
      b6 = 0;
    } 
    String str2 = this.errorThreadName;
    if (str2 != null) {
      b7 = str2.hashCode();
    } else {
      b7 = 0;
    } 
    Boolean bool = this.fatal;
    if (bool != null) {
      b8 = bool.hashCode();
    } else {
      b8 = 0;
    } 
    Date date = this.appLaunchTimestamp;
    if (date != null) {
      b9 = date.hashCode();
    } else {
      b9 = 0;
    } 
    String str1 = this.architecture;
    if (str1 != null)
      j = str1.hashCode(); 
    return (((((((((i * 31 + b1) * 31 + b2) * 31 + b3) * 31 + b4) * 31 + b5) * 31 + b6) * 31 + b7) * 31 + b8) * 31 + b9) * 31 + j;
  }
  
  public void read(JSONObject paramJSONObject) throws JSONException {
    super.read(paramJSONObject);
    setId(UUID.fromString(paramJSONObject.getString("id")));
    setProcessId(JSONUtils.readInteger(paramJSONObject, "processId"));
    setProcessName(paramJSONObject.optString("processName", null));
    setParentProcessId(JSONUtils.readInteger(paramJSONObject, "parentProcessId"));
    setParentProcessName(paramJSONObject.optString("parentProcessName", null));
    setErrorThreadId(JSONUtils.readLong(paramJSONObject, "errorThreadId"));
    setErrorThreadName(paramJSONObject.optString("errorThreadName", null));
    setFatal(JSONUtils.readBoolean(paramJSONObject, "fatal"));
    setAppLaunchTimestamp(JSONDateUtils.toDate(paramJSONObject.getString("appLaunchTimestamp")));
    setArchitecture(paramJSONObject.optString("architecture", null));
  }
  
  public void setAppLaunchTimestamp(Date paramDate) {
    this.appLaunchTimestamp = paramDate;
  }
  
  public void setArchitecture(String paramString) {
    this.architecture = paramString;
  }
  
  public void setErrorThreadId(Long paramLong) {
    this.errorThreadId = paramLong;
  }
  
  public void setErrorThreadName(String paramString) {
    this.errorThreadName = paramString;
  }
  
  public void setFatal(Boolean paramBoolean) {
    this.fatal = paramBoolean;
  }
  
  public void setId(UUID paramUUID) {
    this.id = paramUUID;
  }
  
  public void setParentProcessId(Integer paramInteger) {
    this.parentProcessId = paramInteger;
  }
  
  public void setParentProcessName(String paramString) {
    this.parentProcessName = paramString;
  }
  
  public void setProcessId(Integer paramInteger) {
    this.processId = paramInteger;
  }
  
  public void setProcessName(String paramString) {
    this.processName = paramString;
  }
  
  public void write(JSONStringer paramJSONStringer) throws JSONException {
    super.write(paramJSONStringer);
    JSONUtils.write(paramJSONStringer, "id", getId());
    JSONUtils.write(paramJSONStringer, "processId", getProcessId());
    JSONUtils.write(paramJSONStringer, "processName", getProcessName());
    JSONUtils.write(paramJSONStringer, "parentProcessId", getParentProcessId());
    JSONUtils.write(paramJSONStringer, "parentProcessName", getParentProcessName());
    JSONUtils.write(paramJSONStringer, "errorThreadId", getErrorThreadId());
    JSONUtils.write(paramJSONStringer, "errorThreadName", getErrorThreadName());
    JSONUtils.write(paramJSONStringer, "fatal", getFatal());
    JSONUtils.write(paramJSONStringer, "appLaunchTimestamp", JSONDateUtils.toString(getAppLaunchTimestamp()));
    JSONUtils.write(paramJSONStringer, "architecture", getArchitecture());
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/microsoft/appcenter/crashes/ingestion/models/AbstractErrorLog.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */