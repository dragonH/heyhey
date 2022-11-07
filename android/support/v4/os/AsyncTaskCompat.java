package android.support.v4.os;

import android.os.AsyncTask;

@Deprecated
public final class AsyncTaskCompat {
  @Deprecated
  public static <Params, Progress, Result> AsyncTask<Params, Progress, Result> executeParallel(AsyncTask<Params, Progress, Result> paramAsyncTask, Params... paramVarArgs) {
    if (paramAsyncTask != null) {
      paramAsyncTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, (Object[])paramVarArgs);
      return paramAsyncTask;
    } 
    throw new IllegalArgumentException("task can not be null");
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/android/support/v4/os/AsyncTaskCompat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */