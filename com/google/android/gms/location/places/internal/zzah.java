package com.google.android.gms.location.places.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.zzbf;
import com.google.android.gms.internal.zzbck;
import com.google.android.gms.internal.zzbcn;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.PlaceLikelihood;
import java.util.Arrays;

public final class zzah extends zzbck implements PlaceLikelihood {
  public static final Parcelable.Creator<zzah> CREATOR = new zzai();
  
  private PlaceEntity zzido;
  
  private float zzidp;
  
  zzah(PlaceEntity paramPlaceEntity, float paramFloat) {
    this.zzido = paramPlaceEntity;
    this.zzidp = paramFloat;
  }
  
  public final boolean equals(Object paramObject) {
    if (this == paramObject)
      return true; 
    if (!(paramObject instanceof zzah))
      return false; 
    paramObject = paramObject;
    return (this.zzido.equals(((zzah)paramObject).zzido) && this.zzidp == ((zzah)paramObject).zzidp);
  }
  
  public final float getLikelihood() {
    return this.zzidp;
  }
  
  public final Place getPlace() {
    return this.zzido;
  }
  
  public final int hashCode() {
    return Arrays.hashCode(new Object[] { this.zzido, Float.valueOf(this.zzidp) });
  }
  
  public final boolean isDataValid() {
    return true;
  }
  
  public final String toString() {
    return zzbf.zzt(this).zzg("place", this.zzido).zzg("likelihood", Float.valueOf(this.zzidp)).toString();
  }
  
  public final void writeToParcel(Parcel paramParcel, int paramInt) {
    int i = zzbcn.zze(paramParcel);
    zzbcn.zza(paramParcel, 1, (Parcelable)this.zzido, paramInt, false);
    zzbcn.zza(paramParcel, 2, this.zzidp);
    zzbcn.zzai(paramParcel, i);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/location/places/internal/zzah.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */