package com.google.android.gms.common.internal;

import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Response;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;

public final class zzbi {
  private static final zzbo zzfvr = new zzbj();
  
  public static <R extends com.google.android.gms.common.api.Result, T extends Response<R>> Task<T> zza(PendingResult<R> paramPendingResult, T paramT) {
    return zza(paramPendingResult, new zzbl((Response)paramT));
  }
  
  public static <R extends com.google.android.gms.common.api.Result, T> Task<T> zza(PendingResult<R> paramPendingResult, zzbn<R, T> paramzzbn) {
    zzbo zzbo1 = zzfvr;
    TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
    paramPendingResult.zza(new zzbk(paramPendingResult, taskCompletionSource, paramzzbn, zzbo1));
    return taskCompletionSource.getTask();
  }
  
  public static <R extends com.google.android.gms.common.api.Result> Task<Void> zzb(PendingResult<R> paramPendingResult) {
    return zza(paramPendingResult, new zzbm());
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/common/internal/zzbi.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */