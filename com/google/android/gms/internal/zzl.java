package com.google.android.gms.internal;

import android.net.TrafficStats;
import android.os.Process;
import android.os.SystemClock;
import java.util.concurrent.BlockingQueue;

public final class zzl extends Thread {
  private final zzb zzi;
  
  private final zzw zzj;
  
  private volatile boolean zzk = false;
  
  private final BlockingQueue<zzp<?>> zzw;
  
  private final zzk zzx;
  
  public zzl(BlockingQueue<zzp<?>> paramBlockingQueue, zzk paramzzk, zzb paramzzb, zzw paramzzw) {
    this.zzw = paramBlockingQueue;
    this.zzx = paramzzk;
    this.zzi = paramzzb;
    this.zzj = paramzzw;
  }
  
  public final void quit() {
    this.zzk = true;
    interrupt();
  }
  
  public final void run() {
    Process.setThreadPriority(10);
    while (true) {
      long l = SystemClock.elapsedRealtime();
      try {
        zzp<?> zzp = this.zzw.take();
        try {
          zzp.zzb("network-queue-take");
          TrafficStats.setThreadStatsTag(zzp.zzc());
          zzn zzn = this.zzx.zza(zzp);
          zzp.zzb("network-http-complete");
          if (zzn.zzz && zzp.zzl()) {
            zzp.zzc("not-modified");
            continue;
          } 
          zzt<?> zzt = zzp.zza(zzn);
          zzp.zzb("network-parse-complete");
          if (zzp.zzh() && zzt.zzbe != null) {
            this.zzi.zza(zzp.getUrl(), zzt.zzbe);
            zzp.zzb("network-cache-written");
          } 
          zzp.zzk();
          this.zzj.zza(zzp, zzt);
        } catch (zzaa zzaa) {
          zzaa.zza(SystemClock.elapsedRealtime() - l);
          this.zzj.zza(zzp, zzaa);
        } catch (Exception exception) {
          zzab.zza(exception, "Unhandled exception %s", new Object[] { exception.toString() });
          exception = new zzaa(exception);
          exception.zza(SystemClock.elapsedRealtime() - l);
          this.zzj.zza(zzp, (zzaa)exception);
        } 
      } catch (InterruptedException interruptedException) {
        if (this.zzk)
          break; 
      } 
    } 
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/internal/zzl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */