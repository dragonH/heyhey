package com.google.android.gms.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public class zzec extends Binder implements IInterface {
  public IBinder asBinder() {
    return (IBinder)this;
  }
  
  protected final boolean zza(int paramInt1, Parcel paramParcel1, Parcel paramParcel2, int paramInt2) throws RemoteException {
    if (paramInt1 > 16777215)
      return onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2); 
    paramParcel1.enforceInterface(getInterfaceDescriptor());
    return false;
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/internal/zzec.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */