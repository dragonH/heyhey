package com.google.android.gms.common.api.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.tasks.TaskCompletionSource;

public abstract class zzdd<A extends Api.zzb, TResult> {
  protected abstract void zza(A paramA, TaskCompletionSource<TResult> paramTaskCompletionSource) throws RemoteException;
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/common/api/internal/zzdd.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */