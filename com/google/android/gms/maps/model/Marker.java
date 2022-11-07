package com.google.android.gms.maps.model;

import android.os.RemoteException;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.zzbp;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.zzn;
import com.google.android.gms.maps.model.internal.zzp;

public final class Marker {
  private final zzp zzijh;
  
  public Marker(zzp paramzzp) {
    this.zzijh = (zzp)zzbp.zzu(paramzzp);
  }
  
  public final boolean equals(Object paramObject) {
    if (!(paramObject instanceof Marker))
      return false; 
    try {
      return this.zzijh.zzj(((Marker)paramObject).zzijh);
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
  
  public final float getAlpha() {
    try {
      return this.zzijh.getAlpha();
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
  
  public final String getId() {
    try {
      return this.zzijh.getId();
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
  
  public final LatLng getPosition() {
    try {
      return this.zzijh.getPosition();
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
  
  public final float getRotation() {
    try {
      return this.zzijh.getRotation();
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
  
  public final String getSnippet() {
    try {
      return this.zzijh.getSnippet();
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
  
  @Nullable
  public final Object getTag() {
    try {
      return zzn.zzx(this.zzijh.getTag());
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
  
  public final String getTitle() {
    try {
      return this.zzijh.getTitle();
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
  
  public final float getZIndex() {
    try {
      return this.zzijh.getZIndex();
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
  
  public final int hashCode() {
    try {
      return this.zzijh.hashCodeRemote();
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
  
  public final void hideInfoWindow() {
    try {
      this.zzijh.hideInfoWindow();
      return;
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
  
  public final boolean isDraggable() {
    try {
      return this.zzijh.isDraggable();
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
  
  public final boolean isFlat() {
    try {
      return this.zzijh.isFlat();
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
  
  public final boolean isInfoWindowShown() {
    try {
      return this.zzijh.isInfoWindowShown();
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
  
  public final boolean isVisible() {
    try {
      return this.zzijh.isVisible();
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
  
  public final void remove() {
    try {
      this.zzijh.remove();
      return;
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
  
  public final void setAlpha(float paramFloat) {
    try {
      this.zzijh.setAlpha(paramFloat);
      return;
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
  
  public final void setAnchor(float paramFloat1, float paramFloat2) {
    try {
      this.zzijh.setAnchor(paramFloat1, paramFloat2);
      return;
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
  
  public final void setDraggable(boolean paramBoolean) {
    try {
      this.zzijh.setDraggable(paramBoolean);
      return;
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
  
  public final void setFlat(boolean paramBoolean) {
    try {
      this.zzijh.setFlat(paramBoolean);
      return;
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
  
  public final void setIcon(@Nullable BitmapDescriptor paramBitmapDescriptor) {
    if (paramBitmapDescriptor == null)
      try {
        this.zzijh.zzad(null);
        return;
      } catch (RemoteException remoteException) {
        throw new RuntimeRemoteException(remoteException);
      }  
    IObjectWrapper iObjectWrapper = remoteException.zzatl();
    this.zzijh.zzad(iObjectWrapper);
  }
  
  public final void setInfoWindowAnchor(float paramFloat1, float paramFloat2) {
    try {
      this.zzijh.setInfoWindowAnchor(paramFloat1, paramFloat2);
      return;
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
  
  public final void setPosition(@NonNull LatLng paramLatLng) {
    if (paramLatLng != null)
      try {
        this.zzijh.setPosition(paramLatLng);
        return;
      } catch (RemoteException remoteException) {
        throw new RuntimeRemoteException(remoteException);
      }  
    throw new IllegalArgumentException("latlng cannot be null - a position is required.");
  }
  
  public final void setRotation(float paramFloat) {
    try {
      this.zzijh.setRotation(paramFloat);
      return;
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
  
  public final void setSnippet(@Nullable String paramString) {
    try {
      this.zzijh.setSnippet(paramString);
      return;
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
  
  public final void setTag(@Nullable Object paramObject) {
    try {
      this.zzijh.setTag(zzn.zzw(paramObject));
      return;
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
  
  public final void setTitle(@Nullable String paramString) {
    try {
      this.zzijh.setTitle(paramString);
      return;
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
  
  public final void setVisible(boolean paramBoolean) {
    try {
      this.zzijh.setVisible(paramBoolean);
      return;
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
  
  public final void setZIndex(float paramFloat) {
    try {
      this.zzijh.setZIndex(paramFloat);
      return;
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
  
  public final void showInfoWindow() {
    try {
      this.zzijh.showInfoWindow();
      return;
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/maps/model/Marker.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */