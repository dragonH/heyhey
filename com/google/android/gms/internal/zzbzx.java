package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.internal.zzn;
import com.google.android.gms.common.internal.zzbp;
import com.google.android.gms.location.LocationSettingsResult;

final class zzbzx extends zzbzn {
  private zzn<LocationSettingsResult> zzhzt;
  
  public zzbzx(zzn<LocationSettingsResult> paramzzn) {
    boolean bool;
    if (paramzzn != null) {
      bool = true;
    } else {
      bool = false;
    } 
    zzbp.zzb(bool, "listener can't be null.");
    this.zzhzt = paramzzn;
  }
  
  public final void zza(LocationSettingsResult paramLocationSettingsResult) throws RemoteException {
    this.zzhzt.setResult(paramLocationSettingsResult);
    this.zzhzt = null;
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/internal/zzbzx.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */