package com.google.android.gms.common.api.internal;

import android.os.Bundle;
import com.google.android.gms.common.api.GoogleApiClient;
import java.util.concurrent.atomic.AtomicReference;

final class zzbf implements GoogleApiClient.ConnectionCallbacks {
  zzbf(zzbd paramzzbd, AtomicReference paramAtomicReference, zzda paramzzda) {}
  
  public final void onConnected(Bundle paramBundle) {
    zzbd.zza(this.zzfmv, this.zzfmw.get(), this.zzfmx, true);
  }
  
  public final void onConnectionSuspended(int paramInt) {}
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/common/api/internal/zzbf.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */