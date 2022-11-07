package com.google.android.gms.internal;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.internal.zzm;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.SettingsApi;

public final class zzcaf implements SettingsApi {
  public final PendingResult<LocationSettingsResult> checkLocationSettings(GoogleApiClient paramGoogleApiClient, LocationSettingsRequest paramLocationSettingsRequest) {
    return (PendingResult<LocationSettingsResult>)paramGoogleApiClient.zzd((zzm)new zzcag(this, paramGoogleApiClient, paramLocationSettingsRequest, null));
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/internal/zzcaf.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */