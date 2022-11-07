package com.google.android.gms.tasks;

import android.support.annotation.NonNull;
import java.util.concurrent.Executor;

final class zzc<TResult, TContinuationResult> implements OnFailureListener, OnSuccessListener<TContinuationResult>, zzk<TResult> {
  private final Executor zzjqr;
  
  private final Continuation<TResult, Task<TContinuationResult>> zzkfu;
  
  private final zzn<TContinuationResult> zzkfv;
  
  public zzc(@NonNull Executor paramExecutor, @NonNull Continuation<TResult, Task<TContinuationResult>> paramContinuation, @NonNull zzn<TContinuationResult> paramzzn) {
    this.zzjqr = paramExecutor;
    this.zzkfu = paramContinuation;
    this.zzkfv = paramzzn;
  }
  
  public final void cancel() {
    throw new UnsupportedOperationException();
  }
  
  public final void onComplete(@NonNull Task<TResult> paramTask) {
    this.zzjqr.execute(new zzd(this, paramTask));
  }
  
  public final void onFailure(@NonNull Exception paramException) {
    this.zzkfv.setException(paramException);
  }
  
  public final void onSuccess(TContinuationResult paramTContinuationResult) {
    this.zzkfv.setResult(paramTContinuationResult);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/tasks/zzc.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */