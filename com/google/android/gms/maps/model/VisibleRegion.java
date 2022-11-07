package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.zzbf;
import com.google.android.gms.internal.zzbck;
import com.google.android.gms.internal.zzbcn;
import java.util.Arrays;

public final class VisibleRegion extends zzbck {
  public static final Parcelable.Creator<VisibleRegion> CREATOR = new zzu();
  
  public final LatLng farLeft;
  
  public final LatLng farRight;
  
  public final LatLngBounds latLngBounds;
  
  public final LatLng nearLeft;
  
  public final LatLng nearRight;
  
  public VisibleRegion(LatLng paramLatLng1, LatLng paramLatLng2, LatLng paramLatLng3, LatLng paramLatLng4, LatLngBounds paramLatLngBounds) {
    this.nearLeft = paramLatLng1;
    this.nearRight = paramLatLng2;
    this.farLeft = paramLatLng3;
    this.farRight = paramLatLng4;
    this.latLngBounds = paramLatLngBounds;
  }
  
  public final boolean equals(Object paramObject) {
    if (this == paramObject)
      return true; 
    if (!(paramObject instanceof VisibleRegion))
      return false; 
    paramObject = paramObject;
    return (this.nearLeft.equals(((VisibleRegion)paramObject).nearLeft) && this.nearRight.equals(((VisibleRegion)paramObject).nearRight) && this.farLeft.equals(((VisibleRegion)paramObject).farLeft) && this.farRight.equals(((VisibleRegion)paramObject).farRight) && this.latLngBounds.equals(((VisibleRegion)paramObject).latLngBounds));
  }
  
  public final int hashCode() {
    return Arrays.hashCode(new Object[] { this.nearLeft, this.nearRight, this.farLeft, this.farRight, this.latLngBounds });
  }
  
  public final String toString() {
    return zzbf.zzt(this).zzg("nearLeft", this.nearLeft).zzg("nearRight", this.nearRight).zzg("farLeft", this.farLeft).zzg("farRight", this.farRight).zzg("latLngBounds", this.latLngBounds).toString();
  }
  
  public final void writeToParcel(Parcel paramParcel, int paramInt) {
    int i = zzbcn.zze(paramParcel);
    zzbcn.zza(paramParcel, 2, (Parcelable)this.nearLeft, paramInt, false);
    zzbcn.zza(paramParcel, 3, (Parcelable)this.nearRight, paramInt, false);
    zzbcn.zza(paramParcel, 4, (Parcelable)this.farLeft, paramInt, false);
    zzbcn.zza(paramParcel, 5, (Parcelable)this.farRight, paramInt, false);
    zzbcn.zza(paramParcel, 6, (Parcelable)this.latLngBounds, paramInt, false);
    zzbcn.zzai(paramParcel, i);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/maps/model/VisibleRegion.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */