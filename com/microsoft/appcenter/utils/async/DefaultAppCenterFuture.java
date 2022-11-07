package com.microsoft.appcenter.utils.async;

import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class DefaultAppCenterFuture<T> implements AppCenterFuture<T> {
  private Collection<AppCenterConsumer<T>> mConsumers;
  
  private final CountDownLatch mLatch = new CountDownLatch(1);
  
  private T mResult;
  
  public void complete(T paramT) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: invokevirtual isDone : ()Z
    //   6: ifne -> 42
    //   9: aload_0
    //   10: aload_1
    //   11: putfield mResult : Ljava/lang/Object;
    //   14: aload_0
    //   15: getfield mLatch : Ljava/util/concurrent/CountDownLatch;
    //   18: invokevirtual countDown : ()V
    //   21: aload_0
    //   22: getfield mConsumers : Ljava/util/Collection;
    //   25: ifnull -> 42
    //   28: new com/microsoft/appcenter/utils/async/DefaultAppCenterFuture$2
    //   31: astore_2
    //   32: aload_2
    //   33: aload_0
    //   34: aload_1
    //   35: invokespecial <init> : (Lcom/microsoft/appcenter/utils/async/DefaultAppCenterFuture;Ljava/lang/Object;)V
    //   38: aload_2
    //   39: invokestatic runOnUiThread : (Ljava/lang/Runnable;)V
    //   42: aload_0
    //   43: monitorexit
    //   44: return
    //   45: astore_1
    //   46: aload_0
    //   47: monitorexit
    //   48: aload_1
    //   49: athrow
    // Exception table:
    //   from	to	target	type
    //   2	42	45	finally
  }
  
  public T get() {
    while (true) {
      try {
        this.mLatch.await();
        return this.mResult;
      } catch (InterruptedException interruptedException) {}
    } 
  }
  
  public boolean isDone() {
    while (true) {
      try {
        return this.mLatch.await(0L, TimeUnit.MILLISECONDS);
      } catch (InterruptedException interruptedException) {}
    } 
  }
  
  public void thenAccept(AppCenterConsumer<T> paramAppCenterConsumer) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: invokevirtual isDone : ()Z
    //   6: ifeq -> 26
    //   9: new com/microsoft/appcenter/utils/async/DefaultAppCenterFuture$1
    //   12: astore_2
    //   13: aload_2
    //   14: aload_0
    //   15: aload_1
    //   16: invokespecial <init> : (Lcom/microsoft/appcenter/utils/async/DefaultAppCenterFuture;Lcom/microsoft/appcenter/utils/async/AppCenterConsumer;)V
    //   19: aload_2
    //   20: invokestatic runOnUiThread : (Ljava/lang/Runnable;)V
    //   23: goto -> 57
    //   26: aload_0
    //   27: getfield mConsumers : Ljava/util/Collection;
    //   30: ifnonnull -> 46
    //   33: new java/util/LinkedList
    //   36: astore_2
    //   37: aload_2
    //   38: invokespecial <init> : ()V
    //   41: aload_0
    //   42: aload_2
    //   43: putfield mConsumers : Ljava/util/Collection;
    //   46: aload_0
    //   47: getfield mConsumers : Ljava/util/Collection;
    //   50: aload_1
    //   51: invokeinterface add : (Ljava/lang/Object;)Z
    //   56: pop
    //   57: aload_0
    //   58: monitorexit
    //   59: return
    //   60: astore_1
    //   61: aload_0
    //   62: monitorexit
    //   63: aload_1
    //   64: athrow
    // Exception table:
    //   from	to	target	type
    //   2	23	60	finally
    //   26	46	60	finally
    //   46	57	60	finally
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/microsoft/appcenter/utils/async/DefaultAppCenterFuture.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */