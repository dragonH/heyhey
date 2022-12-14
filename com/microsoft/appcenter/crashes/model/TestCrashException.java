package com.microsoft.appcenter.crashes.model;

import android.support.annotation.VisibleForTesting;

public class TestCrashException extends RuntimeException {
  @VisibleForTesting
  static final String CRASH_MESSAGE = "Test crash exception generated by SDK";
  
  public TestCrashException() {
    super("Test crash exception generated by SDK");
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/microsoft/appcenter/crashes/model/TestCrashException.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */