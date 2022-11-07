package com.google.android.gms.common.api.internal;

import android.support.annotation.WorkerThread;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.internal.zzj;
import java.util.Iterator;
import java.util.Map;

final class zzau extends zzbb {
  private final Map<Api.zze, zzat> zzflz;
  
  public zzau(zzar paramzzar, Map<Api.zze, zzat> paramMap) {
    super(paramzzar, null);
    this.zzflz = paramMap;
  }
  
  @WorkerThread
  public final void zzagz() {
    null = this.zzflz.keySet().iterator();
    boolean bool1 = true;
    int i = 0;
    boolean bool2 = false;
    boolean bool3 = true;
    while (true) {
      if (null.hasNext()) {
        Api.zze zze = null.next();
        if (zze.zzaff()) {
          if (!zzat.zza(this.zzflz.get(zze))) {
            bool2 = true;
            break;
          } 
          bool2 = true;
          continue;
        } 
        bool3 = false;
        continue;
      } 
      boolean bool = false;
      bool1 = bool2;
      bool2 = bool;
      break;
    } 
    if (bool1)
      i = zzar.zzb(this.zzflx).isGooglePlayServicesAvailable(zzar.zza(this.zzflx)); 
    if (i != 0 && (bool2 || bool3)) {
      ConnectionResult connectionResult = new ConnectionResult(i, null);
      zzar.zzd(this.zzflx).zza(new zzav(this, this.zzflx, connectionResult));
      return;
    } 
    if (zzar.zze(this.zzflx))
      zzar.zzf(this.zzflx).connect(); 
    for (Api.zze zze : this.zzflz.keySet()) {
      zzj zzj = this.zzflz.get(zze);
      if (zze.zzaff() && i != 0) {
        zzar.zzd(this.zzflx).zza(new zzaw(this, this.zzflx, zzj));
        continue;
      } 
      zze.zza(zzj);
    } 
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/common/api/internal/zzau.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */