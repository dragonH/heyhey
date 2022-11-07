package com.google.android.gms.common.api.internal;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.zzbp;

public final class zzw implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {
  public final Api<?> zzfdg;
  
  private final boolean zzfjs;
  
  private zzx zzfjt;
  
  public zzw(Api<?> paramApi, boolean paramBoolean) {
    this.zzfdg = paramApi;
    this.zzfjs = paramBoolean;
  }
  
  private final void zzagh() {
    zzbp.zzb(this.zzfjt, "Callbacks must be attached to a ClientConnectionHelper instance before connecting the client.");
  }
  
  public final void onConnected(@Nullable Bundle paramBundle) {
    zzagh();
    this.zzfjt.onConnected(paramBundle);
  }
  
  public final void onConnectionFailed(@NonNull ConnectionResult paramConnectionResult) {
    zzagh();
    this.zzfjt.zza(paramConnectionResult, this.zzfdg, this.zzfjs);
  }
  
  public final void onConnectionSuspended(int paramInt) {
    zzagh();
    this.zzfjt.onConnectionSuspended(paramInt);
  }
  
  public final void zza(zzx paramzzx) {
    this.zzfjt = paramzzx;
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/common/api/internal/zzw.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */