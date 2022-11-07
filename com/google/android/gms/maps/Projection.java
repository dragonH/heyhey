package com.google.android.gms.maps;

import android.graphics.Point;
import android.os.RemoteException;
import com.google.android.gms.dynamic.zzn;
import com.google.android.gms.maps.internal.IProjectionDelegate;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.RuntimeRemoteException;
import com.google.android.gms.maps.model.VisibleRegion;

public final class Projection {
  private final IProjectionDelegate zzihf;
  
  Projection(IProjectionDelegate paramIProjectionDelegate) {
    this.zzihf = paramIProjectionDelegate;
  }
  
  public final LatLng fromScreenLocation(Point paramPoint) {
    try {
      return this.zzihf.fromScreenLocation(zzn.zzw(paramPoint));
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
  
  public final VisibleRegion getVisibleRegion() {
    try {
      return this.zzihf.getVisibleRegion();
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
  
  public final Point toScreenLocation(LatLng paramLatLng) {
    try {
      return (Point)zzn.zzx(this.zzihf.toScreenLocation(paramLatLng));
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/maps/Projection.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */