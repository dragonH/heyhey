package com.microsoft.appcenter;

public class CancellationException extends Exception {
  public CancellationException() {
    super("Request cancelled because Channel is disabled.");
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/microsoft/appcenter/CancellationException.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */