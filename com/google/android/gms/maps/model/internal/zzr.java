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

public final class zzr extends zzeb implements zzp {
  zzr(IBinder paramIBinder) {
    super(paramIBinder, "com.google.android.gms.maps.model.internal.IMarkerDelegate");
  }
  
  public final float getAlpha() throws RemoteException {
    Parcel parcel = zza(26, zzax());
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
  
  public final float getRotation() throws RemoteException {
    Parcel parcel = zza(23, zzax());
    float f = parcel.readFloat();
    parcel.recycle();
    return f;
  }
  
  public final String getSnippet() throws RemoteException {
    Parcel parcel = zza(8, zzax());
    String str = parcel.readString();
    parcel.recycle();
    return str;
  }
  
  public final IObjectWrapper getTag() throws RemoteException {
    Parcel parcel = zza(30, zzax());
    IObjectWrapper iObjectWrapper = IObjectWrapper.zza.zzao(parcel.readStrongBinder());
    parcel.recycle();
    return iObjectWrapper;
  }
  
  public final String getTitle() throws RemoteException {
    Parcel parcel = zza(6, zzax());
    String str = parcel.readString();
    parcel.recycle();
    return str;
  }
  
  public final float getZIndex() throws RemoteException {
    Parcel parcel = zza(28, zzax());
    float f = parcel.readFloat();
    parcel.recycle();
    return f;
  }
  
  public final int hashCodeRemote() throws RemoteException {
    Parcel parcel = zza(17, zzax());
    int i = parcel.readInt();
    parcel.recycle();
    return i;
  }
  
  public final void hideInfoWindow() throws RemoteException {
    zzb(12, zzax());
  }
  
  public final boolean isDraggable() throws RemoteException {
    Parcel parcel = zza(10, zzax());
    boolean bool = zzed.zza(parcel);
    parcel.recycle();
    return bool;
  }
  
  public final boolean isFlat() throws RemoteException {
    Parcel parcel = zza(21, zzax());
    boolean bool = zzed.zza(parcel);
    parcel.recycle();
    return bool;
  }
  
  public final boolean isInfoWindowShown() throws RemoteException {
    Parcel parcel = zza(13, zzax());
    boolean bool = zzed.zza(parcel);
    parcel.recycle();
    return bool;
  }
  
  public final boolean isVisible() throws RemoteException {
    Parcel parcel = zza(15, zzax());
    boolean bool = zzed.zza(parcel);
    parcel.recycle();
    return bool;
  }
  
  public final void remove() throws RemoteException {
    zzb(1, zzax());
  }
  
  public final void setAlpha(float paramFloat) throws RemoteException {
    Parcel parcel = zzax();
    parcel.writeFloat(paramFloat);
    zzb(25, parcel);
  }
  
  public final void setAnchor(float paramFloat1, float paramFloat2) throws RemoteException {
    Parcel parcel = zzax();
    parcel.writeFloat(paramFloat1);
    parcel.writeFloat(paramFloat2);
    zzb(19, parcel);
  }
  
  public final void setDraggable(boolean paramBoolean) throws RemoteException {
    Parcel parcel = zzax();
    zzed.zza(parcel, paramBoolean);
    zzb(9, parcel);
  }
  
  public final void setFlat(boolean paramBoolean) throws RemoteException {
    Parcel parcel = zzax();
    zzed.zza(parcel, paramBoolean);
    zzb(20, parcel);
  }
  
  public final void setInfoWindowAnchor(float paramFloat1, float paramFloat2) throws RemoteException {
    Parcel parcel = zzax();
    parcel.writeFloat(paramFloat1);
    parcel.writeFloat(paramFloat2);
    zzb(24, parcel);
  }
  
  public final void setPosition(LatLng paramLatLng) throws RemoteException {
    Parcel parcel = zzax();
    zzed.zza(parcel, (Parcelable)paramLatLng);
    zzb(3, parcel);
  }
  
  public final void setRotation(float paramFloat) throws RemoteException {
    Parcel parcel = zzax();
    parcel.writeFloat(paramFloat);
    zzb(22, parcel);
  }
  
  public final void setSnippet(String paramString) throws RemoteException {
    Parcel parcel = zzax();
    parcel.writeString(paramString);
    zzb(7, parcel);
  }
  
  public final void setTag(IObjectWrapper paramIObjectWrapper) throws RemoteException {
    Parcel parcel = zzax();
    zzed.zza(parcel, (IInterface)paramIObjectWrapper);
    zzb(29, parcel);
  }
  
  public final void setTitle(String paramString) throws RemoteException {
    Parcel parcel = zzax();
    parcel.writeString(paramString);
    zzb(5, parcel);
  }
  
  public final void setVisible(boolean paramBoolean) throws RemoteException {
    Parcel parcel = zzax();
    zzed.zza(parcel, paramBoolean);
    zzb(14, parcel);
  }
  
  public final void setZIndex(float paramFloat) throws RemoteException {
    Parcel parcel = zzax();
    parcel.writeFloat(paramFloat);
    zzb(27, parcel);
  }
  
  public final void showInfoWindow() throws RemoteException {
    zzb(11, zzax());
  }
  
  public final void zzad(IObjectWrapper paramIObjectWrapper) throws RemoteException {
    Parcel parcel = zzax();
    zzed.zza(parcel, (IInterface)paramIObjectWrapper);
    zzb(18, parcel);
  }
  
  public final boolean zzj(zzp paramzzp) throws RemoteException {
    Parcel parcel2 = zzax();
    zzed.zza(parcel2, paramzzp);
    Parcel parcel1 = zza(16, parcel2);
    boolean bool = zzed.zza(parcel1);
    parcel1.recycle();
    return bool;
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/maps/model/internal/zzr.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */