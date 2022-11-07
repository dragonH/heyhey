package com.microsoft.windowsazure.messaging;

public class NotificationHubException extends Exception {
  private static final long serialVersionUID = -2417498840698257022L;
  
  private int mStatusCode;
  
  NotificationHubException(String paramString, int paramInt) {
    super(paramString);
    this.mStatusCode = paramInt;
  }
  
  public int getStatusCode() {
    return this.mStatusCode;
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/microsoft/windowsazure/messaging/NotificationHubException.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */