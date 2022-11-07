package com.google.android.gms.location.places.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.internal.zzbp;
import com.google.android.gms.internal.zzbco;
import com.google.android.gms.internal.zzbcp;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.maps.model.LatLngBounds;

public class PlacePicker extends zza {
  public static final int RESULT_ERROR = 2;
  
  @Deprecated
  public static String getAttributions(Intent paramIntent) {
    return paramIntent.getStringExtra("third_party_attributions");
  }
  
  public static LatLngBounds getLatLngBounds(Intent paramIntent) {
    return (LatLngBounds)zzbcp.zza(paramIntent, "final_latlng_bounds", LatLngBounds.CREATOR);
  }
  
  public static Place getPlace(Context paramContext, Intent paramIntent) {
    return zza.getPlace(paramContext, paramIntent);
  }
  
  @Deprecated
  public static Place getPlace(Intent paramIntent, Context paramContext) {
    return zza.getPlace(paramContext, paramIntent);
  }
  
  public static class IntentBuilder extends zzb {
    public IntentBuilder() {
      super("com.google.android.gms.location.places.ui.PICK_PLACE");
      this.mIntent.putExtra("gmscore_client_jar_version", GoogleApiAvailability.GOOGLE_PLAY_SERVICES_VERSION_CODE);
    }
    
    public Intent build(Activity param1Activity) throws GooglePlayServicesRepairableException, GooglePlayServicesNotAvailableException {
      return super.build(param1Activity);
    }
    
    public IntentBuilder setLatLngBounds(LatLngBounds param1LatLngBounds) {
      zzbp.zzu(param1LatLngBounds);
      zzbcp.zza((zzbco)param1LatLngBounds, this.mIntent, "latlng_bounds");
      return this;
    }
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/location/places/ui/PlacePicker.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */