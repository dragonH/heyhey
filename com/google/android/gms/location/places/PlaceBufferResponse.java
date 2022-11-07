package com.google.android.gms.location.places;

import android.support.annotation.Nullable;
import com.google.android.gms.common.api.zzb;

public class PlaceBufferResponse extends zzb<Place, PlaceBuffer> {
  @Nullable
  public CharSequence getAttributions() {
    return ((PlaceBuffer)getResult()).getAttributions();
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/location/places/PlaceBufferResponse.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */