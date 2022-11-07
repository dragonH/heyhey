package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;

public final class zzde {
  @Deprecated
  public static Task<Void> zza(Task<Boolean> paramTask) {
    return paramTask.continueWith(new zzdf());
  }
  
  public static <TResult> void zza(Status paramStatus, TResult paramTResult, TaskCompletionSource<TResult> paramTaskCompletionSource) {
    if (paramStatus.isSuccess()) {
      paramTaskCompletionSource.setResult(paramTResult);
      return;
    } 
    paramTaskCompletionSource.setException((Exception)new ApiException(paramStatus));
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/common/api/internal/zzde.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */