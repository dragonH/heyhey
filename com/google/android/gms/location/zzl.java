package com.google.android.gms.location;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.google.android.gms.internal.zzeb;
import com.google.android.gms.internal.zzed;

public final class zzl extends zzeb implements zzj {
  zzl(IBinder paramIBinder) {
    super(paramIBinder, "com.google.android.gms.location.ILocationCallback");
  }
  
  public final void onLocationAvailability(LocationAvailability paramLocationAvailability) throws RemoteException {
    Parcel parcel = zzax();
    zzed.zza(parcel, (Parcelable)paramLocationAvailability);
    zzc(2, parcel);
  }
  
  public final void onLocationResult(LocationResult paramLocationResult) throws RemoteException {
    Parcel parcel = zzax();
    zzed.zza(parcel, (Parcelable)paramLocationResult);
    zzc(1, parcel);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/location/zzl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */