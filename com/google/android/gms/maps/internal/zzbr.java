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
import com.google.android.gms.maps.model.VisibleRegion;

public final class zzbr extends zzeb implements IProjectionDelegate {
  zzbr(IBinder paramIBinder) {
    super(paramIBinder, "com.google.android.gms.maps.internal.IProjectionDelegate");
  }
  
  public final LatLng fromScreenLocation(IObjectWrapper paramIObjectWrapper) throws RemoteException {
    Parcel parcel2 = zzax();
    zzed.zza(parcel2, (IInterface)paramIObjectWrapper);
    Parcel parcel1 = zza(1, parcel2);
    LatLng latLng = (LatLng)zzed.zza(parcel1, LatLng.CREATOR);
    parcel1.recycle();
    return latLng;
  }
  
  public final VisibleRegion getVisibleRegion() throws RemoteException {
    Parcel parcel = zza(3, zzax());
    VisibleRegion visibleRegion = (VisibleRegion)zzed.zza(parcel, VisibleRegion.CREATOR);
    parcel.recycle();
    return visibleRegion;
  }
  
  public final IObjectWrapper toScreenLocation(LatLng paramLatLng) throws RemoteException {
    Parcel parcel = zzax();
    zzed.zza(parcel, (Parcelable)paramLatLng);
    parcel = zza(2, parcel);
    IObjectWrapper iObjectWrapper = IObjectWrapper.zza.zzao(parcel.readStrongBinder());
    parcel.recycle();
    return iObjectWrapper;
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/maps/internal/zzbr.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */