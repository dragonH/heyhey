package me.leolin.shortcutbadger;

import android.content.ComponentName;
import android.content.Context;
import java.util.List;

public interface Badger {
  void executeBadge(Context paramContext, ComponentName paramComponentName, int paramInt) throws ShortcutBadgeException;
  
  List<String> getSupportLaunchers();
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/me/leolin/shortcutbadger/Badger.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */