package com.microsoft.appcenter.utils.async;

public interface AppCenterFuture<T> {
  T get();
  
  boolean isDone();
  
  void thenAccept(AppCenterConsumer<T> paramAppCenterConsumer);
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/microsoft/appcenter/utils/async/AppCenterFuture.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */