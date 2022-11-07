package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.zzn;

final class zzbcw extends zzbcq {
  private final zzn<Status> zzfwi;
  
  public zzbcw(zzn<Status> paramzzn) {
    this.zzfwi = paramzzn;
  }
  
  public final void zzcg(int paramInt) throws RemoteException {
    this.zzfwi.setResult(new Status(paramInt));
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/internal/zzbcw.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */