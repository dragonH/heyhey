package com.google.android.gms.common.internal;

import android.support.annotation.NonNull;
import com.google.android.gms.common.ConnectionResult;

public final class zzm implements zzj {
  public zzm(zzd paramzzd) {}
  
  public final void zzf(@NonNull ConnectionResult paramConnectionResult) {
    zzd zzd1;
    if (paramConnectionResult.isSuccess()) {
      zzd1 = this.zzftl;
      zzd1.zza((zzam)null, zzd1.zzajl());
      return;
    } 
    if (zzd.zzg(this.zzftl) != null)
      zzd.zzg(this.zzftl).onConnectionFailed((ConnectionResult)zzd1); 
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/common/internal/zzm.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */