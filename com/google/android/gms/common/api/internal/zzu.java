package com.google.android.gms.common.api.internal;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.util.Pair;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

public final class zzu<R extends Result> extends Handler {
  public zzu() {
    this(Looper.getMainLooper());
  }
  
  public zzu(Looper paramLooper) {
    super(paramLooper);
  }
  
  public final void handleMessage(Message paramMessage) {
    StringBuilder stringBuilder;
    int i = paramMessage.what;
    if (i != 1) {
      if (i != 2) {
        stringBuilder = new StringBuilder(45);
        stringBuilder.append("Don't know how to handle message: ");
        stringBuilder.append(i);
        Log.wtf("BasePendingResult", stringBuilder.toString(), new Exception());
        return;
      } 
      ((zzs)((Message)stringBuilder).obj).zzu(Status.zzfhy);
      return;
    } 
    Pair pair = (Pair)((Message)stringBuilder).obj;
    ResultCallback resultCallback = (ResultCallback)pair.first;
    Result result = (Result)pair.second;
    try {
      resultCallback.onResult(result);
      return;
    } catch (RuntimeException runtimeException) {
      zzs.zzd(result);
      throw runtimeException;
    } 
  }
  
  public final void zza(ResultCallback<? super R> paramResultCallback, R paramR) {
    sendMessage(obtainMessage(1, new Pair(paramResultCallback, paramR)));
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/google/android/gms/common/api/internal/zzu.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */