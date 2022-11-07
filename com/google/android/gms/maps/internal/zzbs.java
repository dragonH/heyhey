package com.google.android.gms.maps.internal;

import android.graphics.Bitmap;
import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

public interface zzbs extends IInterface {
  void onSnapshotReady(Bitmap paramBitmap) throws RemoteException;
  
  void zzz(IObjectWrapper paramIObjectWrapper) throws RemoteException;
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/maps/internal/zzbs.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */