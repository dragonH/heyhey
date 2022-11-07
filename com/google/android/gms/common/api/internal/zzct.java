package com.google.android.gms.common.api.internal;

import com.google.android.gms.internal.zzbdz;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public final class zzct {
  private static final ExecutorService zzfnj = new ThreadPoolExecutor(0, 4, 60L, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(), (ThreadFactory)new zzbdz("GAC_Transform"));
  
  public static ExecutorService zzahn() {
    return zzfnj;
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/common/api/internal/zzct.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */