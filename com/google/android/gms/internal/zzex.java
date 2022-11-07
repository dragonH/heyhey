package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

public final class zzex extends zzeb implements zzev {
  zzex(IBinder paramIBinder) {
    super(paramIBinder, "com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
  }
  
  public final String getId() throws RemoteException {
    Parcel parcel = zza(1, zzax());
    String str = parcel.readString();
    parcel.recycle();
    return str;
  }
  
  public final boolean zzb(boolean paramBoolean) throws RemoteException {
    Parcel parcel = zzax();
    zzed.zza(parcel, true);
    parcel = zza(2, parcel);
    paramBoolean = zzed.zza(parcel);
    parcel.recycle();
    return paramBoolean;
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/internal/zzex.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */