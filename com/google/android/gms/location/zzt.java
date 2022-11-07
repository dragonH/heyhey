package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.internal.zzbck;
import com.google.android.gms.internal.zzbcn;

public final class zzt extends zzbck {
  public static final Parcelable.Creator<zzt> CREATOR = new zzu();
  
  private final String zzepl;
  
  private final String zzhye;
  
  private final String zzhyf;
  
  private final int zzhyg;
  
  private final boolean zzhyh;
  
  zzt(String paramString1, String paramString2, String paramString3, int paramInt, boolean paramBoolean) {
    this.zzepl = paramString1;
    this.zzhye = paramString2;
    this.zzhyf = paramString3;
    this.zzhyg = paramInt;
    this.zzhyh = paramBoolean;
  }
  
  public final void writeToParcel(Parcel paramParcel, int paramInt) {
    paramInt = zzbcn.zze(paramParcel);
    zzbcn.zza(paramParcel, 1, this.zzhye, false);
    zzbcn.zza(paramParcel, 2, this.zzhyf, false);
    zzbcn.zzc(paramParcel, 3, this.zzhyg);
    zzbcn.zza(paramParcel, 4, this.zzhyh);
    zzbcn.zza(paramParcel, 5, this.zzepl, false);
    zzbcn.zzai(paramParcel, paramInt);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/location/zzt.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */