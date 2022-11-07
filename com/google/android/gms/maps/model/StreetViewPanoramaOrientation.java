package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import com.google.android.gms.common.internal.zzbf;
import com.google.android.gms.common.internal.zzbp;
import com.google.android.gms.internal.zzbck;
import com.google.android.gms.internal.zzbcn;
import java.util.Arrays;

public class StreetViewPanoramaOrientation extends zzbck {
  public static final Parcelable.Creator<StreetViewPanoramaOrientation> CREATOR = new zzp();
  
  public final float bearing;
  
  public final float tilt;
  
  public StreetViewPanoramaOrientation(float paramFloat1, float paramFloat2) {
    boolean bool;
    if (-90.0F <= paramFloat1 && paramFloat1 <= 90.0F) {
      bool = true;
    } else {
      bool = false;
    } 
    StringBuilder stringBuilder = new StringBuilder(62);
    stringBuilder.append("Tilt needs to be between -90 and 90 inclusive: ");
    stringBuilder.append(paramFloat1);
    zzbp.zzb(bool, stringBuilder.toString());
    this.tilt = paramFloat1 + 0.0F;
    paramFloat1 = paramFloat2;
    if (paramFloat2 <= 0.0D)
      paramFloat1 = paramFloat2 % 360.0F + 360.0F; 
    this.bearing = paramFloat1 % 360.0F;
  }
  
  public static Builder builder() {
    return new Builder();
  }
  
  public static Builder builder(@NonNull StreetViewPanoramaOrientation paramStreetViewPanoramaOrientation) {
    return new Builder(paramStreetViewPanoramaOrientation);
  }
  
  public boolean equals(Object paramObject) {
    if (this == paramObject)
      return true; 
    if (!(paramObject instanceof StreetViewPanoramaOrientation))
      return false; 
    paramObject = paramObject;
    return (Float.floatToIntBits(this.tilt) == Float.floatToIntBits(((StreetViewPanoramaOrientation)paramObject).tilt) && Float.floatToIntBits(this.bearing) == Float.floatToIntBits(((StreetViewPanoramaOrientation)paramObject).bearing));
  }
  
  public int hashCode() {
    return Arrays.hashCode(new Object[] { Float.valueOf(this.tilt), Float.valueOf(this.bearing) });
  }
  
  public String toString() {
    return zzbf.zzt(this).zzg("tilt", Float.valueOf(this.tilt)).zzg("bearing", Float.valueOf(this.bearing)).toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramInt = zzbcn.zze(paramParcel);
    zzbcn.zza(paramParcel, 2, this.tilt);
    zzbcn.zza(paramParcel, 3, this.bearing);
    zzbcn.zzai(paramParcel, paramInt);
  }
  
  public static final class Builder {
    public float bearing;
    
    public float tilt;
    
    public Builder() {}
    
    public Builder(@NonNull StreetViewPanoramaOrientation param1StreetViewPanoramaOrientation) {
      zzbp.zzb(param1StreetViewPanoramaOrientation, "StreetViewPanoramaOrientation");
      this.bearing = param1StreetViewPanoramaOrientation.bearing;
      this.tilt = param1StreetViewPanoramaOrientation.tilt;
    }
    
    public final Builder bearing(float param1Float) {
      this.bearing = param1Float;
      return this;
    }
    
    public final StreetViewPanoramaOrientation build() {
      return new StreetViewPanoramaOrientation(this.tilt, this.bearing);
    }
    
    public final Builder tilt(float param1Float) {
      this.tilt = param1Float;
      return this;
    }
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/maps/model/StreetViewPanoramaOrientation.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */