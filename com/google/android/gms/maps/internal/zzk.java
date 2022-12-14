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

public final class zzk extends zzeb implements IMapViewDelegate {
  zzk(IBinder paramIBinder) {
    super(paramIBinder, "com.google.android.gms.maps.internal.IMapViewDelegate");
  }
  
  public final IGoogleMapDelegate getMap() throws RemoteException {
    IGoogleMapDelegate iGoogleMapDelegate;
    Parcel parcel = zza(1, zzax());
    IBinder iBinder = parcel.readStrongBinder();
    if (iBinder == null) {
      iBinder = null;
    } else {
      IInterface iInterface = iBinder.queryLocalInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
      if (iInterface instanceof IGoogleMapDelegate) {
        iGoogleMapDelegate = (IGoogleMapDelegate)iInterface;
      } else {
        iGoogleMapDelegate = new zzg((IBinder)iGoogleMapDelegate);
      } 
    } 
    parcel.recycle();
    return iGoogleMapDelegate;
  }
  
  public final void getMapAsync(zzap paramzzap) throws RemoteException {
    Parcel parcel = zzax();
    zzed.zza(parcel, paramzzap);
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
  
  public final void onEnterAmbient(Bundle paramBundle) throws RemoteException {
    Parcel parcel = zzax();
    zzed.zza(parcel, (Parcelable)paramBundle);
    zzb(10, parcel);
  }
  
  public final void onExitAmbient() throws RemoteException {
    zzb(11, zzax());
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
  
  public final void onStart() throws RemoteException {
    zzb(12, zzax());
  }
  
  public final void onStop() throws RemoteException {
    zzb(13, zzax());
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/maps/internal/zzk.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */