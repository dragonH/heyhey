package com.google.android.gms.common.api.internal;

import android.os.Bundle;
import android.support.annotation.NonNull;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.internal.zzcpy;

final class zzba implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {
  private zzba(zzar paramzzar) {}
  
  public final void onConnected(Bundle paramBundle) {
    zzar.zzf(this.zzflx).zza((zzcpy)new zzay(this.zzflx));
  }
  
  public final void onConnectionFailed(@NonNull ConnectionResult paramConnectionResult) {
    zzar.zzc(this.zzflx).lock();
    try {
      if (zzar.zzb(this.zzflx, paramConnectionResult)) {
        zzar.zzi(this.zzflx);
        zzar.zzj(this.zzflx);
      } else {
        zzar.zza(this.zzflx, paramConnectionResult);
      } 
      return;
    } finally {
      zzar.zzc(this.zzflx).unlock();
    } 
  }
  
  public final void onConnectionSuspended(int paramInt) {}
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/common/api/internal/zzba.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */