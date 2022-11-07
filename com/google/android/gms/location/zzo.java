package com.google.android.gms.location;

import android.location.Location;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.google.android.gms.internal.zzeb;
import com.google.android.gms.internal.zzed;

public final class zzo extends zzeb implements zzm {
  zzo(IBinder paramIBinder) {
    super(paramIBinder, "com.google.android.gms.location.ILocationListener");
  }
  
  public final void onLocationChanged(Location paramLocation) throws RemoteException {
    Parcel parcel = zzax();
    zzed.zza(parcel, (Parcelable)paramLocation);
    zzc(1, parcel);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/location/zzo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */