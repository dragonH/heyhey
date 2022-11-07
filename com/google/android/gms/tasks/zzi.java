package com.google.android.gms.tasks;

import android.support.annotation.NonNull;
import java.util.concurrent.Executor;

final class zzi<TResult> implements zzk<TResult> {
  private final Object mLock = new Object();
  
  private final Executor zzjqr;
  
  private OnSuccessListener<? super TResult> zzkgd;
  
  public zzi(@NonNull Executor paramExecutor, @NonNull OnSuccessListener<? super TResult> paramOnSuccessListener) {
    this.zzjqr = paramExecutor;
    this.zzkgd = paramOnSuccessListener;
  }
  
  public final void cancel() {
    synchronized (this.mLock) {
      this.zzkgd = null;
      return;
    } 
  }
  
  public final void onComplete(@NonNull Task<TResult> paramTask) {
    if (paramTask.isSuccessful())
      synchronized (this.mLock) {
        if (this.zzkgd == null)
          return; 
        this.zzjqr.execute(new zzj(this, paramTask));
      }  
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/tasks/zzi.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */