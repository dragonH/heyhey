package com.google.android.gms.common.api.internal;

import android.os.IBinder;
import com.google.android.gms.common.api.zze;
import java.lang.ref.WeakReference;
import java.util.NoSuchElementException;

final class zzdl implements IBinder.DeathRecipient, zzdm {
  private final WeakReference<zzs<?>> zzfpv;
  
  private final WeakReference<zze> zzfpw;
  
  private final WeakReference<IBinder> zzfpx;
  
  private zzdl(zzs<?> paramzzs, zze paramzze, IBinder paramIBinder) {
    this.zzfpw = new WeakReference<zze>(paramzze);
    this.zzfpv = new WeakReference<zzs<?>>(paramzzs);
    this.zzfpx = new WeakReference<IBinder>(paramIBinder);
  }
  
  private final void zzair() {
    zzs zzs = this.zzfpv.get();
    zze zze = this.zzfpw.get();
    if (zze != null && zzs != null)
      zze.remove(zzs.zzafs().intValue()); 
    IBinder iBinder = this.zzfpx.get();
    if (iBinder != null)
      try {
        iBinder.unlinkToDeath(this, 0);
      } catch (NoSuchElementException noSuchElementException) {} 
  }
  
  public final void binderDied() {
    zzair();
  }
  
  public final void zzc(zzs<?> paramzzs) {
    zzair();
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/common/api/internal/zzdl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */