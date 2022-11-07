package com.google.android.gms.tasks;

final class zzb implements Runnable {
  zzb(zza paramzza, Task paramTask) {}
  
  public final void run() {
    try {
      Object object = zza.zza(this.zzkfx).then(this.zzkfw);
      zza.zzb(this.zzkfx).setResult(object);
      return;
    } catch (RuntimeExecutionException runtimeExecutionException) {
      if (runtimeExecutionException.getCause() instanceof Exception) {
        zza.zzb(this.zzkfx).setException((Exception)runtimeExecutionException.getCause());
        return;
      } 
      zza.zzb(this.zzkfx).setException(runtimeExecutionException);
      return;
    } catch (Exception exception) {
      zza.zzb(this.zzkfx).setException(exception);
      return;
    } 
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/tasks/zzb.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */