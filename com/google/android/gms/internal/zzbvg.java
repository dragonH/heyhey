package com.google.android.gms.internal;

import android.os.RemoteException;

public final class zzbvg extends zzbvd<Integer> {
  public zzbvg(int paramInt, String paramString, Integer paramInteger) {
    super(0, paramString, paramInteger, null);
  }
  
  private final Integer zzc(zzbvl paramzzbvl) {
    try {
      int i = paramzzbvl.getIntFlagValue(getKey(), ((Integer)zzil()).intValue(), getSource());
      return Integer.valueOf(i);
    } catch (RemoteException remoteException) {
      return (Integer)zzil();
    } 
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/internal/zzbvg.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */