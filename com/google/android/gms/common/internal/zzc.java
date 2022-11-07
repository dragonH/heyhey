package com.google.android.gms.common.internal;

import android.os.Looper;
import android.util.Log;

public final class zzc {
  public static void zzbg(boolean paramBoolean) {
    if (paramBoolean)
      return; 
    throw new IllegalStateException();
  }
  
  public static void zzfy(String paramString) {
    if (Looper.getMainLooper().getThread() == Thread.currentThread())
      return; 
    String str1 = String.valueOf(Thread.currentThread());
    String str2 = String.valueOf(Looper.getMainLooper().getThread());
    StringBuilder stringBuilder = new StringBuilder(str1.length() + 57 + str2.length());
    stringBuilder.append("checkMainThread: current thread ");
    stringBuilder.append(str1);
    stringBuilder.append(" IS NOT the main thread ");
    stringBuilder.append(str2);
    stringBuilder.append("!");
    Log.e("Asserts", stringBuilder.toString());
    throw new IllegalStateException(paramString);
  }
  
  public static void zzr(Object paramObject) {
    if (paramObject != null)
      return; 
    throw new IllegalArgumentException("null reference");
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/common/internal/zzc.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */