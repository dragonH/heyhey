package com.google.android.gms.location.places;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresPermission;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.Response;
import com.google.android.gms.common.api.internal.zzcz;
import com.google.android.gms.common.api.internal.zzg;
import com.google.android.gms.common.internal.zzbi;
import com.google.android.gms.tasks.Task;

public class PlaceDetectionClient extends GoogleApi<PlacesOptions> {
  PlaceDetectionClient(@NonNull Activity paramActivity, PlacesOptions paramPlacesOptions) {
    super(paramActivity, Places.PLACE_DETECTION_API, (Api.ApiOptions)paramPlacesOptions, (zzcz)new zzg());
  }
  
  PlaceDetectionClient(@NonNull Context paramContext, PlacesOptions paramPlacesOptions) {
    super(paramContext, Places.PLACE_DETECTION_API, (Api.ApiOptions)paramPlacesOptions, (zzcz)new zzg());
  }
  
  @RequiresPermission("android.permission.ACCESS_FINE_LOCATION")
  public Task<PlaceLikelihoodBufferResponse> getCurrentPlace(@Nullable PlaceFilter paramPlaceFilter) {
    return zzbi.zza(Places.PlaceDetectionApi.getCurrentPlace(zzafl(), paramPlaceFilter), (Response)new PlaceLikelihoodBufferResponse());
  }
  
  public Task<Void> reportDeviceAtPlace(PlaceReport paramPlaceReport) {
    return zzbi.zzb(Places.PlaceDetectionApi.reportDeviceAtPlace(zzafl(), paramPlaceReport));
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/location/places/PlaceDetectionClient.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */