package com.google.android.gms.maps.model;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.zzbck;
import com.google.android.gms.internal.zzbcn;

public final class MarkerOptions extends zzbck {
  public static final Parcelable.Creator<MarkerOptions> CREATOR = new zzh();
  
  private float mAlpha;
  
  private String zzehk;
  
  private LatLng zzihr;
  
  private float zziio;
  
  private boolean zziip;
  
  private float zziiy;
  
  private float zziiz;
  
  private String zziji;
  
  private BitmapDescriptor zzijj;
  
  private boolean zzijk;
  
  private boolean zzijl;
  
  private float zzijm;
  
  private float zzijn;
  
  private float zzijo;
  
  public MarkerOptions() {
    this.zziiy = 0.5F;
    this.zziiz = 1.0F;
    this.zziip = true;
    this.zzijl = false;
    this.zzijm = 0.0F;
    this.zzijn = 0.5F;
    this.zzijo = 0.0F;
    this.mAlpha = 1.0F;
  }
  
  MarkerOptions(LatLng paramLatLng, String paramString1, String paramString2, IBinder paramIBinder, float paramFloat1, float paramFloat2, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, float paramFloat3, float paramFloat4, float paramFloat5, float paramFloat6, float paramFloat7) {
    BitmapDescriptor bitmapDescriptor;
    this.zziiy = 0.5F;
    this.zziiz = 1.0F;
    this.zziip = true;
    this.zzijl = false;
    this.zzijm = 0.0F;
    this.zzijn = 0.5F;
    this.zzijo = 0.0F;
    this.mAlpha = 1.0F;
    this.zzihr = paramLatLng;
    this.zzehk = paramString1;
    this.zziji = paramString2;
    if (paramIBinder == null) {
      paramLatLng = null;
    } else {
      bitmapDescriptor = new BitmapDescriptor(IObjectWrapper.zza.zzao(paramIBinder));
    } 
    this.zzijj = bitmapDescriptor;
    this.zziiy = paramFloat1;
    this.zziiz = paramFloat2;
    this.zzijk = paramBoolean1;
    this.zziip = paramBoolean2;
    this.zzijl = paramBoolean3;
    this.zzijm = paramFloat3;
    this.zzijn = paramFloat4;
    this.zzijo = paramFloat5;
    this.mAlpha = paramFloat6;
    this.zziio = paramFloat7;
  }
  
  public final MarkerOptions alpha(float paramFloat) {
    this.mAlpha = paramFloat;
    return this;
  }
  
  public final MarkerOptions anchor(float paramFloat1, float paramFloat2) {
    this.zziiy = paramFloat1;
    this.zziiz = paramFloat2;
    return this;
  }
  
  public final MarkerOptions draggable(boolean paramBoolean) {
    this.zzijk = paramBoolean;
    return this;
  }
  
  public final MarkerOptions flat(boolean paramBoolean) {
    this.zzijl = paramBoolean;
    return this;
  }
  
  public final float getAlpha() {
    return this.mAlpha;
  }
  
  public final float getAnchorU() {
    return this.zziiy;
  }
  
  public final float getAnchorV() {
    return this.zziiz;
  }
  
  public final BitmapDescriptor getIcon() {
    return this.zzijj;
  }
  
  public final float getInfoWindowAnchorU() {
    return this.zzijn;
  }
  
  public final float getInfoWindowAnchorV() {
    return this.zzijo;
  }
  
  public final LatLng getPosition() {
    return this.zzihr;
  }
  
  public final float getRotation() {
    return this.zzijm;
  }
  
  public final String getSnippet() {
    return this.zziji;
  }
  
  public final String getTitle() {
    return this.zzehk;
  }
  
  public final float getZIndex() {
    return this.zziio;
  }
  
  public final MarkerOptions icon(@Nullable BitmapDescriptor paramBitmapDescriptor) {
    this.zzijj = paramBitmapDescriptor;
    return this;
  }
  
  public final MarkerOptions infoWindowAnchor(float paramFloat1, float paramFloat2) {
    this.zzijn = paramFloat1;
    this.zzijo = paramFloat2;
    return this;
  }
  
  public final boolean isDraggable() {
    return this.zzijk;
  }
  
  public final boolean isFlat() {
    return this.zzijl;
  }
  
  public final boolean isVisible() {
    return this.zziip;
  }
  
  public final MarkerOptions position(@NonNull LatLng paramLatLng) {
    if (paramLatLng != null) {
      this.zzihr = paramLatLng;
      return this;
    } 
    throw new IllegalArgumentException("latlng cannot be null - a position is required.");
  }
  
  public final MarkerOptions rotation(float paramFloat) {
    this.zzijm = paramFloat;
    return this;
  }
  
  public final MarkerOptions snippet(@Nullable String paramString) {
    this.zziji = paramString;
    return this;
  }
  
  public final MarkerOptions title(@Nullable String paramString) {
    this.zzehk = paramString;
    return this;
  }
  
  public final MarkerOptions visible(boolean paramBoolean) {
    this.zziip = paramBoolean;
    return this;
  }
  
  public final void writeToParcel(Parcel paramParcel, int paramInt) {
    IBinder iBinder;
    int i = zzbcn.zze(paramParcel);
    zzbcn.zza(paramParcel, 2, (Parcelable)getPosition(), paramInt, false);
    zzbcn.zza(paramParcel, 3, getTitle(), false);
    zzbcn.zza(paramParcel, 4, getSnippet(), false);
    BitmapDescriptor bitmapDescriptor = this.zzijj;
    if (bitmapDescriptor == null) {
      bitmapDescriptor = null;
    } else {
      iBinder = bitmapDescriptor.zzatl().asBinder();
    } 
    zzbcn.zza(paramParcel, 5, iBinder, false);
    zzbcn.zza(paramParcel, 6, getAnchorU());
    zzbcn.zza(paramParcel, 7, getAnchorV());
    zzbcn.zza(paramParcel, 8, isDraggable());
    zzbcn.zza(paramParcel, 9, isVisible());
    zzbcn.zza(paramParcel, 10, isFlat());
    zzbcn.zza(paramParcel, 11, getRotation());
    zzbcn.zza(paramParcel, 12, getInfoWindowAnchorU());
    zzbcn.zza(paramParcel, 13, getInfoWindowAnchorV());
    zzbcn.zza(paramParcel, 14, getAlpha());
    zzbcn.zza(paramParcel, 15, getZIndex());
    zzbcn.zzai(paramParcel, i);
  }
  
  public final MarkerOptions zIndex(float paramFloat) {
    this.zziio = paramFloat;
    return this;
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/maps/model/MarkerOptions.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */