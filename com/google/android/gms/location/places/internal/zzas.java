package com.google.android.gms.location.places.internal;

import android.net.Uri;
import android.text.TextUtils;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public final class zzas extends zzav implements Place {
  private final String zzibj;
  
  private final zzaf zzidi;
  
  public zzas(DataHolder paramDataHolder, int paramInt) {
    super(paramDataHolder, paramInt);
    zzaf zzaf1;
    this.zzibj = zzae("place_id", "");
    if (getPlaceTypes().size() > 0 || (getPhoneNumber() != null && getPhoneNumber().length() > 0) || (getWebsiteUri() != null && !getWebsiteUri().equals(Uri.EMPTY)) || getRating() >= 0.0F || getPriceLevel() >= 0) {
      paramInt = 1;
    } else {
      paramInt = 0;
    } 
    paramDataHolder = null;
    DataHolder dataHolder = null;
    if (paramInt != 0) {
      String str;
      List<Integer> list = getPlaceTypes();
      paramDataHolder = dataHolder;
      if (getPhoneNumber() != null)
        str = getPhoneNumber().toString(); 
      zzaf1 = new zzaf(list, str, getWebsiteUri(), getRating(), getPriceLevel());
    } 
    this.zzidi = zzaf1;
  }
  
  private final List<String> zzath() {
    return zzb("place_attributions", Collections.emptyList());
  }
  
  public final CharSequence getAddress() {
    return zzae("place_address", "");
  }
  
  public final CharSequence getAttributions() {
    return zzg.zzj(zzath());
  }
  
  public final String getId() {
    return this.zzibj;
  }
  
  public final LatLng getLatLng() {
    return zza("place_lat_lng", LatLng.CREATOR);
  }
  
  public final Locale getLocale() {
    String str = zzae("place_locale_language", "");
    if (!TextUtils.isEmpty(str))
      return new Locale(str, zzae("place_locale_country", "")); 
    str = zzae("place_locale", "");
    return !TextUtils.isEmpty(str) ? new Locale(str) : Locale.getDefault();
  }
  
  public final CharSequence getName() {
    return zzae("place_name", "");
  }
  
  public final CharSequence getPhoneNumber() {
    return zzae("place_phone_number", "");
  }
  
  public final List<Integer> getPlaceTypes() {
    return zza("place_types", Collections.emptyList());
  }
  
  public final int getPriceLevel() {
    return zzw("place_price_level", -1);
  }
  
  public final float getRating() {
    return zza("place_rating", -1.0F);
  }
  
  public final LatLngBounds getViewport() {
    return zza("place_viewport", LatLngBounds.CREATOR);
  }
  
  public final Uri getWebsiteUri() {
    String str = zzae("place_website_uri", null);
    return (str == null) ? null : Uri.parse(str);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/location/places/internal/zzas.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */