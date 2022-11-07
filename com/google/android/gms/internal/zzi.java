package com.google.android.gms.internal;

import android.os.Handler;
import java.util.concurrent.Executor;

final class zzi implements Executor {
  zzi(zzh paramzzh, Handler paramHandler) {}
  
  public final void execute(Runnable paramRunnable) {
    this.zzs.post(paramRunnable);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/internal/zzi.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */