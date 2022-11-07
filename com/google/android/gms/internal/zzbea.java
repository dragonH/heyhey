package com.google.android.gms.internal;

import android.os.Process;

final class zzbea implements Runnable {
  private final int mPriority;
  
  private final Runnable zzv;
  
  public zzbea(Runnable paramRunnable, int paramInt) {
    this.zzv = paramRunnable;
    this.mPriority = paramInt;
  }
  
  public final void run() {
    Process.setThreadPriority(this.mPriority);
    this.zzv.run();
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/internal/zzbea.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */