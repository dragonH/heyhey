package com.google.android.gms.internal;

import android.os.StrictMode;
import java.util.concurrent.Callable;

public final class zzbvp {
  public static <T> T zza(Callable<T> paramCallable) throws Exception {
    StrictMode.ThreadPolicy threadPolicy = StrictMode.getThreadPolicy();
    try {
      StrictMode.setThreadPolicy(StrictMode.ThreadPolicy.LAX);
      paramCallable = (Callable<T>)paramCallable.call();
      return (T)paramCallable;
    } finally {
      StrictMode.setThreadPolicy(threadPolicy);
    } 
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/internal/zzbvp.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */