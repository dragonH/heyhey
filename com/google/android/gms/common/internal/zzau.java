package com.google.android.gms.common.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.zzeb;

public final class zzau extends zzeb implements zzas {
  zzau(IBinder paramIBinder) {
    super(paramIBinder, "com.google.android.gms.common.internal.ICertData");
  }
  
  public final IObjectWrapper zzaez() throws RemoteException {
    Parcel parcel = zza(1, zzax());
    IObjectWrapper iObjectWrapper = IObjectWrapper.zza.zzao(parcel.readStrongBinder());
    parcel.recycle();
    return iObjectWrapper;
  }
  
  public final int zzafa() throws RemoteException {
    Parcel parcel = zza(2, zzax());
    int i = parcel.readInt();
    parcel.recycle();
    return i;
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/common/internal/zzau.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */