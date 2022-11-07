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
import com.google.android.gms.maps.StreetViewPanoramaOptions;

public final class zzbv extends zzeb implements IStreetViewPanoramaFragmentDelegate {
  zzbv(IBinder paramIBinder) {
    super(paramIBinder, "com.google.android.gms.maps.internal.IStreetViewPanoramaFragmentDelegate");
  }
  
  public final IStreetViewPanoramaDelegate getStreetViewPanorama() throws RemoteException {
    IInterface iInterface;
    Parcel parcel = zza(1, zzax());
    IBinder iBinder = parcel.readStrongBinder();
    if (iBinder == null) {
      iInterface = null;
    } else {
      iInterface = iBinder.queryLocalInterface("com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate");
      if (iInterface instanceof IStreetViewPanoramaDelegate) {
        iInterface = iInterface;
      } else {
        iInterface = new zzbu(iBinder);
      } 
    } 
    parcel.recycle();
    return (IStreetViewPanoramaDelegate)iInterface;
  }
  
  public final void getStreetViewPanoramaAsync(zzbp paramzzbp) throws RemoteException {
    Parcel parcel = zzax();
    zzed.zza(parcel, paramzzbp);
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
  
  public final void onInflate(IObjectWrapper paramIObjectWrapper, StreetViewPanoramaOptions paramStreetViewPanoramaOptions, Bundle paramBundle) throws RemoteException {
    Parcel parcel = zzax();
    zzed.zza(parcel, (IInterface)paramIObjectWrapper);
    zzed.zza(parcel, (Parcelable)paramStreetViewPanoramaOptions);
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
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/maps/internal/zzbv.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */