package com.google.android.gms.maps.model;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import com.google.android.gms.common.internal.zzbp;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.zzbck;
import com.google.android.gms.internal.zzbcn;

public final class GroundOverlayOptions extends zzbck {
  public static final Parcelable.Creator<GroundOverlayOptions> CREATOR = new zzd();
  
  public static final float NO_DIMENSION = -1.0F;
  
  private LatLngBounds zziet;
  
  private float zziij;
  
  private float zziio;
  
  private boolean zziip = true;
  
  private boolean zziiq = false;
  
  @NonNull
  private BitmapDescriptor zziit;
  
  private LatLng zziiu;
  
  private float zziiv;
  
  private float zziiw;
  
  private float zziix = 0.0F;
  
  private float zziiy = 0.5F;
  
  private float zziiz = 0.5F;
  
  public GroundOverlayOptions() {}
  
  GroundOverlayOptions(IBinder paramIBinder, LatLng paramLatLng, float paramFloat1, float paramFloat2, LatLngBounds paramLatLngBounds, float paramFloat3, float paramFloat4, boolean paramBoolean1, float paramFloat5, float paramFloat6, float paramFloat7, boolean paramBoolean2) {
    this.zziit = new BitmapDescriptor(IObjectWrapper.zza.zzao(paramIBinder));
    this.zziiu = paramLatLng;
    this.zziiv = paramFloat1;
    this.zziiw = paramFloat2;
    this.zziet = paramLatLngBounds;
    this.zziij = paramFloat3;
    this.zziio = paramFloat4;
    this.zziip = paramBoolean1;
    this.zziix = paramFloat5;
    this.zziiy = paramFloat6;
    this.zziiz = paramFloat7;
    this.zziiq = paramBoolean2;
  }
  
  private final GroundOverlayOptions zza(LatLng paramLatLng, float paramFloat1, float paramFloat2) {
    this.zziiu = paramLatLng;
    this.zziiv = paramFloat1;
    this.zziiw = paramFloat2;
    return this;
  }
  
  public final GroundOverlayOptions anchor(float paramFloat1, float paramFloat2) {
    this.zziiy = paramFloat1;
    this.zziiz = paramFloat2;
    return this;
  }
  
  public final GroundOverlayOptions bearing(float paramFloat) {
    this.zziij = (paramFloat % 360.0F + 360.0F) % 360.0F;
    return this;
  }
  
  public final GroundOverlayOptions clickable(boolean paramBoolean) {
    this.zziiq = paramBoolean;
    return this;
  }
  
  public final float getAnchorU() {
    return this.zziiy;
  }
  
  public final float getAnchorV() {
    return this.zziiz;
  }
  
  public final float getBearing() {
    return this.zziij;
  }
  
  public final LatLngBounds getBounds() {
    return this.zziet;
  }
  
  public final float getHeight() {
    return this.zziiw;
  }
  
  public final BitmapDescriptor getImage() {
    return this.zziit;
  }
  
  public final LatLng getLocation() {
    return this.zziiu;
  }
  
  public final float getTransparency() {
    return this.zziix;
  }
  
  public final float getWidth() {
    return this.zziiv;
  }
  
  public final float getZIndex() {
    return this.zziio;
  }
  
  public final GroundOverlayOptions image(@NonNull BitmapDescriptor paramBitmapDescriptor) {
    zzbp.zzb(paramBitmapDescriptor, "imageDescriptor must not be null");
    this.zziit = paramBitmapDescriptor;
    return this;
  }
  
  public final boolean isClickable() {
    return this.zziiq;
  }
  
  public final boolean isVisible() {
    return this.zziip;
  }
  
  public final GroundOverlayOptions position(LatLng paramLatLng, float paramFloat) {
    boolean bool2;
    LatLngBounds latLngBounds = this.zziet;
    boolean bool1 = true;
    if (latLngBounds == null) {
      bool2 = true;
    } else {
      bool2 = false;
    } 
    zzbp.zza(bool2, "Position has already been set using positionFromBounds");
    if (paramLatLng != null) {
      bool2 = true;
    } else {
      bool2 = false;
    } 
    zzbp.zzb(bool2, "Location must be specified");
    if (paramFloat >= 0.0F) {
      bool2 = bool1;
    } else {
      bool2 = false;
    } 
    zzbp.zzb(bool2, "Width must be non-negative");
    return zza(paramLatLng, paramFloat, -1.0F);
  }
  
  public final GroundOverlayOptions position(LatLng paramLatLng, float paramFloat1, float paramFloat2) {
    boolean bool2;
    LatLngBounds latLngBounds = this.zziet;
    boolean bool1 = true;
    if (latLngBounds == null) {
      bool2 = true;
    } else {
      bool2 = false;
    } 
    zzbp.zza(bool2, "Position has already been set using positionFromBounds");
    if (paramLatLng != null) {
      bool2 = true;
    } else {
      bool2 = false;
    } 
    zzbp.zzb(bool2, "Location must be specified");
    if (paramFloat1 >= 0.0F) {
      bool2 = true;
    } else {
      bool2 = false;
    } 
    zzbp.zzb(bool2, "Width must be non-negative");
    if (paramFloat2 >= 0.0F) {
      bool2 = bool1;
    } else {
      bool2 = false;
    } 
    zzbp.zzb(bool2, "Height must be non-negative");
    return zza(paramLatLng, paramFloat1, paramFloat2);
  }
  
  public final GroundOverlayOptions positionFromBounds(LatLngBounds paramLatLngBounds) {
    boolean bool;
    LatLng latLng = this.zziiu;
    if (latLng == null) {
      bool = true;
    } else {
      bool = false;
    } 
    String str = String.valueOf(latLng);
    StringBuilder stringBuilder = new StringBuilder(str.length() + 46);
    stringBuilder.append("Position has already been set using position: ");
    stringBuilder.append(str);
    zzbp.zza(bool, stringBuilder.toString());
    this.zziet = paramLatLngBounds;
    return this;
  }
  
  public final GroundOverlayOptions transparency(float paramFloat) {
    boolean bool;
    if (paramFloat >= 0.0F && paramFloat <= 1.0F) {
      bool = true;
    } else {
      bool = false;
    } 
    zzbp.zzb(bool, "Transparency must be in the range [0..1]");
    this.zziix = paramFloat;
    return this;
  }
  
  public final GroundOverlayOptions visible(boolean paramBoolean) {
    this.zziip = paramBoolean;
    return this;
  }
  
  public final void writeToParcel(Parcel paramParcel, int paramInt) {
    int i = zzbcn.zze(paramParcel);
    zzbcn.zza(paramParcel, 2, this.zziit.zzatl().asBinder(), false);
    zzbcn.zza(paramParcel, 3, (Parcelable)getLocation(), paramInt, false);
    zzbcn.zza(paramParcel, 4, getWidth());
    zzbcn.zza(paramParcel, 5, getHeight());
    zzbcn.zza(paramParcel, 6, (Parcelable)getBounds(), paramInt, false);
    zzbcn.zza(paramParcel, 7, getBearing());
    zzbcn.zza(paramParcel, 8, getZIndex());
    zzbcn.zza(paramParcel, 9, isVisible());
    zzbcn.zza(paramParcel, 10, getTransparency());
    zzbcn.zza(paramParcel, 11, getAnchorU());
    zzbcn.zza(paramParcel, 12, getAnchorV());
    zzbcn.zza(paramParcel, 13, isClickable());
    zzbcn.zzai(paramParcel, i);
  }
  
  public final GroundOverlayOptions zIndex(float paramFloat) {
    this.zziio = paramFloat;
    return this;
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/maps/model/GroundOverlayOptions.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */