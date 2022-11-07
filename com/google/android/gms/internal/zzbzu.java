package com.google.android.gms.internal;

import android.app.PendingIntent;
import android.content.Context;
import android.location.Location;
import android.os.Looper;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.zzcj;
import com.google.android.gms.common.api.internal.zzcl;
import com.google.android.gms.common.api.internal.zzn;
import com.google.android.gms.common.internal.zzbp;
import com.google.android.gms.common.internal.zzq;
import com.google.android.gms.location.GeofencingRequest;
import com.google.android.gms.location.LocationAvailability;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.zzaa;

public final class zzbzu extends zzbyi {
  private final zzbzo zzhzs;
  
  public zzbzu(Context paramContext, Looper paramLooper, GoogleApiClient.ConnectionCallbacks paramConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener paramOnConnectionFailedListener, String paramString) {
    this(paramContext, paramLooper, paramConnectionCallbacks, paramOnConnectionFailedListener, paramString, zzq.zzcc(paramContext));
  }
  
  public zzbzu(Context paramContext, Looper paramLooper, GoogleApiClient.ConnectionCallbacks paramConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener paramOnConnectionFailedListener, String paramString, zzq paramzzq) {
    super(paramContext, paramLooper, paramConnectionCallbacks, paramOnConnectionFailedListener, paramString, paramzzq);
    this.zzhzs = new zzbzo(paramContext, this.zzhzb);
  }
  
  public final void disconnect() {
    synchronized (this.zzhzs) {
      boolean bool = isConnected();
      if (bool)
        try {
          this.zzhzs.removeAllListeners();
          this.zzhzs.zzasx();
        } catch (Exception exception) {
          Log.e("LocationClientImpl", "Client disconnected before listeners could be cleaned up", exception);
        }  
      super.disconnect();
      return;
    } 
  }
  
  public final Location getLastLocation() {
    return this.zzhzs.getLastLocation();
  }
  
  public final void zza(long paramLong, PendingIntent paramPendingIntent) throws RemoteException {
    boolean bool;
    zzaji();
    zzbp.zzu(paramPendingIntent);
    if (paramLong >= 0L) {
      bool = true;
    } else {
      bool = false;
    } 
    zzbp.zzb(bool, "detectionIntervalMillis must be >= 0");
    ((zzbzk)zzajj()).zza(paramLong, true, paramPendingIntent);
  }
  
  public final void zza(PendingIntent paramPendingIntent, zzbzf paramzzbzf) throws RemoteException {
    this.zzhzs.zza(paramPendingIntent, paramzzbzf);
  }
  
  public final void zza(zzcl<LocationListener> paramzzcl, zzbzf paramzzbzf) throws RemoteException {
    this.zzhzs.zza(paramzzcl, paramzzbzf);
  }
  
  public final void zza(zzbzf paramzzbzf) throws RemoteException {
    this.zzhzs.zza(paramzzbzf);
  }
  
  public final void zza(zzbzy paramzzbzy, zzcj<LocationCallback> paramzzcj, zzbzf paramzzbzf) throws RemoteException {
    synchronized (this.zzhzs) {
      this.zzhzs.zza(paramzzbzy, paramzzcj, paramzzbzf);
      return;
    } 
  }
  
  public final void zza(GeofencingRequest paramGeofencingRequest, PendingIntent paramPendingIntent, zzn<Status> paramzzn) throws RemoteException {
    zzaji();
    zzbp.zzb(paramGeofencingRequest, "geofencingRequest can't be null.");
    zzbp.zzb(paramPendingIntent, "PendingIntent must be specified.");
    zzbp.zzb(paramzzn, "ResultHolder not provided.");
    zzbzv zzbzv = new zzbzv(paramzzn);
    ((zzbzk)zzajj()).zza(paramGeofencingRequest, paramPendingIntent, zzbzv);
  }
  
  public final void zza(LocationRequest paramLocationRequest, PendingIntent paramPendingIntent, zzbzf paramzzbzf) throws RemoteException {
    this.zzhzs.zza(paramLocationRequest, paramPendingIntent, paramzzbzf);
  }
  
  public final void zza(LocationRequest paramLocationRequest, zzcj<LocationListener> paramzzcj, zzbzf paramzzbzf) throws RemoteException {
    synchronized (this.zzhzs) {
      this.zzhzs.zza(paramLocationRequest, paramzzcj, paramzzbzf);
      return;
    } 
  }
  
  public final void zza(LocationSettingsRequest paramLocationSettingsRequest, zzn<LocationSettingsResult> paramzzn, String paramString) throws RemoteException {
    boolean bool2;
    zzaji();
    boolean bool1 = true;
    if (paramLocationSettingsRequest != null) {
      bool2 = true;
    } else {
      bool2 = false;
    } 
    zzbp.zzb(bool2, "locationSettingsRequest can't be null nor empty.");
    if (paramzzn != null) {
      bool2 = bool1;
    } else {
      bool2 = false;
    } 
    zzbp.zzb(bool2, "listener can't be null.");
    zzbzx zzbzx = new zzbzx(paramzzn);
    ((zzbzk)zzajj()).zza(paramLocationSettingsRequest, zzbzx, paramString);
  }
  
  public final void zza(zzaa paramzzaa, zzn<Status> paramzzn) throws RemoteException {
    zzaji();
    zzbp.zzb(paramzzaa, "removeGeofencingRequest can't be null.");
    zzbp.zzb(paramzzn, "ResultHolder not provided.");
    zzbzw zzbzw = new zzbzw(paramzzn);
    ((zzbzk)zzajj()).zza(paramzzaa, zzbzw);
  }
  
  public final LocationAvailability zzasw() {
    return this.zzhzs.zzasw();
  }
  
  public final void zzb(zzcl<LocationCallback> paramzzcl, zzbzf paramzzbzf) throws RemoteException {
    this.zzhzs.zzb(paramzzcl, paramzzbzf);
  }
  
  public final void zzbk(boolean paramBoolean) throws RemoteException {
    this.zzhzs.zzbk(paramBoolean);
  }
  
  public final void zzc(PendingIntent paramPendingIntent) throws RemoteException {
    zzaji();
    zzbp.zzu(paramPendingIntent);
    ((zzbzk)zzajj()).zzc(paramPendingIntent);
  }
  
  public final void zzc(Location paramLocation) throws RemoteException {
    this.zzhzs.zzc(paramLocation);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/internal/zzbzu.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */