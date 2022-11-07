package com.microsoft.appcenter.ingestion.models;

import java.util.List;

public class LogContainer {
  private List<Log> logs;
  
  public boolean equals(Object<Log> paramObject) {
    boolean bool = true;
    if (this == paramObject)
      return true; 
    if (paramObject == null || getClass() != paramObject.getClass())
      return false; 
    LogContainer logContainer = (LogContainer)paramObject;
    paramObject = (Object<Log>)this.logs;
    List<Log> list = logContainer.logs;
    if (paramObject != null) {
      bool = paramObject.equals(list);
    } else if (list != null) {
      bool = false;
    } 
    return bool;
  }
  
  public List<Log> getLogs() {
    return this.logs;
  }
  
  public int hashCode() {
    boolean bool;
    List<Log> list = this.logs;
    if (list != null) {
      bool = list.hashCode();
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public void setLogs(List<Log> paramList) {
    this.logs = paramList;
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/microsoft/appcenter/ingestion/models/LogContainer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */