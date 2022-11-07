package com.google.android.gms.maps.model.internal;

import android.os.IInterface;
import android.os.RemoteException;

public interface zzw extends IInterface {
  void clearTileCache() throws RemoteException;
  
  boolean getFadeIn() throws RemoteException;
  
  String getId() throws RemoteException;
  
  float getTransparency() throws RemoteException;
  
  float getZIndex() throws RemoteException;
  
  int hashCodeRemote() throws RemoteException;
  
  boolean isVisible() throws RemoteException;
  
  void remove() throws RemoteException;
  
  void setFadeIn(boolean paramBoolean) throws RemoteException;
  
  void setTransparency(float paramFloat) throws RemoteException;
  
  void setVisible(boolean paramBoolean) throws RemoteException;
  
  void setZIndex(float paramFloat) throws RemoteException;
  
  boolean zza(zzw paramzzw) throws RemoteException;
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/maps/model/internal/zzw.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */