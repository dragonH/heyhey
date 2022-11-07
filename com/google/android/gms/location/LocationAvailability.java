package com.google.android.gms.location;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.internal.zzbck;
import com.google.android.gms.internal.zzbcn;
import java.util.Arrays;

public final class LocationAvailability extends zzbck implements ReflectedParcelable {
  public static final Parcelable.Creator<LocationAvailability> CREATOR = new zzp();
  
  @Deprecated
  private int zzhxs;
  
  @Deprecated
  private int zzhxt;
  
  private long zzhxu;
  
  private int zzhxv;
  
  private zzy[] zzhxw;
  
  LocationAvailability(int paramInt1, int paramInt2, int paramInt3, long paramLong, zzy[] paramArrayOfzzy) {
    this.zzhxv = paramInt1;
    this.zzhxs = paramInt2;
    this.zzhxt = paramInt3;
    this.zzhxu = paramLong;
    this.zzhxw = paramArrayOfzzy;
  }
  
  public static LocationAvailability extractLocationAvailability(Intent paramIntent) {
    return !hasLocationAvailability(paramIntent) ? null : (LocationAvailability)paramIntent.getExtras().getParcelable("com.google.android.gms.location.EXTRA_LOCATION_AVAILABILITY");
  }
  
  public static boolean hasLocationAvailability(Intent paramIntent) {
    return (paramIntent == null) ? false : paramIntent.hasExtra("com.google.android.gms.location.EXTRA_LOCATION_AVAILABILITY");
  }
  
  public final boolean equals(Object paramObject) {
    if (this == paramObject)
      return true; 
    if (paramObject != null && LocationAvailability.class == paramObject.getClass()) {
      paramObject = paramObject;
      if (this.zzhxs == ((LocationAvailability)paramObject).zzhxs && this.zzhxt == ((LocationAvailability)paramObject).zzhxt && this.zzhxu == ((LocationAvailability)paramObject).zzhxu && this.zzhxv == ((LocationAvailability)paramObject).zzhxv && Arrays.equals((Object[])this.zzhxw, (Object[])((LocationAvailability)paramObject).zzhxw))
        return true; 
    } 
    return false;
  }
  
  public final int hashCode() {
    return Arrays.hashCode(new Object[] { Integer.valueOf(this.zzhxv), Integer.valueOf(this.zzhxs), Integer.valueOf(this.zzhxt), Long.valueOf(this.zzhxu), this.zzhxw });
  }
  
  public final boolean isLocationAvailable() {
    return (this.zzhxv < 1000);
  }
  
  public final String toString() {
    boolean bool = isLocationAvailable();
    StringBuilder stringBuilder = new StringBuilder(48);
    stringBuilder.append("LocationAvailability[isLocationAvailable: ");
    stringBuilder.append(bool);
    stringBuilder.append("]");
    return stringBuilder.toString();
  }
  
  public final void writeToParcel(Parcel paramParcel, int paramInt) {
    int i = zzbcn.zze(paramParcel);
    zzbcn.zzc(paramParcel, 1, this.zzhxs);
    zzbcn.zzc(paramParcel, 2, this.zzhxt);
    zzbcn.zza(paramParcel, 3, this.zzhxu);
    zzbcn.zzc(paramParcel, 4, this.zzhxv);
    zzbcn.zza(paramParcel, 5, (Parcelable[])this.zzhxw, paramInt, false);
    zzbcn.zzai(paramParcel, i);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/location/LocationAvailability.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */