package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.zzbf;
import java.util.Arrays;
import java.util.List;

@Deprecated
public final class zzcaj extends zzbck {
  public static final Parcelable.Creator<zzcaj> CREATOR = new zzcal();
  
  private final String zzdxf;
  
  private final String zzibj;
  
  private final List<zzcah> zzieo;
  
  zzcaj(String paramString1, String paramString2, List<zzcah> paramList) {
    this.zzdxf = paramString1;
    this.zzibj = paramString2;
    this.zzieo = paramList;
  }
  
  public final boolean equals(Object paramObject) {
    if (this == paramObject)
      return true; 
    if (!(paramObject instanceof zzcaj))
      return false; 
    paramObject = paramObject;
    return (this.zzdxf.equals(((zzcaj)paramObject).zzdxf) && this.zzibj.equals(((zzcaj)paramObject).zzibj) && this.zzieo.equals(((zzcaj)paramObject).zzieo));
  }
  
  public final int hashCode() {
    return Arrays.hashCode(new Object[] { this.zzdxf, this.zzibj, this.zzieo });
  }
  
  public final String toString() {
    return zzbf.zzt(this).zzg("accountName", this.zzdxf).zzg("placeId", this.zzibj).zzg("placeAliases", this.zzieo).toString();
  }
  
  public final void writeToParcel(Parcel paramParcel, int paramInt) {
    paramInt = zzbcn.zze(paramParcel);
    zzbcn.zza(paramParcel, 1, this.zzdxf, false);
    zzbcn.zza(paramParcel, 2, this.zzibj, false);
    zzbcn.zzc(paramParcel, 6, this.zzieo, false);
    zzbcn.zzai(paramParcel, paramInt);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/internal/zzcaj.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */