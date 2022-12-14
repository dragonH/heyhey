package com.google.android.gms.maps.internal;

import android.os.Bundle;
import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

public interface IStreetViewPanoramaViewDelegate extends IInterface {
  IStreetViewPanoramaDelegate getStreetViewPanorama() throws RemoteException;
  
  void getStreetViewPanoramaAsync(zzbp paramzzbp) throws RemoteException;
  
  IObjectWrapper getView() throws RemoteException;
  
  void onCreate(Bundle paramBundle) throws RemoteException;
  
  void onDestroy() throws RemoteException;
  
  void onLowMemory() throws RemoteException;
  
  void onPause() throws RemoteException;
  
  void onResume() throws RemoteException;
  
  void onSaveInstanceState(Bundle paramBundle) throws RemoteException;
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/maps/internal/IStreetViewPanoramaViewDelegate.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */