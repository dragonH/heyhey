package me.leolin.shortcutbadger.impl;

import android.content.ComponentName;
import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import java.util.Arrays;
import java.util.List;
import me.leolin.shortcutbadger.Badger;
import me.leolin.shortcutbadger.ShortcutBadgeException;

public class NovaHomeBadger implements Badger {
  private static final String CONTENT_URI = "content://com.teslacoilsw.notifier/unread_count";
  
  private static final String COUNT = "count";
  
  private static final String TAG = "tag";
  
  public void executeBadge(Context paramContext, ComponentName paramComponentName, int paramInt) throws ShortcutBadgeException {
    ContentValues contentValues = new ContentValues();
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(paramComponentName.getPackageName());
    stringBuilder.append("/");
    stringBuilder.append(paramComponentName.getClassName());
    contentValues.put("tag", stringBuilder.toString());
    contentValues.put("count", Integer.valueOf(paramInt));
    paramContext.getContentResolver().insert(Uri.parse("content://com.teslacoilsw.notifier/unread_count"), contentValues);
  }
  
  public List<String> getSupportLaunchers() {
    return Arrays.asList(new String[] { "com.teslacoilsw.launcher" });
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/me/leolin/shortcutbadger/impl/NovaHomeBadger.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */