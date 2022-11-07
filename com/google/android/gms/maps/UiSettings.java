package com.google.android.gms.maps;

import android.os.RemoteException;
import com.google.android.gms.maps.internal.IUiSettingsDelegate;
import com.google.android.gms.maps.model.RuntimeRemoteException;

public final class UiSettings {
  private final IUiSettingsDelegate zziic;
  
  UiSettings(IUiSettingsDelegate paramIUiSettingsDelegate) {
    this.zziic = paramIUiSettingsDelegate;
  }
  
  public final boolean isCompassEnabled() {
    try {
      return this.zziic.isCompassEnabled();
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
  
  public final boolean isIndoorLevelPickerEnabled() {
    try {
      return this.zziic.isIndoorLevelPickerEnabled();
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
  
  public final boolean isMapToolbarEnabled() {
    try {
      return this.zziic.isMapToolbarEnabled();
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
  
  public final boolean isMyLocationButtonEnabled() {
    try {
      return this.zziic.isMyLocationButtonEnabled();
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
  
  public final boolean isRotateGesturesEnabled() {
    try {
      return this.zziic.isRotateGesturesEnabled();
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
  
  public final boolean isScrollGesturesEnabled() {
    try {
      return this.zziic.isScrollGesturesEnabled();
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
  
  public final boolean isTiltGesturesEnabled() {
    try {
      return this.zziic.isTiltGesturesEnabled();
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
  
  public final boolean isZoomControlsEnabled() {
    try {
      return this.zziic.isZoomControlsEnabled();
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
  
  public final boolean isZoomGesturesEnabled() {
    try {
      return this.zziic.isZoomGesturesEnabled();
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
  
  public final void setAllGesturesEnabled(boolean paramBoolean) {
    try {
      this.zziic.setAllGesturesEnabled(paramBoolean);
      return;
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
  
  public final void setCompassEnabled(boolean paramBoolean) {
    try {
      this.zziic.setCompassEnabled(paramBoolean);
      return;
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
  
  public final void setIndoorLevelPickerEnabled(boolean paramBoolean) {
    try {
      this.zziic.setIndoorLevelPickerEnabled(paramBoolean);
      return;
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
  
  public final void setMapToolbarEnabled(boolean paramBoolean) {
    try {
      this.zziic.setMapToolbarEnabled(paramBoolean);
      return;
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
  
  public final void setMyLocationButtonEnabled(boolean paramBoolean) {
    try {
      this.zziic.setMyLocationButtonEnabled(paramBoolean);
      return;
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
  
  public final void setRotateGesturesEnabled(boolean paramBoolean) {
    try {
      this.zziic.setRotateGesturesEnabled(paramBoolean);
      return;
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
  
  public final void setScrollGesturesEnabled(boolean paramBoolean) {
    try {
      this.zziic.setScrollGesturesEnabled(paramBoolean);
      return;
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
  
  public final void setTiltGesturesEnabled(boolean paramBoolean) {
    try {
      this.zziic.setTiltGesturesEnabled(paramBoolean);
      return;
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
  
  public final void setZoomControlsEnabled(boolean paramBoolean) {
    try {
      this.zziic.setZoomControlsEnabled(paramBoolean);
      return;
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
  
  public final void setZoomGesturesEnabled(boolean paramBoolean) {
    try {
      this.zziic.setZoomGesturesEnabled(paramBoolean);
      return;
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/maps/UiSettings.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */