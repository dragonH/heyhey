package com.google.android.gms.location.places;

import com.google.android.gms.common.api.Response;

public class PlacePhotoMetadataResponse extends Response<PlacePhotoMetadataResult> {
  public PlacePhotoMetadataBuffer getPhotoMetadata() {
    return ((PlacePhotoMetadataResult)getResult()).getPhotoMetadata();
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/location/places/PlacePhotoMetadataResponse.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */