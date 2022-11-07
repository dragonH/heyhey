package com.google.android.gms.location.places;

import android.support.annotation.Nullable;
import android.support.annotation.RequiresPermission;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;

public interface PlaceDetectionApi {
  @RequiresPermission("android.permission.ACCESS_FINE_LOCATION")
  PendingResult<PlaceLikelihoodBuffer> getCurrentPlace(GoogleApiClient paramGoogleApiClient, @Nullable PlaceFilter paramPlaceFilter);
  
  PendingResult<Status> reportDeviceAtPlace(GoogleApiClient paramGoogleApiClient, PlaceReport paramPlaceReport);
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/location/places/PlaceDetectionApi.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */