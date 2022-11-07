package com.google.android.gms.maps;

import android.graphics.Bitmap;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.zzn;
import com.google.android.gms.maps.internal.zzbt;

final class zzr extends zzbt {
  zzr(GoogleMap paramGoogleMap, GoogleMap.SnapshotReadyCallback paramSnapshotReadyCallback) {}
  
  public final void onSnapshotReady(Bitmap paramBitmap) throws RemoteException {
    this.zzift.onSnapshotReady(paramBitmap);
  }
  
  public final void zzz(IObjectWrapper paramIObjectWrapper) throws RemoteException {
    this.zzift.onSnapshotReady((Bitmap)zzn.zzx(paramIObjectWrapper));
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/maps/zzr.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */