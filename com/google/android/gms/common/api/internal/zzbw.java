package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.ConnectionResult;
import java.util.Collections;

final class zzbw implements Runnable {
  zzbw(zzbv paramzzbv, ConnectionResult paramConnectionResult) {}
  
  public final void run() {
    if (this.zzfoe.isSuccess()) {
      zzbv.zza(this.zzfog, true);
      if (zzbv.zza(this.zzfog).zzaac()) {
        zzbv.zzb(this.zzfog);
        return;
      } 
      zzbv.zza(this.zzfog).zza(null, Collections.emptySet());
      return;
    } 
    ((zzbr)zzbp.zzj(this.zzfog.zzfnu).get(zzbv.zzc(this.zzfog))).onConnectionFailed(this.zzfoe);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/common/api/internal/zzbw.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */