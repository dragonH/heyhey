package com.google.android.gms.location.places;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.location.places.internal.zzab;
import com.google.android.gms.location.places.internal.zzad;
import com.google.android.gms.location.places.internal.zzh;
import com.google.android.gms.location.places.internal.zzn;
import com.google.android.gms.location.places.internal.zzp;
import com.google.android.gms.location.places.internal.zzy;

public class Places {
  public static final Api<PlacesOptions> GEO_DATA_API;
  
  public static final GeoDataApi GeoDataApi;
  
  public static final Api<PlacesOptions> PLACE_DETECTION_API;
  
  public static final PlaceDetectionApi PlaceDetectionApi;
  
  private static Api.zzf<zzn> zzibm = new Api.zzf();
  
  private static Api.zzf<zzab> zzibn = new Api.zzf();
  
  static {
    GEO_DATA_API = new Api("Places.GEO_DATA_API", (Api.zza)new zzp(), zzibm);
    PLACE_DETECTION_API = new Api("Places.PLACE_DETECTION_API", (Api.zza)new zzad(), zzibn);
    GeoDataApi = (GeoDataApi)new zzh();
    PlaceDetectionApi = (PlaceDetectionApi)new zzy();
  }
  
  public static GeoDataClient getGeoDataClient(@NonNull Activity paramActivity, PlacesOptions paramPlacesOptions) {
    return new GeoDataClient(paramActivity, paramPlacesOptions);
  }
  
  public static GeoDataClient getGeoDataClient(@NonNull Context paramContext, PlacesOptions paramPlacesOptions) {
    return new GeoDataClient(paramContext, paramPlacesOptions);
  }
  
  public static PlaceDetectionClient getPlaceDetectionClient(@NonNull Activity paramActivity, PlacesOptions paramPlacesOptions) {
    return new PlaceDetectionClient(paramActivity, paramPlacesOptions);
  }
  
  public static PlaceDetectionClient getPlaceDetectionClient(@NonNull Context paramContext, PlacesOptions paramPlacesOptions) {
    return new PlaceDetectionClient(paramContext, paramPlacesOptions);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/location/places/Places.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */