package com.google.android.gms.common.util;

import android.content.Context;
import android.os.DropBoxManager;
import android.util.Log;
import com.google.android.gms.common.internal.zzbp;

public final class zzf {
  private static final String[] zzfym = new String[] { "android.", "com.android.", "dalvik.", "java.", "javax." };
  
  private static DropBoxManager zzfyn;
  
  private static boolean zzfyo = false;
  
  private static int zzfyp = -1;
  
  private static int zzfyq;
  
  public static boolean zza(Context paramContext, Throwable paramThrowable) {
    return zza(paramContext, paramThrowable, 0);
  }
  
  private static boolean zza(Context paramContext, Throwable paramThrowable, int paramInt) {
    try {
      zzbp.zzu(paramContext);
      zzbp.zzu(paramThrowable);
      return false;
    } catch (Exception exception) {
      Log.e("CrashUtils", "Error adding exception to DropBox!", exception);
      return false;
    } 
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/common/util/zzf.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */