package com.google.android.gms.location.places.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.AutocompleteFilter;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.maps.model.LatLngBounds;

public class PlaceAutocomplete extends zza {
  public static final int MODE_FULLSCREEN = 1;
  
  public static final int MODE_OVERLAY = 2;
  
  public static final int RESULT_ERROR = 2;
  
  public static Place getPlace(Context paramContext, Intent paramIntent) {
    return zza.getPlace(paramContext, paramIntent);
  }
  
  public static Status getStatus(Context paramContext, Intent paramIntent) {
    return zza.getStatus(paramContext, paramIntent);
  }
  
  public static class IntentBuilder extends zzb {
    public IntentBuilder(int param1Int) {
      super("com.google.android.gms.location.places.ui.AUTOCOMPLETE");
      this.mIntent.putExtra("gmscore_client_jar_version", GoogleApiAvailability.GOOGLE_PLAY_SERVICES_VERSION_CODE);
      this.mIntent.putExtra("mode", param1Int);
      this.mIntent.putExtra("origin", 2);
    }
    
    public Intent build(Activity param1Activity) throws GooglePlayServicesRepairableException, GooglePlayServicesNotAvailableException {
      return super.build(param1Activity);
    }
    
    public IntentBuilder setBoundsBias(@Nullable LatLngBounds param1LatLngBounds) {
      if (param1LatLngBounds != null) {
        this.mIntent.putExtra("bounds", (Parcelable)param1LatLngBounds);
      } else {
        this.mIntent.removeExtra("bounds");
      } 
      return this;
    }
    
    public IntentBuilder setFilter(@Nullable AutocompleteFilter param1AutocompleteFilter) {
      if (param1AutocompleteFilter != null) {
        this.mIntent.putExtra("filter", (Parcelable)param1AutocompleteFilter);
      } else {
        this.mIntent.removeExtra("filter");
      } 
      return this;
    }
    
    public final IntentBuilder zzdu(int param1Int) {
      this.mIntent.putExtra("origin", 1);
      return this;
    }
    
    public final IntentBuilder zzih(@Nullable String param1String) {
      if (param1String != null) {
        this.mIntent.putExtra("initial_query", param1String);
      } else {
        this.mIntent.removeExtra("initial_query");
      } 
      return this;
    }
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/location/places/ui/PlaceAutocomplete.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */