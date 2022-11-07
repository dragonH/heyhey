package android.support.v4.app;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.widget.RemoteViews;
import java.util.ArrayList;

@RequiresApi(26)
class NotificationCompatApi26 {
  public static class Builder implements NotificationBuilderWithBuilderAccessor, NotificationBuilderWithActions {
    private Notification.Builder mB;
    
    Builder(Context param1Context, Notification param1Notification1, CharSequence param1CharSequence1, CharSequence param1CharSequence2, CharSequence param1CharSequence3, RemoteViews param1RemoteViews1, int param1Int1, PendingIntent param1PendingIntent1, PendingIntent param1PendingIntent2, Bitmap param1Bitmap, int param1Int2, int param1Int3, boolean param1Boolean1, boolean param1Boolean2, boolean param1Boolean3, int param1Int4, CharSequence param1CharSequence4, boolean param1Boolean4, String param1String1, ArrayList<String> param1ArrayList, Bundle param1Bundle, int param1Int5, int param1Int6, Notification param1Notification2, String param1String2, boolean param1Boolean5, String param1String3, CharSequence[] param1ArrayOfCharSequence, RemoteViews param1RemoteViews2, RemoteViews param1RemoteViews3, RemoteViews param1RemoteViews4, String param1String4, int param1Int7, String param1String5, long param1Long, boolean param1Boolean6, boolean param1Boolean7, int param1Int8) {
      Notification.Builder builder = (new Notification.Builder(param1Context, param1String4)).setWhen(param1Notification1.when).setShowWhen(param1Boolean2).setSmallIcon(param1Notification1.icon, param1Notification1.iconLevel).setContent(param1Notification1.contentView).setTicker(param1Notification1.tickerText, param1RemoteViews1).setSound(param1Notification1.sound, param1Notification1.audioStreamType).setVibrate(param1Notification1.vibrate).setLights(param1Notification1.ledARGB, param1Notification1.ledOnMS, param1Notification1.ledOffMS);
      int i = param1Notification1.flags;
      boolean bool = true;
      if ((i & 0x2) != 0) {
        param1Boolean2 = true;
      } else {
        param1Boolean2 = false;
      } 
      builder = builder.setOngoing(param1Boolean2);
      if ((param1Notification1.flags & 0x8) != 0) {
        param1Boolean2 = true;
      } else {
        param1Boolean2 = false;
      } 
      builder = builder.setOnlyAlertOnce(param1Boolean2);
      if ((param1Notification1.flags & 0x10) != 0) {
        param1Boolean2 = true;
      } else {
        param1Boolean2 = false;
      } 
      builder = builder.setAutoCancel(param1Boolean2).setDefaults(param1Notification1.defaults).setContentTitle(param1CharSequence1).setContentText(param1CharSequence2).setSubText(param1CharSequence4).setContentInfo(param1CharSequence3).setContentIntent(param1PendingIntent1).setDeleteIntent(param1Notification1.deleteIntent);
      if ((param1Notification1.flags & 0x80) != 0) {
        param1Boolean2 = bool;
      } else {
        param1Boolean2 = false;
      } 
      builder = builder.setFullScreenIntent(param1PendingIntent2, param1Boolean2).setLargeIcon(param1Bitmap).setNumber(param1Int1).setUsesChronometer(param1Boolean3).setPriority(param1Int4).setProgress(param1Int2, param1Int3, param1Boolean1).setLocalOnly(param1Boolean4).setExtras(param1Bundle).setGroup(param1String2).setGroupSummary(param1Boolean5).setSortKey(param1String3).setCategory(param1String1).setColor(param1Int5).setVisibility(param1Int6).setPublicVersion(param1Notification2).setRemoteInputHistory(param1ArrayOfCharSequence).setChannelId(param1String4).setBadgeIconType(param1Int7).setShortcutId(param1String5).setTimeoutAfter(param1Long).setGroupAlertBehavior(param1Int8);
      this.mB = builder;
      if (param1Boolean7)
        builder.setColorized(param1Boolean6); 
      if (param1RemoteViews2 != null)
        this.mB.setCustomContentView(param1RemoteViews2); 
      if (param1RemoteViews3 != null)
        this.mB.setCustomBigContentView(param1RemoteViews3); 
      if (param1RemoteViews4 != null)
        this.mB.setCustomHeadsUpContentView(param1RemoteViews4); 
      for (String str : param1ArrayList)
        this.mB.addPerson(str); 
    }
    
    public void addAction(NotificationCompatBase.Action param1Action) {
      NotificationCompatApi24.addAction(this.mB, param1Action);
    }
    
    public Notification build() {
      return this.mB.build();
    }
    
    public Notification.Builder getBuilder() {
      return this.mB;
    }
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/android/support/v4/app/NotificationCompatApi26.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */