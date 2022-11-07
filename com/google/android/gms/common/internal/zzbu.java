package com.google.android.gms.common.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.internal.zzbck;
import com.google.android.gms.internal.zzbcn;

public final class zzbu extends zzbck {
  public static final Parcelable.Creator<zzbu> CREATOR = new zzbv();
  
  private int zzdxs;
  
  private final int zzfwb;
  
  private final int zzfwc;
  
  @Deprecated
  private final Scope[] zzfwd;
  
  zzbu(int paramInt1, int paramInt2, int paramInt3, Scope[] paramArrayOfScope) {
    this.zzdxs = paramInt1;
    this.zzfwb = paramInt2;
    this.zzfwc = paramInt3;
    this.zzfwd = paramArrayOfScope;
  }
  
  public zzbu(int paramInt1, int paramInt2, Scope[] paramArrayOfScope) {
    this(1, paramInt1, paramInt2, null);
  }
  
  public final void writeToParcel(Parcel paramParcel, int paramInt) {
    int i = zzbcn.zze(paramParcel);
    zzbcn.zzc(paramParcel, 1, this.zzdxs);
    zzbcn.zzc(paramParcel, 2, this.zzfwb);
    zzbcn.zzc(paramParcel, 3, this.zzfwc);
    zzbcn.zza(paramParcel, 4, (Parcelable[])this.zzfwd, paramInt, false);
    zzbcn.zzai(paramParcel, i);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/common/internal/zzbu.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */