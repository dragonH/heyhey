package com.google.android.gms.common.api;

import android.support.annotation.NonNull;
import android.util.Log;
import com.google.android.gms.common.annotation.KeepForSdk;

public abstract class ResultCallbacks<R extends Result> implements ResultCallback<R> {
  public abstract void onFailure(@NonNull Status paramStatus);
  
  @KeepForSdk
  public final void onResult(@NonNull R paramR) {
    Status status = paramR.getStatus();
    if (status.isSuccess()) {
      onSuccess(paramR);
      return;
    } 
    onFailure(status);
    if (paramR instanceof Releasable)
      try {
        ((Releasable)paramR).release();
        return;
      } catch (RuntimeException runtimeException) {
        String str = String.valueOf(paramR);
        StringBuilder stringBuilder = new StringBuilder(str.length() + 18);
        stringBuilder.append("Unable to release ");
        stringBuilder.append(str);
        Log.w("ResultCallbacks", stringBuilder.toString(), runtimeException);
      }  
  }
  
  public abstract void onSuccess(@NonNull R paramR);
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/common/api/ResultCallbacks.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */