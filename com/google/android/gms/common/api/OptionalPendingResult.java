package com.google.android.gms.common.api;

public abstract class OptionalPendingResult<R extends Result> extends PendingResult<R> {
  public abstract R get();
  
  public abstract boolean isDone();
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/common/api/OptionalPendingResult.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */