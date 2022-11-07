package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.zzbq;

public final class zzcqd extends zzbck {
  public static final Parcelable.Creator<zzcqd> CREATOR = new zzcqe();
  
  private int zzdxs;
  
  private zzbq zzjnx;
  
  zzcqd(int paramInt, zzbq paramzzbq) {
    this.zzdxs = paramInt;
    this.zzjnx = paramzzbq;
  }
  
  public zzcqd(zzbq paramzzbq) {
    this(1, paramzzbq);
  }
  
  public final void writeToParcel(Parcel paramParcel, int paramInt) {
    int i = zzbcn.zze(paramParcel);
    zzbcn.zzc(paramParcel, 1, this.zzdxs);
    zzbcn.zza(paramParcel, 2, (Parcelable)this.zzjnx, paramInt, false);
    zzbcn.zzai(paramParcel, i);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/internal/zzcqd.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */