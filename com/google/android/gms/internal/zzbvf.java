package com.google.android.gms.internal;

import android.os.RemoteException;

public final class zzbvf extends zzbvd<Boolean> {
  public zzbvf(int paramInt, String paramString, Boolean paramBoolean) {
    super(0, paramString, paramBoolean, null);
  }
  
  private final Boolean zzb(zzbvl paramzzbvl) {
    try {
      boolean bool = paramzzbvl.getBooleanFlagValue(getKey(), ((Boolean)zzil()).booleanValue(), getSource());
      return Boolean.valueOf(bool);
    } catch (RemoteException remoteException) {
      return (Boolean)zzil();
    } 
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/internal/zzbvf.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */