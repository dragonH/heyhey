package com.google.android.gms.common.api;

import android.support.annotation.NonNull;

public abstract class TransformedResult<R extends Result> {
  public abstract void andFinally(@NonNull ResultCallbacks<? super R> paramResultCallbacks);
  
  @NonNull
  public abstract <S extends Result> TransformedResult<S> then(@NonNull ResultTransform<? super R, ? extends S> paramResultTransform);
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/common/api/TransformedResult.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */