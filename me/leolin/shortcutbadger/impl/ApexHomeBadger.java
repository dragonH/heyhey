package me.leolin.shortcutbadger.impl;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import java.util.Arrays;
import java.util.List;
import me.leolin.shortcutbadger.Badger;
import me.leolin.shortcutbadger.ShortcutBadgeException;
import me.leolin.shortcutbadger.util.BroadcastHelper;

public class ApexHomeBadger implements Badger {
  private static final String CLASS = "class";
  
  private static final String COUNT = "count";
  
  private static final String INTENT_UPDATE_COUNTER = "com.anddoes.launcher.COUNTER_CHANGED";
  
  private static final String PACKAGENAME = "package";
  
  public void executeBadge(Context paramContext, ComponentName paramComponentName, int paramInt) throws ShortcutBadgeException {
    Intent intent = new Intent("com.anddoes.launcher.COUNTER_CHANGED");
    intent.putExtra("package", paramComponentName.getPackageName());
    intent.putExtra("count", paramInt);
    intent.putExtra("class", paramComponentName.getClassName());
    if (BroadcastHelper.canResolveBroadcast(paramContext, intent)) {
      paramContext.sendBroadcast(intent);
      return;
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("unable to resolve intent: ");
    stringBuilder.append(intent.toString());
    throw new ShortcutBadgeException(stringBuilder.toString());
  }
  
  public List<String> getSupportLaunchers() {
    return Arrays.asList(new String[] { "com.anddoes.launcher" });
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/me/leolin/shortcutbadger/impl/ApexHomeBadger.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */