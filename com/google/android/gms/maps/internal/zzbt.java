package com.google.android.gms.maps.internal;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.zzec;
import com.google.android.gms.internal.zzed;

public abstract class zzbt extends zzec implements zzbs {
  public zzbt() {
    attachInterface(this, "com.google.android.gms.maps.internal.ISnapshotReadyCallback");
  }
  
  public boolean onTransact(int paramInt1, Parcel paramParcel1, Parcel paramParcel2, int paramInt2) throws RemoteException {
    if (zza(paramInt1, paramParcel1, paramParcel2, paramInt2))
      return true; 
    if (paramInt1 != 1) {
      if (paramInt1 != 2)
        return false; 
      zzz(IObjectWrapper.zza.zzao(paramParcel1.readStrongBinder()));
    } else {
      onSnapshotReady((Bitmap)zzed.zza(paramParcel1, Bitmap.CREATOR));
    } 
    paramParcel2.writeNoException();
    return true;
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/maps/internal/zzbt.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */