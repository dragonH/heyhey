package com.google.android.gms.internal;

import android.app.PendingIntent;
import android.location.Location;
import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.location.GeofencingRequest;
import com.google.android.gms.location.LocationAvailability;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.zzaa;

public interface zzbzk extends IInterface {
  void zza(long paramLong, boolean paramBoolean, PendingIntent paramPendingIntent) throws RemoteException;
  
  void zza(zzbzf paramzzbzf) throws RemoteException;
  
  void zza(zzcaa paramzzcaa) throws RemoteException;
  
  void zza(GeofencingRequest paramGeofencingRequest, PendingIntent paramPendingIntent, zzbzi paramzzbzi) throws RemoteException;
  
  void zza(LocationSettingsRequest paramLocationSettingsRequest, zzbzm paramzzbzm, String paramString) throws RemoteException;
  
  void zza(zzaa paramzzaa, zzbzi paramzzbzi) throws RemoteException;
  
  void zzbk(boolean paramBoolean) throws RemoteException;
  
  void zzc(PendingIntent paramPendingIntent) throws RemoteException;
  
  void zzc(Location paramLocation) throws RemoteException;
  
  Location zzia(String paramString) throws RemoteException;
  
  LocationAvailability zzib(String paramString) throws RemoteException;
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/internal/zzbzk.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */