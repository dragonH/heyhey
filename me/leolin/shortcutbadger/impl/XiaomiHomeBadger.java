package me.leolin.shortcutbadger.impl;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.os.Build;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import me.leolin.shortcutbadger.Badger;
import me.leolin.shortcutbadger.ShortcutBadgeException;
import me.leolin.shortcutbadger.util.BroadcastHelper;

@Deprecated
public class XiaomiHomeBadger implements Badger {
  public static final String EXTRA_UPDATE_APP_COMPONENT_NAME = "android.intent.extra.update_application_component_name";
  
  public static final String EXTRA_UPDATE_APP_MSG_TEXT = "android.intent.extra.update_application_message_text";
  
  public static final String INTENT_ACTION = "android.intent.action.APPLICATION_MESSAGE_UPDATE";
  
  private ResolveInfo resolveInfo;
  
  @TargetApi(16)
  private void tryNewMiuiBadge(Context paramContext, int paramInt) throws ShortcutBadgeException {
    if (this.resolveInfo == null) {
      Intent intent = new Intent("android.intent.action.MAIN");
      intent.addCategory("android.intent.category.HOME");
      this.resolveInfo = paramContext.getPackageManager().resolveActivity(intent, 65536);
    } 
    if (this.resolveInfo != null) {
      NotificationManager notificationManager = (NotificationManager)paramContext.getSystemService("notification");
      Notification notification = (new Notification.Builder(paramContext)).setContentTitle("").setContentText("").setSmallIcon(this.resolveInfo.getIconResource()).build();
      try {
        Object object = notification.getClass().getDeclaredField("extraNotification").get(notification);
        object.getClass().getDeclaredMethod("setMessageCount", new Class[] { int.class }).invoke(object, new Object[] { Integer.valueOf(paramInt) });
        notificationManager.notify(0, notification);
      } catch (Exception exception) {
        throw new ShortcutBadgeException("not able to set badge", exception);
      } 
    } 
  }
  
  public void executeBadge(Context paramContext, ComponentName paramComponentName, int paramInt) throws ShortcutBadgeException {
    String str = "";
    try {
      Object object = Class.forName("android.app.MiuiNotification").newInstance();
      Field field = object.getClass().getDeclaredField("messageCount");
      field.setAccessible(true);
      if (paramInt == 0) {
        String str1 = "";
      } else {
        try {
          Integer integer = Integer.valueOf(paramInt);
          field.set(object, String.valueOf(integer));
        } catch (Exception exception) {
          field.set(object, Integer.valueOf(paramInt));
        } 
        if (Build.MANUFACTURER.equalsIgnoreCase("Xiaomi"))
          tryNewMiuiBadge(paramContext, paramInt); 
      } 
      field.set(object, String.valueOf(exception));
    } catch (Exception exception) {
      Integer integer;
      Intent intent = new Intent("android.intent.action.APPLICATION_MESSAGE_UPDATE");
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append(paramComponentName.getPackageName());
      stringBuilder.append("/");
      stringBuilder.append(paramComponentName.getClassName());
      intent.putExtra("android.intent.extra.update_application_component_name", stringBuilder.toString());
      if (paramInt == 0) {
        String str1 = str;
      } else {
        integer = Integer.valueOf(paramInt);
      } 
      intent.putExtra("android.intent.extra.update_application_message_text", String.valueOf(integer));
      if (BroadcastHelper.canResolveBroadcast(paramContext, intent))
        paramContext.sendBroadcast(intent); 
    } 
    if (Build.MANUFACTURER.equalsIgnoreCase("Xiaomi"))
      tryNewMiuiBadge(paramContext, paramInt); 
  }
  
  public List<String> getSupportLaunchers() {
    return Arrays.asList(new String[] { "com.miui.miuilite", "com.miui.home", "com.miui.miuihome", "com.miui.miuihome2", "com.miui.mihome", "com.miui.mihome2", "com.i.miui.launcher" });
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/me/leolin/shortcutbadger/impl/XiaomiHomeBadger.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */