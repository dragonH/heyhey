package com.google.android.gms.internal;

import android.os.RemoteException;

public final class zzbvh extends zzbvd<Long> {
  public zzbvh(int paramInt, String paramString, Long paramLong) {
    super(0, paramString, paramLong, null);
  }
  
  private final Long zzd(zzbvl paramzzbvl) {
    try {
      long l = paramzzbvl.getLongFlagValue(getKey(), ((Long)zzil()).longValue(), getSource());
      return Long.valueOf(l);
    } catch (RemoteException remoteException) {
      return (Long)zzil();
    } 
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/internal/zzbvh.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */