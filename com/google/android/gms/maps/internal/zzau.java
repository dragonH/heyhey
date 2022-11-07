package com.google.android.gms.maps.internal;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.zzec;
import com.google.android.gms.maps.model.internal.zzq;

public abstract class zzau extends zzec implements zzat {
  public zzau() {
    attachInterface(this, "com.google.android.gms.maps.internal.IOnMarkerDragListener");
  }
  
  public boolean onTransact(int paramInt1, Parcel paramParcel1, Parcel paramParcel2, int paramInt2) throws RemoteException {
    if (zza(paramInt1, paramParcel1, paramParcel2, paramInt2))
      return true; 
    if (paramInt1 != 1) {
      if (paramInt1 != 2) {
        if (paramInt1 != 3)
          return false; 
        zzc(zzq.zzbh(paramParcel1.readStrongBinder()));
      } else {
        zzd(zzq.zzbh(paramParcel1.readStrongBinder()));
      } 
    } else {
      zzb(zzq.zzbh(paramParcel1.readStrongBinder()));
    } 
    paramParcel2.writeNoException();
    return true;
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/maps/internal/zzau.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */