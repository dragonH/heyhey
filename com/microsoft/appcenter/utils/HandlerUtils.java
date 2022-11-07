package com.microsoft.appcenter.utils;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.VisibleForTesting;

public class HandlerUtils {
  @VisibleForTesting
  static final Handler sMainHandler = new Handler(Looper.getMainLooper());
  
  public static Handler getMainHandler() {
    return sMainHandler;
  }
  
  public static void runOnUiThread(Runnable paramRunnable) {
    Thread thread = Thread.currentThread();
    Handler handler = sMainHandler;
    if (thread == handler.getLooper().getThread()) {
      paramRunnable.run();
    } else {
      handler.post(paramRunnable);
    } 
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/microsoft/appcenter/utils/HandlerUtils.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */