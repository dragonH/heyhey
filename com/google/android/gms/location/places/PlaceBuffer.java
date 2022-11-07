package com.google.android.gms.location.places;

import android.support.annotation.Nullable;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.data.AbstractDataBuffer;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.location.places.internal.zzas;

public class PlaceBuffer extends AbstractDataBuffer<Place> implements Result {
  private final Status mStatus;
  
  private final String zzias;
  
  public PlaceBuffer(DataHolder paramDataHolder) {
    super(paramDataHolder);
    this.mStatus = PlacesStatusCodes.zzcl(paramDataHolder.getStatusCode());
    if (paramDataHolder.zzafi() != null) {
      String str = paramDataHolder.zzafi().getString("com.google.android.gms.location.places.PlaceBuffer.ATTRIBUTIONS_EXTRA_KEY");
    } else {
      paramDataHolder = null;
    } 
    this.zzias = (String)paramDataHolder;
  }
  
  public Place get(int paramInt) {
    return (Place)new zzas(this.zzflf, paramInt);
  }
  
  @Nullable
  public CharSequence getAttributions() {
    return this.zzias;
  }
  
  public Status getStatus() {
    return this.mStatus;
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/location/places/PlaceBuffer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */