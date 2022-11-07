package com.google.android.gms.common.api.internal;

import android.os.DeadObjectException;
import android.os.RemoteException;
import android.support.annotation.NonNull;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.util.zzq;

public abstract class zza {
  private int zzecz;
  
  public zza(int paramInt) {
    this.zzecz = paramInt;
  }
  
  private static Status zza(RemoteException paramRemoteException) {
    StringBuilder stringBuilder = new StringBuilder();
    if (zzq.zzald() && paramRemoteException instanceof android.os.TransactionTooLargeException)
      stringBuilder.append("TransactionTooLargeException: "); 
    stringBuilder.append(paramRemoteException.getLocalizedMessage());
    return new Status(8, stringBuilder.toString());
  }
  
  public abstract void zza(@NonNull zzah paramzzah, boolean paramBoolean);
  
  public abstract void zza(zzbr<?> paramzzbr) throws DeadObjectException;
  
  public abstract void zzr(@NonNull Status paramStatus);
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/common/api/internal/zza.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */