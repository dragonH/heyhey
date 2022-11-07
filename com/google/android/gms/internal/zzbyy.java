package com.google.android.gms.internal;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.zzn;

final class zzbyy extends zzbzg {
  private final zzn<Status> zzfwi;
  
  public zzbyy(zzn<Status> paramzzn) {
    this.zzfwi = paramzzn;
  }
  
  public final void zza(zzbyz paramzzbyz) {
    this.zzfwi.setResult(paramzzbyz.getStatus());
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/internal/zzbyy.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */