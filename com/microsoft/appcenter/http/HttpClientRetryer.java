package com.microsoft.appcenter.http;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.VisibleForTesting;
import com.microsoft.appcenter.utils.AppCenterLog;
import java.io.IOException;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class HttpClientRetryer extends HttpClientDecorator {
  @VisibleForTesting
  static final long[] RETRY_INTERVALS;
  
  private final Handler mHandler;
  
  private final Random mRandom = new Random();
  
  static {
    long l = TimeUnit.SECONDS.toMillis(10L);
    TimeUnit timeUnit = TimeUnit.MINUTES;
    RETRY_INTERVALS = new long[] { l, timeUnit.toMillis(5L), timeUnit.toMillis(20L) };
  }
  
  public HttpClientRetryer(HttpClient paramHttpClient) {
    this(paramHttpClient, new Handler(Looper.getMainLooper()));
  }
  
  @VisibleForTesting
  HttpClientRetryer(HttpClient paramHttpClient, Handler paramHandler) {
    super(paramHttpClient);
    this.mHandler = paramHandler;
  }
  
  public ServiceCall callAsync(String paramString1, String paramString2, Map<String, String> paramMap, HttpClient.CallTemplate paramCallTemplate, ServiceCallback paramServiceCallback) {
    RetryableCall retryableCall = new RetryableCall(this.mDecoratedApi, paramString1, paramString2, paramMap, paramCallTemplate, paramServiceCallback);
    retryableCall.run();
    return retryableCall;
  }
  
  private class RetryableCall extends HttpClientCallDecorator {
    private int mRetryCount;
    
    RetryableCall(HttpClient param1HttpClient, String param1String1, String param1String2, Map<String, String> param1Map, HttpClient.CallTemplate param1CallTemplate, ServiceCallback param1ServiceCallback) {
      super(param1HttpClient, param1String1, param1String2, param1Map, param1CallTemplate, param1ServiceCallback);
    }
    
    public void cancel() {
      // Byte code:
      //   0: aload_0
      //   1: monitorenter
      //   2: aload_0
      //   3: getfield this$0 : Lcom/microsoft/appcenter/http/HttpClientRetryer;
      //   6: invokestatic access$000 : (Lcom/microsoft/appcenter/http/HttpClientRetryer;)Landroid/os/Handler;
      //   9: aload_0
      //   10: invokevirtual removeCallbacks : (Ljava/lang/Runnable;)V
      //   13: aload_0
      //   14: invokespecial cancel : ()V
      //   17: aload_0
      //   18: monitorexit
      //   19: return
      //   20: astore_1
      //   21: aload_0
      //   22: monitorexit
      //   23: aload_1
      //   24: athrow
      // Exception table:
      //   from	to	target	type
      //   2	17	20	finally
    }
    
    public void onCallFailed(Exception param1Exception) {
      int i = this.mRetryCount;
      long[] arrayOfLong = HttpClientRetryer.RETRY_INTERVALS;
      if (i < arrayOfLong.length && HttpUtils.isRecoverableError(param1Exception)) {
        i = this.mRetryCount;
        this.mRetryCount = i + 1;
        long l = arrayOfLong[i] / 2L;
        l += HttpClientRetryer.this.mRandom.nextInt((int)l);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Try #");
        stringBuilder.append(this.mRetryCount);
        stringBuilder.append(" failed and will be retried in ");
        stringBuilder.append(l);
        stringBuilder.append(" ms");
        String str2 = stringBuilder.toString();
        String str1 = str2;
        if (param1Exception instanceof java.net.UnknownHostException) {
          StringBuilder stringBuilder1 = new StringBuilder();
          stringBuilder1.append(str2);
          stringBuilder1.append(" (UnknownHostException)");
          str1 = stringBuilder1.toString();
        } 
        AppCenterLog.warn("AppCenter", str1, param1Exception);
        HttpClientRetryer.this.mHandler.postDelayed(this, l);
      } else {
        this.mServiceCallback.onCallFailed(param1Exception);
      } 
    }
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/microsoft/appcenter/http/HttpClientRetryer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */