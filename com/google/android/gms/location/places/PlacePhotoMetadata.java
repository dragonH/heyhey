package com.google.android.gms.location.places;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.data.Freezable;

public interface PlacePhotoMetadata extends Freezable<PlacePhotoMetadata> {
  CharSequence getAttributions();
  
  int getMaxHeight();
  
  int getMaxWidth();
  
  PendingResult<PlacePhotoResult> getPhoto(GoogleApiClient paramGoogleApiClient);
  
  PendingResult<PlacePhotoResult> getScaledPhoto(GoogleApiClient paramGoogleApiClient, int paramInt1, int paramInt2);
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/location/places/PlacePhotoMetadata.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */