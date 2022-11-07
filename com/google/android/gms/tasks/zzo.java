package com.google.android.gms.tasks;

import java.util.concurrent.Callable;

final class zzo implements Runnable {
  zzo(zzn paramzzn, Callable paramCallable) {}
  
  public final void run() {
    try {
      this.zzkgn.setResult(this.zzdbx.call());
      return;
    } catch (Exception exception) {
      this.zzkgn.setException(exception);
      return;
    } 
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/tasks/zzo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */