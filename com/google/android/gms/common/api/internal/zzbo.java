package com.google.android.gms.common.api.internal;

import com.google.android.gms.internal.zzbdz;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public final class zzbo {
  private static final ExecutorService zzfnj = Executors.newFixedThreadPool(2, (ThreadFactory)new zzbdz("GAC_Executor"));
  
  public static ExecutorService zzahn() {
    return zzfnj;
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/common/api/internal/zzbo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */