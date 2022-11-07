package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.common.internal.zzam;

public final class zzcqb extends zzeb implements zzcqa {
  zzcqb(IBinder paramIBinder) {
    super(paramIBinder, "com.google.android.gms.signin.internal.ISignInService");
  }
  
  public final void zza(zzam paramzzam, int paramInt, boolean paramBoolean) throws RemoteException {
    Parcel parcel = zzax();
    zzed.zza(parcel, (IInterface)paramzzam);
    parcel.writeInt(paramInt);
    zzed.zza(parcel, paramBoolean);
    zzb(9, parcel);
  }
  
  public final void zza(zzcqd paramzzcqd, zzcpy paramzzcpy) throws RemoteException {
    Parcel parcel = zzax();
    zzed.zza(parcel, paramzzcqd);
    zzed.zza(parcel, paramzzcpy);
    zzb(12, parcel);
  }
  
  public final void zzec(int paramInt) throws RemoteException {
    Parcel parcel = zzax();
    parcel.writeInt(paramInt);
    zzb(7, parcel);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/internal/zzcqb.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */