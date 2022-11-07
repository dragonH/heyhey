package com.google.android.gms.maps.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.zzeb;
import com.google.android.gms.internal.zzed;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.StreetViewPanoramaOptions;
import com.google.android.gms.maps.model.internal.zza;
import com.google.android.gms.maps.model.internal.zzb;

public final class zzf extends zzeb implements zze {
  zzf(IBinder paramIBinder) {
    super(paramIBinder, "com.google.android.gms.maps.internal.ICreator");
  }
  
  public final IMapViewDelegate zza(IObjectWrapper paramIObjectWrapper, GoogleMapOptions paramGoogleMapOptions) throws RemoteException {
    IInterface iInterface;
    Parcel parcel2 = zzax();
    zzed.zza(parcel2, (IInterface)paramIObjectWrapper);
    zzed.zza(parcel2, (Parcelable)paramGoogleMapOptions);
    Parcel parcel1 = zza(3, parcel2);
    IBinder iBinder = parcel1.readStrongBinder();
    if (iBinder == null) {
      paramIObjectWrapper = null;
    } else {
      iInterface = iBinder.queryLocalInterface("com.google.android.gms.maps.internal.IMapViewDelegate");
      if (iInterface instanceof IMapViewDelegate) {
        iInterface = iInterface;
      } else {
        iInterface = new zzk(iBinder);
      } 
    } 
    parcel1.recycle();
    return (IMapViewDelegate)iInterface;
  }
  
  public final IStreetViewPanoramaViewDelegate zza(IObjectWrapper paramIObjectWrapper, StreetViewPanoramaOptions paramStreetViewPanoramaOptions) throws RemoteException {
    IStreetViewPanoramaViewDelegate iStreetViewPanoramaViewDelegate;
    Parcel parcel2 = zzax();
    zzed.zza(parcel2, (IInterface)paramIObjectWrapper);
    zzed.zza(parcel2, (Parcelable)paramStreetViewPanoramaOptions);
    Parcel parcel1 = zza(7, parcel2);
    IBinder iBinder = parcel1.readStrongBinder();
    if (iBinder == null) {
      iBinder = null;
    } else {
      IInterface iInterface = iBinder.queryLocalInterface("com.google.android.gms.maps.internal.IStreetViewPanoramaViewDelegate");
      if (iInterface instanceof IStreetViewPanoramaViewDelegate) {
        iStreetViewPanoramaViewDelegate = (IStreetViewPanoramaViewDelegate)iInterface;
      } else {
        iStreetViewPanoramaViewDelegate = new zzbw((IBinder)iStreetViewPanoramaViewDelegate);
      } 
    } 
    parcel1.recycle();
    return iStreetViewPanoramaViewDelegate;
  }
  
  public final IMapFragmentDelegate zzaa(IObjectWrapper paramIObjectWrapper) throws RemoteException {
    IInterface iInterface;
    Parcel parcel = zzax();
    zzed.zza(parcel, (IInterface)paramIObjectWrapper);
    parcel = zza(2, parcel);
    IBinder iBinder = parcel.readStrongBinder();
    if (iBinder == null) {
      paramIObjectWrapper = null;
    } else {
      iInterface = iBinder.queryLocalInterface("com.google.android.gms.maps.internal.IMapFragmentDelegate");
      if (iInterface instanceof IMapFragmentDelegate) {
        iInterface = iInterface;
      } else {
        iInterface = new zzj(iBinder);
      } 
    } 
    parcel.recycle();
    return (IMapFragmentDelegate)iInterface;
  }
  
  public final IStreetViewPanoramaFragmentDelegate zzab(IObjectWrapper paramIObjectWrapper) throws RemoteException {
    IInterface iInterface;
    Parcel parcel = zzax();
    zzed.zza(parcel, (IInterface)paramIObjectWrapper);
    parcel = zza(8, parcel);
    IBinder iBinder = parcel.readStrongBinder();
    if (iBinder == null) {
      paramIObjectWrapper = null;
    } else {
      iInterface = iBinder.queryLocalInterface("com.google.android.gms.maps.internal.IStreetViewPanoramaFragmentDelegate");
      if (iInterface instanceof IStreetViewPanoramaFragmentDelegate) {
        iInterface = iInterface;
      } else {
        iInterface = new zzbv(iBinder);
      } 
    } 
    parcel.recycle();
    return (IStreetViewPanoramaFragmentDelegate)iInterface;
  }
  
  public final ICameraUpdateFactoryDelegate zzato() throws RemoteException {
    ICameraUpdateFactoryDelegate iCameraUpdateFactoryDelegate;
    Parcel parcel = zza(4, zzax());
    IBinder iBinder = parcel.readStrongBinder();
    if (iBinder == null) {
      iBinder = null;
    } else {
      IInterface iInterface = iBinder.queryLocalInterface("com.google.android.gms.maps.internal.ICameraUpdateFactoryDelegate");
      if (iInterface instanceof ICameraUpdateFactoryDelegate) {
        iCameraUpdateFactoryDelegate = (ICameraUpdateFactoryDelegate)iInterface;
      } else {
        iCameraUpdateFactoryDelegate = new zzb((IBinder)iCameraUpdateFactoryDelegate);
      } 
    } 
    parcel.recycle();
    return iCameraUpdateFactoryDelegate;
  }
  
  public final zza zzatp() throws RemoteException {
    Parcel parcel = zza(5, zzax());
    zza zza = zzb.zzbc(parcel.readStrongBinder());
    parcel.recycle();
    return zza;
  }
  
  public final void zzi(IObjectWrapper paramIObjectWrapper, int paramInt) throws RemoteException {
    Parcel parcel = zzax();
    zzed.zza(parcel, (IInterface)paramIObjectWrapper);
    parcel.writeInt(paramInt);
    zzb(6, parcel);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/maps/internal/zzf.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */