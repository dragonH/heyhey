package com.google.android.gms.location.places;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.zzbf;
import com.google.android.gms.common.internal.zzbh;
import com.google.android.gms.common.internal.zzbp;
import com.google.android.gms.internal.zzbck;
import com.google.android.gms.internal.zzbcn;
import java.util.Arrays;

public class PlaceReport extends zzbck implements ReflectedParcelable {
  public static final Parcelable.Creator<PlaceReport> CREATOR = new zzl();
  
  private final String mTag;
  
  private final String zzdme;
  
  private int zzdxs;
  
  private final String zzibj;
  
  PlaceReport(int paramInt, String paramString1, String paramString2, String paramString3) {
    this.zzdxs = paramInt;
    this.zzibj = paramString1;
    this.mTag = paramString2;
    this.zzdme = paramString3;
  }
  
  public static PlaceReport create(String paramString1, String paramString2) {
    zzbp.zzu(paramString1);
    zzbp.zzgg(paramString2);
    zzbp.zzgg("unknown");
    zzbp.zzb(true, "Invalid source");
    return new PlaceReport(1, paramString1, paramString2, "unknown");
  }
  
  public boolean equals(Object paramObject) {
    if (!(paramObject instanceof PlaceReport))
      return false; 
    paramObject = paramObject;
    return (zzbf.equal(this.zzibj, ((PlaceReport)paramObject).zzibj) && zzbf.equal(this.mTag, ((PlaceReport)paramObject).mTag) && zzbf.equal(this.zzdme, ((PlaceReport)paramObject).zzdme));
  }
  
  public String getPlaceId() {
    return this.zzibj;
  }
  
  public String getTag() {
    return this.mTag;
  }
  
  public int hashCode() {
    return Arrays.hashCode(new Object[] { this.zzibj, this.mTag, this.zzdme });
  }
  
  public String toString() {
    zzbh zzbh = zzbf.zzt(this);
    zzbh.zzg("placeId", this.zzibj);
    zzbh.zzg("tag", this.mTag);
    if (!"unknown".equals(this.zzdme))
      zzbh.zzg("source", this.zzdme); 
    return zzbh.toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramInt = zzbcn.zze(paramParcel);
    zzbcn.zzc(paramParcel, 1, this.zzdxs);
    zzbcn.zza(paramParcel, 2, getPlaceId(), false);
    zzbcn.zza(paramParcel, 3, getTag(), false);
    zzbcn.zza(paramParcel, 4, this.zzdme, false);
    zzbcn.zzai(paramParcel, paramInt);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/location/places/PlaceReport.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */