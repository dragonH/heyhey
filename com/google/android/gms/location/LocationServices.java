package com.google.android.gms.location;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.internal.zzm;
import com.google.android.gms.common.internal.zzbp;
import com.google.android.gms.internal.zzbym;
import com.google.android.gms.internal.zzbzb;
import com.google.android.gms.internal.zzbzu;
import com.google.android.gms.internal.zzcaf;

public class LocationServices {
  public static final Api<Api.ApiOptions.NoOptions> API;
  
  @Deprecated
  public static final FusedLocationProviderApi FusedLocationApi;
  
  @Deprecated
  public static final GeofencingApi GeofencingApi;
  
  @Deprecated
  public static final SettingsApi SettingsApi;
  
  private static final Api.zzf<zzbzu> zzdwp;
  
  private static final Api.zza<zzbzu, Api.ApiOptions.NoOptions> zzdwq;
  
  static {
    Api.zzf<zzbzu> zzf1 = new Api.zzf();
    zzdwp = zzf1;
    zzs zzs = new zzs();
    zzdwq = zzs;
    API = new Api("LocationServices.API", zzs, zzf1);
    FusedLocationApi = (FusedLocationProviderApi)new zzbym();
    GeofencingApi = (GeofencingApi)new zzbzb();
    SettingsApi = (SettingsApi)new zzcaf();
  }
  
  public static FusedLocationProviderClient getFusedLocationProviderClient(@NonNull Activity paramActivity) {
    return new FusedLocationProviderClient(paramActivity);
  }
  
  public static FusedLocationProviderClient getFusedLocationProviderClient(@NonNull Context paramContext) {
    return new FusedLocationProviderClient(paramContext);
  }
  
  public static GeofencingClient getGeofencingClient(@NonNull Activity paramActivity) {
    return new GeofencingClient(paramActivity);
  }
  
  public static GeofencingClient getGeofencingClient(@NonNull Context paramContext) {
    return new GeofencingClient(paramContext);
  }
  
  public static SettingsClient getSettingsClient(@NonNull Activity paramActivity) {
    return new SettingsClient(paramActivity);
  }
  
  public static SettingsClient getSettingsClient(@NonNull Context paramContext) {
    return new SettingsClient(paramContext);
  }
  
  public static zzbzu zzg(GoogleApiClient paramGoogleApiClient) {
    boolean bool2;
    boolean bool1 = true;
    if (paramGoogleApiClient != null) {
      bool2 = true;
    } else {
      bool2 = false;
    } 
    zzbp.zzb(bool2, "GoogleApiClient parameter is required.");
    zzbzu zzbzu = (zzbzu)paramGoogleApiClient.zza((Api.zzc)zzdwp);
    if (zzbzu != null) {
      bool2 = bool1;
    } else {
      bool2 = false;
    } 
    zzbp.zza(bool2, "GoogleApiClient is not configured to use the LocationServices.API Api. Pass thisinto GoogleApiClient.Builder#addApi() to use this feature.");
    return zzbzu;
  }
  
  public static abstract class zza<R extends Result> extends zzm<R, zzbzu> {
    public zza(GoogleApiClient param1GoogleApiClient) {
      super(LocationServices.API, param1GoogleApiClient);
    }
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/location/LocationServices.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */