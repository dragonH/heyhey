package com.google.android.gms.maps.model.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.zzeb;
import com.google.android.gms.internal.zzed;

public final class zzo extends zzeb implements zzm {
  zzo(IBinder paramIBinder) {
    super(paramIBinder, "com.google.android.gms.maps.model.internal.IIndoorLevelDelegate");
  }
  
  public final void activate() throws RemoteException {
    zzb(3, zzax());
  }
  
  public final String getName() throws RemoteException {
    Parcel parcel = zza(1, zzax());
    String str = parcel.readString();
    parcel.recycle();
    return str;
  }
  
  public final String getShortName() throws RemoteException {
    Parcel parcel = zza(2, zzax());
    String str = parcel.readString();
    parcel.recycle();
    return str;
  }
  
  public final int hashCodeRemote() throws RemoteException {
    Parcel parcel = zza(5, zzax());
    int i = parcel.readInt();
    parcel.recycle();
    return i;
  }
  
  public final boolean zza(zzm paramzzm) throws RemoteException {
    Parcel parcel2 = zzax();
    zzed.zza(parcel2, paramzzm);
    Parcel parcel1 = zza(4, parcel2);
    boolean bool = zzed.zza(parcel1);
    parcel1.recycle();
    return bool;
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/maps/model/internal/zzo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */