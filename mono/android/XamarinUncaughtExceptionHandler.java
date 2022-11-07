package mono.android;

final class XamarinUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {
  Thread.UncaughtExceptionHandler defaultHandler;
  
  public XamarinUncaughtExceptionHandler(Thread.UncaughtExceptionHandler paramUncaughtExceptionHandler) {
    this.defaultHandler = paramUncaughtExceptionHandler;
  }
  
  public final void uncaughtException(Thread paramThread, Throwable paramThrowable) {
    Runtime.propagateUncaughtException(paramThread, paramThrowable);
    Thread.UncaughtExceptionHandler uncaughtExceptionHandler = this.defaultHandler;
    if (uncaughtExceptionHandler != null)
      uncaughtExceptionHandler.uncaughtException(paramThread, paramThrowable); 
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/mono/android/XamarinUncaughtExceptionHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */