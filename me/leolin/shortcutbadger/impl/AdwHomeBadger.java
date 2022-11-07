package me.leolin.shortcutbadger.impl;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import java.util.Arrays;
import java.util.List;
import me.leolin.shortcutbadger.Badger;
import me.leolin.shortcutbadger.ShortcutBadgeException;
import me.leolin.shortcutbadger.util.BroadcastHelper;

public class AdwHomeBadger implements Badger {
  public static final String CLASSNAME = "CNAME";
  
  public static final String COUNT = "COUNT";
  
  public static final String INTENT_UPDATE_COUNTER = "org.adw.launcher.counter.SEND";
  
  public static final String PACKAGENAME = "PNAME";
  
  public void executeBadge(Context paramContext, ComponentName paramComponentName, int paramInt) throws ShortcutBadgeException {
    Intent intent = new Intent("org.adw.launcher.counter.SEND");
    intent.putExtra("PNAME", paramComponentName.getPackageName());
    intent.putExtra("CNAME", paramComponentName.getClassName());
    intent.putExtra("COUNT", paramInt);
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
    return Arrays.asList(new String[] { "org.adw.launcher", "org.adwfreak.launcher" });
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/me/leolin/shortcutbadger/impl/AdwHomeBadger.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */