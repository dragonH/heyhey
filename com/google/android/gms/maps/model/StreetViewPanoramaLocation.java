package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.zzbf;
import com.google.android.gms.internal.zzbck;
import com.google.android.gms.internal.zzbcn;
import java.util.Arrays;

public class StreetViewPanoramaLocation extends zzbck {
  public static final Parcelable.Creator<StreetViewPanoramaLocation> CREATOR = new zzo();
  
  public final StreetViewPanoramaLink[] links;
  
  public final String panoId;
  
  public final LatLng position;
  
  public StreetViewPanoramaLocation(StreetViewPanoramaLink[] paramArrayOfStreetViewPanoramaLink, LatLng paramLatLng, String paramString) {
    this.links = paramArrayOfStreetViewPanoramaLink;
    this.position = paramLatLng;
    this.panoId = paramString;
  }
  
  public boolean equals(Object paramObject) {
    if (this == paramObject)
      return true; 
    if (!(paramObject instanceof StreetViewPanoramaLocation))
      return false; 
    paramObject = paramObject;
    return (this.panoId.equals(((StreetViewPanoramaLocation)paramObject).panoId) && this.position.equals(((StreetViewPanoramaLocation)paramObject).position));
  }
  
  public int hashCode() {
    return Arrays.hashCode(new Object[] { this.position, this.panoId });
  }
  
  public String toString() {
    return zzbf.zzt(this).zzg("panoId", this.panoId).zzg("position", this.position.toString()).toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    int i = zzbcn.zze(paramParcel);
    zzbcn.zza(paramParcel, 2, (Parcelable[])this.links, paramInt, false);
    zzbcn.zza(paramParcel, 3, (Parcelable)this.position, paramInt, false);
    zzbcn.zza(paramParcel, 4, this.panoId, false);
    zzbcn.zzai(paramParcel, i);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/maps/model/StreetViewPanoramaLocation.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */