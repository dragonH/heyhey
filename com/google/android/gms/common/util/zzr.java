package com.google.android.gms.common.util;

import android.os.Process;
import android.os.StrictMode;
import java.io.BufferedReader;
import java.io.Closeable;
import java.io.FileReader;
import java.io.IOException;

public final class zzr {
  private static String zzfzf;
  
  private static final int zzfzg = Process.myPid();
  
  public static String zzalk() {
    if (zzfzf == null)
      zzfzf = zzch(zzfzg); 
    return zzfzf;
  }
  
  private static String zzch(int paramInt) {
    IOException iOException2;
    null = null;
    String str = null;
    if (paramInt <= 0)
      return null; 
    try {
      StrictMode.ThreadPolicy threadPolicy = StrictMode.allowThreadDiskReads();
      try {
        BufferedReader bufferedReader = new BufferedReader();
        FileReader fileReader = new FileReader();
        StringBuilder stringBuilder = new StringBuilder();
        this(25);
        stringBuilder.append("/proc/");
        stringBuilder.append(paramInt);
        stringBuilder.append("/cmdline");
        this(stringBuilder.toString());
        this(fileReader);
      } finally {
        StrictMode.setThreadPolicy(threadPolicy);
      } 
      zzn.closeQuietly((Closeable)SYNTHETIC_LOCAL_VARIABLE_4);
      throw null;
    } catch (IOException null) {
    
    } finally {
      iOException2 = iOException1;
      zzn.closeQuietly((Closeable)iOException2);
    } 
    zzn.closeQuietly((Closeable)iOException2);
    return (String)SYNTHETIC_LOCAL_VARIABLE_1;
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/common/util/zzr.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */