package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;

public final class zzbdi extends zzbck {
  public static final Parcelable.Creator<zzbdi> CREATOR = new zzbdk();
  
  private int versionCode;
  
  final String zzfwo;
  
  final int zzfwp;
  
  zzbdi(int paramInt1, String paramString, int paramInt2) {
    this.versionCode = paramInt1;
    this.zzfwo = paramString;
    this.zzfwp = paramInt2;
  }
  
  zzbdi(String paramString, int paramInt) {
    this.versionCode = 1;
    this.zzfwo = paramString;
    this.zzfwp = paramInt;
  }
  
  public final void writeToParcel(Parcel paramParcel, int paramInt) {
    paramInt = zzbcn.zze(paramParcel);
    zzbcn.zzc(paramParcel, 1, this.versionCode);
    zzbcn.zza(paramParcel, 2, this.zzfwo, false);
    zzbcn.zzc(paramParcel, 3, this.zzfwp);
    zzbcn.zzai(paramParcel, paramInt);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/internal/zzbdi.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */