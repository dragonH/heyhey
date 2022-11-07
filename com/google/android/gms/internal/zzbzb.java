package com.google.android.gms.internal;

import android.app.PendingIntent;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.zzm;
import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.GeofencingApi;
import com.google.android.gms.location.GeofencingRequest;
import com.google.android.gms.location.zzaa;
import java.util.List;

public final class zzbzb implements GeofencingApi {
  private final PendingResult<Status> zza(GoogleApiClient paramGoogleApiClient, zzaa paramzzaa) {
    return (PendingResult<Status>)paramGoogleApiClient.zze((zzm)new zzbzd(this, paramGoogleApiClient, paramzzaa));
  }
  
  public final PendingResult<Status> addGeofences(GoogleApiClient paramGoogleApiClient, GeofencingRequest paramGeofencingRequest, PendingIntent paramPendingIntent) {
    return (PendingResult<Status>)paramGoogleApiClient.zze((zzm)new zzbzc(this, paramGoogleApiClient, paramGeofencingRequest, paramPendingIntent));
  }
  
  @Deprecated
  public final PendingResult<Status> addGeofences(GoogleApiClient paramGoogleApiClient, List<Geofence> paramList, PendingIntent paramPendingIntent) {
    GeofencingRequest.Builder builder = new GeofencingRequest.Builder();
    builder.addGeofences(paramList);
    builder.setInitialTrigger(5);
    return addGeofences(paramGoogleApiClient, builder.build(), paramPendingIntent);
  }
  
  public final PendingResult<Status> removeGeofences(GoogleApiClient paramGoogleApiClient, PendingIntent paramPendingIntent) {
    return zza(paramGoogleApiClient, zzaa.zzb(paramPendingIntent));
  }
  
  public final PendingResult<Status> removeGeofences(GoogleApiClient paramGoogleApiClient, List<String> paramList) {
    return zza(paramGoogleApiClient, zzaa.zzz(paramList));
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/internal/zzbzb.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */