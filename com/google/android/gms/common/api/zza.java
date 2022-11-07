package com.google.android.gms.common.api;

final class zza implements PendingResult.zza {
  zza(Batch paramBatch) {}
  
  public final void zzq(Status paramStatus) {
    synchronized (Batch.zza(this.zzfgo)) {
      if (this.zzfgo.isCanceled())
        return; 
      if (paramStatus.isCanceled()) {
        Batch.zza(this.zzfgo, true);
      } else if (!paramStatus.isSuccess()) {
        Batch.zzb(this.zzfgo, true);
      } 
      Batch.zzb(this.zzfgo);
      if (Batch.zzc(this.zzfgo) == 0)
        if (Batch.zzd(this.zzfgo)) {
          Batch.zze(this.zzfgo);
        } else {
          if (Batch.zzf(this.zzfgo)) {
            paramStatus = new Status();
            this(13);
          } else {
            paramStatus = Status.zzfhv;
          } 
          Batch batch = this.zzfgo;
          BatchResult batchResult = new BatchResult();
          this(paramStatus, (PendingResult<?>[])Batch.zzg(batch));
          batch.setResult(batchResult);
        }  
      return;
    } 
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/common/api/zza.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */