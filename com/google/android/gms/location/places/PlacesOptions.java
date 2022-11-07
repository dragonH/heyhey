package com.google.android.gms.location.places;

import android.support.annotation.Nullable;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.internal.zzbf;
import java.util.Arrays;
import java.util.Locale;

public final class PlacesOptions implements Api.ApiOptions.Optional {
  @Nullable
  public final String zzdxf = null;
  
  @Nullable
  public final String zzibt = null;
  
  @Nullable
  public final String zzibu = null;
  
  public final int zzibv = 0;
  
  @Nullable
  public final Locale zzibw = null;
  
  private PlacesOptions(Builder paramBuilder) {}
  
  public final boolean equals(Object paramObject) {
    boolean bool = paramObject instanceof PlacesOptions;
    paramObject = Integer.valueOf(0);
    return (bool && zzbf.equal(null, null) && zzbf.equal(null, null) && zzbf.equal(paramObject, paramObject) && zzbf.equal(null, null) && zzbf.equal(null, null));
  }
  
  public final int hashCode() {
    return Arrays.hashCode(new Object[] { null, null, Integer.valueOf(0), null, null });
  }
  
  public static class Builder {
    private int zzibv = 0;
    
    public PlacesOptions build() {
      return new PlacesOptions(this, null);
    }
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/location/places/PlacesOptions.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */