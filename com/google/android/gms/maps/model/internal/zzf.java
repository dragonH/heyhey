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
import com.google.android.gms.maps.model.PatternItem;
import java.util.ArrayList;
import java.util.List;

public final class zzf extends zzeb implements zzd {
  zzf(IBinder paramIBinder) {
    super(paramIBinder, "com.google.android.gms.maps.model.internal.ICircleDelegate");
  }
  
  public final LatLng getCenter() throws RemoteException {
    Parcel parcel = zza(4, zzax());
    LatLng latLng = (LatLng)zzed.zza(parcel, LatLng.CREATOR);
    parcel.recycle();
    return latLng;
  }
  
  public final int getFillColor() throws RemoteException {
    Parcel parcel = zza(12, zzax());
    int i = parcel.readInt();
    parcel.recycle();
    return i;
  }
  
  public final String getId() throws RemoteException {
    Parcel parcel = zza(2, zzax());
    String str = parcel.readString();
    parcel.recycle();
    return str;
  }
  
  public final double getRadius() throws RemoteException {
    Parcel parcel = zza(6, zzax());
    double d = parcel.readDouble();
    parcel.recycle();
    return d;
  }
  
  public final int getStrokeColor() throws RemoteException {
    Parcel parcel = zza(10, zzax());
    int i = parcel.readInt();
    parcel.recycle();
    return i;
  }
  
  public final List<PatternItem> getStrokePattern() throws RemoteException {
    Parcel parcel = zza(22, zzax());
    ArrayList<PatternItem> arrayList = parcel.createTypedArrayList(PatternItem.CREATOR);
    parcel.recycle();
    return arrayList;
  }
  
  public final float getStrokeWidth() throws RemoteException {
    Parcel parcel = zza(8, zzax());
    float f = parcel.readFloat();
    parcel.recycle();
    return f;
  }
  
  public final IObjectWrapper getTag() throws RemoteException {
    Parcel parcel = zza(24, zzax());
    IObjectWrapper iObjectWrapper = IObjectWrapper.zza.zzao(parcel.readStrongBinder());
    parcel.recycle();
    return iObjectWrapper;
  }
  
  public final float getZIndex() throws RemoteException {
    Parcel parcel = zza(14, zzax());
    float f = parcel.readFloat();
    parcel.recycle();
    return f;
  }
  
  public final int hashCodeRemote() throws RemoteException {
    Parcel parcel = zza(18, zzax());
    int i = parcel.readInt();
    parcel.recycle();
    return i;
  }
  
  public final boolean isClickable() throws RemoteException {
    Parcel parcel = zza(20, zzax());
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
  
  public final void setCenter(LatLng paramLatLng) throws RemoteException {
    Parcel parcel = zzax();
    zzed.zza(parcel, (Parcelable)paramLatLng);
    zzb(3, parcel);
  }
  
  public final void setClickable(boolean paramBoolean) throws RemoteException {
    Parcel parcel = zzax();
    zzed.zza(parcel, paramBoolean);
    zzb(19, parcel);
  }
  
  public final void setFillColor(int paramInt) throws RemoteException {
    Parcel parcel = zzax();
    parcel.writeInt(paramInt);
    zzb(11, parcel);
  }
  
  public final void setRadius(double paramDouble) throws RemoteException {
    Parcel parcel = zzax();
    parcel.writeDouble(paramDouble);
    zzb(5, parcel);
  }
  
  public final void setStrokeColor(int paramInt) throws RemoteException {
    Parcel parcel = zzax();
    parcel.writeInt(paramInt);
    zzb(9, parcel);
  }
  
  public final void setStrokePattern(List<PatternItem> paramList) throws RemoteException {
    Parcel parcel = zzax();
    parcel.writeTypedList(paramList);
    zzb(21, parcel);
  }
  
  public final void setStrokeWidth(float paramFloat) throws RemoteException {
    Parcel parcel = zzax();
    parcel.writeFloat(paramFloat);
    zzb(7, parcel);
  }
  
  public final void setTag(IObjectWrapper paramIObjectWrapper) throws RemoteException {
    Parcel parcel = zzax();
    zzed.zza(parcel, (IInterface)paramIObjectWrapper);
    zzb(23, parcel);
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
  
  public final boolean zzb(zzd paramzzd) throws RemoteException {
    Parcel parcel2 = zzax();
    zzed.zza(parcel2, paramzzd);
    Parcel parcel1 = zza(17, parcel2);
    boolean bool = zzed.zza(parcel1);
    parcel1.recycle();
    return bool;
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/maps/model/internal/zzf.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */