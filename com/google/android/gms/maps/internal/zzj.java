package com.google.android.gms.maps.internal;

import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.zzeb;
import com.google.android.gms.internal.zzed;
import com.google.android.gms.maps.GoogleMapOptions;

public final class zzj extends zzeb implements IMapFragmentDelegate {
  zzj(IBinder paramIBinder) {
    super(paramIBinder, "com.google.android.gms.maps.internal.IMapFragmentDelegate");
  }
  
  public final IGoogleMapDelegate getMap() throws RemoteException {
    IInterface iInterface;
    Parcel parcel = zza(1, zzax());
    IBinder iBinder = parcel.readStrongBinder();
    if (iBinder == null) {
      iInterface = null;
    } else {
      iInterface = iBinder.queryLocalInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
      if (iInterface instanceof IGoogleMapDelegate) {
        iInterface = iInterface;
      } else {
        iInterface = new zzg(iBinder);
      } 
    } 
    parcel.recycle();
    return (IGoogleMapDelegate)iInterface;
  }
  
  public final void getMapAsync(zzap paramzzap) throws RemoteException {
    Parcel parcel = zzax();
    zzed.zza(parcel, paramzzap);
    zzb(12, parcel);
  }
  
  public final boolean isReady() throws RemoteException {
    Parcel parcel = zza(11, zzax());
    boolean bool = zzed.zza(parcel);
    parcel.recycle();
    return bool;
  }
  
  public final void onCreate(Bundle paramBundle) throws RemoteException {
    Parcel parcel = zzax();
    zzed.zza(parcel, (Parcelable)paramBundle);
    zzb(3, parcel);
  }
  
  public final IObjectWrapper onCreateView(IObjectWrapper paramIObjectWrapper1, IObjectWrapper paramIObjectWrapper2, Bundle paramBundle) throws RemoteException {
    Parcel parcel2 = zzax();
    zzed.zza(parcel2, (IInterface)paramIObjectWrapper1);
    zzed.zza(parcel2, (IInterface)paramIObjectWrapper2);
    zzed.zza(parcel2, (Parcelable)paramBundle);
    Parcel parcel1 = zza(4, parcel2);
    paramIObjectWrapper2 = IObjectWrapper.zza.zzao(parcel1.readStrongBinder());
    parcel1.recycle();
    return paramIObjectWrapper2;
  }
  
  public final void onDestroy() throws RemoteException {
    zzb(8, zzax());
  }
  
  public final void onDestroyView() throws RemoteException {
    zzb(7, zzax());
  }
  
  public final void onEnterAmbient(Bundle paramBundle) throws RemoteException {
    Parcel parcel = zzax();
    zzed.zza(parcel, (Parcelable)paramBundle);
    zzb(13, parcel);
  }
  
  public final void onExitAmbient() throws RemoteException {
    zzb(14, zzax());
  }
  
  public final void onInflate(IObjectWrapper paramIObjectWrapper, GoogleMapOptions paramGoogleMapOptions, Bundle paramBundle) throws RemoteException {
    Parcel parcel = zzax();
    zzed.zza(parcel, (IInterface)paramIObjectWrapper);
    zzed.zza(parcel, (Parcelable)paramGoogleMapOptions);
    zzed.zza(parcel, (Parcelable)paramBundle);
    zzb(2, parcel);
  }
  
  public final void onLowMemory() throws RemoteException {
    zzb(9, zzax());
  }
  
  public final void onPause() throws RemoteException {
    zzb(6, zzax());
  }
  
  public final void onResume() throws RemoteException {
    zzb(5, zzax());
  }
  
  public final void onSaveInstanceState(Bundle paramBundle) throws RemoteException {
    Parcel parcel = zzax();
    zzed.zza(parcel, (Parcelable)paramBundle);
    parcel = zza(10, parcel);
    if (parcel.readInt() != 0)
      paramBundle.readFromParcel(parcel); 
    parcel.recycle();
  }
  
  public final void onStart() throws RemoteException {
    zzb(15, zzax());
  }
  
  public final void onStop() throws RemoteException {
    zzb(16, zzax());
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/maps/internal/zzj.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */