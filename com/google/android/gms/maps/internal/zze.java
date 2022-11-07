package com.google.android.gms.maps.internal;

import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.StreetViewPanoramaOptions;
import com.google.android.gms.maps.model.internal.zza;

public interface zze extends IInterface {
  IMapViewDelegate zza(IObjectWrapper paramIObjectWrapper, GoogleMapOptions paramGoogleMapOptions) throws RemoteException;
  
  IStreetViewPanoramaViewDelegate zza(IObjectWrapper paramIObjectWrapper, StreetViewPanoramaOptions paramStreetViewPanoramaOptions) throws RemoteException;
  
  IMapFragmentDelegate zzaa(IObjectWrapper paramIObjectWrapper) throws RemoteException;
  
  IStreetViewPanoramaFragmentDelegate zzab(IObjectWrapper paramIObjectWrapper) throws RemoteException;
  
  ICameraUpdateFactoryDelegate zzato() throws RemoteException;
  
  zza zzatp() throws RemoteException;
  
  void zzi(IObjectWrapper paramIObjectWrapper, int paramInt) throws RemoteException;
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/maps/internal/zze.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */