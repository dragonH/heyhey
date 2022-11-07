package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;

public final class zzbyz extends zzbck implements Result {
  public static final Parcelable.Creator<zzbyz> CREATOR;
  
  private static zzbyz zzhzj = new zzbyz(Status.zzfhv);
  
  private final Status mStatus;
  
  static {
    CREATOR = new zzbza();
  }
  
  public zzbyz(Status paramStatus) {
    this.mStatus = paramStatus;
  }
  
  public final Status getStatus() {
    return this.mStatus;
  }
  
  public final void writeToParcel(Parcel paramParcel, int paramInt) {
    int i = zzbcn.zze(paramParcel);
    zzbcn.zza(paramParcel, 1, (Parcelable)getStatus(), paramInt, false);
    zzbcn.zzai(paramParcel, i);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/internal/zzbyz.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */