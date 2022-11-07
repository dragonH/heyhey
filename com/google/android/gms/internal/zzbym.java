package com.google.android.gms.internal;

import android.app.PendingIntent;
import android.location.Location;
import android.os.Looper;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.zzm;
import com.google.android.gms.common.internal.zzbp;
import com.google.android.gms.location.FusedLocationProviderApi;
import com.google.android.gms.location.LocationAvailability;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

public final class zzbym implements FusedLocationProviderApi {
  public final PendingResult<Status> flushLocations(GoogleApiClient paramGoogleApiClient) {
    return (PendingResult<Status>)paramGoogleApiClient.zze((zzm)new zzbyr(this, paramGoogleApiClient));
  }
  
  public final Location getLastLocation(GoogleApiClient paramGoogleApiClient) {
    zzbzu zzbzu = LocationServices.zzg(paramGoogleApiClient);
    try {
      return zzbzu.getLastLocation();
    } catch (Exception exception) {
      return null;
    } 
  }
  
  public final LocationAvailability getLocationAvailability(GoogleApiClient paramGoogleApiClient) {
    zzbzu zzbzu = LocationServices.zzg(paramGoogleApiClient);
    try {
      return zzbzu.zzasw();
    } catch (Exception exception) {
      return null;
    } 
  }
  
  public final PendingResult<Status> removeLocationUpdates(GoogleApiClient paramGoogleApiClient, PendingIntent paramPendingIntent) {
    return (PendingResult<Status>)paramGoogleApiClient.zze((zzm)new zzbyw(this, paramGoogleApiClient, paramPendingIntent));
  }
  
  public final PendingResult<Status> removeLocationUpdates(GoogleApiClient paramGoogleApiClient, LocationCallback paramLocationCallback) {
    return (PendingResult<Status>)paramGoogleApiClient.zze((zzm)new zzbyo(this, paramGoogleApiClient, paramLocationCallback));
  }
  
  public final PendingResult<Status> removeLocationUpdates(GoogleApiClient paramGoogleApiClient, LocationListener paramLocationListener) {
    return (PendingResult<Status>)paramGoogleApiClient.zze((zzm)new zzbyv(this, paramGoogleApiClient, paramLocationListener));
  }
  
  public final PendingResult<Status> requestLocationUpdates(GoogleApiClient paramGoogleApiClient, LocationRequest paramLocationRequest, PendingIntent paramPendingIntent) {
    return (PendingResult<Status>)paramGoogleApiClient.zze((zzm)new zzbyu(this, paramGoogleApiClient, paramLocationRequest, paramPendingIntent));
  }
  
  public final PendingResult<Status> requestLocationUpdates(GoogleApiClient paramGoogleApiClient, LocationRequest paramLocationRequest, LocationCallback paramLocationCallback, Looper paramLooper) {
    return (PendingResult<Status>)paramGoogleApiClient.zze((zzm)new zzbyt(this, paramGoogleApiClient, paramLocationRequest, paramLocationCallback, paramLooper));
  }
  
  public final PendingResult<Status> requestLocationUpdates(GoogleApiClient paramGoogleApiClient, LocationRequest paramLocationRequest, LocationListener paramLocationListener) {
    zzbp.zzb(Looper.myLooper(), "Calling thread must be a prepared Looper thread.");
    return (PendingResult<Status>)paramGoogleApiClient.zze((zzm)new zzbyn(this, paramGoogleApiClient, paramLocationRequest, paramLocationListener));
  }
  
  public final PendingResult<Status> requestLocationUpdates(GoogleApiClient paramGoogleApiClient, LocationRequest paramLocationRequest, LocationListener paramLocationListener, Looper paramLooper) {
    return (PendingResult<Status>)paramGoogleApiClient.zze((zzm)new zzbys(this, paramGoogleApiClient, paramLocationRequest, paramLocationListener, paramLooper));
  }
  
  public final PendingResult<Status> setMockLocation(GoogleApiClient paramGoogleApiClient, Location paramLocation) {
    return (PendingResult<Status>)paramGoogleApiClient.zze((zzm)new zzbyq(this, paramGoogleApiClient, paramLocation));
  }
  
  public final PendingResult<Status> setMockMode(GoogleApiClient paramGoogleApiClient, boolean paramBoolean) {
    return (PendingResult<Status>)paramGoogleApiClient.zze((zzm)new zzbyp(this, paramGoogleApiClient, paramBoolean));
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/internal/zzbym.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */