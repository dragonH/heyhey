package com.microsoft.appcenter.crashes;

import android.support.annotation.VisibleForTesting;
import com.microsoft.appcenter.utils.ShutdownHelper;

class UncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {
  private Thread.UncaughtExceptionHandler mDefaultUncaughtExceptionHandler;
  
  private boolean mIgnoreDefaultExceptionHandler = false;
  
  @VisibleForTesting
  Thread.UncaughtExceptionHandler getDefaultUncaughtExceptionHandler() {
    return this.mDefaultUncaughtExceptionHandler;
  }
  
  void register() {
    if (!this.mIgnoreDefaultExceptionHandler) {
      this.mDefaultUncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
    } else {
      this.mDefaultUncaughtExceptionHandler = null;
    } 
    Thread.setDefaultUncaughtExceptionHandler(this);
  }
  
  @VisibleForTesting
  void setIgnoreDefaultExceptionHandler(boolean paramBoolean) {
    this.mIgnoreDefaultExceptionHandler = paramBoolean;
    if (paramBoolean)
      this.mDefaultUncaughtExceptionHandler = null; 
  }
  
  public void uncaughtException(Thread paramThread, Throwable paramThrowable) {
    Crashes.getInstance().saveUncaughtException(paramThread, paramThrowable);
    Thread.UncaughtExceptionHandler uncaughtExceptionHandler = this.mDefaultUncaughtExceptionHandler;
    if (uncaughtExceptionHandler != null) {
      uncaughtExceptionHandler.uncaughtException(paramThread, paramThrowable);
    } else {
      ShutdownHelper.shutdown(10);
    } 
  }
  
  void unregister() {
    Thread.setDefaultUncaughtExceptionHandler(this.mDefaultUncaughtExceptionHandler);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/microsoft/appcenter/crashes/UncaughtExceptionHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */