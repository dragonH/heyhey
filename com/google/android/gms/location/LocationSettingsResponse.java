package com.google.android.gms.location;

import com.google.android.gms.common.api.Response;

public class LocationSettingsResponse extends Response<LocationSettingsResult> {
  public LocationSettingsStates getLocationSettingsStates() {
    return ((LocationSettingsResult)getResult()).getLocationSettingsStates();
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/location/LocationSettingsResponse.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */