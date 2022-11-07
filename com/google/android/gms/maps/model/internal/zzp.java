package com.google.android.gms.maps.model.internal;

import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.maps.model.LatLng;

public interface zzp extends IInterface {
  float getAlpha() throws RemoteException;
  
  String getId() throws RemoteException;
  
  LatLng getPosition() throws RemoteException;
  
  float getRotation() throws RemoteException;
  
  String getSnippet() throws RemoteException;
  
  IObjectWrapper getTag() throws RemoteException;
  
  String getTitle() throws RemoteException;
  
  float getZIndex() throws RemoteException;
  
  int hashCodeRemote() throws RemoteException;
  
  void hideInfoWindow() throws RemoteException;
  
  boolean isDraggable() throws RemoteException;
  
  boolean isFlat() throws RemoteException;
  
  boolean isInfoWindowShown() throws RemoteException;
  
  boolean isVisible() throws RemoteException;
  
  void remove() throws RemoteException;
  
  void setAlpha(float paramFloat) throws RemoteException;
  
  void setAnchor(float paramFloat1, float paramFloat2) throws RemoteException;
  
  void setDraggable(boolean paramBoolean) throws RemoteException;
  
  void setFlat(boolean paramBoolean) throws RemoteException;
  
  void setInfoWindowAnchor(float paramFloat1, float paramFloat2) throws RemoteException;
  
  void setPosition(LatLng paramLatLng) throws RemoteException;
  
  void setRotation(float paramFloat) throws RemoteException;
  
  void setSnippet(String paramString) throws RemoteException;
  
  void setTag(IObjectWrapper paramIObjectWrapper) throws RemoteException;
  
  void setTitle(String paramString) throws RemoteException;
  
  void setVisible(boolean paramBoolean) throws RemoteException;
  
  void setZIndex(float paramFloat) throws RemoteException;
  
  void showInfoWindow() throws RemoteException;
  
  void zzad(IObjectWrapper paramIObjectWrapper) throws RemoteException;
  
  boolean zzj(zzp paramzzp) throws RemoteException;
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/maps/model/internal/zzp.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */