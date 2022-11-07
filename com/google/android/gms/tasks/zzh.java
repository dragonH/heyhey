package com.google.android.gms.tasks;

final class zzh implements Runnable {
  zzh(zzg paramzzg, Task paramTask) {}
  
  public final void run() {
    synchronized (zzg.zza(this.zzkgc)) {
      if (zzg.zzb(this.zzkgc) != null)
        zzg.zzb(this.zzkgc).onFailure(this.zzkfw.getException()); 
      return;
    } 
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/tasks/zzh.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */