package com.google.android.gms.location.places.internal;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.zzm;
import com.google.android.gms.location.places.PlaceDetectionApi;
import com.google.android.gms.location.places.PlaceFilter;
import com.google.android.gms.location.places.PlaceLikelihoodBuffer;
import com.google.android.gms.location.places.PlaceReport;
import com.google.android.gms.location.places.Places;

public final class zzy implements PlaceDetectionApi {
  public final PendingResult<PlaceLikelihoodBuffer> getCurrentPlace(GoogleApiClient paramGoogleApiClient, PlaceFilter paramPlaceFilter) {
    return (PendingResult<PlaceLikelihoodBuffer>)paramGoogleApiClient.zzd((zzm)new zzz(this, Places.PLACE_DETECTION_API, paramGoogleApiClient, paramPlaceFilter));
  }
  
  public final PendingResult<Status> reportDeviceAtPlace(GoogleApiClient paramGoogleApiClient, PlaceReport paramPlaceReport) {
    return (PendingResult<Status>)paramGoogleApiClient.zze((zzm)new zzaa(this, Places.PLACE_DETECTION_API, paramGoogleApiClient, paramPlaceReport));
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/location/places/internal/zzy.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */