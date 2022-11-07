package com.google.android.gms.maps.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.zzeb;
import com.google.android.gms.internal.zzed;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.StreetViewPanoramaCamera;
import com.google.android.gms.maps.model.StreetViewPanoramaLocation;
import com.google.android.gms.maps.model.StreetViewPanoramaOrientation;

public final class zzbu extends zzeb implements IStreetViewPanoramaDelegate {
  zzbu(IBinder paramIBinder) {
    super(paramIBinder, "com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate");
  }
  
  public final void animateTo(StreetViewPanoramaCamera paramStreetViewPanoramaCamera, long paramLong) throws RemoteException {
    Parcel parcel = zzax();
    zzed.zza(parcel, (Parcelable)paramStreetViewPanoramaCamera);
    parcel.writeLong(paramLong);
    zzb(9, parcel);
  }
  
  public final void enablePanning(boolean paramBoolean) throws RemoteException {
    Parcel parcel = zzax();
    zzed.zza(parcel, paramBoolean);
    zzb(2, parcel);
  }
  
  public final void enableStreetNames(boolean paramBoolean) throws RemoteException {
    Parcel parcel = zzax();
    zzed.zza(parcel, paramBoolean);
    zzb(4, parcel);
  }
  
  public final void enableUserNavigation(boolean paramBoolean) throws RemoteException {
    Parcel parcel = zzax();
    zzed.zza(parcel, paramBoolean);
    zzb(3, parcel);
  }
  
  public final void enableZoom(boolean paramBoolean) throws RemoteException {
    Parcel parcel = zzax();
    zzed.zza(parcel, paramBoolean);
    zzb(1, parcel);
  }
  
  public final StreetViewPanoramaCamera getPanoramaCamera() throws RemoteException {
    Parcel parcel = zza(10, zzax());
    StreetViewPanoramaCamera streetViewPanoramaCamera = (StreetViewPanoramaCamera)zzed.zza(parcel, StreetViewPanoramaCamera.CREATOR);
    parcel.recycle();
    return streetViewPanoramaCamera;
  }
  
  public final StreetViewPanoramaLocation getStreetViewPanoramaLocation() throws RemoteException {
    Parcel parcel = zza(14, zzax());
    StreetViewPanoramaLocation streetViewPanoramaLocation = (StreetViewPanoramaLocation)zzed.zza(parcel, StreetViewPanoramaLocation.CREATOR);
    parcel.recycle();
    return streetViewPanoramaLocation;
  }
  
  public final boolean isPanningGesturesEnabled() throws RemoteException {
    Parcel parcel = zza(6, zzax());
    boolean bool = zzed.zza(parcel);
    parcel.recycle();
    return bool;
  }
  
  public final boolean isStreetNamesEnabled() throws RemoteException {
    Parcel parcel = zza(8, zzax());
    boolean bool = zzed.zza(parcel);
    parcel.recycle();
    return bool;
  }
  
  public final boolean isUserNavigationEnabled() throws RemoteException {
    Parcel parcel = zza(7, zzax());
    boolean bool = zzed.zza(parcel);
    parcel.recycle();
    return bool;
  }
  
  public final boolean isZoomGesturesEnabled() throws RemoteException {
    Parcel parcel = zza(5, zzax());
    boolean bool = zzed.zza(parcel);
    parcel.recycle();
    return bool;
  }
  
  public final IObjectWrapper orientationToPoint(StreetViewPanoramaOrientation paramStreetViewPanoramaOrientation) throws RemoteException {
    Parcel parcel = zzax();
    zzed.zza(parcel, (Parcelable)paramStreetViewPanoramaOrientation);
    parcel = zza(19, parcel);
    IObjectWrapper iObjectWrapper = IObjectWrapper.zza.zzao(parcel.readStrongBinder());
    parcel.recycle();
    return iObjectWrapper;
  }
  
  public final StreetViewPanoramaOrientation pointToOrientation(IObjectWrapper paramIObjectWrapper) throws RemoteException {
    Parcel parcel2 = zzax();
    zzed.zza(parcel2, (IInterface)paramIObjectWrapper);
    Parcel parcel1 = zza(18, parcel2);
    StreetViewPanoramaOrientation streetViewPanoramaOrientation = (StreetViewPanoramaOrientation)zzed.zza(parcel1, StreetViewPanoramaOrientation.CREATOR);
    parcel1.recycle();
    return streetViewPanoramaOrientation;
  }
  
  public final void setOnStreetViewPanoramaCameraChangeListener(zzbh paramzzbh) throws RemoteException {
    Parcel parcel = zzax();
    zzed.zza(parcel, paramzzbh);
    zzb(16, parcel);
  }
  
  public final void setOnStreetViewPanoramaChangeListener(zzbj paramzzbj) throws RemoteException {
    Parcel parcel = zzax();
    zzed.zza(parcel, paramzzbj);
    zzb(15, parcel);
  }
  
  public final void setOnStreetViewPanoramaClickListener(zzbl paramzzbl) throws RemoteException {
    Parcel parcel = zzax();
    zzed.zza(parcel, paramzzbl);
    zzb(17, parcel);
  }
  
  public final void setOnStreetViewPanoramaLongClickListener(zzbn paramzzbn) throws RemoteException {
    Parcel parcel = zzax();
    zzed.zza(parcel, paramzzbn);
    zzb(20, parcel);
  }
  
  public final void setPosition(LatLng paramLatLng) throws RemoteException {
    Parcel parcel = zzax();
    zzed.zza(parcel, (Parcelable)paramLatLng);
    zzb(12, parcel);
  }
  
  public final void setPositionWithID(String paramString) throws RemoteException {
    Parcel parcel = zzax();
    parcel.writeString(paramString);
    zzb(11, parcel);
  }
  
  public final void setPositionWithRadius(LatLng paramLatLng, int paramInt) throws RemoteException {
    Parcel parcel = zzax();
    zzed.zza(parcel, (Parcelable)paramLatLng);
    parcel.writeInt(paramInt);
    zzb(13, parcel);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/maps/internal/zzbu.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */