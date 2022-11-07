package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.zzbs;

public final class zzcqf extends zzbck {
  public static final Parcelable.Creator<zzcqf> CREATOR = new zzcqg();
  
  private int zzdxs;
  
  private final ConnectionResult zzfiz;
  
  private final zzbs zzjny;
  
  public zzcqf(int paramInt) {
    this(new ConnectionResult(8, null), null);
  }
  
  zzcqf(int paramInt, ConnectionResult paramConnectionResult, zzbs paramzzbs) {
    this.zzdxs = paramInt;
    this.zzfiz = paramConnectionResult;
    this.zzjny = paramzzbs;
  }
  
  private zzcqf(ConnectionResult paramConnectionResult, zzbs paramzzbs) {
    this(1, paramConnectionResult, null);
  }
  
  public final void writeToParcel(Parcel paramParcel, int paramInt) {
    int i = zzbcn.zze(paramParcel);
    zzbcn.zzc(paramParcel, 1, this.zzdxs);
    zzbcn.zza(paramParcel, 2, (Parcelable)this.zzfiz, paramInt, false);
    zzbcn.zza(paramParcel, 3, (Parcelable)this.zzjny, paramInt, false);
    zzbcn.zzai(paramParcel, i);
  }
  
  public final ConnectionResult zzagd() {
    return this.zzfiz;
  }
  
  public final zzbs zzbcc() {
    return this.zzjny;
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/internal/zzcqf.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */