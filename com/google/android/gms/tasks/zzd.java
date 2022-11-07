package com.google.android.gms.tasks;

import java.util.concurrent.Executor;

final class zzd implements Runnable {
  zzd(zzc paramzzc, Task paramTask) {}
  
  public final void run() {
    try {
      Task task = (Task)zzc.zza(this.zzkfy).then(this.zzkfw);
      if (task == null) {
        this.zzkfy.onFailure(new NullPointerException("Continuation returned null"));
        return;
      } 
      Executor executor = TaskExecutors.zzkgi;
      task.addOnSuccessListener(executor, this.zzkfy);
      task.addOnFailureListener(executor, this.zzkfy);
      return;
    } catch (RuntimeExecutionException runtimeExecutionException) {
      if (runtimeExecutionException.getCause() instanceof Exception) {
        zzc.zzb(this.zzkfy).setException((Exception)runtimeExecutionException.getCause());
        return;
      } 
      zzc.zzb(this.zzkfy).setException(runtimeExecutionException);
      return;
    } catch (Exception exception) {
      zzc.zzb(this.zzkfy).setException(exception);
      return;
    } 
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/tasks/zzd.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */