package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.zzbf;

public final class zzbyk extends zzbck {
  public static final Parcelable.Creator<zzbyk> CREATOR = new zzbyl();
  
  private String packageName;
  
  private int uid;
  
  public zzbyk(int paramInt, String paramString) {
    this.uid = paramInt;
    this.packageName = paramString;
  }
  
  public final boolean equals(Object paramObject) {
    if (paramObject == this)
      return true; 
    if (paramObject != null && paramObject instanceof zzbyk) {
      paramObject = paramObject;
      if (((zzbyk)paramObject).uid == this.uid && zzbf.equal(((zzbyk)paramObject).packageName, this.packageName))
        return true; 
    } 
    return false;
  }
  
  public final int hashCode() {
    return this.uid;
  }
  
  public final String toString() {
    return String.format("%d:%s", new Object[] { Integer.valueOf(this.uid), this.packageName });
  }
  
  public final void writeToParcel(Parcel paramParcel, int paramInt) {
    paramInt = zzbcn.zze(paramParcel);
    zzbcn.zzc(paramParcel, 1, this.uid);
    zzbcn.zza(paramParcel, 2, this.packageName, false);
    zzbcn.zzai(paramParcel, paramInt);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/internal/zzbyk.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */