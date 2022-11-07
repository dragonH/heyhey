package com.google.android.gms.maps.internal;

import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.zzec;
import com.google.android.gms.internal.zzed;
import com.google.android.gms.maps.model.internal.zzq;

public abstract class zzi extends zzec implements zzh {
  public zzi() {
    attachInterface(this, "com.google.android.gms.maps.internal.IInfoWindowAdapter");
  }
  
  public boolean onTransact(int paramInt1, Parcel paramParcel1, Parcel paramParcel2, int paramInt2) throws RemoteException {
    IObjectWrapper iObjectWrapper;
    if (zza(paramInt1, paramParcel1, paramParcel2, paramInt2))
      return true; 
    if (paramInt1 != 1) {
      if (paramInt1 != 2)
        return false; 
      iObjectWrapper = zzi(zzq.zzbh(paramParcel1.readStrongBinder()));
    } else {
      iObjectWrapper = zzh(zzq.zzbh(iObjectWrapper.readStrongBinder()));
    } 
    paramParcel2.writeNoException();
    zzed.zza(paramParcel2, (IInterface)iObjectWrapper);
    return true;
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/maps/internal/zzi.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */