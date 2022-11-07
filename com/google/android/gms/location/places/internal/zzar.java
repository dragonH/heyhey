package com.google.android.gms.location.places.internal;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.location.places.PlacePhotoMetadata;
import com.google.android.gms.location.places.PlacePhotoResult;

public final class zzar extends zzav implements PlacePhotoMetadata {
  private final String zziee = getString("photo_fife_url");
  
  public zzar(DataHolder paramDataHolder, int paramInt) {
    super(paramDataHolder, paramInt);
  }
  
  public final CharSequence getAttributions() {
    return zzae("photo_attributions", null);
  }
  
  public final int getMaxHeight() {
    return zzw("photo_max_height", 0);
  }
  
  public final int getMaxWidth() {
    return zzw("photo_max_width", 0);
  }
  
  public final PendingResult<PlacePhotoResult> getPhoto(GoogleApiClient paramGoogleApiClient) {
    return getScaledPhoto(paramGoogleApiClient, getMaxWidth(), getMaxHeight());
  }
  
  public final PendingResult<PlacePhotoResult> getScaledPhoto(GoogleApiClient paramGoogleApiClient, int paramInt1, int paramInt2) {
    return ((PlacePhotoMetadata)freeze()).getScaledPhoto(paramGoogleApiClient, paramInt1, paramInt2);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/location/places/internal/zzar.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */