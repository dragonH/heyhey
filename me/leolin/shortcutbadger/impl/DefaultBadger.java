package me.leolin.shortcutbadger.impl;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import java.util.Collections;
import java.util.List;
import me.leolin.shortcutbadger.Badger;
import me.leolin.shortcutbadger.ShortcutBadgeException;
import me.leolin.shortcutbadger.util.BroadcastHelper;

public class DefaultBadger implements Badger {
  private static final String INTENT_ACTION = "android.intent.action.BADGE_COUNT_UPDATE";
  
  private static final String INTENT_EXTRA_ACTIVITY_NAME = "badge_count_class_name";
  
  private static final String INTENT_EXTRA_BADGE_COUNT = "badge_count";
  
  private static final String INTENT_EXTRA_PACKAGENAME = "badge_count_package_name";
  
  public void executeBadge(Context paramContext, ComponentName paramComponentName, int paramInt) throws ShortcutBadgeException {
    Intent intent = new Intent("android.intent.action.BADGE_COUNT_UPDATE");
    intent.putExtra("badge_count", paramInt);
    intent.putExtra("badge_count_package_name", paramComponentName.getPackageName());
    intent.putExtra("badge_count_class_name", paramComponentName.getClassName());
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
    return Collections.singletonList("fr.neamar.kiss");
  }
  
  boolean isSupported(Context paramContext) {
    return BroadcastHelper.canResolveBroadcast(paramContext, new Intent("android.intent.action.BADGE_COUNT_UPDATE"));
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/me/leolin/shortcutbadger/impl/DefaultBadger.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */