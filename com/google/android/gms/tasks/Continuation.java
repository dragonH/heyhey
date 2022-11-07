package com.google.android.gms.tasks;

import android.support.annotation.NonNull;

public interface Continuation<TResult, TContinuationResult> {
  TContinuationResult then(@NonNull Task<TResult> paramTask) throws Exception;
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/tasks/Continuation.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */