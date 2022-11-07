package com.google.android.gms.maps.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.zzeb;
import com.google.android.gms.internal.zzed;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;

public final class zzb extends zzeb implements ICameraUpdateFactoryDelegate {
  zzb(IBinder paramIBinder) {
    super(paramIBinder, "com.google.android.gms.maps.internal.ICameraUpdateFactoryDelegate");
  }
  
  public final IObjectWrapper newCameraPosition(CameraPosition paramCameraPosition) throws RemoteException {
    Parcel parcel2 = zzax();
    zzed.zza(parcel2, (Parcelable)paramCameraPosition);
    Parcel parcel1 = zza(7, parcel2);
    IObjectWrapper iObjectWrapper = IObjectWrapper.zza.zzao(parcel1.readStrongBinder());
    parcel1.recycle();
    return iObjectWrapper;
  }
  
  public final IObjectWrapper newLatLng(LatLng paramLatLng) throws RemoteException {
    Parcel parcel = zzax();
    zzed.zza(parcel, (Parcelable)paramLatLng);
    parcel = zza(8, parcel);
    IObjectWrapper iObjectWrapper = IObjectWrapper.zza.zzao(parcel.readStrongBinder());
    parcel.recycle();
    return iObjectWrapper;
  }
  
  public final IObjectWrapper newLatLngBounds(LatLngBounds paramLatLngBounds, int paramInt) throws RemoteException {
    Parcel parcel = zzax();
    zzed.zza(parcel, (Parcelable)paramLatLngBounds);
    parcel.writeInt(paramInt);
    parcel = zza(10, parcel);
    IObjectWrapper iObjectWrapper = IObjectWrapper.zza.zzao(parcel.readStrongBinder());
    parcel.recycle();
    return iObjectWrapper;
  }
  
  public final IObjectWrapper newLatLngBoundsWithSize(LatLngBounds paramLatLngBounds, int paramInt1, int paramInt2, int paramInt3) throws RemoteException {
    Parcel parcel = zzax();
    zzed.zza(parcel, (Parcelable)paramLatLngBounds);
    parcel.writeInt(paramInt1);
    parcel.writeInt(paramInt2);
    parcel.writeInt(paramInt3);
    parcel = zza(11, parcel);
    IObjectWrapper iObjectWrapper = IObjectWrapper.zza.zzao(parcel.readStrongBinder());
    parcel.recycle();
    return iObjectWrapper;
  }
  
  public final IObjectWrapper newLatLngZoom(LatLng paramLatLng, float paramFloat) throws RemoteException {
    Parcel parcel2 = zzax();
    zzed.zza(parcel2, (Parcelable)paramLatLng);
    parcel2.writeFloat(paramFloat);
    Parcel parcel1 = zza(9, parcel2);
    IObjectWrapper iObjectWrapper = IObjectWrapper.zza.zzao(parcel1.readStrongBinder());
    parcel1.recycle();
    return iObjectWrapper;
  }
  
  public final IObjectWrapper scrollBy(float paramFloat1, float paramFloat2) throws RemoteException {
    Parcel parcel = zzax();
    parcel.writeFloat(paramFloat1);
    parcel.writeFloat(paramFloat2);
    parcel = zza(3, parcel);
    IObjectWrapper iObjectWrapper = IObjectWrapper.zza.zzao(parcel.readStrongBinder());
    parcel.recycle();
    return iObjectWrapper;
  }
  
  public final IObjectWrapper zoomBy(float paramFloat) throws RemoteException {
    Parcel parcel1 = zzax();
    parcel1.writeFloat(paramFloat);
    Parcel parcel2 = zza(5, parcel1);
    IObjectWrapper iObjectWrapper = IObjectWrapper.zza.zzao(parcel2.readStrongBinder());
    parcel2.recycle();
    return iObjectWrapper;
  }
  
  public final IObjectWrapper zoomByWithFocus(float paramFloat, int paramInt1, int paramInt2) throws RemoteException {
    Parcel parcel1 = zzax();
    parcel1.writeFloat(paramFloat);
    parcel1.writeInt(paramInt1);
    parcel1.writeInt(paramInt2);
    Parcel parcel2 = zza(6, parcel1);
    IObjectWrapper iObjectWrapper = IObjectWrapper.zza.zzao(parcel2.readStrongBinder());
    parcel2.recycle();
    return iObjectWrapper;
  }
  
  public final IObjectWrapper zoomIn() throws RemoteException {
    Parcel parcel = zza(1, zzax());
    IObjectWrapper iObjectWrapper = IObjectWrapper.zza.zzao(parcel.readStrongBinder());
    parcel.recycle();
    return iObjectWrapper;
  }
  
  public final IObjectWrapper zoomOut() throws RemoteException {
    Parcel parcel = zza(2, zzax());
    IObjectWrapper iObjectWrapper = IObjectWrapper.zza.zzao(parcel.readStrongBinder());
    parcel.recycle();
    return iObjectWrapper;
  }
  
  public final IObjectWrapper zoomTo(float paramFloat) throws RemoteException {
    Parcel parcel = zzax();
    parcel.writeFloat(paramFloat);
    parcel = zza(4, parcel);
    IObjectWrapper iObjectWrapper = IObjectWrapper.zza.zzao(parcel.readStrongBinder());
    parcel.recycle();
    return iObjectWrapper;
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/maps/internal/zzb.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */