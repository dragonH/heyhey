package com.google.android.gms.tasks;

final class zzj implements Runnable {
  zzj(zzi paramzzi, Task paramTask) {}
  
  public final void run() {
    synchronized (zzi.zza(this.zzkge)) {
      if (zzi.zzb(this.zzkge) != null)
        zzi.zzb(this.zzkge).onSuccess(this.zzkfw.getResult()); 
      return;
    } 
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/tasks/zzj.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */