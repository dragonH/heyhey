package com.google.android.gms.location.places.internal;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.internal.zzbf;
import com.google.android.gms.location.places.PlacePhotoMetadata;
import com.google.android.gms.location.places.PlacePhotoResult;
import com.google.android.gms.location.places.Places;
import java.util.Arrays;

public final class zzaq implements PlacePhotoMetadata {
  private final int mIndex;
  
  private final int zzcex;
  
  private final int zzcey;
  
  private final String zziee;
  
  private final CharSequence zzief;
  
  public zzaq(String paramString, int paramInt1, int paramInt2, CharSequence paramCharSequence, int paramInt3) {
    this.zziee = paramString;
    this.zzcex = paramInt1;
    this.zzcey = paramInt2;
    this.zzief = paramCharSequence;
    this.mIndex = paramInt3;
  }
  
  public final boolean equals(Object paramObject) {
    if (paramObject == this)
      return true; 
    if (!(paramObject instanceof zzaq))
      return false; 
    paramObject = paramObject;
    return (((zzaq)paramObject).zzcex == this.zzcex && ((zzaq)paramObject).zzcey == this.zzcey && zzbf.equal(((zzaq)paramObject).zziee, this.zziee) && zzbf.equal(((zzaq)paramObject).zzief, this.zzief));
  }
  
  public final CharSequence getAttributions() {
    return this.zzief;
  }
  
  public final int getIndex() {
    return this.mIndex;
  }
  
  public final int getMaxHeight() {
    return this.zzcey;
  }
  
  public final int getMaxWidth() {
    return this.zzcex;
  }
  
  public final PendingResult<PlacePhotoResult> getPhoto(GoogleApiClient paramGoogleApiClient) {
    return getScaledPhoto(paramGoogleApiClient, getMaxWidth(), getMaxHeight());
  }
  
  public final PendingResult<PlacePhotoResult> getScaledPhoto(GoogleApiClient paramGoogleApiClient, int paramInt1, int paramInt2) {
    return ((zzh)Places.GeoDataApi).zza(paramGoogleApiClient, this, paramInt1, paramInt2);
  }
  
  public final int hashCode() {
    return Arrays.hashCode(new Object[] { Integer.valueOf(this.zzcex), Integer.valueOf(this.zzcey), this.zziee, this.zzief });
  }
  
  public final boolean isDataValid() {
    return true;
  }
  
  public final String zzatg() {
    return this.zziee;
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/location/places/internal/zzaq.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */