package com.google.android.gms.location.places;

import com.google.android.gms.common.data.AbstractDataBuffer;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.location.places.internal.zzar;

public class PlacePhotoMetadataBuffer extends AbstractDataBuffer<PlacePhotoMetadata> {
  public PlacePhotoMetadataBuffer(DataHolder paramDataHolder) {
    super(paramDataHolder);
  }
  
  public PlacePhotoMetadata get(int paramInt) {
    return (PlacePhotoMetadata)new zzar(this.zzflf, paramInt);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/location/places/PlacePhotoMetadataBuffer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */