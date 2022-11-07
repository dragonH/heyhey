package com.google.android.gms.common.internal;

import android.os.IBinder;
import android.os.RemoteException;
import com.google.android.gms.internal.zzeb;

public final class zzar extends zzeb implements zzap {
  zzar(IBinder paramIBinder) {
    super(paramIBinder, "com.google.android.gms.common.internal.ICancelToken");
  }
  
  public final void cancel() throws RemoteException {
    zzc(2, zzax());
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/common/internal/zzar.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */