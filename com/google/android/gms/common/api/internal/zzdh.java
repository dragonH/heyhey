package com.google.android.gms.common.api.internal;

import android.support.annotation.WorkerThread;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Result;

final class zzdh implements Runnable {
  zzdh(zzdg paramzzdg, Result paramResult) {}
  
  @WorkerThread
  public final void run() {
    Exception exception;
    try {
      ThreadLocal<Boolean> threadLocal = zzs.zzfje;
      threadLocal.set(Boolean.TRUE);
      PendingResult pendingResult = zzdg.zzc(this.zzfpp).onSuccess(this.zzfpo);
      zzdg.zzd(this.zzfpp).sendMessage(zzdg.zzd(this.zzfpp).obtainMessage(0, pendingResult));
      threadLocal.set(Boolean.FALSE);
      zzdg.zza(this.zzfpp, this.zzfpo);
      GoogleApiClient googleApiClient1 = zzdg.zze(this.zzfpp).get();
      if (googleApiClient1 != null)
        googleApiClient1.zzb(this.zzfpp); 
      return;
    } catch (RuntimeException runtimeException) {
      zzdg.zzd(this.zzfpp).sendMessage(zzdg.zzd(this.zzfpp).obtainMessage(1, runtimeException));
      zzs.zzfje.set(Boolean.FALSE);
      zzdg.zza(this.zzfpp, this.zzfpo);
      GoogleApiClient googleApiClient1 = zzdg.zze(this.zzfpp).get();
      if (googleApiClient1 != null)
        googleApiClient1.zzb(this.zzfpp); 
      return;
    } finally {}
    zzs.zzfje.set(Boolean.FALSE);
    zzdg.zza(this.zzfpp, this.zzfpo);
    GoogleApiClient googleApiClient = zzdg.zze(this.zzfpp).get();
    if (googleApiClient != null)
      googleApiClient.zzb(this.zzfpp); 
    throw exception;
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/common/api/internal/zzdh.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */