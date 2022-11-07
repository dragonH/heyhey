package com.google.android.gms.maps.model.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.zzeb;
import com.google.android.gms.internal.zzed;

public final class zzy extends zzeb implements zzw {
  zzy(IBinder paramIBinder) {
    super(paramIBinder, "com.google.android.gms.maps.model.internal.ITileOverlayDelegate");
  }
  
  public final void clearTileCache() throws RemoteException {
    zzb(2, zzax());
  }
  
  public final boolean getFadeIn() throws RemoteException {
    Parcel parcel = zza(11, zzax());
    boolean bool = zzed.zza(parcel);
    parcel.recycle();
    return bool;
  }
  
  public final String getId() throws RemoteException {
    Parcel parcel = zza(3, zzax());
    String str = parcel.readString();
    parcel.recycle();
    return str;
  }
  
  public final float getTransparency() throws RemoteException {
    Parcel parcel = zza(13, zzax());
    float f = parcel.readFloat();
    parcel.recycle();
    return f;
  }
  
  public final float getZIndex() throws RemoteException {
    Parcel parcel = zza(5, zzax());
    float f = parcel.readFloat();
    parcel.recycle();
    return f;
  }
  
  public final int hashCodeRemote() throws RemoteException {
    Parcel parcel = zza(9, zzax());
    int i = parcel.readInt();
    parcel.recycle();
    return i;
  }
  
  public final boolean isVisible() throws RemoteException {
    Parcel parcel = zza(7, zzax());
    boolean bool = zzed.zza(parcel);
    parcel.recycle();
    return bool;
  }
  
  public final void remove() throws RemoteException {
    zzb(1, zzax());
  }
  
  public final void setFadeIn(boolean paramBoolean) throws RemoteException {
    Parcel parcel = zzax();
    zzed.zza(parcel, paramBoolean);
    zzb(10, parcel);
  }
  
  public final void setTransparency(float paramFloat) throws RemoteException {
    Parcel parcel = zzax();
    parcel.writeFloat(paramFloat);
    zzb(12, parcel);
  }
  
  public final void setVisible(boolean paramBoolean) throws RemoteException {
    Parcel parcel = zzax();
    zzed.zza(parcel, paramBoolean);
    zzb(6, parcel);
  }
  
  public final void setZIndex(float paramFloat) throws RemoteException {
    Parcel parcel = zzax();
    parcel.writeFloat(paramFloat);
    zzb(4, parcel);
  }
  
  public final boolean zza(zzw paramzzw) throws RemoteException {
    Parcel parcel2 = zzax();
    zzed.zza(parcel2, paramzzw);
    Parcel parcel1 = zza(8, parcel2);
    boolean bool = zzed.zza(parcel1);
    parcel1.recycle();
    return bool;
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/maps/model/internal/zzy.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */