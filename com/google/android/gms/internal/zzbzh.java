package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

public final class zzbzh extends zzeb implements zzbzf {
  zzbzh(IBinder paramIBinder) {
    super(paramIBinder, "com.google.android.gms.location.internal.IFusedLocationProviderCallback");
  }
  
  public final void zza(zzbyz paramzzbyz) throws RemoteException {
    Parcel parcel = zzax();
    zzed.zza(parcel, paramzzbyz);
    zzc(1, parcel);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/internal/zzbzh.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */