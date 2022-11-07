package com.google.android.gms.common.api;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.zzbp;
import com.google.android.gms.internal.zzbck;
import com.google.android.gms.internal.zzbcn;

public final class Scope extends zzbck implements ReflectedParcelable {
  public static final Parcelable.Creator<Scope> CREATOR = new zzf();
  
  private int zzdxs;
  
  private final String zzfhu;
  
  Scope(int paramInt, String paramString) {
    zzbp.zzh(paramString, "scopeUri must not be null or empty");
    this.zzdxs = paramInt;
    this.zzfhu = paramString;
  }
  
  public Scope(String paramString) {
    this(1, paramString);
  }
  
  public final boolean equals(Object paramObject) {
    return (this == paramObject) ? true : (!(paramObject instanceof Scope) ? false : this.zzfhu.equals(((Scope)paramObject).zzfhu));
  }
  
  public final int hashCode() {
    return this.zzfhu.hashCode();
  }
  
  public final String toString() {
    return this.zzfhu;
  }
  
  public final void writeToParcel(Parcel paramParcel, int paramInt) {
    paramInt = zzbcn.zze(paramParcel);
    zzbcn.zzc(paramParcel, 1, this.zzdxs);
    zzbcn.zza(paramParcel, 2, this.zzfhu, false);
    zzbcn.zzai(paramParcel, paramInt);
  }
  
  public final String zzaft() {
    return this.zzfhu;
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/common/api/Scope.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */