package com.google.android.gms.location;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresPermission;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.internal.zzcz;
import com.google.android.gms.common.api.internal.zzg;
import com.google.android.gms.common.internal.zzbi;
import com.google.android.gms.tasks.Task;
import java.util.List;

public class GeofencingClient extends GoogleApi<Api.ApiOptions.NoOptions> {
  public GeofencingClient(@NonNull Activity paramActivity) {
    super(paramActivity, LocationServices.API, null, (zzcz)new zzg());
  }
  
  public GeofencingClient(@NonNull Context paramContext) {
    super(paramContext, LocationServices.API, null, (zzcz)new zzg());
  }
  
  @RequiresPermission("android.permission.ACCESS_FINE_LOCATION")
  public Task<Void> addGeofences(GeofencingRequest paramGeofencingRequest, PendingIntent paramPendingIntent) {
    return zzbi.zzb(LocationServices.GeofencingApi.addGeofences(zzafl(), paramGeofencingRequest, paramPendingIntent));
  }
  
  public Task<Void> removeGeofences(PendingIntent paramPendingIntent) {
    return zzbi.zzb(LocationServices.GeofencingApi.removeGeofences(zzafl(), paramPendingIntent));
  }
  
  public Task<Void> removeGeofences(List<String> paramList) {
    return zzbi.zzb(LocationServices.GeofencingApi.removeGeofences(zzafl(), paramList));
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/location/GeofencingClient.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */