package com.google.android.gms.location.places;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.Response;
import com.google.android.gms.common.api.internal.zzcz;
import com.google.android.gms.common.api.internal.zzg;
import com.google.android.gms.common.internal.zzbi;
import com.google.android.gms.location.places.internal.zzh;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.tasks.Task;

public class GeoDataClient extends GoogleApi<PlacesOptions> {
  GeoDataClient(@NonNull Activity paramActivity, PlacesOptions paramPlacesOptions) {
    super(paramActivity, Places.GEO_DATA_API, (Api.ApiOptions)paramPlacesOptions, (zzcz)new zzg());
  }
  
  GeoDataClient(@NonNull Context paramContext, PlacesOptions paramPlacesOptions) {
    super(paramContext, Places.GEO_DATA_API, (Api.ApiOptions)paramPlacesOptions, (zzcz)new zzg());
  }
  
  public Task<PlaceBufferResponse> addPlace(AddPlaceRequest paramAddPlaceRequest) {
    return zzbi.zza(Places.GeoDataApi.addPlace(zzafl(), paramAddPlaceRequest), (Response)new PlaceBufferResponse());
  }
  
  public Task<AutocompletePredictionBufferResponse> getAutocompletePredictions(String paramString, LatLngBounds paramLatLngBounds, AutocompleteFilter paramAutocompleteFilter) {
    return zzbi.zza(Places.GeoDataApi.getAutocompletePredictions(zzafl(), paramString, paramLatLngBounds, paramAutocompleteFilter), (Response)new AutocompletePredictionBufferResponse());
  }
  
  public Task<PlacePhotoResponse> getPhoto(PlacePhotoMetadata paramPlacePhotoMetadata) {
    return getScaledPhoto(paramPlacePhotoMetadata, paramPlacePhotoMetadata.getMaxWidth(), paramPlacePhotoMetadata.getMaxHeight());
  }
  
  public Task<PlaceBufferResponse> getPlaceById(String... paramVarArgs) {
    return zzbi.zza(Places.GeoDataApi.getPlaceById(zzafl(), paramVarArgs), (Response)new PlaceBufferResponse());
  }
  
  public Task<PlacePhotoMetadataResponse> getPlacePhotos(String paramString) {
    return zzbi.zza(Places.GeoDataApi.getPlacePhotos(zzafl(), paramString), new PlacePhotoMetadataResponse());
  }
  
  public Task<PlacePhotoResponse> getScaledPhoto(PlacePhotoMetadata paramPlacePhotoMetadata, int paramInt1, int paramInt2) {
    return zzbi.zza(((zzh)Places.GeoDataApi).zza(zzafl(), paramPlacePhotoMetadata, paramInt1, paramInt2), new PlacePhotoResponse());
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/location/places/GeoDataClient.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */