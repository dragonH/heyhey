package com.google.android.gms.maps.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.zzec;

public abstract class zzbq extends zzec implements zzbp {
  public zzbq() {
    attachInterface(this, "com.google.android.gms.maps.internal.IOnStreetViewPanoramaReadyCallback");
  }
  
  public boolean onTransact(int paramInt1, Parcel paramParcel1, Parcel paramParcel2, int paramInt2) throws RemoteException {
    if (zza(paramInt1, paramParcel1, paramParcel2, paramInt2))
      return true; 
    if (paramInt1 == 1) {
      IInterface iInterface;
      IBinder iBinder = paramParcel1.readStrongBinder();
      if (iBinder == null) {
        paramParcel1 = null;
      } else {
        iInterface = iBinder.queryLocalInterface("com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate");
        if (iInterface instanceof IStreetViewPanoramaDelegate) {
          iInterface = iInterface;
        } else {
          iInterface = new zzbu(iBinder);
        } 
      } 
      zza((IStreetViewPanoramaDelegate)iInterface);
      paramParcel2.writeNoException();
      return true;
    } 
    return false;
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/maps/internal/zzbq.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */