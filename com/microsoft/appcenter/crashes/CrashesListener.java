package com.microsoft.appcenter.crashes;

import android.support.annotation.UiThread;
import android.support.annotation.WorkerThread;
import com.microsoft.appcenter.crashes.ingestion.models.ErrorAttachmentLog;
import com.microsoft.appcenter.crashes.model.ErrorReport;

public interface CrashesListener {
  @WorkerThread
  Iterable<ErrorAttachmentLog> getErrorAttachments(ErrorReport paramErrorReport);
  
  @UiThread
  void onBeforeSending(ErrorReport paramErrorReport);
  
  @UiThread
  void onSendingFailed(ErrorReport paramErrorReport, Exception paramException);
  
  @UiThread
  void onSendingSucceeded(ErrorReport paramErrorReport);
  
  @UiThread
  boolean shouldAwaitUserConfirmation();
  
  @WorkerThread
  boolean shouldProcess(ErrorReport paramErrorReport);
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/microsoft/appcenter/crashes/CrashesListener.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */