package com.google.android.gms.tasks;

import android.support.annotation.NonNull;

interface zzk<TResult> {
  void cancel();
  
  void onComplete(@NonNull Task<TResult> paramTask);
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/tasks/zzk.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */