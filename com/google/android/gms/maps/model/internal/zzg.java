package com.google.android.gms.maps.model.internal;

import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;

public interface zzg extends IInterface {
  float getBearing() throws RemoteException;
  
  LatLngBounds getBounds() throws RemoteException;
  
  float getHeight() throws RemoteException;
  
  String getId() throws RemoteException;
  
  LatLng getPosition() throws RemoteException;
  
  IObjectWrapper getTag() throws RemoteException;
  
  float getTransparency() throws RemoteException;
  
  float getWidth() throws RemoteException;
  
  float getZIndex() throws RemoteException;
  
  int hashCodeRemote() throws RemoteException;
  
  boolean isClickable() throws RemoteException;
  
  boolean isVisible() throws RemoteException;
  
  void remove() throws RemoteException;
  
  void setBearing(float paramFloat) throws RemoteException;
  
  void setClickable(boolean paramBoolean) throws RemoteException;
  
  void setDimensions(float paramFloat) throws RemoteException;
  
  void setPosition(LatLng paramLatLng) throws RemoteException;
  
  void setPositionFromBounds(LatLngBounds paramLatLngBounds) throws RemoteException;
  
  void setTag(IObjectWrapper paramIObjectWrapper) throws RemoteException;
  
  void setTransparency(float paramFloat) throws RemoteException;
  
  void setVisible(boolean paramBoolean) throws RemoteException;
  
  void setZIndex(float paramFloat) throws RemoteException;
  
  void zzac(IObjectWrapper paramIObjectWrapper) throws RemoteException;
  
  boolean zzb(zzg paramzzg) throws RemoteException;
  
  void zzf(float paramFloat1, float paramFloat2) throws RemoteException;
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/maps/model/internal/zzg.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */