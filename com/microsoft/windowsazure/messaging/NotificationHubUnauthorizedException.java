package com.microsoft.windowsazure.messaging;

public class NotificationHubUnauthorizedException extends NotificationHubException {
  private static final long serialVersionUID = -5926583893712403416L;
  
  NotificationHubUnauthorizedException() {
    super("Unauthorized", 401);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/com/microsoft/windowsazure/messaging/NotificationHubUnauthorizedException.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */