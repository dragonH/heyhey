package com.google.android.gms.location;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.internal.zzcz;
import com.google.android.gms.common.api.internal.zzg;
import com.google.android.gms.common.internal.zzbi;
import com.google.android.gms.tasks.Task;

public class SettingsClient extends GoogleApi<Api.ApiOptions.NoOptions> {
  public SettingsClient(@NonNull Activity paramActivity) {
    super(paramActivity, LocationServices.API, null, (zzcz)new zzg());
  }
  
  public SettingsClient(@NonNull Context paramContext) {
    super(paramContext, LocationServices.API, null, (zzcz)new zzg());
  }
  
  public Task<LocationSettingsResponse> checkLocationSettings(LocationSettingsRequest paramLocationSettingsRequest) {
    return zzbi.zza(LocationServices.SettingsApi.checkLocationSettings(zzafl(), paramLocationSettingsRequest), new LocationSettingsResponse());
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/location/SettingsClient.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */