package com.google.android.gms.location.places.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.google.android.gms.internal.zzeb;
import com.google.android.gms.internal.zzed;
import com.google.android.gms.location.places.PlaceFilter;
import com.google.android.gms.location.places.PlaceReport;

public final class zzr extends zzeb implements zzq {
  zzr(IBinder paramIBinder) {
    super(paramIBinder, "com.google.android.gms.location.places.internal.IGooglePlaceDetectionService");
  }
  
  public final void zza(PlaceFilter paramPlaceFilter, zzat paramzzat, zzw paramzzw) throws RemoteException {
    Parcel parcel = zzax();
    zzed.zza(parcel, (Parcelable)paramPlaceFilter);
    zzed.zza(parcel, (Parcelable)paramzzat);
    zzed.zza(parcel, paramzzw);
    zzb(6, parcel);
  }
  
  public final void zza(PlaceReport paramPlaceReport, zzat paramzzat, zzw paramzzw) throws RemoteException {
    Parcel parcel = zzax();
    zzed.zza(parcel, (Parcelable)paramPlaceReport);
    zzed.zza(parcel, (Parcelable)paramzzat);
    zzed.zza(parcel, paramzzw);
    zzb(7, parcel);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/location/places/internal/zzr.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */