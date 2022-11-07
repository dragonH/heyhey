package com.google.android.gms.location.places;

import android.support.annotation.Nullable;
import com.google.android.gms.common.api.zzb;

public class PlaceLikelihoodBufferResponse extends zzb<PlaceLikelihood, PlaceLikelihoodBuffer> {
  @Nullable
  public CharSequence getAttributions() {
    return ((PlaceLikelihoodBuffer)getResult()).getAttributions();
  }
  
  public String toString() {
    return ((PlaceLikelihoodBuffer)getResult()).toString();
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/location/places/PlaceLikelihoodBufferResponse.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */