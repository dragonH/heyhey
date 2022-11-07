package com.google.android.gms.common.api.internal;

import android.support.annotation.WorkerThread;

abstract class zzbb implements Runnable {
  private zzbb(zzar paramzzar) {}
  
  @WorkerThread
  public void run() {
    zzar.zzc(this.zzflx).lock();
    try {
      boolean bool = Thread.interrupted();
      if (!bool)
        zzagz(); 
    } catch (RuntimeException runtimeException) {
    
    } finally {
      Exception exception;
    } 
    zzar.zzc(this.zzflx).unlock();
  }
  
  @WorkerThread
  protected abstract void zzagz();
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/common/api/internal/zzbb.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */