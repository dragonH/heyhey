package com.google.android.gms.maps.model.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.zzeb;
import com.google.android.gms.internal.zzed;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;

public final class zzi extends zzeb implements zzg {
  zzi(IBinder paramIBinder) {
    super(paramIBinder, "com.google.android.gms.maps.model.internal.IGroundOverlayDelegate");
  }
  
  public final float getBearing() throws RemoteException {
    Parcel parcel = zza(12, zzax());
    float f = parcel.readFloat();
    parcel.recycle();
    return f;
  }
  
  public final LatLngBounds getBounds() throws RemoteException {
    Parcel parcel = zza(10, zzax());
    LatLngBounds latLngBounds = (LatLngBounds)zzed.zza(parcel, LatLngBounds.CREATOR);
    parcel.recycle();
    return latLngBounds;
  }
  
  public final float getHeight() throws RemoteException {
    Parcel parcel = zza(8, zzax());
    float f = parcel.readFloat();
    parcel.recycle();
    return f;
  }
  
  public final String getId() throws RemoteException {
    Parcel parcel = zza(2, zzax());
    String str = parcel.readString();
    parcel.recycle();
    return str;
  }
  
  public final LatLng getPosition() throws RemoteException {
    Parcel parcel = zza(4, zzax());
    LatLng latLng = (LatLng)zzed.zza(parcel, LatLng.CREATOR);
    parcel.recycle();
    return latLng;
  }
  
  public final IObjectWrapper getTag() throws RemoteException {
    Parcel parcel = zza(25, zzax());
    IObjectWrapper iObjectWrapper = IObjectWrapper.zza.zzao(parcel.readStrongBinder());
    parcel.recycle();
    return iObjectWrapper;
  }
  
  public final float getTransparency() throws RemoteException {
    Parcel parcel = zza(18, zzax());
    float f = parcel.readFloat();
    parcel.recycle();
    return f;
  }
  
  public final float getWidth() throws RemoteException {
    Parcel parcel = zza(7, zzax());
    float f = parcel.readFloat();
    parcel.recycle();
    return f;
  }
  
  public final float getZIndex() throws RemoteException {
    Parcel parcel = zza(14, zzax());
    float f = parcel.readFloat();
    parcel.recycle();
    return f;
  }
  
  public final int hashCodeRemote() throws RemoteException {
    Parcel parcel = zza(20, zzax());
    int i = parcel.readInt();
    parcel.recycle();
    return i;
  }
  
  public final boolean isClickable() throws RemoteException {
    Parcel parcel = zza(23, zzax());
    boolean bool = zzed.zza(parcel);
    parcel.recycle();
    return bool;
  }
  
  public final boolean isVisible() throws RemoteException {
    Parcel parcel = zza(16, zzax());
    boolean bool = zzed.zza(parcel);
    parcel.recycle();
    return bool;
  }
  
  public final void remove() throws RemoteException {
    zzb(1, zzax());
  }
  
  public final void setBearing(float paramFloat) throws RemoteException {
    Parcel parcel = zzax();
    parcel.writeFloat(paramFloat);
    zzb(11, parcel);
  }
  
  public final void setClickable(boolean paramBoolean) throws RemoteException {
    Parcel parcel = zzax();
    zzed.zza(parcel, paramBoolean);
    zzb(22, parcel);
  }
  
  public final void setDimensions(float paramFloat) throws RemoteException {
    Parcel parcel = zzax();
    parcel.writeFloat(paramFloat);
    zzb(5, parcel);
  }
  
  public final void setPosition(LatLng paramLatLng) throws RemoteException {
    Parcel parcel = zzax();
    zzed.zza(parcel, (Parcelable)paramLatLng);
    zzb(3, parcel);
  }
  
  public final void setPositionFromBounds(LatLngBounds paramLatLngBounds) throws RemoteException {
    Parcel parcel = zzax();
    zzed.zza(parcel, (Parcelable)paramLatLngBounds);
    zzb(9, parcel);
  }
  
  public final void setTag(IObjectWrapper paramIObjectWrapper) throws RemoteException {
    Parcel parcel = zzax();
    zzed.zza(parcel, (IInterface)paramIObjectWrapper);
    zzb(24, parcel);
  }
  
  public final void setTransparency(float paramFloat) throws RemoteException {
    Parcel parcel = zzax();
    parcel.writeFloat(paramFloat);
    zzb(17, parcel);
  }
  
  public final void setVisible(boolean paramBoolean) throws RemoteException {
    Parcel parcel = zzax();
    zzed.zza(parcel, paramBoolean);
    zzb(15, parcel);
  }
  
  public final void setZIndex(float paramFloat) throws RemoteException {
    Parcel parcel = zzax();
    parcel.writeFloat(paramFloat);
    zzb(13, parcel);
  }
  
  public final void zzac(IObjectWrapper paramIObjectWrapper) throws RemoteException {
    Parcel parcel = zzax();
    zzed.zza(parcel, (IInterface)paramIObjectWrapper);
    zzb(21, parcel);
  }
  
  public final boolean zzb(zzg paramzzg) throws RemoteException {
    Parcel parcel2 = zzax();
    zzed.zza(parcel2, paramzzg);
    Parcel parcel1 = zza(19, parcel2);
    boolean bool = zzed.zza(parcel1);
    parcel1.recycle();
    return bool;
  }
  
  public final void zzf(float paramFloat1, float paramFloat2) throws RemoteException {
    Parcel parcel = zzax();
    parcel.writeFloat(paramFloat1);
    parcel.writeFloat(paramFloat2);
    zzb(6, parcel);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/maps/model/internal/zzi.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */