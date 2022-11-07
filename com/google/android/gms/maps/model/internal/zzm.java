package com.google.android.gms.maps.model.internal;

import android.os.IInterface;
import android.os.RemoteException;

public interface zzm extends IInterface {
  void activate() throws RemoteException;
  
  String getName() throws RemoteException;
  
  String getShortName() throws RemoteException;
  
  int hashCodeRemote() throws RemoteException;
  
  boolean zza(zzm paramzzm) throws RemoteException;
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/maps/model/internal/zzm.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */