package com.google.android.gms.common.api.internal;

import android.os.RemoteException;
import android.support.annotation.NonNull;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.TaskCompletionSource;

public final class zzf extends zzb<Boolean> {
  private zzcl<?> zzfih;
  
  public zzf(zzcl<?> paramzzcl, TaskCompletionSource<Boolean> paramTaskCompletionSource) {
    super(4, paramTaskCompletionSource);
    this.zzfih = paramzzcl;
  }
  
  public final void zzb(zzbr<?> paramzzbr) throws RemoteException {
    zzcs zzcs = paramzzbr.zzahw().remove(this.zzfih);
    if (zzcs != null) {
      zzcs.zzfie.zzc(paramzzbr.zzagn(), (TaskCompletionSource)this.zzdzc);
      zzcs.zzfid.zzail();
      return;
    } 
    this.zzdzc.trySetResult(Boolean.FALSE);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/common/api/internal/zzf.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */