package com.google.android.gms.tasks;

import android.support.annotation.NonNull;
import java.util.concurrent.Executor;

final class zzg<TResult> implements zzk<TResult> {
  private final Object mLock = new Object();
  
  private final Executor zzjqr;
  
  private OnFailureListener zzkgb;
  
  public zzg(@NonNull Executor paramExecutor, @NonNull OnFailureListener paramOnFailureListener) {
    this.zzjqr = paramExecutor;
    this.zzkgb = paramOnFailureListener;
  }
  
  public final void cancel() {
    synchronized (this.mLock) {
      this.zzkgb = null;
      return;
    } 
  }
  
  public final void onComplete(@NonNull Task<TResult> paramTask) {
    if (!paramTask.isSuccessful())
      synchronized (this.mLock) {
        if (this.zzkgb == null)
          return; 
        this.zzjqr.execute(new zzh(this, paramTask));
      }  
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/tasks/zzg.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */