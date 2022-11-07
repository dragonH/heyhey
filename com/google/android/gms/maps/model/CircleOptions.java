package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import com.google.android.gms.internal.zzbck;
import com.google.android.gms.internal.zzbcn;
import java.util.List;

public final class CircleOptions extends zzbck {
  public static final Parcelable.Creator<CircleOptions> CREATOR = new zzc();
  
  private int mFillColor = 0;
  
  private int mStrokeColor = -16777216;
  
  private float mStrokeWidth = 10.0F;
  
  private LatLng zziim = null;
  
  private double zziin = 0.0D;
  
  private float zziio = 0.0F;
  
  private boolean zziip = true;
  
  private boolean zziiq = false;
  
  @Nullable
  private List<PatternItem> zziir = null;
  
  public CircleOptions() {}
  
  CircleOptions(LatLng paramLatLng, double paramDouble, float paramFloat1, int paramInt1, int paramInt2, float paramFloat2, boolean paramBoolean1, boolean paramBoolean2, @Nullable List<PatternItem> paramList) {}
  
  public final CircleOptions center(LatLng paramLatLng) {
    this.zziim = paramLatLng;
    return this;
  }
  
  public final CircleOptions clickable(boolean paramBoolean) {
    this.zziiq = paramBoolean;
    return this;
  }
  
  public final CircleOptions fillColor(int paramInt) {
    this.mFillColor = paramInt;
    return this;
  }
  
  public final LatLng getCenter() {
    return this.zziim;
  }
  
  public final int getFillColor() {
    return this.mFillColor;
  }
  
  public final double getRadius() {
    return this.zziin;
  }
  
  public final int getStrokeColor() {
    return this.mStrokeColor;
  }
  
  @Nullable
  public final List<PatternItem> getStrokePattern() {
    return this.zziir;
  }
  
  public final float getStrokeWidth() {
    return this.mStrokeWidth;
  }
  
  public final float getZIndex() {
    return this.zziio;
  }
  
  public final boolean isClickable() {
    return this.zziiq;
  }
  
  public final boolean isVisible() {
    return this.zziip;
  }
  
  public final CircleOptions radius(double paramDouble) {
    this.zziin = paramDouble;
    return this;
  }
  
  public final CircleOptions strokeColor(int paramInt) {
    this.mStrokeColor = paramInt;
    return this;
  }
  
  public final CircleOptions strokePattern(@Nullable List<PatternItem> paramList) {
    this.zziir = paramList;
    return this;
  }
  
  public final CircleOptions strokeWidth(float paramFloat) {
    this.mStrokeWidth = paramFloat;
    return this;
  }
  
  public final CircleOptions visible(boolean paramBoolean) {
    this.zziip = paramBoolean;
    return this;
  }
  
  public final void writeToParcel(Parcel paramParcel, int paramInt) {
    int i = zzbcn.zze(paramParcel);
    zzbcn.zza(paramParcel, 2, (Parcelable)getCenter(), paramInt, false);
    zzbcn.zza(paramParcel, 3, getRadius());
    zzbcn.zza(paramParcel, 4, getStrokeWidth());
    zzbcn.zzc(paramParcel, 5, getStrokeColor());
    zzbcn.zzc(paramParcel, 6, getFillColor());
    zzbcn.zza(paramParcel, 7, getZIndex());
    zzbcn.zza(paramParcel, 8, isVisible());
    zzbcn.zza(paramParcel, 9, isClickable());
    zzbcn.zzc(paramParcel, 10, getStrokePattern(), false);
    zzbcn.zzai(paramParcel, i);
  }
  
  public final CircleOptions zIndex(float paramFloat) {
    this.zziio = paramFloat;
    return this;
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/maps/model/CircleOptions.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */