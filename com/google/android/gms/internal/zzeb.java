package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public class zzeb implements IInterface {
  private final IBinder zzajx;
  
  private final String zzajy;
  
  protected zzeb(IBinder paramIBinder, String paramString) {
    this.zzajx = paramIBinder;
    this.zzajy = paramString;
  }
  
  public IBinder asBinder() {
    return this.zzajx;
  }
  
  protected final Parcel zza(int paramInt, Parcel paramParcel) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      this.zzajx.transact(paramInt, paramParcel, parcel, 0);
      parcel.readException();
      paramParcel.recycle();
      return parcel;
    } catch (RuntimeException runtimeException) {
      parcel.recycle();
      throw runtimeException;
    } finally {}
    paramParcel.recycle();
    throw parcel;
  }
  
  protected final Parcel zzax() {
    Parcel parcel = Parcel.obtain();
    parcel.writeInterfaceToken(this.zzajy);
    return parcel;
  }
  
  protected final void zzb(int paramInt, Parcel paramParcel) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      this.zzajx.transact(paramInt, paramParcel, parcel, 0);
      parcel.readException();
      return;
    } finally {
      paramParcel.recycle();
      parcel.recycle();
    } 
  }
  
  protected final void zzc(int paramInt, Parcel paramParcel) throws RemoteException {
    try {
      this.zzajx.transact(paramInt, paramParcel, null, 1);
      return;
    } finally {
      paramParcel.recycle();
    } 
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/internal/zzeb.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */