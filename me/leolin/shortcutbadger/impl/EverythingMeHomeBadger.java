package me.leolin.shortcutbadger.impl;

import android.content.ComponentName;
import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import java.util.Arrays;
import java.util.List;
import me.leolin.shortcutbadger.Badger;
import me.leolin.shortcutbadger.ShortcutBadgeException;

public class EverythingMeHomeBadger implements Badger {
  private static final String COLUMN_ACTIVITY_NAME = "activity_name";
  
  private static final String COLUMN_COUNT = "count";
  
  private static final String COLUMN_PACKAGE_NAME = "package_name";
  
  private static final String CONTENT_URI = "content://me.everything.badger/apps";
  
  public void executeBadge(Context paramContext, ComponentName paramComponentName, int paramInt) throws ShortcutBadgeException {
    ContentValues contentValues = new ContentValues();
    contentValues.put("package_name", paramComponentName.getPackageName());
    contentValues.put("activity_name", paramComponentName.getClassName());
    contentValues.put("count", Integer.valueOf(paramInt));
    paramContext.getContentResolver().insert(Uri.parse("content://me.everything.badger/apps"), contentValues);
  }
  
  public List<String> getSupportLaunchers() {
    return Arrays.asList(new String[] { "me.everything.launcher" });
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/me/leolin/shortcutbadger/impl/EverythingMeHomeBadger.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */