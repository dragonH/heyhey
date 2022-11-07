package com.google.android.gms.common;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.internal.zzbck;
import com.google.android.gms.internal.zzbcn;

public final class zzc extends zzbck {
  public static final Parcelable.Creator<zzc> CREATOR = new zzd();
  
  private String name;
  
  private int version;
  
  public zzc(String paramString, int paramInt) {
    this.name = paramString;
    this.version = paramInt;
  }
  
  public final void writeToParcel(Parcel paramParcel, int paramInt) {
    paramInt = zzbcn.zze(paramParcel);
    zzbcn.zza(paramParcel, 1, this.name, false);
    zzbcn.zzc(paramParcel, 2, this.version);
    zzbcn.zzai(paramParcel, paramInt);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/common/zzc.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */