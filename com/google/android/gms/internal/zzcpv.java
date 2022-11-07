package com.google.android.gms.internal;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;

public final class zzcpv extends zzbck implements Result {
  public static final Parcelable.Creator<zzcpv> CREATOR = new zzcpw();
  
  private int zzdxs;
  
  private int zzjnt;
  
  private Intent zzjnu;
  
  public zzcpv() {
    this(0, null);
  }
  
  zzcpv(int paramInt1, int paramInt2, Intent paramIntent) {
    this.zzdxs = paramInt1;
    this.zzjnt = paramInt2;
    this.zzjnu = paramIntent;
  }
  
  private zzcpv(int paramInt, Intent paramIntent) {
    this(2, 0, null);
  }
  
  public final Status getStatus() {
    return (this.zzjnt == 0) ? Status.zzfhv : Status.zzfhz;
  }
  
  public final void writeToParcel(Parcel paramParcel, int paramInt) {
    int i = zzbcn.zze(paramParcel);
    zzbcn.zzc(paramParcel, 1, this.zzdxs);
    zzbcn.zzc(paramParcel, 2, this.zzjnt);
    zzbcn.zza(paramParcel, 3, (Parcelable)this.zzjnu, paramInt, false);
    zzbcn.zzai(paramParcel, i);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/internal/zzcpv.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */