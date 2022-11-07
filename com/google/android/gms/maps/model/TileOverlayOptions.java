package com.google.android.gms.maps.model;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.zzbp;
import com.google.android.gms.internal.zzbck;
import com.google.android.gms.internal.zzbcn;
import com.google.android.gms.maps.model.internal.zzaa;
import com.google.android.gms.maps.model.internal.zzz;

public final class TileOverlayOptions extends zzbck {
  public static final Parcelable.Creator<TileOverlayOptions> CREATOR = new zzt();
  
  private float zziio;
  
  private boolean zziip;
  
  private float zziix;
  
  private zzz zzikc;
  
  private TileProvider zzikd;
  
  private boolean zzike;
  
  public TileOverlayOptions() {
    this.zziip = true;
    this.zzike = true;
    this.zziix = 0.0F;
  }
  
  TileOverlayOptions(IBinder paramIBinder, boolean paramBoolean1, float paramFloat1, boolean paramBoolean2, float paramFloat2) {
    zzr zzr;
    this.zziip = true;
    this.zzike = true;
    this.zziix = 0.0F;
    zzz zzz1 = zzaa.zzbl(paramIBinder);
    this.zzikc = zzz1;
    if (zzz1 == null) {
      zzz1 = null;
    } else {
      zzr = new zzr(this);
    } 
    this.zzikd = zzr;
    this.zziip = paramBoolean1;
    this.zziio = paramFloat1;
    this.zzike = paramBoolean2;
    this.zziix = paramFloat2;
  }
  
  public final TileOverlayOptions fadeIn(boolean paramBoolean) {
    this.zzike = paramBoolean;
    return this;
  }
  
  public final boolean getFadeIn() {
    return this.zzike;
  }
  
  public final TileProvider getTileProvider() {
    return this.zzikd;
  }
  
  public final float getTransparency() {
    return this.zziix;
  }
  
  public final float getZIndex() {
    return this.zziio;
  }
  
  public final boolean isVisible() {
    return this.zziip;
  }
  
  public final TileOverlayOptions tileProvider(TileProvider paramTileProvider) {
    zzs zzs;
    this.zzikd = paramTileProvider;
    if (paramTileProvider == null) {
      paramTileProvider = null;
    } else {
      zzs = new zzs(this, paramTileProvider);
    } 
    this.zzikc = (zzz)zzs;
    return this;
  }
  
  public final TileOverlayOptions transparency(float paramFloat) {
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
  
  public final TileOverlayOptions visible(boolean paramBoolean) {
    this.zziip = paramBoolean;
    return this;
  }
  
  public final void writeToParcel(Parcel paramParcel, int paramInt) {
    paramInt = zzbcn.zze(paramParcel);
    zzbcn.zza(paramParcel, 2, this.zzikc.asBinder(), false);
    zzbcn.zza(paramParcel, 3, isVisible());
    zzbcn.zza(paramParcel, 4, getZIndex());
    zzbcn.zza(paramParcel, 5, getFadeIn());
    zzbcn.zza(paramParcel, 6, getTransparency());
    zzbcn.zzai(paramParcel, paramInt);
  }
  
  public final TileOverlayOptions zIndex(float paramFloat) {
    this.zziio = paramFloat;
    return this;
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/maps/model/TileOverlayOptions.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */