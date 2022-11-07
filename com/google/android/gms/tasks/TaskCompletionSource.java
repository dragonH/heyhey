package com.google.android.gms.tasks;

import android.support.annotation.NonNull;

public class TaskCompletionSource<TResult> {
  private final zzn<TResult> zzkgh = new zzn<TResult>();
  
  @NonNull
  public Task<TResult> getTask() {
    return this.zzkgh;
  }
  
  public void setException(@NonNull Exception paramException) {
    this.zzkgh.setException(paramException);
  }
  
  public void setResult(TResult paramTResult) {
    this.zzkgh.setResult(paramTResult);
  }
  
  public boolean trySetException(@NonNull Exception paramException) {
    return this.zzkgh.trySetException(paramException);
  }
  
  public boolean trySetResult(TResult paramTResult) {
    return this.zzkgh.trySetResult(paramTResult);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/tasks/TaskCompletionSource.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */