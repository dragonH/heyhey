package com.google.android.gms.maps.internal;

import android.os.Bundle;
import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.maps.GoogleMapOptions;

public interface IMapFragmentDelegate extends IInterface {
  IGoogleMapDelegate getMap() throws RemoteException;
  
  void getMapAsync(zzap paramzzap) throws RemoteException;
  
  boolean isReady() throws RemoteException;
  
  void onCreate(Bundle paramBundle) throws RemoteException;
  
  IObjectWrapper onCreateView(IObjectWrapper paramIObjectWrapper1, IObjectWrapper paramIObjectWrapper2, Bundle paramBundle) throws RemoteException;
  
  void onDestroy() throws RemoteException;
  
  void onDestroyView() throws RemoteException;
  
  void onEnterAmbient(Bundle paramBundle) throws RemoteException;
  
  void onExitAmbient() throws RemoteException;
  
  void onInflate(IObjectWrapper paramIObjectWrapper, GoogleMapOptions paramGoogleMapOptions, Bundle paramBundle) throws RemoteException;
  
  void onLowMemory() throws RemoteException;
  
  void onPause() throws RemoteException;
  
  void onResume() throws RemoteException;
  
  void onSaveInstanceState(Bundle paramBundle) throws RemoteException;
  
  void onStart() throws RemoteException;
  
  void onStop() throws RemoteException;
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/maps/internal/IMapFragmentDelegate.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */