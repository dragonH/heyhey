package com.google.android.gms.common.api.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.tasks.TaskCompletionSource;

public abstract class zzdn<A extends Api.zzb, L> {
  private final zzcl<L> zzfov;
  
  protected zzdn(zzcl<L> paramzzcl) {
    this.zzfov = paramzzcl;
  }
  
  public final zzcl<L> zzaik() {
    return this.zzfov;
  }
  
  protected abstract void zzc(A paramA, TaskCompletionSource<Boolean> paramTaskCompletionSource) throws RemoteException;
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/common/api/internal/zzdn.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */