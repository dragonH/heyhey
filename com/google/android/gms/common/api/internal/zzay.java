package com.google.android.gms.common.api.internal;

import android.support.annotation.BinderThread;
import com.google.android.gms.internal.zzcpx;
import com.google.android.gms.internal.zzcqf;
import java.lang.ref.WeakReference;

final class zzay extends zzcpx {
  private final WeakReference<zzar> zzfly;
  
  zzay(zzar paramzzar) {
    this.zzfly = new WeakReference<zzar>(paramzzar);
  }
  
  @BinderThread
  public final void zzb(zzcqf paramzzcqf) {
    zzar zzar = this.zzfly.get();
    if (zzar == null)
      return; 
    zzar.zzd(zzar).zza(new zzaz(this, zzar, zzar, paramzzcqf));
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/common/api/internal/zzay.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */