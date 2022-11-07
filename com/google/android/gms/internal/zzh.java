package com.google.android.gms.internal;

import android.os.Handler;
import java.util.concurrent.Executor;

public final class zzh implements zzw {
  private final Executor zzr;
  
  public zzh(Handler paramHandler) {
    this.zzr = new zzi(this, paramHandler);
  }
  
  public final void zza(zzp<?> paramzzp, zzaa paramzzaa) {
    paramzzp.zzb("post-error");
    zzt<?> zzt = zzt.zzc(paramzzaa);
    this.zzr.execute(new zzj(this, paramzzp, zzt, null));
  }
  
  public final void zza(zzp<?> paramzzp, zzt<?> paramzzt) {
    zza(paramzzp, paramzzt, null);
  }
  
  public final void zza(zzp<?> paramzzp, zzt<?> paramzzt, Runnable paramRunnable) {
    paramzzp.zzk();
    paramzzp.zzb("post-response");
    this.zzr.execute(new zzj(this, paramzzp, paramzzt, paramRunnable));
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/internal/zzh.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */