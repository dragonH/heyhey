package com.google.android.gms.internal;

import com.google.android.gms.common.internal.zzbp;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

public final class zzbdz implements ThreadFactory {
  private final int mPriority;
  
  private final String zzfzn;
  
  private final AtomicInteger zzfzo = new AtomicInteger();
  
  private final ThreadFactory zzfzp = Executors.defaultThreadFactory();
  
  public zzbdz(String paramString) {
    this(paramString, 0);
  }
  
  private zzbdz(String paramString, int paramInt) {
    this.zzfzn = (String)zzbp.zzb(paramString, "Name must not be null");
    this.mPriority = 0;
  }
  
  public final Thread newThread(Runnable paramRunnable) {
    Thread thread = this.zzfzp.newThread(new zzbea(paramRunnable, 0));
    String str = this.zzfzn;
    int i = this.zzfzo.getAndIncrement();
    StringBuilder stringBuilder = new StringBuilder(String.valueOf(str).length() + 13);
    stringBuilder.append(str);
    stringBuilder.append("[");
    stringBuilder.append(i);
    stringBuilder.append("]");
    thread.setName(stringBuilder.toString());
    return thread;
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/internal/zzbdz.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */