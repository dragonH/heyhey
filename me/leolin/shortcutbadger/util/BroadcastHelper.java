package me.leolin.shortcutbadger.util;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import java.util.List;

public class BroadcastHelper {
  public static boolean canResolveBroadcast(Context paramContext, Intent paramIntent) {
    PackageManager packageManager = paramContext.getPackageManager();
    boolean bool1 = false;
    List list = packageManager.queryBroadcastReceivers(paramIntent, 0);
    boolean bool2 = bool1;
    if (list != null) {
      bool2 = bool1;
      if (list.size() > 0)
        bool2 = true; 
    } 
    return bool2;
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/me/leolin/shortcutbadger/util/BroadcastHelper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */