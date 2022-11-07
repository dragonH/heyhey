package com.microsoft.appcenter.utils;

import android.os.AsyncTask;
import android.support.annotation.NonNull;
import java.util.concurrent.RejectedExecutionException;

public class AsyncTaskUtils {
  @SafeVarargs
  @NonNull
  public static <Params, Type extends AsyncTask<Params, ?, ?>> Type execute(String paramString, @NonNull Type paramType, Params... paramVarArgs) {
    try {
      return (Type)paramType.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, (Object[])paramVarArgs);
    } catch (RejectedExecutionException rejectedExecutionException) {
      AppCenterLog.warn(paramString, "THREAD_POOL_EXECUTOR saturated, fall back on SERIAL_EXECUTOR which has an unbounded queue", rejectedExecutionException);
      return (Type)paramType.executeOnExecutor(AsyncTask.SERIAL_EXECUTOR, (Object[])paramVarArgs);
    } 
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/microsoft/appcenter/utils/AsyncTaskUtils.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */