package me.leolin.shortcutbadger.impl;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import java.util.Arrays;
import java.util.List;
import me.leolin.shortcutbadger.Badger;
import me.leolin.shortcutbadger.ShortcutBadgeException;
import me.leolin.shortcutbadger.util.BroadcastHelper;

public class NewHtcHomeBadger implements Badger {
  public static final String COUNT = "count";
  
  public static final String EXTRA_COMPONENT = "com.htc.launcher.extra.COMPONENT";
  
  public static final String EXTRA_COUNT = "com.htc.launcher.extra.COUNT";
  
  public static final String INTENT_SET_NOTIFICATION = "com.htc.launcher.action.SET_NOTIFICATION";
  
  public static final String INTENT_UPDATE_SHORTCUT = "com.htc.launcher.action.UPDATE_SHORTCUT";
  
  public static final String PACKAGENAME = "packagename";
  
  public void executeBadge(Context paramContext, ComponentName paramComponentName, int paramInt) throws ShortcutBadgeException {
    Intent intent1 = new Intent("com.htc.launcher.action.SET_NOTIFICATION");
    intent1.putExtra("com.htc.launcher.extra.COMPONENT", paramComponentName.flattenToShortString());
    intent1.putExtra("com.htc.launcher.extra.COUNT", paramInt);
    Intent intent2 = new Intent("com.htc.launcher.action.UPDATE_SHORTCUT");
    intent2.putExtra("packagename", paramComponentName.getPackageName());
    intent2.putExtra("count", paramInt);
    if (BroadcastHelper.canResolveBroadcast(paramContext, intent1) || BroadcastHelper.canResolveBroadcast(paramContext, intent2)) {
      paramContext.sendBroadcast(intent1);
      paramContext.sendBroadcast(intent2);
      return;
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("unable to resolve intent: ");
    stringBuilder.append(intent2.toString());
    throw new ShortcutBadgeException(stringBuilder.toString());
  }
  
  public List<String> getSupportLaunchers() {
    return Arrays.asList(new String[] { "com.htc.launcher" });
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/me/leolin/shortcutbadger/impl/NewHtcHomeBadger.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */