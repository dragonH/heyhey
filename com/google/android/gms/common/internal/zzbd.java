package com.google.android.gms.common.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.zzeb;
import com.google.android.gms.internal.zzed;

public final class zzbd extends zzeb implements zzbc {
  zzbd(IBinder paramIBinder) {
    super(paramIBinder, "com.google.android.gms.common.internal.ISignInButtonCreator");
  }
  
  public final IObjectWrapper zza(IObjectWrapper paramIObjectWrapper, zzbu paramzzbu) throws RemoteException {
    Parcel parcel2 = zzax();
    zzed.zza(parcel2, (IInterface)paramIObjectWrapper);
    zzed.zza(parcel2, (Parcelable)paramzzbu);
    Parcel parcel1 = zza(2, parcel2);
    paramIObjectWrapper = IObjectWrapper.zza.zzao(parcel1.readStrongBinder());
    parcel1.recycle();
    return paramIObjectWrapper;
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/common/internal/zzbd.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */