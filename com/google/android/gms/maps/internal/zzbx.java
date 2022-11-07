package com.google.android.gms.maps.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.zzeb;
import com.google.android.gms.internal.zzed;

public final class zzbx extends zzeb implements IUiSettingsDelegate {
  zzbx(IBinder paramIBinder) {
    super(paramIBinder, "com.google.android.gms.maps.internal.IUiSettingsDelegate");
  }
  
  public final boolean isCompassEnabled() throws RemoteException {
    Parcel parcel = zza(10, zzax());
    boolean bool = zzed.zza(parcel);
    parcel.recycle();
    return bool;
  }
  
  public final boolean isIndoorLevelPickerEnabled() throws RemoteException {
    Parcel parcel = zza(17, zzax());
    boolean bool = zzed.zza(parcel);
    parcel.recycle();
    return bool;
  }
  
  public final boolean isMapToolbarEnabled() throws RemoteException {
    Parcel parcel = zza(19, zzax());
    boolean bool = zzed.zza(parcel);
    parcel.recycle();
    return bool;
  }
  
  public final boolean isMyLocationButtonEnabled() throws RemoteException {
    Parcel parcel = zza(11, zzax());
    boolean bool = zzed.zza(parcel);
    parcel.recycle();
    return bool;
  }
  
  public final boolean isRotateGesturesEnabled() throws RemoteException {
    Parcel parcel = zza(15, zzax());
    boolean bool = zzed.zza(parcel);
    parcel.recycle();
    return bool;
  }
  
  public final boolean isScrollGesturesEnabled() throws RemoteException {
    Parcel parcel = zza(12, zzax());
    boolean bool = zzed.zza(parcel);
    parcel.recycle();
    return bool;
  }
  
  public final boolean isTiltGesturesEnabled() throws RemoteException {
    Parcel parcel = zza(14, zzax());
    boolean bool = zzed.zza(parcel);
    parcel.recycle();
    return bool;
  }
  
  public final boolean isZoomControlsEnabled() throws RemoteException {
    Parcel parcel = zza(9, zzax());
    boolean bool = zzed.zza(parcel);
    parcel.recycle();
    return bool;
  }
  
  public final boolean isZoomGesturesEnabled() throws RemoteException {
    Parcel parcel = zza(13, zzax());
    boolean bool = zzed.zza(parcel);
    parcel.recycle();
    return bool;
  }
  
  public final void setAllGesturesEnabled(boolean paramBoolean) throws RemoteException {
    Parcel parcel = zzax();
    zzed.zza(parcel, paramBoolean);
    zzb(8, parcel);
  }
  
  public final void setCompassEnabled(boolean paramBoolean) throws RemoteException {
    Parcel parcel = zzax();
    zzed.zza(parcel, paramBoolean);
    zzb(2, parcel);
  }
  
  public final void setIndoorLevelPickerEnabled(boolean paramBoolean) throws RemoteException {
    Parcel parcel = zzax();
    zzed.zza(parcel, paramBoolean);
    zzb(16, parcel);
  }
  
  public final void setMapToolbarEnabled(boolean paramBoolean) throws RemoteException {
    Parcel parcel = zzax();
    zzed.zza(parcel, paramBoolean);
    zzb(18, parcel);
  }
  
  public final void setMyLocationButtonEnabled(boolean paramBoolean) throws RemoteException {
    Parcel parcel = zzax();
    zzed.zza(parcel, paramBoolean);
    zzb(3, parcel);
  }
  
  public final void setRotateGesturesEnabled(boolean paramBoolean) throws RemoteException {
    Parcel parcel = zzax();
    zzed.zza(parcel, paramBoolean);
    zzb(7, parcel);
  }
  
  public final void setScrollGesturesEnabled(boolean paramBoolean) throws RemoteException {
    Parcel parcel = zzax();
    zzed.zza(parcel, paramBoolean);
    zzb(4, parcel);
  }
  
  public final void setTiltGesturesEnabled(boolean paramBoolean) throws RemoteException {
    Parcel parcel = zzax();
    zzed.zza(parcel, paramBoolean);
    zzb(6, parcel);
  }
  
  public final void setZoomControlsEnabled(boolean paramBoolean) throws RemoteException {
    Parcel parcel = zzax();
    zzed.zza(parcel, paramBoolean);
    zzb(1, parcel);
  }
  
  public final void setZoomGesturesEnabled(boolean paramBoolean) throws RemoteException {
    Parcel parcel = zzax();
    zzed.zza(parcel, paramBoolean);
    zzb(5, parcel);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/maps/internal/zzbx.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */