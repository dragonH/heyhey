package com.google.android.gms.location.places;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.data.AbstractDataBuffer;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.internal.zzbf;
import com.google.android.gms.location.places.internal.zzd;

public class AutocompletePredictionBuffer extends AbstractDataBuffer<AutocompletePrediction> implements Result {
  public AutocompletePredictionBuffer(DataHolder paramDataHolder) {
    super(paramDataHolder);
  }
  
  public AutocompletePrediction get(int paramInt) {
    return (AutocompletePrediction)new zzd(this.zzflf, paramInt);
  }
  
  public Status getStatus() {
    return PlacesStatusCodes.zzcl(this.zzflf.getStatusCode());
  }
  
  public String toString() {
    return zzbf.zzt(this).zzg("status", getStatus()).toString();
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/location/places/AutocompletePredictionBuffer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */