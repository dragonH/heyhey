package android.support.v4.content;

import android.os.AsyncTask;
import java.util.concurrent.Executor;

@Deprecated
public final class ParallelExecutorCompat {
  @Deprecated
  public static Executor getParallelExecutor() {
    return AsyncTask.THREAD_POOL_EXECUTOR;
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/android/support/v4/content/ParallelExecutorCompat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */