package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import com.google.android.gms.internal.zzbck;
import com.google.android.gms.internal.zzbcn;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public final class PolygonOptions extends zzbck {
  public static final Parcelable.Creator<PolygonOptions> CREATOR = new zzk();
  
  private int mFillColor;
  
  private int mStrokeColor;
  
  private float mStrokeWidth;
  
  private float zziio;
  
  private boolean zziip;
  
  private boolean zziiq;
  
  @Nullable
  private List<PatternItem> zziir;
  
  private final List<LatLng> zzijr;
  
  private final List<List<LatLng>> zzijs;
  
  private boolean zzijt;
  
  private int zziju;
  
  public PolygonOptions() {
    this.mStrokeWidth = 10.0F;
    this.mStrokeColor = -16777216;
    this.mFillColor = 0;
    this.zziio = 0.0F;
    this.zziip = true;
    this.zzijt = false;
    this.zziiq = false;
    this.zziju = 0;
    this.zziir = null;
    this.zzijr = new ArrayList<LatLng>();
    this.zzijs = new ArrayList<List<LatLng>>();
  }
  
  PolygonOptions(List<LatLng> paramList, List<List<LatLng>> paramList1, float paramFloat1, int paramInt1, int paramInt2, float paramFloat2, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, int paramInt3, @Nullable List<PatternItem> paramList2) {
    this.zzijr = paramList;
    this.zzijs = paramList1;
    this.mStrokeWidth = paramFloat1;
    this.mStrokeColor = paramInt1;
    this.mFillColor = paramInt2;
    this.zziio = paramFloat2;
    this.zziip = paramBoolean1;
    this.zzijt = paramBoolean2;
    this.zziiq = paramBoolean3;
    this.zziju = paramInt3;
    this.zziir = paramList2;
  }
  
  public final PolygonOptions add(LatLng paramLatLng) {
    this.zzijr.add(paramLatLng);
    return this;
  }
  
  public final PolygonOptions add(LatLng... paramVarArgs) {
    this.zzijr.addAll(Arrays.asList(paramVarArgs));
    return this;
  }
  
  public final PolygonOptions addAll(Iterable<LatLng> paramIterable) {
    for (LatLng latLng : paramIterable)
      this.zzijr.add(latLng); 
    return this;
  }
  
  public final PolygonOptions addHole(Iterable<LatLng> paramIterable) {
    ArrayList<LatLng> arrayList = new ArrayList();
    Iterator<LatLng> iterator = paramIterable.iterator();
    while (iterator.hasNext())
      arrayList.add(iterator.next()); 
    this.zzijs.add(arrayList);
    return this;
  }
  
  public final PolygonOptions clickable(boolean paramBoolean) {
    this.zziiq = paramBoolean;
    return this;
  }
  
  public final PolygonOptions fillColor(int paramInt) {
    this.mFillColor = paramInt;
    return this;
  }
  
  public final PolygonOptions geodesic(boolean paramBoolean) {
    this.zzijt = paramBoolean;
    return this;
  }
  
  public final int getFillColor() {
    return this.mFillColor;
  }
  
  public final List<List<LatLng>> getHoles() {
    return this.zzijs;
  }
  
  public final List<LatLng> getPoints() {
    return this.zzijr;
  }
  
  public final int getStrokeColor() {
    return this.mStrokeColor;
  }
  
  public final int getStrokeJointType() {
    return this.zziju;
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
  
  public final boolean isGeodesic() {
    return this.zzijt;
  }
  
  public final boolean isVisible() {
    return this.zziip;
  }
  
  public final PolygonOptions strokeColor(int paramInt) {
    this.mStrokeColor = paramInt;
    return this;
  }
  
  public final PolygonOptions strokeJointType(int paramInt) {
    this.zziju = paramInt;
    return this;
  }
  
  public final PolygonOptions strokePattern(@Nullable List<PatternItem> paramList) {
    this.zziir = paramList;
    return this;
  }
  
  public final PolygonOptions strokeWidth(float paramFloat) {
    this.mStrokeWidth = paramFloat;
    return this;
  }
  
  public final PolygonOptions visible(boolean paramBoolean) {
    this.zziip = paramBoolean;
    return this;
  }
  
  public final void writeToParcel(Parcel paramParcel, int paramInt) {
    paramInt = zzbcn.zze(paramParcel);
    zzbcn.zzc(paramParcel, 2, getPoints(), false);
    zzbcn.zzd(paramParcel, 3, this.zzijs, false);
    zzbcn.zza(paramParcel, 4, getStrokeWidth());
    zzbcn.zzc(paramParcel, 5, getStrokeColor());
    zzbcn.zzc(paramParcel, 6, getFillColor());
    zzbcn.zza(paramParcel, 7, getZIndex());
    zzbcn.zza(paramParcel, 8, isVisible());
    zzbcn.zza(paramParcel, 9, isGeodesic());
    zzbcn.zza(paramParcel, 10, isClickable());
    zzbcn.zzc(paramParcel, 11, getStrokeJointType());
    zzbcn.zzc(paramParcel, 12, getStrokePattern(), false);
    zzbcn.zzai(paramParcel, paramInt);
  }
  
  public final PolygonOptions zIndex(float paramFloat) {
    this.zziio = paramFloat;
    return this;
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/maps/model/PolygonOptions.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */