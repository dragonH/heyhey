package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.api.Releasable;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.data.DataHolder;

public class zzam implements Releasable, Result {
  private Status mStatus;
  
  protected final DataHolder zzflf;
  
  protected zzam(DataHolder paramDataHolder, Status paramStatus) {
    this.mStatus = paramStatus;
    this.zzflf = paramDataHolder;
  }
  
  public Status getStatus() {
    return this.mStatus;
  }
  
  public void release() {
    DataHolder dataHolder = this.zzflf;
    if (dataHolder != null)
      dataHolder.close(); 
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/common/api/internal/zzam.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */