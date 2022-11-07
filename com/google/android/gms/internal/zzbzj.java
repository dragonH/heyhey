package com.google.android.gms.internal;

import android.app.PendingIntent;
import android.os.Parcel;
import android.os.RemoteException;

public abstract class zzbzj extends zzec implements zzbzi {
  public zzbzj() {
    attachInterface(this, "com.google.android.gms.location.internal.IGeofencerCallbacks");
  }
  
  public boolean onTransact(int paramInt1, Parcel paramParcel1, Parcel paramParcel2, int paramInt2) throws RemoteException {
    if (zza(paramInt1, paramParcel1, paramParcel2, paramInt2))
      return true; 
    if (paramInt1 != 1) {
      if (paramInt1 != 2) {
        if (paramInt1 != 3)
          return false; 
        zza(paramParcel1.readInt(), zzed.<PendingIntent>zza(paramParcel1, PendingIntent.CREATOR));
      } else {
        zzb(paramParcel1.readInt(), paramParcel1.createStringArray());
      } 
    } else {
      zza(paramParcel1.readInt(), paramParcel1.createStringArray());
    } 
    return true;
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/internal/zzbzj.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */