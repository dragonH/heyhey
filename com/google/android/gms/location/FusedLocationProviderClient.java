package com.google.android.gms.location;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.location.Location;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresPermission;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.internal.zzcj;
import com.google.android.gms.common.api.internal.zzcl;
import com.google.android.gms.common.api.internal.zzcn;
import com.google.android.gms.common.api.internal.zzcz;
import com.google.android.gms.common.api.internal.zzde;
import com.google.android.gms.common.api.internal.zzg;
import com.google.android.gms.common.internal.zzbi;
import com.google.android.gms.common.internal.zzbp;
import com.google.android.gms.internal.zzbyz;
import com.google.android.gms.internal.zzbzg;
import com.google.android.gms.internal.zzbzy;
import com.google.android.gms.internal.zzcam;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;

public class FusedLocationProviderClient extends GoogleApi<Api.ApiOptions.NoOptions> {
  public FusedLocationProviderClient(@NonNull Activity paramActivity) {
    super(paramActivity, LocationServices.API, null, (zzcz)new zzg());
  }
  
  public FusedLocationProviderClient(@NonNull Context paramContext) {
    super(paramContext, LocationServices.API, null, (zzcz)new zzg());
  }
  
  public Task<Void> flushLocations() {
    return zzbi.zzb(LocationServices.FusedLocationApi.flushLocations(zzafl()));
  }
  
  @RequiresPermission(anyOf = {"android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"})
  public Task<Location> getLastLocation() {
    return zza(new zze(this));
  }
  
  @RequiresPermission(anyOf = {"android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"})
  public Task<LocationAvailability> getLocationAvailability() {
    return zza(new zzf(this));
  }
  
  public Task<Void> removeLocationUpdates(PendingIntent paramPendingIntent) {
    return zzbi.zzb(LocationServices.FusedLocationApi.removeLocationUpdates(zzafl(), paramPendingIntent));
  }
  
  public Task<Void> removeLocationUpdates(LocationCallback paramLocationCallback) {
    zzcl zzcl = zzcn.zza(paramLocationCallback, LocationCallback.class.getSimpleName());
    zzbp.zzb(zzcl, "Listener key cannot be null.");
    return zzde.zza(this.zzfgv.zza(this, zzcl));
  }
  
  @RequiresPermission(anyOf = {"android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"})
  public Task<Void> requestLocationUpdates(LocationRequest paramLocationRequest, PendingIntent paramPendingIntent) {
    return zzbi.zzb(LocationServices.FusedLocationApi.requestLocationUpdates(zzafl(), paramLocationRequest, paramPendingIntent));
  }
  
  @RequiresPermission(anyOf = {"android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"})
  public Task<Void> requestLocationUpdates(LocationRequest paramLocationRequest, LocationCallback paramLocationCallback, @Nullable Looper paramLooper) {
    zzbzy zzbzy = zzbzy.zza(paramLocationRequest);
    zzcj zzcj = zzcn.zzb(paramLocationCallback, zzcam.zzb(paramLooper), LocationCallback.class.getSimpleName());
    zzg zzg = new zzg(this, zzcj, zzbzy, zzcj);
    zzh zzh = new zzh(this, zzcj.zzaik());
    zzbp.zzu(zzg);
    zzbp.zzu(zzh);
    zzbp.zzb(zzg.zzaik(), "Listener has already been released.");
    zzbp.zzb(zzh.zzaik(), "Listener has already been released.");
    zzbp.zzb(zzg.zzaik().equals(zzh.zzaik()), "Listener registration and unregistration methods must be constructed with the same ListenerHolder.");
    return this.zzfgv.zza(this, zzg, zzh);
  }
  
  @RequiresPermission(anyOf = {"android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"})
  public Task<Void> setMockLocation(Location paramLocation) {
    return zzbi.zzb(LocationServices.FusedLocationApi.setMockLocation(zzafl(), paramLocation));
  }
  
  @RequiresPermission(anyOf = {"android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"})
  public Task<Void> setMockMode(boolean paramBoolean) {
    return zzbi.zzb(LocationServices.FusedLocationApi.setMockMode(zzafl(), paramBoolean));
  }
  
  static final class zza extends zzbzg {
    private final TaskCompletionSource<Void> zzdzc;
    
    public zza(TaskCompletionSource<Void> param1TaskCompletionSource) {
      this.zzdzc = param1TaskCompletionSource;
    }
    
    public final void zza(zzbyz param1zzbyz) {
      zzde.zza(param1zzbyz.getStatus(), null, this.zzdzc);
    }
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/location/FusedLocationProviderClient.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */