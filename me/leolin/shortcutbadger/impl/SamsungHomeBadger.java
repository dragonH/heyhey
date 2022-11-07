package me.leolin.shortcutbadger.impl;

import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import java.util.Arrays;
import java.util.List;
import me.leolin.shortcutbadger.Badger;
import me.leolin.shortcutbadger.ShortcutBadgeException;
import me.leolin.shortcutbadger.util.CloseHelper;

public class SamsungHomeBadger implements Badger {
  private static final String[] CONTENT_PROJECTION = new String[] { "_id", "class" };
  
  private static final String CONTENT_URI = "content://com.sec.badge/apps?notify=true";
  
  private DefaultBadger defaultBadger;
  
  public SamsungHomeBadger() {
    if (Build.VERSION.SDK_INT >= 21)
      this.defaultBadger = new DefaultBadger(); 
  }
  
  private ContentValues getContentValues(ComponentName paramComponentName, int paramInt, boolean paramBoolean) {
    ContentValues contentValues = new ContentValues();
    if (paramBoolean) {
      contentValues.put("package", paramComponentName.getPackageName());
      contentValues.put("class", paramComponentName.getClassName());
    } 
    contentValues.put("badgecount", Integer.valueOf(paramInt));
    return contentValues;
  }
  
  public void executeBadge(Context paramContext, ComponentName paramComponentName, int paramInt) throws ShortcutBadgeException {
    DefaultBadger defaultBadger = this.defaultBadger;
    if (defaultBadger != null && defaultBadger.isSupported(paramContext)) {
      this.defaultBadger.executeBadge(paramContext, paramComponentName, paramInt);
    } else {
      Cursor cursor;
      Uri uri = Uri.parse("content://com.sec.badge/apps?notify=true");
      ContentResolver contentResolver = paramContext.getContentResolver();
      paramContext = null;
      try {
        Cursor cursor1 = contentResolver.query(uri, CONTENT_PROJECTION, "package=?", new String[] { paramComponentName.getPackageName() }, null);
        if (cursor1 != null) {
          cursor = cursor1;
          String str = paramComponentName.getClassName();
          boolean bool = false;
          while (true) {
            cursor = cursor1;
            if (cursor1.moveToNext()) {
              cursor = cursor1;
              int i = cursor1.getInt(0);
              cursor = cursor1;
              contentResolver.update(uri, getContentValues(paramComponentName, paramInt, false), "_id=?", new String[] { String.valueOf(i) });
              cursor = cursor1;
              if (str.equals(cursor1.getString(cursor1.getColumnIndex("class"))))
                bool = true; 
              continue;
            } 
            if (!bool) {
              cursor = cursor1;
              contentResolver.insert(uri, getContentValues(paramComponentName, paramInt, true));
            } 
            break;
          } 
        } 
        return;
      } finally {
        CloseHelper.close(cursor);
      } 
    } 
  }
  
  public List<String> getSupportLaunchers() {
    return Arrays.asList(new String[] { "com.sec.android.app.launcher", "com.sec.android.app.twlauncher" });
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/me/leolin/shortcutbadger/impl/SamsungHomeBadger.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */