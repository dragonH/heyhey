package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.zzbf;
import com.google.android.gms.location.LocationRequest;
import java.util.Collections;
import java.util.List;

public final class zzbzy extends zzbck {
  public static final Parcelable.Creator<zzbzy> CREATOR;
  
  static final List<zzbyk> zzhzu = Collections.emptyList();
  
  @Nullable
  private String mTag;
  
  @Nullable
  private String zzeft;
  
  private LocationRequest zzgzt;
  
  private List<zzbyk> zzhzv;
  
  private boolean zzhzw;
  
  private boolean zzhzx;
  
  private boolean zzhzy;
  
  private boolean zzhzz = true;
  
  static {
    CREATOR = new zzbzz();
  }
  
  zzbzy(LocationRequest paramLocationRequest, List<zzbyk> paramList, @Nullable String paramString1, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, String paramString2) {
    this.zzgzt = paramLocationRequest;
    this.zzhzv = paramList;
    this.mTag = paramString1;
    this.zzhzw = paramBoolean1;
    this.zzhzx = paramBoolean2;
    this.zzhzy = paramBoolean3;
    this.zzeft = paramString2;
  }
  
  @Deprecated
  public static zzbzy zza(LocationRequest paramLocationRequest) {
    return new zzbzy(paramLocationRequest, zzhzu, null, false, false, false, null);
  }
  
  public final boolean equals(Object paramObject) {
    if (!(paramObject instanceof zzbzy))
      return false; 
    paramObject = paramObject;
    return (zzbf.equal(this.zzgzt, ((zzbzy)paramObject).zzgzt) && zzbf.equal(this.zzhzv, ((zzbzy)paramObject).zzhzv) && zzbf.equal(this.mTag, ((zzbzy)paramObject).mTag) && this.zzhzw == ((zzbzy)paramObject).zzhzw && this.zzhzx == ((zzbzy)paramObject).zzhzx && this.zzhzy == ((zzbzy)paramObject).zzhzy && zzbf.equal(this.zzeft, ((zzbzy)paramObject).zzeft));
  }
  
  public final int hashCode() {
    return this.zzgzt.hashCode();
  }
  
  public final String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(this.zzgzt.toString());
    if (this.mTag != null) {
      stringBuilder.append(" tag=");
      stringBuilder.append(this.mTag);
    } 
    if (this.zzeft != null) {
      stringBuilder.append(" moduleId=");
      stringBuilder.append(this.zzeft);
    } 
    stringBuilder.append(" hideAppOps=");
    stringBuilder.append(this.zzhzw);
    stringBuilder.append(" clients=");
    stringBuilder.append(this.zzhzv);
    stringBuilder.append(" forceCoarseLocation=");
    stringBuilder.append(this.zzhzx);
    if (this.zzhzy)
      stringBuilder.append(" exemptFromBackgroundThrottle"); 
    return stringBuilder.toString();
  }
  
  public final void writeToParcel(Parcel paramParcel, int paramInt) {
    int i = zzbcn.zze(paramParcel);
    zzbcn.zza(paramParcel, 1, (Parcelable)this.zzgzt, paramInt, false);
    zzbcn.zzc(paramParcel, 5, this.zzhzv, false);
    zzbcn.zza(paramParcel, 6, this.mTag, false);
    zzbcn.zza(paramParcel, 7, this.zzhzw);
    zzbcn.zza(paramParcel, 8, this.zzhzx);
    zzbcn.zza(paramParcel, 9, this.zzhzy);
    zzbcn.zza(paramParcel, 10, this.zzeft, false);
    zzbcn.zzai(paramParcel, i);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/internal/zzbzy.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */