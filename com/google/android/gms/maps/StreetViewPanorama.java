package com.google.android.gms.maps;

import android.graphics.Point;
import android.os.RemoteException;
import android.support.annotation.NonNull;
import com.google.android.gms.common.internal.zzbp;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.zzn;
import com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate;
import com.google.android.gms.maps.internal.zzbh;
import com.google.android.gms.maps.internal.zzbj;
import com.google.android.gms.maps.internal.zzbl;
import com.google.android.gms.maps.internal.zzbn;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.RuntimeRemoteException;
import com.google.android.gms.maps.model.StreetViewPanoramaCamera;
import com.google.android.gms.maps.model.StreetViewPanoramaLocation;
import com.google.android.gms.maps.model.StreetViewPanoramaOrientation;

public class StreetViewPanorama {
  private final IStreetViewPanoramaDelegate zzihg;
  
  public StreetViewPanorama(@NonNull IStreetViewPanoramaDelegate paramIStreetViewPanoramaDelegate) {
    this.zzihg = (IStreetViewPanoramaDelegate)zzbp.zzb(paramIStreetViewPanoramaDelegate, "delegate");
  }
  
  public void animateTo(StreetViewPanoramaCamera paramStreetViewPanoramaCamera, long paramLong) {
    try {
      this.zzihg.animateTo(paramStreetViewPanoramaCamera, paramLong);
      return;
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
  
  public StreetViewPanoramaLocation getLocation() {
    try {
      return this.zzihg.getStreetViewPanoramaLocation();
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
  
  public StreetViewPanoramaCamera getPanoramaCamera() {
    try {
      return this.zzihg.getPanoramaCamera();
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
  
  public boolean isPanningGesturesEnabled() {
    try {
      return this.zzihg.isPanningGesturesEnabled();
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
  
  public boolean isStreetNamesEnabled() {
    try {
      return this.zzihg.isStreetNamesEnabled();
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
  
  public boolean isUserNavigationEnabled() {
    try {
      return this.zzihg.isUserNavigationEnabled();
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
  
  public boolean isZoomGesturesEnabled() {
    try {
      return this.zzihg.isZoomGesturesEnabled();
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
  
  public Point orientationToPoint(StreetViewPanoramaOrientation paramStreetViewPanoramaOrientation) {
    try {
      IObjectWrapper iObjectWrapper = this.zzihg.orientationToPoint(paramStreetViewPanoramaOrientation);
      return (iObjectWrapper == null) ? null : (Point)zzn.zzx(iObjectWrapper);
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
  
  public StreetViewPanoramaOrientation pointToOrientation(Point paramPoint) {
    try {
      return this.zzihg.pointToOrientation(zzn.zzw(paramPoint));
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
  
  public final void setOnStreetViewPanoramaCameraChangeListener(OnStreetViewPanoramaCameraChangeListener paramOnStreetViewPanoramaCameraChangeListener) {
    if (paramOnStreetViewPanoramaCameraChangeListener == null)
      try {
        this.zzihg.setOnStreetViewPanoramaCameraChangeListener(null);
        return;
      } catch (RemoteException remoteException) {
        throw new RuntimeRemoteException(remoteException);
      }  
    IStreetViewPanoramaDelegate iStreetViewPanoramaDelegate = this.zzihg;
    zzae zzae = new zzae();
    this(this, (OnStreetViewPanoramaCameraChangeListener)remoteException);
    iStreetViewPanoramaDelegate.setOnStreetViewPanoramaCameraChangeListener((zzbh)zzae);
  }
  
  public final void setOnStreetViewPanoramaChangeListener(OnStreetViewPanoramaChangeListener paramOnStreetViewPanoramaChangeListener) {
    if (paramOnStreetViewPanoramaChangeListener == null)
      try {
        this.zzihg.setOnStreetViewPanoramaChangeListener(null);
        return;
      } catch (RemoteException remoteException) {
        throw new RuntimeRemoteException(remoteException);
      }  
    IStreetViewPanoramaDelegate iStreetViewPanoramaDelegate = this.zzihg;
    zzad zzad = new zzad();
    this(this, (OnStreetViewPanoramaChangeListener)remoteException);
    iStreetViewPanoramaDelegate.setOnStreetViewPanoramaChangeListener((zzbj)zzad);
  }
  
  public final void setOnStreetViewPanoramaClickListener(OnStreetViewPanoramaClickListener paramOnStreetViewPanoramaClickListener) {
    if (paramOnStreetViewPanoramaClickListener == null)
      try {
        this.zzihg.setOnStreetViewPanoramaClickListener(null);
        return;
      } catch (RemoteException remoteException) {
        throw new RuntimeRemoteException(remoteException);
      }  
    IStreetViewPanoramaDelegate iStreetViewPanoramaDelegate = this.zzihg;
    zzaf zzaf = new zzaf();
    this(this, (OnStreetViewPanoramaClickListener)remoteException);
    iStreetViewPanoramaDelegate.setOnStreetViewPanoramaClickListener((zzbl)zzaf);
  }
  
  public final void setOnStreetViewPanoramaLongClickListener(OnStreetViewPanoramaLongClickListener paramOnStreetViewPanoramaLongClickListener) {
    if (paramOnStreetViewPanoramaLongClickListener == null)
      try {
        this.zzihg.setOnStreetViewPanoramaLongClickListener(null);
        return;
      } catch (RemoteException remoteException) {
        throw new RuntimeRemoteException(remoteException);
      }  
    IStreetViewPanoramaDelegate iStreetViewPanoramaDelegate = this.zzihg;
    zzag zzag = new zzag();
    this(this, (OnStreetViewPanoramaLongClickListener)remoteException);
    iStreetViewPanoramaDelegate.setOnStreetViewPanoramaLongClickListener((zzbn)zzag);
  }
  
  public void setPanningGesturesEnabled(boolean paramBoolean) {
    try {
      this.zzihg.enablePanning(paramBoolean);
      return;
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
  
  public void setPosition(LatLng paramLatLng) {
    try {
      this.zzihg.setPosition(paramLatLng);
      return;
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
  
  public void setPosition(LatLng paramLatLng, int paramInt) {
    try {
      this.zzihg.setPositionWithRadius(paramLatLng, paramInt);
      return;
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
  
  public void setPosition(String paramString) {
    try {
      this.zzihg.setPositionWithID(paramString);
      return;
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
  
  public void setStreetNamesEnabled(boolean paramBoolean) {
    try {
      this.zzihg.enableStreetNames(paramBoolean);
      return;
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
  
  public void setUserNavigationEnabled(boolean paramBoolean) {
    try {
      this.zzihg.enableUserNavigation(paramBoolean);
      return;
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
  
  public void setZoomGesturesEnabled(boolean paramBoolean) {
    try {
      this.zzihg.enableZoom(paramBoolean);
      return;
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
  
  public static interface OnStreetViewPanoramaCameraChangeListener {
    void onStreetViewPanoramaCameraChange(StreetViewPanoramaCamera param1StreetViewPanoramaCamera);
  }
  
  public static interface OnStreetViewPanoramaChangeListener {
    void onStreetViewPanoramaChange(StreetViewPanoramaLocation param1StreetViewPanoramaLocation);
  }
  
  public static interface OnStreetViewPanoramaClickListener {
    void onStreetViewPanoramaClick(StreetViewPanoramaOrientation param1StreetViewPanoramaOrientation);
  }
  
  public static interface OnStreetViewPanoramaLongClickListener {
    void onStreetViewPanoramaLongClick(StreetViewPanoramaOrientation param1StreetViewPanoramaOrientation);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/maps/StreetViewPanorama.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */