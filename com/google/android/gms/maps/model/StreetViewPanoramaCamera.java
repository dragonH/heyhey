package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.zzbf;
import com.google.android.gms.common.internal.zzbp;
import com.google.android.gms.internal.zzbck;
import com.google.android.gms.internal.zzbcn;
import java.util.Arrays;

public class StreetViewPanoramaCamera extends zzbck implements ReflectedParcelable {
  public static final Parcelable.Creator<StreetViewPanoramaCamera> CREATOR = new zzm();
  
  public final float bearing;
  
  public final float tilt;
  
  public final float zoom;
  
  @NonNull
  private final StreetViewPanoramaOrientation zzika;
  
  public StreetViewPanoramaCamera(float paramFloat1, float paramFloat2, float paramFloat3) {
    boolean bool;
    if (-90.0F <= paramFloat2 && paramFloat2 <= 90.0F) {
      bool = true;
    } else {
      bool = false;
    } 
    StringBuilder stringBuilder = new StringBuilder(62);
    stringBuilder.append("Tilt needs to be between -90 and 90 inclusive: ");
    stringBuilder.append(paramFloat2);
    zzbp.zzb(bool, stringBuilder.toString());
    float f = paramFloat1;
    if (paramFloat1 <= 0.0D)
      f = 0.0F; 
    this.zoom = f;
    this.tilt = 0.0F + paramFloat2;
    if (paramFloat3 <= 0.0D) {
      paramFloat1 = paramFloat3 % 360.0F + 360.0F;
    } else {
      paramFloat1 = paramFloat3;
    } 
    this.bearing = paramFloat1 % 360.0F;
    this.zzika = (new StreetViewPanoramaOrientation.Builder()).tilt(paramFloat2).bearing(paramFloat3).build();
  }
  
  public static Builder builder() {
    return new Builder();
  }
  
  public static Builder builder(@NonNull StreetViewPanoramaCamera paramStreetViewPanoramaCamera) {
    return new Builder(paramStreetViewPanoramaCamera);
  }
  
  public boolean equals(Object paramObject) {
    if (this == paramObject)
      return true; 
    if (!(paramObject instanceof StreetViewPanoramaCamera))
      return false; 
    paramObject = paramObject;
    return (Float.floatToIntBits(this.zoom) == Float.floatToIntBits(((StreetViewPanoramaCamera)paramObject).zoom) && Float.floatToIntBits(this.tilt) == Float.floatToIntBits(((StreetViewPanoramaCamera)paramObject).tilt) && Float.floatToIntBits(this.bearing) == Float.floatToIntBits(((StreetViewPanoramaCamera)paramObject).bearing));
  }
  
  @NonNull
  public StreetViewPanoramaOrientation getOrientation() {
    return this.zzika;
  }
  
  public int hashCode() {
    return Arrays.hashCode(new Object[] { Float.valueOf(this.zoom), Float.valueOf(this.tilt), Float.valueOf(this.bearing) });
  }
  
  public String toString() {
    return zzbf.zzt(this).zzg("zoom", Float.valueOf(this.zoom)).zzg("tilt", Float.valueOf(this.tilt)).zzg("bearing", Float.valueOf(this.bearing)).toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramInt = zzbcn.zze(paramParcel);
    zzbcn.zza(paramParcel, 2, this.zoom);
    zzbcn.zza(paramParcel, 3, this.tilt);
    zzbcn.zza(paramParcel, 4, this.bearing);
    zzbcn.zzai(paramParcel, paramInt);
  }
  
  public static final class Builder {
    public float bearing;
    
    public float tilt;
    
    public float zoom;
    
    public Builder() {}
    
    public Builder(@NonNull StreetViewPanoramaCamera param1StreetViewPanoramaCamera) {
      zzbp.zzb(param1StreetViewPanoramaCamera, "StreetViewPanoramaCamera");
      this.zoom = param1StreetViewPanoramaCamera.zoom;
      this.bearing = param1StreetViewPanoramaCamera.bearing;
      this.tilt = param1StreetViewPanoramaCamera.tilt;
    }
    
    public final Builder bearing(float param1Float) {
      this.bearing = param1Float;
      return this;
    }
    
    public final StreetViewPanoramaCamera build() {
      return new StreetViewPanoramaCamera(this.zoom, this.tilt, this.bearing);
    }
    
    public final Builder orientation(StreetViewPanoramaOrientation param1StreetViewPanoramaOrientation) {
      zzbp.zzb(param1StreetViewPanoramaOrientation, "StreetViewPanoramaOrientation");
      this.tilt = param1StreetViewPanoramaOrientation.tilt;
      this.bearing = param1StreetViewPanoramaOrientation.bearing;
      return this;
    }
    
    public final Builder tilt(float param1Float) {
      this.tilt = param1Float;
      return this;
    }
    
    public final Builder zoom(float param1Float) {
      this.zoom = param1Float;
      return this;
    }
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/maps/model/StreetViewPanoramaCamera.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */