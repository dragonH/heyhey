package com.google.android.gms.tasks;

import android.support.annotation.NonNull;
import java.util.concurrent.Executor;

final class zze<TResult> implements zzk<TResult> {
  private final Object mLock = new Object();
  
  private final Executor zzjqr;
  
  private OnCompleteListener<TResult> zzkfz;
  
  public zze(@NonNull Executor paramExecutor, @NonNull OnCompleteListener<TResult> paramOnCompleteListener) {
    this.zzjqr = paramExecutor;
    this.zzkfz = paramOnCompleteListener;
  }
  
  public final void cancel() {
    synchronized (this.mLock) {
      this.zzkfz = null;
      return;
    } 
  }
  
  public final void onComplete(@NonNull Task<TResult> paramTask) {
    synchronized (this.mLock) {
      if (this.zzkfz == null)
        return; 
      this.zzjqr.execute(new zzf(this, paramTask));
      return;
    } 
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/tasks/zze.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */