package com.microsoft.appcenter.utils;

import android.os.Process;

public class ShutdownHelper {
  public static void shutdown(int paramInt) {
    Process.killProcess(Process.myPid());
    System.exit(paramInt);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/microsoft/appcenter/utils/ShutdownHelper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */