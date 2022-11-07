package com.microsoft.appcenter.crashes.model;

import com.microsoft.appcenter.ingestion.models.Device;
import java.util.Date;

public class ErrorReport {
  private Date appErrorTime;
  
  private Date appStartTime;
  
  private Device device;
  
  private String id;
  
  private String threadName;
  
  private Throwable throwable;
  
  public Date getAppErrorTime() {
    return this.appErrorTime;
  }
  
  public Date getAppStartTime() {
    return this.appStartTime;
  }
  
  public Device getDevice() {
    return this.device;
  }
  
  public String getId() {
    return this.id;
  }
  
  public String getThreadName() {
    return this.threadName;
  }
  
  public Throwable getThrowable() {
    return this.throwable;
  }
  
  public void setAppErrorTime(Date paramDate) {
    this.appErrorTime = paramDate;
  }
  
  public void setAppStartTime(Date paramDate) {
    this.appStartTime = paramDate;
  }
  
  public void setDevice(Device paramDevice) {
    this.device = paramDevice;
  }
  
  public void setId(String paramString) {
    this.id = paramString;
  }
  
  public void setThreadName(String paramString) {
    this.threadName = paramString;
  }
  
  public void setThrowable(Throwable paramThrowable) {
    this.throwable = paramThrowable;
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/microsoft/appcenter/crashes/model/ErrorReport.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */