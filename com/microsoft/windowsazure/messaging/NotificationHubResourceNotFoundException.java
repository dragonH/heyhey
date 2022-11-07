package com.microsoft.windowsazure.messaging;

public class NotificationHubResourceNotFoundException extends NotificationHubException {
  private static final long serialVersionUID = -1205615098165583127L;
  
  NotificationHubResourceNotFoundException() {
    super("Resource not found", 404);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/microsoft/windowsazure/messaging/NotificationHubResourceNotFoundException.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */