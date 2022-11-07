package android.support.v4.app;

import android.app.Notification;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Build;
import android.os.IBinder;
import android.os.RemoteException;

public abstract class NotificationCompatSideChannelService extends Service {
  public abstract void cancel(String paramString1, int paramInt, String paramString2);
  
  public abstract void cancelAll(String paramString);
  
  void checkPermission(int paramInt, String paramString) {
    String[] arrayOfString = getPackageManager().getPackagesForUid(paramInt);
    int i = arrayOfString.length;
    for (byte b = 0; b < i; b++) {
      if (arrayOfString[b].equals(paramString))
        return; 
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("NotificationSideChannelService: Uid ");
    stringBuilder.append(paramInt);
    stringBuilder.append(" is not authorized for package ");
    stringBuilder.append(paramString);
    SecurityException securityException = new SecurityException(stringBuilder.toString());
    throw securityException;
  }
  
  public abstract void notify(String paramString1, int paramInt, String paramString2, Notification paramNotification);
  
  public IBinder onBind(Intent paramIntent) {
    return (IBinder)(paramIntent.getAction().equals("android.support.BIND_NOTIFICATION_SIDE_CHANNEL") ? ((Build.VERSION.SDK_INT > 19) ? null : new NotificationSideChannelStub()) : null);
  }
  
  private class NotificationSideChannelStub extends INotificationSideChannel.Stub {
    public void cancel(String param1String1, int param1Int, String param1String2) throws RemoteException {
      NotificationCompatSideChannelService.this.checkPermission(Binder.getCallingUid(), param1String1);
      long l = Binder.clearCallingIdentity();
      try {
        NotificationCompatSideChannelService.this.cancel(param1String1, param1Int, param1String2);
        return;
      } finally {
        Binder.restoreCallingIdentity(l);
      } 
    }
    
    public void cancelAll(String param1String) {
      NotificationCompatSideChannelService.this.checkPermission(Binder.getCallingUid(), param1String);
      long l = Binder.clearCallingIdentity();
      try {
        NotificationCompatSideChannelService.this.cancelAll(param1String);
        return;
      } finally {
        Binder.restoreCallingIdentity(l);
      } 
    }
    
    public void notify(String param1String1, int param1Int, String param1String2, Notification param1Notification) throws RemoteException {
      NotificationCompatSideChannelService.this.checkPermission(Binder.getCallingUid(), param1String1);
      long l = Binder.clearCallingIdentity();
      try {
        NotificationCompatSideChannelService.this.notify(param1String1, param1Int, param1String2, param1Notification);
        return;
      } finally {
        Binder.restoreCallingIdentity(l);
      } 
    }
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/android/support/v4/app/NotificationCompatSideChannelService.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */