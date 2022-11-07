package com.google.android.gms.maps.model.internal;

import android.graphics.Bitmap;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.zzeb;
import com.google.android.gms.internal.zzed;

public final class zzc extends zzeb implements zza {
  zzc(IBinder paramIBinder) {
    super(paramIBinder, "com.google.android.gms.maps.model.internal.IBitmapDescriptorFactoryDelegate");
  }
  
  public final IObjectWrapper zzats() throws RemoteException {
    Parcel parcel = zza(4, zzax());
    IObjectWrapper iObjectWrapper = IObjectWrapper.zza.zzao(parcel.readStrongBinder());
    parcel.recycle();
    return iObjectWrapper;
  }
  
  public final IObjectWrapper zzd(Bitmap paramBitmap) throws RemoteException {
    Parcel parcel2 = zzax();
    zzed.zza(parcel2, (Parcelable)paramBitmap);
    Parcel parcel1 = zza(6, parcel2);
    IObjectWrapper iObjectWrapper = IObjectWrapper.zza.zzao(parcel1.readStrongBinder());
    parcel1.recycle();
    return iObjectWrapper;
  }
  
  public final IObjectWrapper zzdv(int paramInt) throws RemoteException {
    Parcel parcel = zzax();
    parcel.writeInt(paramInt);
    parcel = zza(1, parcel);
    IObjectWrapper iObjectWrapper = IObjectWrapper.zza.zzao(parcel.readStrongBinder());
    parcel.recycle();
    return iObjectWrapper;
  }
  
  public final IObjectWrapper zze(float paramFloat) throws RemoteException {
    Parcel parcel1 = zzax();
    parcel1.writeFloat(paramFloat);
    Parcel parcel2 = zza(5, parcel1);
    IObjectWrapper iObjectWrapper = IObjectWrapper.zza.zzao(parcel2.readStrongBinder());
    parcel2.recycle();
    return iObjectWrapper;
  }
  
  public final IObjectWrapper zzii(String paramString) throws RemoteException {
    Parcel parcel = zzax();
    parcel.writeString(paramString);
    parcel = zza(2, parcel);
    IObjectWrapper iObjectWrapper = IObjectWrapper.zza.zzao(parcel.readStrongBinder());
    parcel.recycle();
    return iObjectWrapper;
  }
  
  public final IObjectWrapper zzij(String paramString) throws RemoteException {
    Parcel parcel = zzax();
    parcel.writeString(paramString);
    parcel = zza(3, parcel);
    IObjectWrapper iObjectWrapper = IObjectWrapper.zza.zzao(parcel.readStrongBinder());
    parcel.recycle();
    return iObjectWrapper;
  }
  
  public final IObjectWrapper zzik(String paramString) throws RemoteException {
    Parcel parcel2 = zzax();
    parcel2.writeString(paramString);
    Parcel parcel1 = zza(7, parcel2);
    IObjectWrapper iObjectWrapper = IObjectWrapper.zza.zzao(parcel1.readStrongBinder());
    parcel1.recycle();
    return iObjectWrapper;
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/maps/model/internal/zzc.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */