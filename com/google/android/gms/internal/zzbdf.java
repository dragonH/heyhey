package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;

public final class zzbdf extends zzbck {
  public static final Parcelable.Creator<zzbdf> CREATOR = new zzbdg();
  
  private int zzdxs;
  
  private final zzbdh zzfwk;
  
  zzbdf(int paramInt, zzbdh paramzzbdh) {
    this.zzdxs = paramInt;
    this.zzfwk = paramzzbdh;
  }
  
  private zzbdf(zzbdh paramzzbdh) {
    this.zzdxs = 1;
    this.zzfwk = paramzzbdh;
  }
  
  public static zzbdf zza(zzbdn<?, ?> paramzzbdn) {
    if (paramzzbdn instanceof zzbdh)
      return new zzbdf((zzbdh)paramzzbdn); 
    throw new IllegalArgumentException("Unsupported safe parcelable field converter class.");
  }
  
  public final void writeToParcel(Parcel paramParcel, int paramInt) {
    int i = zzbcn.zze(paramParcel);
    zzbcn.zzc(paramParcel, 1, this.zzdxs);
    zzbcn.zza(paramParcel, 2, this.zzfwk, paramInt, false);
    zzbcn.zzai(paramParcel, i);
  }
  
  public final zzbdn<?, ?> zzakp() {
    zzbdh zzbdh1 = this.zzfwk;
    if (zzbdh1 != null)
      return zzbdh1; 
    throw new IllegalStateException("There was no converter wrapped in this ConverterWrapper.");
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/internal/zzbdf.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */