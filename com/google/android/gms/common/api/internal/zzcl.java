package com.google.android.gms.common.api.internal;

public final class zzcl<L> {
  private final L mListener;
  
  private final String zzfox;
  
  zzcl(L paramL, String paramString) {
    this.mListener = paramL;
    this.zzfox = paramString;
  }
  
  public final boolean equals(Object paramObject) {
    if (this == paramObject)
      return true; 
    if (!(paramObject instanceof zzcl))
      return false; 
    paramObject = paramObject;
    return (this.mListener == ((zzcl)paramObject).mListener && this.zzfox.equals(((zzcl)paramObject).zzfox));
  }
  
  public final int hashCode() {
    return System.identityHashCode(this.mListener) * 31 + this.zzfox.hashCode();
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/common/api/internal/zzcl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */