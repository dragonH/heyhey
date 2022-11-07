package com.google.android.gms.location.places.internal;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.zzec;
import com.google.android.gms.internal.zzed;
import com.google.android.gms.location.places.PlacePhotoMetadataResult;
import com.google.android.gms.location.places.PlacePhotoResult;

public abstract class zzv extends zzec implements zzu {
  public zzv() {
    attachInterface(this, "com.google.android.gms.location.places.internal.IPhotosCallbacks");
  }
  
  public boolean onTransact(int paramInt1, Parcel paramParcel1, Parcel paramParcel2, int paramInt2) throws RemoteException {
    if (zza(paramInt1, paramParcel1, paramParcel2, paramInt2))
      return true; 
    if (paramInt1 != 2) {
      if (paramInt1 != 3)
        return false; 
      zza((PlacePhotoMetadataResult)zzed.zza(paramParcel1, PlacePhotoMetadataResult.CREATOR));
    } else {
      zza((PlacePhotoResult)zzed.zza(paramParcel1, PlacePhotoResult.CREATOR));
    } 
    return true;
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/location/places/internal/zzv.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */