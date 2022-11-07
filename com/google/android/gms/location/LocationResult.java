package com.google.android.gms.location;

import android.content.Intent;
import android.location.Location;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.internal.zzbck;
import com.google.android.gms.internal.zzbcn;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public final class LocationResult extends zzbck implements ReflectedParcelable {
  public static final Parcelable.Creator<LocationResult> CREATOR;
  
  static final List<Location> zzhyc = Collections.emptyList();
  
  private final List<Location> zzhyd;
  
  static {
    CREATOR = new zzr();
  }
  
  LocationResult(List<Location> paramList) {
    this.zzhyd = paramList;
  }
  
  public static LocationResult create(List<Location> paramList) {
    List<Location> list = paramList;
    if (paramList == null)
      list = zzhyc; 
    return new LocationResult(list);
  }
  
  public static LocationResult extractResult(Intent paramIntent) {
    return !hasResult(paramIntent) ? null : (LocationResult)paramIntent.getExtras().getParcelable("com.google.android.gms.location.EXTRA_LOCATION_RESULT");
  }
  
  public static boolean hasResult(Intent paramIntent) {
    return (paramIntent == null) ? false : paramIntent.hasExtra("com.google.android.gms.location.EXTRA_LOCATION_RESULT");
  }
  
  public final boolean equals(Object paramObject) {
    if (!(paramObject instanceof LocationResult))
      return false; 
    paramObject = paramObject;
    if (((LocationResult)paramObject).zzhyd.size() != this.zzhyd.size())
      return false; 
    Iterator<Location> iterator1 = ((LocationResult)paramObject).zzhyd.iterator();
    Iterator<Location> iterator2 = this.zzhyd.iterator();
    while (iterator1.hasNext()) {
      Location location = iterator2.next();
      paramObject = iterator1.next();
      if (location.getTime() != paramObject.getTime())
        return false; 
    } 
    return true;
  }
  
  public final Location getLastLocation() {
    int i = this.zzhyd.size();
    return (i == 0) ? null : this.zzhyd.get(i - 1);
  }
  
  @NonNull
  public final List<Location> getLocations() {
    return this.zzhyd;
  }
  
  public final int hashCode() {
    Iterator<Location> iterator = this.zzhyd.iterator();
    int i;
    for (i = 17; iterator.hasNext(); i = i * 31 + (int)(l ^ l >>> 32L))
      long l = ((Location)iterator.next()).getTime(); 
    return i;
  }
  
  public final String toString() {
    String str = String.valueOf(this.zzhyd);
    StringBuilder stringBuilder = new StringBuilder(str.length() + 27);
    stringBuilder.append("LocationResult[locations: ");
    stringBuilder.append(str);
    stringBuilder.append("]");
    return stringBuilder.toString();
  }
  
  public final void writeToParcel(Parcel paramParcel, int paramInt) {
    paramInt = zzbcn.zze(paramParcel);
    zzbcn.zzc(paramParcel, 1, getLocations(), false);
    zzbcn.zzai(paramParcel, paramInt);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/location/LocationResult.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */