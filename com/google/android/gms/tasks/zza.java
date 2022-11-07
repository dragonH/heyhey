package com.google.android.gms.tasks;

import android.support.annotation.NonNull;
import java.util.concurrent.Executor;

final class zza<TResult, TContinuationResult> implements zzk<TResult> {
  private final Executor zzjqr;
  
  private final Continuation<TResult, TContinuationResult> zzkfu;
  
  private final zzn<TContinuationResult> zzkfv;
  
  public zza(@NonNull Executor paramExecutor, @NonNull Continuation<TResult, TContinuationResult> paramContinuation, @NonNull zzn<TContinuationResult> paramzzn) {
    this.zzjqr = paramExecutor;
    this.zzkfu = paramContinuation;
    this.zzkfv = paramzzn;
  }
  
  public final void cancel() {
    throw new UnsupportedOperationException();
  }
  
  public final void onComplete(@NonNull Task<TResult> paramTask) {
    this.zzjqr.execute(new zzb(this, paramTask));
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/tasks/zza.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */