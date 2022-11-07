package com.google.android.gms.common.api.internal;

import java.lang.ref.WeakReference;

final class zzbj extends zzbz {
  private WeakReference<zzbd> zzfmz;
  
  zzbj(zzbd paramzzbd) {
    this.zzfmz = new WeakReference<zzbd>(paramzzbd);
  }
  
  public final void zzage() {
    zzbd zzbd = this.zzfmz.get();
    if (zzbd == null)
      return; 
    zzbd.zza(zzbd);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/common/api/internal/zzbj.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */