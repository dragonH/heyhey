package com.google.android.gms.location.places;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.internal.zzbck;
import com.google.android.gms.internal.zzbcn;

public class PlacePhotoMetadataResult extends zzbck implements Result {
  public static final Parcelable.Creator<PlacePhotoMetadataResult> CREATOR = new zzj();
  
  private final Status mStatus;
  
  private DataHolder zzibg;
  
  private final PlacePhotoMetadataBuffer zzibh;
  
  public PlacePhotoMetadataResult(Status paramStatus, DataHolder paramDataHolder) {
    PlacePhotoMetadataBuffer placePhotoMetadataBuffer;
    this.mStatus = paramStatus;
    this.zzibg = paramDataHolder;
    if (paramDataHolder == null) {
      paramStatus = null;
    } else {
      placePhotoMetadataBuffer = new PlacePhotoMetadataBuffer(paramDataHolder);
    } 
    this.zzibh = placePhotoMetadataBuffer;
  }
  
  public PlacePhotoMetadataBuffer getPhotoMetadata() {
    return this.zzibh;
  }
  
  public Status getStatus() {
    return this.mStatus;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    int i = zzbcn.zze(paramParcel);
    zzbcn.zza(paramParcel, 1, (Parcelable)getStatus(), paramInt, false);
    zzbcn.zza(paramParcel, 2, (Parcelable)this.zzibg, paramInt, false);
    zzbcn.zzai(paramParcel, i);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/location/places/PlacePhotoMetadataResult.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */