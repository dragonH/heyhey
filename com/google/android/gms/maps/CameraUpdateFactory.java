package com.google.android.gms.maps;

import android.graphics.Point;
import android.os.RemoteException;
import com.google.android.gms.common.internal.zzbp;
import com.google.android.gms.maps.internal.ICameraUpdateFactoryDelegate;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.RuntimeRemoteException;

public final class CameraUpdateFactory {
  private static ICameraUpdateFactoryDelegate zziez;
  
  public static CameraUpdate newCameraPosition(CameraPosition paramCameraPosition) {
    try {
      return new CameraUpdate(zzatm().newCameraPosition(paramCameraPosition));
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
  
  public static CameraUpdate newLatLng(LatLng paramLatLng) {
    try {
      return new CameraUpdate(zzatm().newLatLng(paramLatLng));
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
  
  public static CameraUpdate newLatLngBounds(LatLngBounds paramLatLngBounds, int paramInt) {
    try {
      return new CameraUpdate(zzatm().newLatLngBounds(paramLatLngBounds, paramInt));
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
  
  public static CameraUpdate newLatLngBounds(LatLngBounds paramLatLngBounds, int paramInt1, int paramInt2, int paramInt3) {
    try {
      return new CameraUpdate(zzatm().newLatLngBoundsWithSize(paramLatLngBounds, paramInt1, paramInt2, paramInt3));
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
  
  public static CameraUpdate newLatLngZoom(LatLng paramLatLng, float paramFloat) {
    try {
      return new CameraUpdate(zzatm().newLatLngZoom(paramLatLng, paramFloat));
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
  
  public static CameraUpdate scrollBy(float paramFloat1, float paramFloat2) {
    try {
      return new CameraUpdate(zzatm().scrollBy(paramFloat1, paramFloat2));
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
  
  public static CameraUpdate zoomBy(float paramFloat) {
    try {
      return new CameraUpdate(zzatm().zoomBy(paramFloat));
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
  
  public static CameraUpdate zoomBy(float paramFloat, Point paramPoint) {
    try {
      return new CameraUpdate(zzatm().zoomByWithFocus(paramFloat, paramPoint.x, paramPoint.y));
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
  
  public static CameraUpdate zoomIn() {
    try {
      return new CameraUpdate(zzatm().zoomIn());
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
  
  public static CameraUpdate zoomOut() {
    try {
      return new CameraUpdate(zzatm().zoomOut());
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
  
  public static CameraUpdate zoomTo(float paramFloat) {
    try {
      return new CameraUpdate(zzatm().zoomTo(paramFloat));
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
  
  public static void zza(ICameraUpdateFactoryDelegate paramICameraUpdateFactoryDelegate) {
    zziez = (ICameraUpdateFactoryDelegate)zzbp.zzu(paramICameraUpdateFactoryDelegate);
  }
  
  private static ICameraUpdateFactoryDelegate zzatm() {
    return (ICameraUpdateFactoryDelegate)zzbp.zzb(zziez, "CameraUpdateFactory is not initialized");
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/maps/CameraUpdateFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */