package com.google.android.gms.common.api.internal;

import android.os.DeadObjectException;
import android.os.RemoteException;
import android.support.annotation.NonNull;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.TaskCompletionSource;

abstract class zzb<T> extends zza {
  protected final TaskCompletionSource<T> zzdzc;
  
  public zzb(int paramInt, TaskCompletionSource<T> paramTaskCompletionSource) {
    super(paramInt);
    this.zzdzc = paramTaskCompletionSource;
  }
  
  public void zza(@NonNull zzah paramzzah, boolean paramBoolean) {}
  
  public final void zza(zzbr<?> paramzzbr) throws DeadObjectException {
    try {
      zzb(paramzzbr);
      return;
    } catch (DeadObjectException deadObjectException) {
      super.zzr(zza.zzb((RemoteException)deadObjectException));
      throw deadObjectException;
    } catch (RemoteException remoteException) {
      super.zzr(zza.zzb(remoteException));
      return;
    } 
  }
  
  protected abstract void zzb(zzbr<?> paramzzbr) throws RemoteException;
  
  public void zzr(@NonNull Status paramStatus) {
    this.zzdzc.trySetException((Exception)new ApiException(paramStatus));
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/common/api/internal/zzb.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */