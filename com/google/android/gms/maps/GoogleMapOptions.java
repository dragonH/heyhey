package com.google.android.gms.maps;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import com.google.android.gms.R;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.zzbf;
import com.google.android.gms.internal.zzbck;
import com.google.android.gms.internal.zzbcn;
import com.google.android.gms.maps.internal.zza;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLngBounds;

public final class GoogleMapOptions extends zzbck implements ReflectedParcelable {
  public static final Parcelable.Creator<GoogleMapOptions> CREATOR = new zzaa();
  
  private Boolean zzigd;
  
  private Boolean zzige;
  
  private int zzigf = -1;
  
  private CameraPosition zzigg;
  
  private Boolean zzigh;
  
  private Boolean zzigi;
  
  private Boolean zzigj;
  
  private Boolean zzigk;
  
  private Boolean zzigl;
  
  private Boolean zzigm;
  
  private Boolean zzign;
  
  private Boolean zzigo;
  
  private Boolean zzigp;
  
  private Float zzigq = null;
  
  private Float zzigr = null;
  
  private LatLngBounds zzigs = null;
  
  public GoogleMapOptions() {}
  
  GoogleMapOptions(byte paramByte1, byte paramByte2, int paramInt, CameraPosition paramCameraPosition, byte paramByte3, byte paramByte4, byte paramByte5, byte paramByte6, byte paramByte7, byte paramByte8, byte paramByte9, byte paramByte10, byte paramByte11, Float paramFloat1, Float paramFloat2, LatLngBounds paramLatLngBounds) {
    this.zzigd = zza.zza(paramByte1);
    this.zzige = zza.zza(paramByte2);
    this.zzigf = paramInt;
    this.zzigg = paramCameraPosition;
    this.zzigh = zza.zza(paramByte3);
    this.zzigi = zza.zza(paramByte4);
    this.zzigj = zza.zza(paramByte5);
    this.zzigk = zza.zza(paramByte6);
    this.zzigl = zza.zza(paramByte7);
    this.zzigm = zza.zza(paramByte8);
    this.zzign = zza.zza(paramByte9);
    this.zzigo = zza.zza(paramByte10);
    this.zzigp = zza.zza(paramByte11);
    this.zzigq = paramFloat1;
    this.zzigr = paramFloat2;
    this.zzigs = paramLatLngBounds;
  }
  
  public static GoogleMapOptions createFromAttributes(Context paramContext, AttributeSet paramAttributeSet) {
    if (paramAttributeSet == null)
      return null; 
    TypedArray typedArray = paramContext.getResources().obtainAttributes(paramAttributeSet, R.styleable.MapAttrs);
    GoogleMapOptions googleMapOptions = new GoogleMapOptions();
    int i = R.styleable.MapAttrs_mapType;
    if (typedArray.hasValue(i))
      googleMapOptions.mapType(typedArray.getInt(i, -1)); 
    i = R.styleable.MapAttrs_zOrderOnTop;
    if (typedArray.hasValue(i))
      googleMapOptions.zOrderOnTop(typedArray.getBoolean(i, false)); 
    i = R.styleable.MapAttrs_useViewLifecycle;
    if (typedArray.hasValue(i))
      googleMapOptions.useViewLifecycleInFragment(typedArray.getBoolean(i, false)); 
    i = R.styleable.MapAttrs_uiCompass;
    if (typedArray.hasValue(i))
      googleMapOptions.compassEnabled(typedArray.getBoolean(i, true)); 
    i = R.styleable.MapAttrs_uiRotateGestures;
    if (typedArray.hasValue(i))
      googleMapOptions.rotateGesturesEnabled(typedArray.getBoolean(i, true)); 
    i = R.styleable.MapAttrs_uiScrollGestures;
    if (typedArray.hasValue(i))
      googleMapOptions.scrollGesturesEnabled(typedArray.getBoolean(i, true)); 
    i = R.styleable.MapAttrs_uiTiltGestures;
    if (typedArray.hasValue(i))
      googleMapOptions.tiltGesturesEnabled(typedArray.getBoolean(i, true)); 
    i = R.styleable.MapAttrs_uiZoomGestures;
    if (typedArray.hasValue(i))
      googleMapOptions.zoomGesturesEnabled(typedArray.getBoolean(i, true)); 
    i = R.styleable.MapAttrs_uiZoomControls;
    if (typedArray.hasValue(i))
      googleMapOptions.zoomControlsEnabled(typedArray.getBoolean(i, true)); 
    i = R.styleable.MapAttrs_liteMode;
    if (typedArray.hasValue(i))
      googleMapOptions.liteMode(typedArray.getBoolean(i, false)); 
    i = R.styleable.MapAttrs_uiMapToolbar;
    if (typedArray.hasValue(i))
      googleMapOptions.mapToolbarEnabled(typedArray.getBoolean(i, true)); 
    i = R.styleable.MapAttrs_ambientEnabled;
    if (typedArray.hasValue(i))
      googleMapOptions.ambientEnabled(typedArray.getBoolean(i, false)); 
    i = R.styleable.MapAttrs_cameraMinZoomPreference;
    if (typedArray.hasValue(i))
      googleMapOptions.minZoomPreference(typedArray.getFloat(i, Float.NEGATIVE_INFINITY)); 
    if (typedArray.hasValue(i))
      googleMapOptions.maxZoomPreference(typedArray.getFloat(R.styleable.MapAttrs_cameraMaxZoomPreference, Float.POSITIVE_INFINITY)); 
    googleMapOptions.latLngBoundsForCameraTarget(LatLngBounds.createFromAttributes(paramContext, paramAttributeSet));
    googleMapOptions.camera(CameraPosition.createFromAttributes(paramContext, paramAttributeSet));
    typedArray.recycle();
    return googleMapOptions;
  }
  
  public final GoogleMapOptions ambientEnabled(boolean paramBoolean) {
    this.zzigp = Boolean.valueOf(paramBoolean);
    return this;
  }
  
  public final GoogleMapOptions camera(CameraPosition paramCameraPosition) {
    this.zzigg = paramCameraPosition;
    return this;
  }
  
  public final GoogleMapOptions compassEnabled(boolean paramBoolean) {
    this.zzigi = Boolean.valueOf(paramBoolean);
    return this;
  }
  
  public final Boolean getAmbientEnabled() {
    return this.zzigp;
  }
  
  public final CameraPosition getCamera() {
    return this.zzigg;
  }
  
  public final Boolean getCompassEnabled() {
    return this.zzigi;
  }
  
  public final LatLngBounds getLatLngBoundsForCameraTarget() {
    return this.zzigs;
  }
  
  public final Boolean getLiteMode() {
    return this.zzign;
  }
  
  public final Boolean getMapToolbarEnabled() {
    return this.zzigo;
  }
  
  public final int getMapType() {
    return this.zzigf;
  }
  
  public final Float getMaxZoomPreference() {
    return this.zzigr;
  }
  
  public final Float getMinZoomPreference() {
    return this.zzigq;
  }
  
  public final Boolean getRotateGesturesEnabled() {
    return this.zzigm;
  }
  
  public final Boolean getScrollGesturesEnabled() {
    return this.zzigj;
  }
  
  public final Boolean getTiltGesturesEnabled() {
    return this.zzigl;
  }
  
  public final Boolean getUseViewLifecycleInFragment() {
    return this.zzige;
  }
  
  public final Boolean getZOrderOnTop() {
    return this.zzigd;
  }
  
  public final Boolean getZoomControlsEnabled() {
    return this.zzigh;
  }
  
  public final Boolean getZoomGesturesEnabled() {
    return this.zzigk;
  }
  
  public final GoogleMapOptions latLngBoundsForCameraTarget(LatLngBounds paramLatLngBounds) {
    this.zzigs = paramLatLngBounds;
    return this;
  }
  
  public final GoogleMapOptions liteMode(boolean paramBoolean) {
    this.zzign = Boolean.valueOf(paramBoolean);
    return this;
  }
  
  public final GoogleMapOptions mapToolbarEnabled(boolean paramBoolean) {
    this.zzigo = Boolean.valueOf(paramBoolean);
    return this;
  }
  
  public final GoogleMapOptions mapType(int paramInt) {
    this.zzigf = paramInt;
    return this;
  }
  
  public final GoogleMapOptions maxZoomPreference(float paramFloat) {
    this.zzigr = Float.valueOf(paramFloat);
    return this;
  }
  
  public final GoogleMapOptions minZoomPreference(float paramFloat) {
    this.zzigq = Float.valueOf(paramFloat);
    return this;
  }
  
  public final GoogleMapOptions rotateGesturesEnabled(boolean paramBoolean) {
    this.zzigm = Boolean.valueOf(paramBoolean);
    return this;
  }
  
  public final GoogleMapOptions scrollGesturesEnabled(boolean paramBoolean) {
    this.zzigj = Boolean.valueOf(paramBoolean);
    return this;
  }
  
  public final GoogleMapOptions tiltGesturesEnabled(boolean paramBoolean) {
    this.zzigl = Boolean.valueOf(paramBoolean);
    return this;
  }
  
  public final String toString() {
    return zzbf.zzt(this).zzg("MapType", Integer.valueOf(this.zzigf)).zzg("LiteMode", this.zzign).zzg("Camera", this.zzigg).zzg("CompassEnabled", this.zzigi).zzg("ZoomControlsEnabled", this.zzigh).zzg("ScrollGesturesEnabled", this.zzigj).zzg("ZoomGesturesEnabled", this.zzigk).zzg("TiltGesturesEnabled", this.zzigl).zzg("RotateGesturesEnabled", this.zzigm).zzg("MapToolbarEnabled", this.zzigo).zzg("AmbientEnabled", this.zzigp).zzg("MinZoomPreference", this.zzigq).zzg("MaxZoomPreference", this.zzigr).zzg("LatLngBoundsForCameraTarget", this.zzigs).zzg("ZOrderOnTop", this.zzigd).zzg("UseViewLifecycleInFragment", this.zzige).toString();
  }
  
  public final GoogleMapOptions useViewLifecycleInFragment(boolean paramBoolean) {
    this.zzige = Boolean.valueOf(paramBoolean);
    return this;
  }
  
  public final void writeToParcel(Parcel paramParcel, int paramInt) {
    int i = zzbcn.zze(paramParcel);
    zzbcn.zza(paramParcel, 2, zza.zzb(this.zzigd));
    zzbcn.zza(paramParcel, 3, zza.zzb(this.zzige));
    zzbcn.zzc(paramParcel, 4, getMapType());
    zzbcn.zza(paramParcel, 5, (Parcelable)getCamera(), paramInt, false);
    zzbcn.zza(paramParcel, 6, zza.zzb(this.zzigh));
    zzbcn.zza(paramParcel, 7, zza.zzb(this.zzigi));
    zzbcn.zza(paramParcel, 8, zza.zzb(this.zzigj));
    zzbcn.zza(paramParcel, 9, zza.zzb(this.zzigk));
    zzbcn.zza(paramParcel, 10, zza.zzb(this.zzigl));
    zzbcn.zza(paramParcel, 11, zza.zzb(this.zzigm));
    zzbcn.zza(paramParcel, 12, zza.zzb(this.zzign));
    zzbcn.zza(paramParcel, 14, zza.zzb(this.zzigo));
    zzbcn.zza(paramParcel, 15, zza.zzb(this.zzigp));
    zzbcn.zza(paramParcel, 16, getMinZoomPreference(), false);
    zzbcn.zza(paramParcel, 17, getMaxZoomPreference(), false);
    zzbcn.zza(paramParcel, 18, (Parcelable)getLatLngBoundsForCameraTarget(), paramInt, false);
    zzbcn.zzai(paramParcel, i);
  }
  
  public final GoogleMapOptions zOrderOnTop(boolean paramBoolean) {
    this.zzigd = Boolean.valueOf(paramBoolean);
    return this;
  }
  
  public final GoogleMapOptions zoomControlsEnabled(boolean paramBoolean) {
    this.zzigh = Boolean.valueOf(paramBoolean);
    return this;
  }
  
  public final GoogleMapOptions zoomGesturesEnabled(boolean paramBoolean) {
    this.zzigk = Boolean.valueOf(paramBoolean);
    return this;
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/maps/GoogleMapOptions.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */