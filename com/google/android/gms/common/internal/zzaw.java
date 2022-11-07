package com.google.android.gms.common.internal;

import android.os.Bundle;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.zzec;
import com.google.android.gms.internal.zzed;

public abstract class zzaw extends zzec implements zzav {
  public zzaw() {
    attachInterface(this, "com.google.android.gms.common.internal.IGmsCallbacks");
  }
  
  public boolean onTransact(int paramInt1, Parcel paramParcel1, Parcel paramParcel2, int paramInt2) throws RemoteException {
    if (zza(paramInt1, paramParcel1, paramParcel2, paramInt2))
      return true; 
    if (paramInt1 != 1) {
      if (paramInt1 != 2)
        return false; 
      zza(paramParcel1.readInt(), (Bundle)zzed.zza(paramParcel1, Bundle.CREATOR));
    } else {
      zza(paramParcel1.readInt(), paramParcel1.readStrongBinder(), (Bundle)zzed.zza(paramParcel1, Bundle.CREATOR));
    } 
    paramParcel2.writeNoException();
    return true;
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/common/internal/zzaw.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */