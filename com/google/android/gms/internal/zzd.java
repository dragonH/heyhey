package com.google.android.gms.internal;

import android.os.Process;
import java.util.concurrent.BlockingQueue;

public final class zzd extends Thread {
  private static final boolean DEBUG = zzab.DEBUG;
  
  private final BlockingQueue<zzp<?>> zzg;
  
  private final BlockingQueue<zzp<?>> zzh;
  
  private final zzb zzi;
  
  private final zzw zzj;
  
  private volatile boolean zzk = false;
  
  public zzd(BlockingQueue<zzp<?>> paramBlockingQueue1, BlockingQueue<zzp<?>> paramBlockingQueue2, zzb paramzzb, zzw paramzzw) {
    this.zzg = paramBlockingQueue1;
    this.zzh = paramBlockingQueue2;
    this.zzi = paramzzb;
    this.zzj = paramzzw;
  }
  
  public final void quit() {
    this.zzk = true;
    interrupt();
  }
  
  public final void run() {
    if (DEBUG)
      zzab.zza("start new dispatcher", new Object[0]); 
    Process.setThreadPriority(10);
    this.zzi.initialize();
    while (true) {
      try {
        zzt<?> zzt;
        zzp<?> zzp = this.zzg.take();
        zzp.zzb("cache-queue-take");
        zzc zzc = this.zzi.zza(zzp.getUrl());
        if (zzc == null) {
          zzp.zzb("cache-miss");
          BlockingQueue<zzp<?>> blockingQueue = this.zzh;
        } else {
          boolean bool;
          if (zzc.zzd < System.currentTimeMillis()) {
            bool = true;
          } else {
            bool = false;
          } 
          if (bool) {
            zzp.zzb("cache-hit-expired");
            zzp.zza(zzc);
            BlockingQueue<zzp<?>> blockingQueue = this.zzh;
          } else {
            zzp.zzb("cache-hit");
            zzn zzn = new zzn();
            this(zzc.data, zzc.zzf);
            zzt = zzp.zza(zzn);
            zzp.zzb("cache-hit-parsed");
            if (zzc.zze < System.currentTimeMillis()) {
              bool = true;
            } else {
              bool = false;
            } 
            if (!bool) {
              this.zzj.zza(zzp, zzt);
              continue;
            } 
            zzp.zzb("cache-hit-refresh-needed");
            zzp.zza(zzc);
            zzt.zzbg = true;
            zzw zzw1 = this.zzj;
            zze zze = new zze();
            this(this, zzp);
            zzw1.zza(zzp, zzt, zze);
            continue;
          } 
        } 
        zzt.put(zzp);
      } catch (InterruptedException interruptedException) {
        if (this.zzk)
          break; 
      } 
    } 
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/internal/zzd.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */