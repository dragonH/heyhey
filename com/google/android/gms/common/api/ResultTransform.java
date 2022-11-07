package com.google.android.gms.common.api;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.WorkerThread;
import com.google.android.gms.common.api.internal.zzcu;

public abstract class ResultTransform<R extends Result, S extends Result> {
  @NonNull
  public final PendingResult<S> createFailedResult(@NonNull Status paramStatus) {
    return (PendingResult<S>)new zzcu(paramStatus);
  }
  
  @NonNull
  public Status onFailure(@NonNull Status paramStatus) {
    return paramStatus;
  }
  
  @Nullable
  @WorkerThread
  public abstract PendingResult<S> onSuccess(@NonNull R paramR);
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/common/api/ResultTransform.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */