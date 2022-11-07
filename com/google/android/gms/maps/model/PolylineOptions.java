package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.zzbp;
import com.google.android.gms.internal.zzbck;
import com.google.android.gms.internal.zzbcn;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class PolylineOptions extends zzbck {
  public static final Parcelable.Creator<PolylineOptions> CREATOR = new zzl();
  
  private int mColor = -16777216;
  
  private float zziio = 0.0F;
  
  private boolean zziip = true;
  
  private boolean zziiq = false;
  
  private float zziiv = 10.0F;
  
  private final List<LatLng> zzijr = new ArrayList<LatLng>();
  
  private boolean zzijt = false;
  
  @NonNull
  private Cap zzijw = new ButtCap();
  
  @NonNull
  private Cap zzijx = new ButtCap();
  
  private int zzijy = 0;
  
  @Nullable
  private List<PatternItem> zzijz = null;
  
  public PolylineOptions() {}
  
  PolylineOptions(List<LatLng> paramList, float paramFloat1, int paramInt1, float paramFloat2, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, @Nullable Cap paramCap1, @Nullable Cap paramCap2, int paramInt2, @Nullable List<PatternItem> paramList1) {
    this.zziiv = paramFloat1;
    this.mColor = paramInt1;
    this.zziio = paramFloat2;
    this.zziip = paramBoolean1;
    this.zzijt = paramBoolean2;
    this.zziiq = paramBoolean3;
    if (paramCap1 != null)
      this.zzijw = paramCap1; 
    if (paramCap2 != null)
      this.zzijx = paramCap2; 
    this.zzijy = paramInt2;
    this.zzijz = paramList1;
  }
  
  public final PolylineOptions add(LatLng paramLatLng) {
    this.zzijr.add(paramLatLng);
    return this;
  }
  
  public final PolylineOptions add(LatLng... paramVarArgs) {
    this.zzijr.addAll(Arrays.asList(paramVarArgs));
    return this;
  }
  
  public final PolylineOptions addAll(Iterable<LatLng> paramIterable) {
    for (LatLng latLng : paramIterable)
      this.zzijr.add(latLng); 
    return this;
  }
  
  public final PolylineOptions clickable(boolean paramBoolean) {
    this.zziiq = paramBoolean;
    return this;
  }
  
  public final PolylineOptions color(int paramInt) {
    this.mColor = paramInt;
    return this;
  }
  
  public final PolylineOptions endCap(@NonNull Cap paramCap) {
    this.zzijx = (Cap)zzbp.zzb(paramCap, "endCap must not be null");
    return this;
  }
  
  public final PolylineOptions geodesic(boolean paramBoolean) {
    this.zzijt = paramBoolean;
    return this;
  }
  
  public final int getColor() {
    return this.mColor;
  }
  
  @NonNull
  public final Cap getEndCap() {
    return this.zzijx;
  }
  
  public final int getJointType() {
    return this.zzijy;
  }
  
  @Nullable
  public final List<PatternItem> getPattern() {
    return this.zzijz;
  }
  
  public final List<LatLng> getPoints() {
    return this.zzijr;
  }
  
  @NonNull
  public final Cap getStartCap() {
    return this.zzijw;
  }
  
  public final float getWidth() {
    return this.zziiv;
  }
  
  public final float getZIndex() {
    return this.zziio;
  }
  
  public final boolean isClickable() {
    return this.zziiq;
  }
  
  public final boolean isGeodesic() {
    return this.zzijt;
  }
  
  public final boolean isVisible() {
    return this.zziip;
  }
  
  public final PolylineOptions jointType(int paramInt) {
    this.zzijy = paramInt;
    return this;
  }
  
  public final PolylineOptions pattern(@Nullable List<PatternItem> paramList) {
    this.zzijz = paramList;
    return this;
  }
  
  public final PolylineOptions startCap(@NonNull Cap paramCap) {
    this.zzijw = (Cap)zzbp.zzb(paramCap, "startCap must not be null");
    return this;
  }
  
  public final PolylineOptions visible(boolean paramBoolean) {
    this.zziip = paramBoolean;
    return this;
  }
  
  public final PolylineOptions width(float paramFloat) {
    this.zziiv = paramFloat;
    return this;
  }
  
  public final void writeToParcel(Parcel paramParcel, int paramInt) {
    int i = zzbcn.zze(paramParcel);
    zzbcn.zzc(paramParcel, 2, getPoints(), false);
    zzbcn.zza(paramParcel, 3, getWidth());
    zzbcn.zzc(paramParcel, 4, getColor());
    zzbcn.zza(paramParcel, 5, getZIndex());
    zzbcn.zza(paramParcel, 6, isVisible());
    zzbcn.zza(paramParcel, 7, isGeodesic());
    zzbcn.zza(paramParcel, 8, isClickable());
    zzbcn.zza(paramParcel, 9, (Parcelable)getStartCap(), paramInt, false);
    zzbcn.zza(paramParcel, 10, (Parcelable)getEndCap(), paramInt, false);
    zzbcn.zzc(paramParcel, 11, getJointType());
    zzbcn.zzc(paramParcel, 12, getPattern(), false);
    zzbcn.zzai(paramParcel, i);
  }
  
  public final PolylineOptions zIndex(float paramFloat) {
    this.zziio = paramFloat;
    return this;
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/maps/model/PolylineOptions.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */