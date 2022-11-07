package com.google.android.gms.maps.model;

import android.os.RemoteException;

public final class RuntimeRemoteException extends RuntimeException {
  public RuntimeRemoteException(RemoteException paramRemoteException) {
    super((Throwable)paramRemoteException);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/maps/model/RuntimeRemoteException.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */