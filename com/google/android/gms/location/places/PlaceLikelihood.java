package com.google.android.gms.location.places;

import com.google.android.gms.common.data.Freezable;

public interface PlaceLikelihood extends Freezable<PlaceLikelihood> {
  float getLikelihood();
  
  Place getPlace();
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/location/places/PlaceLikelihood.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */