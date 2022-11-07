package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.zzbf;
import java.util.Arrays;

@Deprecated
public final class zzcah extends zzbck {
  public static final Parcelable.Creator<zzcah> CREATOR = new zzcai();
  
  private static zzcah zziel = new zzcah("Home");
  
  private static zzcah zziem = new zzcah("Work");
  
  private final String zzien;
  
  zzcah(String paramString) {
    this.zzien = paramString;
  }
  
  public final boolean equals(Object paramObject) {
    if (this == paramObject)
      return true; 
    if (!(paramObject instanceof zzcah))
      return false; 
    paramObject = paramObject;
    return zzbf.equal(this.zzien, ((zzcah)paramObject).zzien);
  }
  
  public final int hashCode() {
    return Arrays.hashCode(new Object[] { this.zzien });
  }
  
  public final String toString() {
    return zzbf.zzt(this).zzg("alias", this.zzien).toString();
  }
  
  public final void writeToParcel(Parcel paramParcel, int paramInt) {
    paramInt = zzbcn.zze(paramParcel);
    zzbcn.zza(paramParcel, 1, this.zzien, false);
    zzbcn.zzai(paramParcel, paramInt);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/internal/zzcah.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */