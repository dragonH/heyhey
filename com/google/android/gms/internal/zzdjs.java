package com.google.android.gms.internal;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

final class zzdjs {
  private final ConcurrentHashMap<zzdjt, List<Throwable>> zzlhz = new ConcurrentHashMap<zzdjt, List<Throwable>>(16, 0.75F, 10);
  
  private final ReferenceQueue<Throwable> zzlia = new ReferenceQueue<Throwable>();
  
  public final List<Throwable> zza(Throwable paramThrowable, boolean paramBoolean) {
    while (true) {
      Reference<? extends Throwable> reference = this.zzlia.poll();
      if (reference != null) {
        this.zzlhz.remove(reference);
        continue;
      } 
      zzdjt zzdjt = new zzdjt(paramThrowable, null);
      return this.zzlhz.get(zzdjt);
    } 
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/internal/zzdjs.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */