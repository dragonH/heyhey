package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

public abstract class zzbvm extends zzec implements zzbvl {
  public zzbvm() {
    attachInterface(this, "com.google.android.gms.flags.IFlagProvider");
  }
  
  public static zzbvl asInterface(IBinder paramIBinder) {
    if (paramIBinder == null)
      return null; 
    IInterface iInterface = paramIBinder.queryLocalInterface("com.google.android.gms.flags.IFlagProvider");
    return (iInterface instanceof zzbvl) ? (zzbvl)iInterface : new zzbvn(paramIBinder);
  }
  
  public boolean onTransact(int paramInt1, Parcel paramParcel1, Parcel paramParcel2, int paramInt2) throws RemoteException {
    String str;
    if (zza(paramInt1, paramParcel1, paramParcel2, paramInt2))
      return true; 
    if (paramInt1 != 1) {
      if (paramInt1 != 2) {
        if (paramInt1 != 3) {
          if (paramInt1 != 4) {
            if (paramInt1 != 5)
              return false; 
            str = getStringFlagValue(paramParcel1.readString(), paramParcel1.readString(), paramParcel1.readInt());
            paramParcel2.writeNoException();
            paramParcel2.writeString(str);
          } else {
            long l = getLongFlagValue(str.readString(), str.readLong(), str.readInt());
            paramParcel2.writeNoException();
            paramParcel2.writeLong(l);
          } 
        } else {
          paramInt1 = getIntFlagValue(str.readString(), str.readInt(), str.readInt());
          paramParcel2.writeNoException();
          paramParcel2.writeInt(paramInt1);
        } 
      } else {
        boolean bool = getBooleanFlagValue(str.readString(), zzed.zza((Parcel)str), str.readInt());
        paramParcel2.writeNoException();
        zzed.zza(paramParcel2, bool);
      } 
    } else {
      init(IObjectWrapper.zza.zzao(str.readStrongBinder()));
      paramParcel2.writeNoException();
    } 
    return true;
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/internal/zzbvm.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */