package com.google.android.gms.internal;

import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

public interface zzbvl extends IInterface {
  boolean getBooleanFlagValue(String paramString, boolean paramBoolean, int paramInt) throws RemoteException;
  
  int getIntFlagValue(String paramString, int paramInt1, int paramInt2) throws RemoteException;
  
  long getLongFlagValue(String paramString, long paramLong, int paramInt) throws RemoteException;
  
  String getStringFlagValue(String paramString1, String paramString2, int paramInt) throws RemoteException;
  
  void init(IObjectWrapper paramIObjectWrapper) throws RemoteException;
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/internal/zzbvl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */