package com.google.android.gms.tasks;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import java.util.concurrent.Executor;

public final class TaskExecutors {
  public static final Executor MAIN_THREAD = new zza();
  
  static final Executor zzkgi = new zzm();
  
  static final class zza implements Executor {
    private final Handler mHandler = new Handler(Looper.getMainLooper());
    
    public final void execute(@NonNull Runnable param1Runnable) {
      this.mHandler.post(param1Runnable);
    }
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/tasks/TaskExecutors.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */