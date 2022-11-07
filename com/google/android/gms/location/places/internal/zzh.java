package com.google.android.gms.location.places.internal;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.internal.zzm;
import com.google.android.gms.common.internal.zzbp;
import com.google.android.gms.location.places.AddPlaceRequest;
import com.google.android.gms.location.places.AutocompleteFilter;
import com.google.android.gms.location.places.AutocompletePredictionBuffer;
import com.google.android.gms.location.places.GeoDataApi;
import com.google.android.gms.location.places.PlaceBuffer;
import com.google.android.gms.location.places.PlacePhotoMetadata;
import com.google.android.gms.location.places.PlacePhotoMetadataResult;
import com.google.android.gms.location.places.PlacePhotoResult;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.maps.model.LatLngBounds;

public final class zzh implements GeoDataApi {
  public final PendingResult<PlaceBuffer> addPlace(GoogleApiClient paramGoogleApiClient, AddPlaceRequest paramAddPlaceRequest) {
    return (PendingResult<PlaceBuffer>)paramGoogleApiClient.zze((zzm)new zzi(this, Places.GEO_DATA_API, paramGoogleApiClient, paramAddPlaceRequest));
  }
  
  public final PendingResult<AutocompletePredictionBuffer> getAutocompletePredictions(GoogleApiClient paramGoogleApiClient, String paramString, LatLngBounds paramLatLngBounds, AutocompleteFilter paramAutocompleteFilter) {
    return (PendingResult<AutocompletePredictionBuffer>)paramGoogleApiClient.zzd((zzm)new zzm(this, Places.GEO_DATA_API, paramGoogleApiClient, paramString, paramLatLngBounds, paramAutocompleteFilter));
  }
  
  public final PendingResult<PlaceBuffer> getPlaceById(GoogleApiClient paramGoogleApiClient, String... paramVarArgs) {
    boolean bool;
    if (paramVarArgs != null && paramVarArgs.length > 0) {
      bool = true;
    } else {
      bool = false;
    } 
    zzbp.zzbh(bool);
    return (PendingResult<PlaceBuffer>)paramGoogleApiClient.zzd((zzm)new zzl(this, Places.GEO_DATA_API, paramGoogleApiClient, paramVarArgs));
  }
  
  public final PendingResult<PlacePhotoMetadataResult> getPlacePhotos(GoogleApiClient paramGoogleApiClient, String paramString) {
    boolean bool;
    zzbp.zzb(paramString, "placeId cannot be null");
    if (paramString != "") {
      bool = true;
    } else {
      bool = false;
    } 
    zzbp.zzb(bool, "placeId cannot be empty");
    return (PendingResult<PlacePhotoMetadataResult>)paramGoogleApiClient.zzd((zzm)new zzj(this, Places.GEO_DATA_API, paramGoogleApiClient, paramString));
  }
  
  public final PendingResult<PlacePhotoResult> zza(GoogleApiClient paramGoogleApiClient, PlacePhotoMetadata paramPlacePhotoMetadata, int paramInt1, int paramInt2) {
    boolean bool2;
    zzbp.zzb(paramPlacePhotoMetadata, "photo cannot be null");
    boolean bool1 = true;
    if (paramInt1 > 0) {
      bool2 = true;
    } else {
      bool2 = false;
    } 
    zzbp.zzb(bool2, "width must be > 0");
    if (paramInt2 > 0) {
      bool2 = bool1;
    } else {
      bool2 = false;
    } 
    zzbp.zzb(bool2, "height must be > 0");
    zzaq zzaq = (zzaq)paramPlacePhotoMetadata.freeze();
    String str = zzaq.zzatg();
    int i = zzaq.getIndex();
    zzbp.zzb(str, "fifeUrl cannot be null");
    return (PendingResult<PlacePhotoResult>)paramGoogleApiClient.zzd((zzm)new zzk(this, Places.GEO_DATA_API, paramGoogleApiClient, str, paramInt1, paramInt2, i));
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/location/places/internal/zzh.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */