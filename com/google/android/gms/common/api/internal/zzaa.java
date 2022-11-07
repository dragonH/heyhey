package com.google.android.gms.common.api.internal;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.google.android.gms.common.ConnectionResult;

final class zzaa implements zzce {
  private zzaa(zzy paramzzy) {}
  
  public final void zzc(@NonNull ConnectionResult paramConnectionResult) {
    zzy.zza(this.zzfkg).lock();
    try {
      zzy.zza(this.zzfkg, paramConnectionResult);
      zzy.zzb(this.zzfkg);
      return;
    } finally {
      zzy.zza(this.zzfkg).unlock();
    } 
  }
  
  public final void zzf(int paramInt, boolean paramBoolean) {
    zzy.zza(this.zzfkg).lock();
    try {
      if (zzy.zzc(this.zzfkg) || zzy.zzd(this.zzfkg) == null || !zzy.zzd(this.zzfkg).isSuccess()) {
        zzy.zza(this.zzfkg, false);
        zzy.zza(this.zzfkg, paramInt, paramBoolean);
      } else {
        zzy.zza(this.zzfkg, true);
        zzy.zze(this.zzfkg).onConnectionSuspended(paramInt);
      } 
      return;
    } finally {
      zzy.zza(this.zzfkg).unlock();
    } 
  }
  
  public final void zzj(@Nullable Bundle paramBundle) {
    zzy.zza(this.zzfkg).lock();
    try {
      zzy.zza(this.zzfkg, paramBundle);
      zzy.zza(this.zzfkg, ConnectionResult.zzfff);
      zzy.zzb(this.zzfkg);
      return;
    } finally {
      zzy.zza(this.zzfkg).unlock();
    } 
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/common/api/internal/zzaa.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */