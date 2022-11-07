package com.microsoft.appcenter.utils;

import android.support.annotation.IntRange;
import android.util.Log;

public class AppCenterLog {
  public static final String LOG_TAG = "AppCenter";
  
  public static final int NONE = 8;
  
  private static int sLogLevel = 7;
  
  public static void debug(String paramString1, String paramString2) {
    if (sLogLevel <= 3)
      Log.d(paramString1, paramString2); 
  }
  
  public static void debug(String paramString1, String paramString2, Throwable paramThrowable) {
    if (sLogLevel <= 3)
      Log.d(paramString1, paramString2, paramThrowable); 
  }
  
  public static void error(String paramString1, String paramString2) {
    if (sLogLevel <= 6)
      Log.e(paramString1, paramString2); 
  }
  
  public static void error(String paramString1, String paramString2, Throwable paramThrowable) {
    if (sLogLevel <= 6)
      Log.e(paramString1, paramString2, paramThrowable); 
  }
  
  @IntRange(from = 2L, to = 8L)
  public static int getLogLevel() {
    return sLogLevel;
  }
  
  public static void info(String paramString1, String paramString2) {
    if (sLogLevel <= 4)
      Log.i(paramString1, paramString2); 
  }
  
  public static void info(String paramString1, String paramString2, Throwable paramThrowable) {
    if (sLogLevel <= 4)
      Log.i(paramString1, paramString2, paramThrowable); 
  }
  
  public static void logAssert(String paramString1, String paramString2) {
    if (sLogLevel <= 7)
      Log.println(7, paramString1, paramString2); 
  }
  
  public static void logAssert(String paramString1, String paramString2, Throwable paramThrowable) {
    if (sLogLevel <= 7) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append(paramString2);
      stringBuilder.append("\n");
      stringBuilder.append(Log.getStackTraceString(paramThrowable));
      Log.println(7, paramString1, stringBuilder.toString());
    } 
  }
  
  public static void setLogLevel(@IntRange(from = 2L, to = 8L) int paramInt) {
    sLogLevel = paramInt;
  }
  
  public static void verbose(String paramString1, String paramString2) {
    if (sLogLevel <= 2)
      Log.v(paramString1, paramString2); 
  }
  
  public static void verbose(String paramString1, String paramString2, Throwable paramThrowable) {
    if (sLogLevel <= 2)
      Log.v(paramString1, paramString2, paramThrowable); 
  }
  
  public static void warn(String paramString1, String paramString2) {
    if (sLogLevel <= 5)
      Log.w(paramString1, paramString2); 
  }
  
  public static void warn(String paramString1, String paramString2, Throwable paramThrowable) {
    if (sLogLevel <= 5)
      Log.w(paramString1, paramString2, paramThrowable); 
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/microsoft/appcenter/utils/AppCenterLog.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */