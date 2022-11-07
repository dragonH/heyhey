package com.google.android.gms.maps;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.zzbf;
import com.google.android.gms.internal.zzbck;
import com.google.android.gms.internal.zzbcn;
import com.google.android.gms.maps.internal.zza;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.StreetViewPanoramaCamera;

public final class StreetViewPanoramaOptions extends zzbck implements ReflectedParcelable {
  public static final Parcelable.Creator<StreetViewPanoramaOptions> CREATOR = new zzai();
  
  private Boolean zzige;
  
  private Boolean zzigk;
  
  private StreetViewPanoramaCamera zzihp;
  
  private String zzihq;
  
  private LatLng zzihr;
  
  private Integer zzihs;
  
  private Boolean zziht;
  
  private Boolean zzihu;
  
  private Boolean zzihv;
  
  public StreetViewPanoramaOptions() {
    Boolean bool = Boolean.TRUE;
    this.zziht = bool;
    this.zzigk = bool;
    this.zzihu = bool;
    this.zzihv = bool;
  }
  
  StreetViewPanoramaOptions(StreetViewPanoramaCamera paramStreetViewPanoramaCamera, String paramString, LatLng paramLatLng, Integer paramInteger, byte paramByte1, byte paramByte2, byte paramByte3, byte paramByte4, byte paramByte5) {
    Boolean bool = Boolean.TRUE;
    this.zziht = bool;
    this.zzigk = bool;
    this.zzihu = bool;
    this.zzihv = bool;
    this.zzihp = paramStreetViewPanoramaCamera;
    this.zzihr = paramLatLng;
    this.zzihs = paramInteger;
    this.zzihq = paramString;
    this.zziht = zza.zza(paramByte1);
    this.zzigk = zza.zza(paramByte2);
    this.zzihu = zza.zza(paramByte3);
    this.zzihv = zza.zza(paramByte4);
    this.zzige = zza.zza(paramByte5);
  }
  
  public final Boolean getPanningGesturesEnabled() {
    return this.zzihu;
  }
  
  public final String getPanoramaId() {
    return this.zzihq;
  }
  
  public final LatLng getPosition() {
    return this.zzihr;
  }
  
  public final Integer getRadius() {
    return this.zzihs;
  }
  
  public final Boolean getStreetNamesEnabled() {
    return this.zzihv;
  }
  
  public final StreetViewPanoramaCamera getStreetViewPanoramaCamera() {
    return this.zzihp;
  }
  
  public final Boolean getUseViewLifecycleInFragment() {
    return this.zzige;
  }
  
  public final Boolean getUserNavigationEnabled() {
    return this.zziht;
  }
  
  public final Boolean getZoomGesturesEnabled() {
    return this.zzigk;
  }
  
  public final StreetViewPanoramaOptions panningGesturesEnabled(boolean paramBoolean) {
    this.zzihu = Boolean.valueOf(paramBoolean);
    return this;
  }
  
  public final StreetViewPanoramaOptions panoramaCamera(StreetViewPanoramaCamera paramStreetViewPanoramaCamera) {
    this.zzihp = paramStreetViewPanoramaCamera;
    return this;
  }
  
  public final StreetViewPanoramaOptions panoramaId(String paramString) {
    this.zzihq = paramString;
    return this;
  }
  
  public final StreetViewPanoramaOptions position(LatLng paramLatLng) {
    this.zzihr = paramLatLng;
    return this;
  }
  
  public final StreetViewPanoramaOptions position(LatLng paramLatLng, Integer paramInteger) {
    this.zzihr = paramLatLng;
    this.zzihs = paramInteger;
    return this;
  }
  
  public final StreetViewPanoramaOptions streetNamesEnabled(boolean paramBoolean) {
    this.zzihv = Boolean.valueOf(paramBoolean);
    return this;
  }
  
  public final String toString() {
    return zzbf.zzt(this).zzg("PanoramaId", this.zzihq).zzg("Position", this.zzihr).zzg("Radius", this.zzihs).zzg("StreetViewPanoramaCamera", this.zzihp).zzg("UserNavigationEnabled", this.zziht).zzg("ZoomGesturesEnabled", this.zzigk).zzg("PanningGesturesEnabled", this.zzihu).zzg("StreetNamesEnabled", this.zzihv).zzg("UseViewLifecycleInFragment", this.zzige).toString();
  }
  
  public final StreetViewPanoramaOptions useViewLifecycleInFragment(boolean paramBoolean) {
    this.zzige = Boolean.valueOf(paramBoolean);
    return this;
  }
  
  public final StreetViewPanoramaOptions userNavigationEnabled(boolean paramBoolean) {
    this.zziht = Boolean.valueOf(paramBoolean);
    return this;
  }
  
  public final void writeToParcel(Parcel paramParcel, int paramInt) {
    int i = zzbcn.zze(paramParcel);
    zzbcn.zza(paramParcel, 2, (Parcelable)getStreetViewPanoramaCamera(), paramInt, false);
    zzbcn.zza(paramParcel, 3, getPanoramaId(), false);
    zzbcn.zza(paramParcel, 4, (Parcelable)getPosition(), paramInt, false);
    zzbcn.zza(paramParcel, 5, getRadius(), false);
    zzbcn.zza(paramParcel, 6, zza.zzb(this.zziht));
    zzbcn.zza(paramParcel, 7, zza.zzb(this.zzigk));
    zzbcn.zza(paramParcel, 8, zza.zzb(this.zzihu));
    zzbcn.zza(paramParcel, 9, zza.zzb(this.zzihv));
    zzbcn.zza(paramParcel, 10, zza.zzb(this.zzige));
    zzbcn.zzai(paramParcel, i);
  }
  
  public final StreetViewPanoramaOptions zoomGesturesEnabled(boolean paramBoolean) {
    this.zzigk = Boolean.valueOf(paramBoolean);
    return this;
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/maps/StreetViewPanoramaOptions.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */