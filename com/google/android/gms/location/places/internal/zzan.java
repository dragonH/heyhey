package com.google.android.gms.location.places.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.internal.zzbck;
import com.google.android.gms.internal.zzbcn;

public final class zzan extends zzbck {
  public static final Parcelable.Creator<zzan> CREATOR = new zze();
  
  private int zzidv;
  
  private int zzidw;
  
  zzan(int paramInt1, int paramInt2) {
    this.zzidv = paramInt1;
    this.zzidw = paramInt2;
  }
  
  public final void writeToParcel(Parcel paramParcel, int paramInt) {
    paramInt = zzbcn.zze(paramParcel);
    zzbcn.zzc(paramParcel, 1, this.zzidv);
    zzbcn.zzc(paramParcel, 2, this.zzidw);
    zzbcn.zzai(paramParcel, paramInt);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/location/places/internal/zzan.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */