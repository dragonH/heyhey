package com.google.android.gms.tasks;

final class zzf implements Runnable {
  zzf(zze paramzze, Task paramTask) {}
  
  public final void run() {
    synchronized (zze.zza(this.zzkga)) {
      if (zze.zzb(this.zzkga) != null)
        zze.zzb(this.zzkga).onComplete(this.zzkfw); 
      return;
    } 
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/tasks/zzf.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */