package com.google.android.gms.maps.model.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.zzeb;
import com.google.android.gms.internal.zzed;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.PatternItem;
import java.util.ArrayList;
import java.util.List;

public final class zzu extends zzeb implements zzs {
  zzu(IBinder paramIBinder) {
    super(paramIBinder, "com.google.android.gms.maps.model.internal.IPolygonDelegate");
  }
  
  public final int getFillColor() throws RemoteException {
    Parcel parcel = zza(12, zzax());
    int i = parcel.readInt();
    parcel.recycle();
    return i;
  }
  
  public final List getHoles() throws RemoteException {
    Parcel parcel = zza(6, zzax());
    ArrayList arrayList = zzed.zzb(parcel);
    parcel.recycle();
    return arrayList;
  }
  
  public final String getId() throws RemoteException {
    Parcel parcel = zza(2, zzax());
    String str = parcel.readString();
    parcel.recycle();
    return str;
  }
  
  public final List<LatLng> getPoints() throws RemoteException {
    Parcel parcel = zza(4, zzax());
    ArrayList<LatLng> arrayList = parcel.createTypedArrayList(LatLng.CREATOR);
    parcel.recycle();
    return arrayList;
  }
  
  public final int getStrokeColor() throws RemoteException {
    Parcel parcel = zza(10, zzax());
    int i = parcel.readInt();
    parcel.recycle();
    return i;
  }
  
  public final int getStrokeJointType() throws RemoteException {
    Parcel parcel = zza(24, zzax());
    int i = parcel.readInt();
    parcel.recycle();
    return i;
  }
  
  public final List<PatternItem> getStrokePattern() throws RemoteException {
    Parcel parcel = zza(26, zzax());
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
    Parcel parcel = zza(28, zzax());
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
    Parcel parcel = zza(20, zzax());
    int i = parcel.readInt();
    parcel.recycle();
    return i;
  }
  
  public final boolean isClickable() throws RemoteException {
    Parcel parcel = zza(22, zzax());
    boolean bool = zzed.zza(parcel);
    parcel.recycle();
    return bool;
  }
  
  public final boolean isGeodesic() throws RemoteException {
    Parcel parcel = zza(18, zzax());
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
  
  public final void setClickable(boolean paramBoolean) throws RemoteException {
    Parcel parcel = zzax();
    zzed.zza(parcel, paramBoolean);
    zzb(21, parcel);
  }
  
  public final void setFillColor(int paramInt) throws RemoteException {
    Parcel parcel = zzax();
    parcel.writeInt(paramInt);
    zzb(11, parcel);
  }
  
  public final void setGeodesic(boolean paramBoolean) throws RemoteException {
    Parcel parcel = zzax();
    zzed.zza(parcel, paramBoolean);
    zzb(17, parcel);
  }
  
  public final void setHoles(List paramList) throws RemoteException {
    Parcel parcel = zzax();
    parcel.writeList(paramList);
    zzb(5, parcel);
  }
  
  public final void setPoints(List<LatLng> paramList) throws RemoteException {
    Parcel parcel = zzax();
    parcel.writeTypedList(paramList);
    zzb(3, parcel);
  }
  
  public final void setStrokeColor(int paramInt) throws RemoteException {
    Parcel parcel = zzax();
    parcel.writeInt(paramInt);
    zzb(9, parcel);
  }
  
  public final void setStrokeJointType(int paramInt) throws RemoteException {
    Parcel parcel = zzax();
    parcel.writeInt(paramInt);
    zzb(23, parcel);
  }
  
  public final void setStrokePattern(List<PatternItem> paramList) throws RemoteException {
    Parcel parcel = zzax();
    parcel.writeTypedList(paramList);
    zzb(25, parcel);
  }
  
  public final void setStrokeWidth(float paramFloat) throws RemoteException {
    Parcel parcel = zzax();
    parcel.writeFloat(paramFloat);
    zzb(7, parcel);
  }
  
  public final void setTag(IObjectWrapper paramIObjectWrapper) throws RemoteException {
    Parcel parcel = zzax();
    zzed.zza(parcel, (IInterface)paramIObjectWrapper);
    zzb(27, parcel);
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
  
  public final boolean zzb(zzs paramzzs) throws RemoteException {
    Parcel parcel2 = zzax();
    zzed.zza(parcel2, paramzzs);
    Parcel parcel1 = zza(19, parcel2);
    boolean bool = zzed.zza(parcel1);
    parcel1.recycle();
    return bool;
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/maps/model/internal/zzu.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */