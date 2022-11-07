package com.google.android.gms.location;

import android.location.Location;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.zzec;
import com.google.android.gms.internal.zzed;

public abstract class zzn extends zzec implements zzm {
  public zzn() {
    attachInterface(this, "com.google.android.gms.location.ILocationListener");
  }
  
  public static zzm zzbb(IBinder paramIBinder) {
    if (paramIBinder == null)
      return null; 
    IInterface iInterface = paramIBinder.queryLocalInterface("com.google.android.gms.location.ILocationListener");
    return (iInterface instanceof zzm) ? (zzm)iInterface : new zzo(paramIBinder);
  }
  
  public boolean onTransact(int paramInt1, Parcel paramParcel1, Parcel paramParcel2, int paramInt2) throws RemoteException {
    if (zza(paramInt1, paramParcel1, paramParcel2, paramInt2))
      return true; 
    if (paramInt1 == 1) {
      onLocationChanged((Location)zzed.zza(paramParcel1, Location.CREATOR));
      return true;
    } 
    return false;
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/location/zzn.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */