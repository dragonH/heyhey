package com.microsoft.appcenter.crashes;

import com.microsoft.appcenter.crashes.ingestion.models.ErrorAttachmentLog;
import com.microsoft.appcenter.crashes.model.ErrorReport;

public abstract class AbstractCrashesListener implements CrashesListener {
  public Iterable<ErrorAttachmentLog> getErrorAttachments(ErrorReport paramErrorReport) {
    return null;
  }
  
  public void onBeforeSending(ErrorReport paramErrorReport) {}
  
  public void onSendingFailed(ErrorReport paramErrorReport, Exception paramException) {}
  
  public void onSendingSucceeded(ErrorReport paramErrorReport) {}
  
  public boolean shouldAwaitUserConfirmation() {
    return false;
  }
  
  public boolean shouldProcess(ErrorReport paramErrorReport) {
    return true;
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/microsoft/appcenter/crashes/AbstractCrashesListener.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */