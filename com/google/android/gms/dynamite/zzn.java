package com.google.android.gms.dynamite;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.zzeb;
import com.google.android.gms.internal.zzed;

public final class zzn extends zzeb implements zzm {
  zzn(IBinder paramIBinder) {
    super(paramIBinder, "com.google.android.gms.dynamite.IDynamiteLoaderV2");
  }
  
  public final IObjectWrapper zza(IObjectWrapper paramIObjectWrapper1, String paramString, int paramInt, IObjectWrapper paramIObjectWrapper2) throws RemoteException {
    Parcel parcel2 = zzax();
    zzed.zza(parcel2, (IInterface)paramIObjectWrapper1);
    parcel2.writeString(paramString);
    parcel2.writeInt(paramInt);
    zzed.zza(parcel2, (IInterface)paramIObjectWrapper2);
    Parcel parcel1 = zza(2, parcel2);
    paramIObjectWrapper1 = IObjectWrapper.zza.zzao(parcel1.readStrongBinder());
    parcel1.recycle();
    return paramIObjectWrapper1;
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/dynamite/zzn.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */