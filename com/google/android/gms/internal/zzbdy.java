package com.google.android.gms.internal;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import java.util.concurrent.Executor;

public final class zzbdy implements Executor {
  private final Handler mHandler;
  
  public zzbdy(Looper paramLooper) {
    this.mHandler = new Handler(paramLooper);
  }
  
  public final void execute(@NonNull Runnable paramRunnable) {
    this.mHandler.post(paramRunnable);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/internal/zzbdy.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */