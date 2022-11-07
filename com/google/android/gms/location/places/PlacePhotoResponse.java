package com.google.android.gms.location.places;

import android.graphics.Bitmap;
import com.google.android.gms.common.api.Response;

public class PlacePhotoResponse extends Response<PlacePhotoResult> {
  public Bitmap getBitmap() {
    return ((PlacePhotoResult)getResult()).getBitmap();
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/location/places/PlacePhotoResponse.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */