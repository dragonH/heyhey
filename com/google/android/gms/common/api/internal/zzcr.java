package com.google.android.gms.common.api.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.tasks.TaskCompletionSource;

public abstract class zzcr<A extends Api.zzb, L> {
  private final zzcj<L> zzfpc;
  
  protected zzcr(zzcj<L> paramzzcj) {
    this.zzfpc = paramzzcj;
  }
  
  public final zzcl<L> zzaik() {
    return this.zzfpc.zzaik();
  }
  
  public final void zzail() {
    this.zzfpc.clear();
  }
  
  protected abstract void zzb(A paramA, TaskCompletionSource<Void> paramTaskCompletionSource) throws RemoteException;
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/common/api/internal/zzcr.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */