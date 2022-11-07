package com.google.android.gms.internal;

import android.app.PendingIntent;
import android.location.Location;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.google.android.gms.location.GeofencingRequest;
import com.google.android.gms.location.LocationAvailability;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.zzaa;

public final class zzbzl extends zzeb implements zzbzk {
  zzbzl(IBinder paramIBinder) {
    super(paramIBinder, "com.google.android.gms.location.internal.IGoogleLocationManagerService");
  }
  
  public final void zza(long paramLong, boolean paramBoolean, PendingIntent paramPendingIntent) throws RemoteException {
    Parcel parcel = zzax();
    parcel.writeLong(paramLong);
    zzed.zza(parcel, true);
    zzed.zza(parcel, (Parcelable)paramPendingIntent);
    zzb(5, parcel);
  }
  
  public final void zza(zzbzf paramzzbzf) throws RemoteException {
    Parcel parcel = zzax();
    zzed.zza(parcel, paramzzbzf);
    zzb(67, parcel);
  }
  
  public final void zza(zzcaa paramzzcaa) throws RemoteException {
    Parcel parcel = zzax();
    zzed.zza(parcel, paramzzcaa);
    zzb(59, parcel);
  }
  
  public final void zza(GeofencingRequest paramGeofencingRequest, PendingIntent paramPendingIntent, zzbzi paramzzbzi) throws RemoteException {
    Parcel parcel = zzax();
    zzed.zza(parcel, (Parcelable)paramGeofencingRequest);
    zzed.zza(parcel, (Parcelable)paramPendingIntent);
    zzed.zza(parcel, paramzzbzi);
    zzb(57, parcel);
  }
  
  public final void zza(LocationSettingsRequest paramLocationSettingsRequest, zzbzm paramzzbzm, String paramString) throws RemoteException {
    Parcel parcel = zzax();
    zzed.zza(parcel, (Parcelable)paramLocationSettingsRequest);
    zzed.zza(parcel, paramzzbzm);
    parcel.writeString(paramString);
    zzb(63, parcel);
  }
  
  public final void zza(zzaa paramzzaa, zzbzi paramzzbzi) throws RemoteException {
    Parcel parcel = zzax();
    zzed.zza(parcel, (Parcelable)paramzzaa);
    zzed.zza(parcel, paramzzbzi);
    zzb(74, parcel);
  }
  
  public final void zzbk(boolean paramBoolean) throws RemoteException {
    Parcel parcel = zzax();
    zzed.zza(parcel, paramBoolean);
    zzb(12, parcel);
  }
  
  public final void zzc(PendingIntent paramPendingIntent) throws RemoteException {
    Parcel parcel = zzax();
    zzed.zza(parcel, (Parcelable)paramPendingIntent);
    zzb(6, parcel);
  }
  
  public final void zzc(Location paramLocation) throws RemoteException {
    Parcel parcel = zzax();
    zzed.zza(parcel, (Parcelable)paramLocation);
    zzb(13, parcel);
  }
  
  public final Location zzia(String paramString) throws RemoteException {
    Parcel parcel2 = zzax();
    parcel2.writeString(paramString);
    Parcel parcel1 = zza(21, parcel2);
    Location location = zzed.<Location>zza(parcel1, Location.CREATOR);
    parcel1.recycle();
    return location;
  }
  
  public final LocationAvailability zzib(String paramString) throws RemoteException {
    Parcel parcel2 = zzax();
    parcel2.writeString(paramString);
    Parcel parcel1 = zza(34, parcel2);
    LocationAvailability locationAvailability = zzed.<LocationAvailability>zza(parcel1, LocationAvailability.CREATOR);
    parcel1.recycle();
    return locationAvailability;
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/internal/zzbzl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */