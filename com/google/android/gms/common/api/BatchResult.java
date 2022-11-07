package com.google.android.gms.common.api;

import com.google.android.gms.common.internal.zzbp;
import java.util.concurrent.TimeUnit;

public final class BatchResult implements Result {
  private final Status mStatus;
  
  private final PendingResult<?>[] zzfgn;
  
  BatchResult(Status paramStatus, PendingResult<?>[] paramArrayOfPendingResult) {
    this.mStatus = paramStatus;
    this.zzfgn = paramArrayOfPendingResult;
  }
  
  public final Status getStatus() {
    return this.mStatus;
  }
  
  public final <R extends Result> R take(BatchResultToken<R> paramBatchResultToken) {
    boolean bool;
    if (paramBatchResultToken.mId < this.zzfgn.length) {
      bool = true;
    } else {
      bool = false;
    } 
    zzbp.zzb(bool, "The result token does not belong to this batch");
    return (R)this.zzfgn[paramBatchResultToken.mId].await(0L, TimeUnit.MILLISECONDS);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/common/api/BatchResult.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */