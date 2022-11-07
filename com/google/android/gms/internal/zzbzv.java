package com.google.android.gms.internal;

import android.app.PendingIntent;
import android.util.Log;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.zzn;
import com.google.android.gms.location.LocationStatusCodes;

final class zzbzv extends zzbzj {
  private zzn<Status> zzhzt;
  
  public zzbzv(zzn<Status> paramzzn) {
    this.zzhzt = paramzzn;
  }
  
  public final void zza(int paramInt, PendingIntent paramPendingIntent) {
    Log.wtf("LocationClientImpl", "Unexpected call to onRemoveGeofencesByPendingIntentResult");
  }
  
  public final void zza(int paramInt, String[] paramArrayOfString) {
    if (this.zzhzt == null) {
      Log.wtf("LocationClientImpl", "onAddGeofenceResult called multiple times");
      return;
    } 
    Status status = LocationStatusCodes.zzdr(LocationStatusCodes.zzdq(paramInt));
    this.zzhzt.setResult(status);
    this.zzhzt = null;
  }
  
  public final void zzb(int paramInt, String[] paramArrayOfString) {
    Log.wtf("LocationClientImpl", "Unexpected call to onRemoveGeofencesByRequestIdsResult");
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/internal/zzbzv.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */