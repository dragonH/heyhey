package com.google.android.gms.common.api;

import com.google.android.gms.common.api.internal.zzs;
import java.util.ArrayList;
import java.util.List;

public final class Batch extends zzs<BatchResult> {
  private final Object mLock = new Object();
  
  private int zzfgk;
  
  private boolean zzfgl;
  
  private boolean zzfgm;
  
  private final PendingResult<?>[] zzfgn;
  
  private Batch(List<PendingResult<?>> paramList, GoogleApiClient paramGoogleApiClient) {
    super(paramGoogleApiClient);
    int i = paramList.size();
    this.zzfgk = i;
    PendingResult[] arrayOfPendingResult = new PendingResult[i];
    this.zzfgn = (PendingResult<?>[])arrayOfPendingResult;
    if (paramList.isEmpty()) {
      setResult(new BatchResult(Status.zzfhv, (PendingResult<?>[])arrayOfPendingResult));
      return;
    } 
    for (i = 0; i < paramList.size(); i++) {
      PendingResult<?> pendingResult = paramList.get(i);
      this.zzfgn[i] = pendingResult;
      pendingResult.zza(new zza(this));
    } 
  }
  
  public final void cancel() {
    super.cancel();
    PendingResult<?>[] arrayOfPendingResult = this.zzfgn;
    int i = arrayOfPendingResult.length;
    for (byte b = 0; b < i; b++)
      arrayOfPendingResult[b].cancel(); 
  }
  
  public final BatchResult createFailedResult(Status paramStatus) {
    return new BatchResult(paramStatus, this.zzfgn);
  }
  
  public static final class Builder {
    private GoogleApiClient zzepb;
    
    private List<PendingResult<?>> zzfgp = new ArrayList<PendingResult<?>>();
    
    public Builder(GoogleApiClient param1GoogleApiClient) {
      this.zzepb = param1GoogleApiClient;
    }
    
    public final <R extends Result> BatchResultToken<R> add(PendingResult<R> param1PendingResult) {
      BatchResultToken<Result> batchResultToken = new BatchResultToken<Result>(this.zzfgp.size());
      this.zzfgp.add(param1PendingResult);
      return (BatchResultToken)batchResultToken;
    }
    
    public final Batch build() {
      return new Batch(this.zzfgp, this.zzepb, null);
    }
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/common/api/Batch.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */