package me.leolin.shortcutbadger.impl;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import java.util.Arrays;
import java.util.List;
import me.leolin.shortcutbadger.Badger;
import me.leolin.shortcutbadger.ShortcutBadgeException;

public class VivoHomeBadger implements Badger {
  public void executeBadge(Context paramContext, ComponentName paramComponentName, int paramInt) throws ShortcutBadgeException {
    Intent intent = new Intent("launcher.action.CHANGE_APPLICATION_NOTIFICATION_NUM");
    intent.putExtra("packageName", paramContext.getPackageName());
    intent.putExtra("className", paramComponentName.getClassName());
    intent.putExtra("notificationNum", paramInt);
    paramContext.sendBroadcast(intent);
  }
  
  public List<String> getSupportLaunchers() {
    return Arrays.asList(new String[] { "com.vivo.launcher" });
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/me/leolin/shortcutbadger/impl/VivoHomeBadger.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */