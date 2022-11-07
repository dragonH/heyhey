package com.google.android.gms.tasks;

import android.support.annotation.NonNull;
import java.util.ArrayDeque;
import java.util.Queue;

final class zzl<TResult> {
  private final Object mLock = new Object();
  
  private Queue<zzk<TResult>> zzkgf;
  
  private boolean zzkgg;
  
  public final void zza(@NonNull zzk<TResult> paramzzk) {
    synchronized (this.mLock) {
      if (this.zzkgf == null) {
        ArrayDeque<zzk<TResult>> arrayDeque = new ArrayDeque();
        this();
        this.zzkgf = arrayDeque;
      } 
      this.zzkgf.add(paramzzk);
      return;
    } 
  }
  
  public final void zzb(@NonNull Task<TResult> paramTask) {
    synchronized (this.mLock) {
      if (this.zzkgf == null || this.zzkgg)
        return; 
      this.zzkgg = true;
      while (true) {
        synchronized (this.mLock) {
          zzk<TResult> zzk = this.zzkgf.poll();
          if (zzk == null) {
            this.zzkgg = false;
            return;
          } 
          zzk.onComplete(paramTask);
        } 
      } 
    } 
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/tasks/zzl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */