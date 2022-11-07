package com.google.android.gms.internal;

import android.app.PendingIntent;
import android.util.Log;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.zzn;
import com.google.android.gms.location.LocationStatusCodes;

final class zzbzw extends zzbzj {
  private zzn<Status> zzhzt;
  
  public zzbzw(zzn<Status> paramzzn) {
    this.zzhzt = paramzzn;
  }
  
  private final void zzds(int paramInt) {
    if (this.zzhzt == null) {
      Log.wtf("LocationClientImpl", "onRemoveGeofencesResult called multiple times");
      return;
    } 
    Status status = LocationStatusCodes.zzdr(LocationStatusCodes.zzdq(paramInt));
    this.zzhzt.setResult(status);
    this.zzhzt = null;
  }
  
  public final void zza(int paramInt, PendingIntent paramPendingIntent) {
    zzds(paramInt);
  }
  
  public final void zza(int paramInt, String[] paramArrayOfString) {
    Log.wtf("LocationClientImpl", "Unexpected call to onAddGeofencesResult");
  }
  
  public final void zzb(int paramInt, String[] paramArrayOfString) {
    zzds(paramInt);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/internal/zzbzw.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */