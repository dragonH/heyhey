package com.google.android.gms.internal;

import android.os.RemoteException;

public final class zzbvi extends zzbvd<String> {
  public zzbvi(int paramInt, String paramString1, String paramString2) {
    super(0, paramString1, paramString2, null);
  }
  
  private final String zze(zzbvl paramzzbvl) {
    try {
      return paramzzbvl.getStringFlagValue(getKey(), (String)zzil(), getSource());
    } catch (RemoteException remoteException) {
      return (String)zzil();
    } 
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/internal/zzbvi.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */