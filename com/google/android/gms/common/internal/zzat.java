package com.google.android.gms.common.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.zzec;
import com.google.android.gms.internal.zzed;

public abstract class zzat extends zzec implements zzas {
  public zzat() {
    attachInterface(this, "com.google.android.gms.common.internal.ICertData");
  }
  
  public static zzas zzak(IBinder paramIBinder) {
    if (paramIBinder == null)
      return null; 
    IInterface iInterface = paramIBinder.queryLocalInterface("com.google.android.gms.common.internal.ICertData");
    return (iInterface instanceof zzas) ? (zzas)iInterface : new zzau(paramIBinder);
  }
  
  public boolean onTransact(int paramInt1, Parcel paramParcel1, Parcel paramParcel2, int paramInt2) throws RemoteException {
    if (zza(paramInt1, paramParcel1, paramParcel2, paramInt2))
      return true; 
    if (paramInt1 != 1) {
      if (paramInt1 != 2)
        return false; 
      paramInt1 = zzafa();
      paramParcel2.writeNoException();
      paramParcel2.writeInt(paramInt1);
    } else {
      IObjectWrapper iObjectWrapper = zzaez();
      paramParcel2.writeNoException();
      zzed.zza(paramParcel2, (IInterface)iObjectWrapper);
    } 
    return true;
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/common/internal/zzat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */