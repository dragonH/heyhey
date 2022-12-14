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

public final class zzbw extends zzeb implements IStreetViewPanoramaViewDelegate {
  zzbw(IBinder paramIBinder) {
    super(paramIBinder, "com.google.android.gms.maps.internal.IStreetViewPanoramaViewDelegate");
  }
  
  public final IStreetViewPanoramaDelegate getStreetViewPanorama() throws RemoteException {
    IStreetViewPanoramaDelegate iStreetViewPanoramaDelegate;
    Parcel parcel = zza(1, zzax());
    IBinder iBinder = parcel.readStrongBinder();
    if (iBinder == null) {
      iBinder = null;
    } else {
      IInterface iInterface = iBinder.queryLocalInterface("com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate");
      if (iInterface instanceof IStreetViewPanoramaDelegate) {
        iStreetViewPanoramaDelegate = (IStreetViewPanoramaDelegate)iInterface;
      } else {
        iStreetViewPanoramaDelegate = new zzbu((IBinder)iStreetViewPanoramaDelegate);
      } 
    } 
    parcel.recycle();
    return iStreetViewPanoramaDelegate;
  }
  
  public final void getStreetViewPanoramaAsync(zzbp paramzzbp) throws RemoteException {
    Parcel parcel = zzax();
    zzed.zza(parcel, paramzzbp);
    zzb(9, parcel);
  }
  
  public final IObjectWrapper getView() throws RemoteException {
    Parcel parcel = zza(8, zzax());
    IObjectWrapper iObjectWrapper = IObjectWrapper.zza.zzao(parcel.readStrongBinder());
    parcel.recycle();
    return iObjectWrapper;
  }
  
  public final void onCreate(Bundle paramBundle) throws RemoteException {
    Parcel parcel = zzax();
    zzed.zza(parcel, (Parcelable)paramBundle);
    zzb(2, parcel);
  }
  
  public final void onDestroy() throws RemoteException {
    zzb(5, zzax());
  }
  
  public final void onLowMemory() throws RemoteException {
    zzb(6, zzax());
  }
  
  public final void onPause() throws RemoteException {
    zzb(4, zzax());
  }
  
  public final void onResume() throws RemoteException {
    zzb(3, zzax());
  }
  
  public final void onSaveInstanceState(Bundle paramBundle) throws RemoteException {
    Parcel parcel = zzax();
    zzed.zza(parcel, (Parcelable)paramBundle);
    parcel = zza(7, parcel);
    if (parcel.readInt() != 0)
      paramBundle.readFromParcel(parcel); 
    parcel.recycle();
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/maps/internal/zzbw.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */