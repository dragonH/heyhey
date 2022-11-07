package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;

public final class zzbdt extends zzbck {
  public static final Parcelable.Creator<zzbdt> CREATOR = new zzbdq();
  
  final String key;
  
  private int versionCode;
  
  final zzbdm<?, ?> zzfxe;
  
  zzbdt(int paramInt, String paramString, zzbdm<?, ?> paramzzbdm) {
    this.versionCode = paramInt;
    this.key = paramString;
    this.zzfxe = paramzzbdm;
  }
  
  zzbdt(String paramString, zzbdm<?, ?> paramzzbdm) {
    this.versionCode = 1;
    this.key = paramString;
    this.zzfxe = paramzzbdm;
  }
  
  public final void writeToParcel(Parcel paramParcel, int paramInt) {
    int i = zzbcn.zze(paramParcel);
    zzbcn.zzc(paramParcel, 1, this.versionCode);
    zzbcn.zza(paramParcel, 2, this.key, false);
    zzbcn.zza(paramParcel, 3, this.zzfxe, paramInt, false);
    zzbcn.zzai(paramParcel, i);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/internal/zzbdt.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */