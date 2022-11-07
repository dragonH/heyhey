package com.google.android.gms.maps.model.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import java.util.List;

public interface zzj extends IInterface {
  int getActiveLevelIndex() throws RemoteException;
  
  List<IBinder> getLevels() throws RemoteException;
  
  int hashCodeRemote() throws RemoteException;
  
  boolean isUnderground() throws RemoteException;
  
  boolean zzb(zzj paramzzj) throws RemoteException;
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/maps/model/internal/zzj.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */