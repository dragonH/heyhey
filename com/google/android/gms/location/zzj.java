package com.google.android.gms.location;

import android.os.IInterface;
import android.os.RemoteException;

public interface zzj extends IInterface {
  void onLocationAvailability(LocationAvailability paramLocationAvailability) throws RemoteException;
  
  void onLocationResult(LocationResult paramLocationResult) throws RemoteException;
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/location/zzj.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */