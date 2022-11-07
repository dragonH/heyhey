package com.google.android.gms.location.places.internal;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.internal.zzbp;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.PlaceLikelihood;

public final class zzaj extends zzav implements PlaceLikelihood {
  public zzaj(DataHolder paramDataHolder, int paramInt) {
    super(paramDataHolder, paramInt);
  }
  
  public final float getLikelihood() {
    return zza("place_likelihood", -1.0F);
  }
  
  public final Place getPlace() {
    return new zzas(this.zzflf, this.zzfqh);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/location/places/internal/zzaj.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */