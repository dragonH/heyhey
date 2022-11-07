package com.google.android.gms.internal;

final class zzj implements Runnable {
  private final zzp zzt;
  
  private final zzt zzu;
  
  private final Runnable zzv;
  
  public zzj(zzh paramzzh, zzp paramzzp, zzt paramzzt, Runnable paramRunnable) {
    this.zzt = paramzzp;
    this.zzu = paramzzt;
    this.zzv = paramRunnable;
  }
  
  public final void run() {
    boolean bool;
    zzt zzt1 = this.zzu;
    zzaa zzaa = zzt1.zzbf;
    if (zzaa == null) {
      bool = true;
    } else {
      bool = false;
    } 
    if (bool) {
      this.zzt.zza(zzt1.result);
    } else {
      this.zzt.zzb(zzaa);
    } 
    if (this.zzu.zzbg) {
      this.zzt.zzb("intermediate-response");
    } else {
      this.zzt.zzc("done");
    } 
    Runnable runnable = this.zzv;
    if (runnable != null)
      runnable.run(); 
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/internal/zzj.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */