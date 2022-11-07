package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.internal.zzbck;
import com.google.android.gms.internal.zzbcn;

public final class PointOfInterest extends zzbck {
  public static final Parcelable.Creator<PointOfInterest> CREATOR = new zzj();
  
  public final LatLng latLng;
  
  public final String name;
  
  public final String placeId;
  
  public PointOfInterest(LatLng paramLatLng, String paramString1, String paramString2) {
    this.latLng = paramLatLng;
    this.placeId = paramString1;
    this.name = paramString2;
  }
  
  public final void writeToParcel(Parcel paramParcel, int paramInt) {
    int i = zzbcn.zze(paramParcel);
    zzbcn.zza(paramParcel, 2, (Parcelable)this.latLng, paramInt, false);
    zzbcn.zza(paramParcel, 3, this.placeId, false);
    zzbcn.zza(paramParcel, 4, this.name, false);
    zzbcn.zzai(paramParcel, i);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/maps/model/PointOfInterest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */