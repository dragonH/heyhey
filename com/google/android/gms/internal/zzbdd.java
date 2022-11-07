package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

public final class zzbdd extends zzeb implements zzbdc {
  zzbdd(IBinder paramIBinder) {
    super(paramIBinder, "com.google.android.gms.common.internal.service.ICommonService");
  }
  
  public final void zza(zzbda paramzzbda) throws RemoteException {
    Parcel parcel = zzax();
    zzed.zza(parcel, paramzzbda);
    zzc(1, parcel);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/internal/zzbdd.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */