package com.google.android.gms.common.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

final class zzay implements zzax {
  private final IBinder zzajx;
  
  zzay(IBinder paramIBinder) {
    this.zzajx = paramIBinder;
  }
  
  public final IBinder asBinder() {
    return this.zzajx;
  }
  
  public final void zza(zzav paramzzav, zzy paramzzy) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("com.google.android.gms.common.internal.IGmsServiceBroker");
      parcel1.writeStrongBinder(paramzzav.asBinder());
      parcel1.writeInt(1);
      paramzzy.writeToParcel(parcel1, 0);
      this.zzajx.transact(46, parcel1, parcel2, 0);
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/common/internal/zzay.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */