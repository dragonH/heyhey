package com.microsoft.appcenter;

import android.content.Context;
import com.microsoft.appcenter.utils.AppCenterLog;

public class Constants {
  public static boolean APPLICATION_DEBUGGABLE = false;
  
  static final int DEFAULT_TRIGGER_COUNT = 50;
  
  public static final int DEFAULT_TRIGGER_INTERVAL = 3000;
  
  static final int DEFAULT_TRIGGER_MAX_PARALLEL_REQUESTS = 3;
  
  public static String FILES_PATH;
  
  private static void loadFilesPath(Context paramContext) {
    if (paramContext != null)
      try {
        FILES_PATH = paramContext.getFilesDir().getAbsolutePath();
      } catch (Exception exception) {
        AppCenterLog.error("AppCenter", "Exception thrown when accessing the application filesystem", exception);
      }  
  }
  
  public static void loadFromContext(Context paramContext) {
    loadFilesPath(paramContext);
    setDebuggableFlag(paramContext);
  }
  
  private static void setDebuggableFlag(Context paramContext) {
    if (paramContext != null && paramContext.getApplicationInfo() != null) {
      boolean bool;
      if (((paramContext.getApplicationInfo()).flags & 0x2) > 0) {
        bool = true;
      } else {
        bool = false;
      } 
      APPLICATION_DEBUGGABLE = bool;
    } 
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/microsoft/appcenter/Constants.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */