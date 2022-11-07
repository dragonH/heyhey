package com.google.android.gms.common.api.internal;

import android.support.annotation.NonNull;
import android.support.annotation.WorkerThread;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.zzam;
import com.google.android.gms.common.internal.zzj;
import java.util.Set;

final class zzbv implements zzcy, zzj {
  private Set<Scope> zzecm = null;
  
  private final zzh<?> zzfgs;
  
  private final Api.zze zzfkh;
  
  private zzam zzflt = null;
  
  private boolean zzfof = false;
  
  public zzbv(zzbp paramzzbp, Api.zze paramzze, zzh<?> paramzzh) {
    this.zzfkh = paramzze;
    this.zzfgs = paramzzh;
  }
  
  @WorkerThread
  private final void zzaid() {
    if (this.zzfof) {
      zzam zzam1 = this.zzflt;
      if (zzam1 != null)
        this.zzfkh.zza(zzam1, this.zzecm); 
    } 
  }
  
  @WorkerThread
  public final void zzb(zzam paramzzam, Set<Scope> paramSet) {
    if (paramzzam == null || paramSet == null) {
      Log.wtf("GoogleApiManager", "Received null response from onSignInSuccess", new Exception());
      zzh(new ConnectionResult(4));
      return;
    } 
    this.zzflt = paramzzam;
    this.zzecm = paramSet;
    zzaid();
  }
  
  public final void zzf(@NonNull ConnectionResult paramConnectionResult) {
    zzbp.zza(this.zzfnu).post(new zzbw(this, paramConnectionResult));
  }
  
  @WorkerThread
  public final void zzh(ConnectionResult paramConnectionResult) {
    ((zzbr)zzbp.zzj(this.zzfnu).get(this.zzfgs)).zzh(paramConnectionResult);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/common/api/internal/zzbv.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */