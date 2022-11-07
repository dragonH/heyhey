package com.google.android.gms.maps.model.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.zzeb;
import com.google.android.gms.internal.zzed;
import java.util.ArrayList;
import java.util.List;

public final class zzl extends zzeb implements zzj {
  zzl(IBinder paramIBinder) {
    super(paramIBinder, "com.google.android.gms.maps.model.internal.IIndoorBuildingDelegate");
  }
  
  public final int getActiveLevelIndex() throws RemoteException {
    Parcel parcel = zza(1, zzax());
    int i = parcel.readInt();
    parcel.recycle();
    return i;
  }
  
  public final List<IBinder> getLevels() throws RemoteException {
    Parcel parcel = zza(3, zzax());
    ArrayList<IBinder> arrayList = parcel.createBinderArrayList();
    parcel.recycle();
    return arrayList;
  }
  
  public final int hashCodeRemote() throws RemoteException {
    Parcel parcel = zza(6, zzax());
    int i = parcel.readInt();
    parcel.recycle();
    return i;
  }
  
  public final boolean isUnderground() throws RemoteException {
    Parcel parcel = zza(4, zzax());
    boolean bool = zzed.zza(parcel);
    parcel.recycle();
    return bool;
  }
  
  public final boolean zzb(zzj paramzzj) throws RemoteException {
    Parcel parcel2 = zzax();
    zzed.zza(parcel2, paramzzj);
    Parcel parcel1 = zza(5, parcel2);
    boolean bool = zzed.zza(parcel1);
    parcel1.recycle();
    return bool;
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/maps/model/internal/zzl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */