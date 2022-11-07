package com.google.android.gms.common.api.internal;

import android.os.Looper;
import android.support.annotation.NonNull;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.internal.zzbp;
import com.google.android.gms.common.internal.zzj;
import java.lang.ref.WeakReference;

final class zzat implements zzj {
  private final Api<?> zzfdg;
  
  private final boolean zzfjs;
  
  private final WeakReference<zzar> zzfly;
  
  public zzat(zzar paramzzar, Api<?> paramApi, boolean paramBoolean) {
    this.zzfly = new WeakReference<zzar>(paramzzar);
    this.zzfdg = paramApi;
    this.zzfjs = paramBoolean;
  }
  
  public final void zzf(@NonNull ConnectionResult paramConnectionResult) {
    boolean bool;
    zzar zzar = this.zzfly.get();
    if (zzar == null)
      return; 
    if (Looper.myLooper() == (zzar.zzd(zzar)).zzfju.getLooper()) {
      bool = true;
    } else {
      bool = false;
    } 
    zzbp.zza(bool, "onReportServiceBinding must be called on the GoogleApiClient handler thread");
    zzar.zzc(zzar).lock();
    try {
      bool = zzar.zza(zzar, 0);
      if (bool) {
        if (!paramConnectionResult.isSuccess())
          zzar.zza(zzar, paramConnectionResult, this.zzfdg, this.zzfjs); 
        if (zzar.zzk(zzar))
          zzar.zzj(zzar); 
      } 
      return;
    } finally {
      zzar.zzc(zzar).unlock();
    } 
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/common/api/internal/zzat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */