package com.google.android.gms.common.internal;

import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.concurrent.TimeUnit;

final class zzbk implements PendingResult.zza {
  zzbk(PendingResult paramPendingResult, TaskCompletionSource paramTaskCompletionSource, zzbn paramzzbn, zzbo paramzzbo) {}
  
  public final void zzq(Status paramStatus) {
    Result result;
    if (paramStatus.isSuccess()) {
      result = this.zzfvs.await(0L, TimeUnit.MILLISECONDS);
      this.zzfvt.setResult(this.zzfvu.zzb(result));
      return;
    } 
    this.zzfvt.setException((Exception)this.zzfvv.zzy((Status)result));
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/common/internal/zzbk.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */