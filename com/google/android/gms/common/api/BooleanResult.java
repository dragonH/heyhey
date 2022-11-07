package com.google.android.gms.common.api;

import com.google.android.gms.common.internal.zzbp;

public class BooleanResult implements Result {
  private final Status mStatus;
  
  private final boolean zzfgq;
  
  public BooleanResult(Status paramStatus, boolean paramBoolean) {
    this.mStatus = (Status)zzbp.zzb(paramStatus, "Status must not be null");
    this.zzfgq = paramBoolean;
  }
  
  public final boolean equals(Object paramObject) {
    if (paramObject == this)
      return true; 
    if (!(paramObject instanceof BooleanResult))
      return false; 
    paramObject = paramObject;
    return (this.mStatus.equals(((BooleanResult)paramObject).mStatus) && this.zzfgq == ((BooleanResult)paramObject).zzfgq);
  }
  
  public Status getStatus() {
    return this.mStatus;
  }
  
  public boolean getValue() {
    return this.zzfgq;
  }
  
  public final int hashCode() {
    return (this.mStatus.hashCode() + 527) * 31 + this.zzfgq;
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/common/api/BooleanResult.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */