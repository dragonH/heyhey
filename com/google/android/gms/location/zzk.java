package com.google.android.gms.location;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.zzec;
import com.google.android.gms.internal.zzed;

public abstract class zzk extends zzec implements zzj {
  public zzk() {
    attachInterface(this, "com.google.android.gms.location.ILocationCallback");
  }
  
  public static zzj zzba(IBinder paramIBinder) {
    if (paramIBinder == null)
      return null; 
    IInterface iInterface = paramIBinder.queryLocalInterface("com.google.android.gms.location.ILocationCallback");
    return (iInterface instanceof zzj) ? (zzj)iInterface : new zzl(paramIBinder);
  }
  
  public boolean onTransact(int paramInt1, Parcel paramParcel1, Parcel paramParcel2, int paramInt2) throws RemoteException {
    if (zza(paramInt1, paramParcel1, paramParcel2, paramInt2))
      return true; 
    if (paramInt1 != 1) {
      if (paramInt1 != 2)
        return false; 
      onLocationAvailability((LocationAvailability)zzed.zza(paramParcel1, LocationAvailability.CREATOR));
    } else {
      onLocationResult((LocationResult)zzed.zza(paramParcel1, LocationResult.CREATOR));
    } 
    return true;
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/location/zzk.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */