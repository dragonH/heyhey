package me.leolin.shortcutbadger.impl;

import android.content.AsyncQueryHandler;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Looper;
import java.util.Arrays;
import java.util.List;
import me.leolin.shortcutbadger.Badger;
import me.leolin.shortcutbadger.ShortcutBadgeException;

public class SonyHomeBadger implements Badger {
  private static final String INTENT_ACTION = "com.sonyericsson.home.action.UPDATE_BADGE";
  
  private static final String INTENT_EXTRA_ACTIVITY_NAME = "com.sonyericsson.home.intent.extra.badge.ACTIVITY_NAME";
  
  private static final String INTENT_EXTRA_MESSAGE = "com.sonyericsson.home.intent.extra.badge.MESSAGE";
  
  private static final String INTENT_EXTRA_PACKAGE_NAME = "com.sonyericsson.home.intent.extra.badge.PACKAGE_NAME";
  
  private static final String INTENT_EXTRA_SHOW_MESSAGE = "com.sonyericsson.home.intent.extra.badge.SHOW_MESSAGE";
  
  private static final String PROVIDER_COLUMNS_ACTIVITY_NAME = "activity_name";
  
  private static final String PROVIDER_COLUMNS_BADGE_COUNT = "badge_count";
  
  private static final String PROVIDER_COLUMNS_PACKAGE_NAME = "package_name";
  
  private static final String PROVIDER_CONTENT_URI = "content://com.sonymobile.home.resourceprovider/badge";
  
  private static final String SONY_HOME_PROVIDER_NAME = "com.sonymobile.home.resourceprovider";
  
  private final Uri BADGE_CONTENT_URI = Uri.parse("content://com.sonymobile.home.resourceprovider/badge");
  
  private AsyncQueryHandler mQueryHandler;
  
  private ContentValues createContentValues(int paramInt, ComponentName paramComponentName) {
    ContentValues contentValues = new ContentValues();
    contentValues.put("badge_count", Integer.valueOf(paramInt));
    contentValues.put("package_name", paramComponentName.getPackageName());
    contentValues.put("activity_name", paramComponentName.getClassName());
    return contentValues;
  }
  
  private static void executeBadgeByBroadcast(Context paramContext, ComponentName paramComponentName, int paramInt) {
    boolean bool;
    Intent intent = new Intent("com.sonyericsson.home.action.UPDATE_BADGE");
    intent.putExtra("com.sonyericsson.home.intent.extra.badge.PACKAGE_NAME", paramComponentName.getPackageName());
    intent.putExtra("com.sonyericsson.home.intent.extra.badge.ACTIVITY_NAME", paramComponentName.getClassName());
    intent.putExtra("com.sonyericsson.home.intent.extra.badge.MESSAGE", String.valueOf(paramInt));
    if (paramInt > 0) {
      bool = true;
    } else {
      bool = false;
    } 
    intent.putExtra("com.sonyericsson.home.intent.extra.badge.SHOW_MESSAGE", bool);
    paramContext.sendBroadcast(intent);
  }
  
  private void executeBadgeByContentProvider(Context paramContext, ComponentName paramComponentName, int paramInt) {
    if (paramInt < 0)
      return; 
    ContentValues contentValues = createContentValues(paramInt, paramComponentName);
    if (Looper.myLooper() == Looper.getMainLooper()) {
      if (this.mQueryHandler == null)
        this.mQueryHandler = new AsyncQueryHandler(paramContext.getApplicationContext().getContentResolver()) {
          
          }; 
      insertBadgeAsync(contentValues);
    } else {
      insertBadgeSync(paramContext, contentValues);
    } 
  }
  
  private void insertBadgeAsync(ContentValues paramContentValues) {
    this.mQueryHandler.startInsert(0, null, this.BADGE_CONTENT_URI, paramContentValues);
  }
  
  private void insertBadgeSync(Context paramContext, ContentValues paramContentValues) {
    paramContext.getApplicationContext().getContentResolver().insert(this.BADGE_CONTENT_URI, paramContentValues);
  }
  
  private static boolean sonyBadgeContentProviderExists(Context paramContext) {
    PackageManager packageManager = paramContext.getPackageManager();
    boolean bool = false;
    if (packageManager.resolveContentProvider("com.sonymobile.home.resourceprovider", 0) != null)
      bool = true; 
    return bool;
  }
  
  public void executeBadge(Context paramContext, ComponentName paramComponentName, int paramInt) throws ShortcutBadgeException {
    if (sonyBadgeContentProviderExists(paramContext)) {
      executeBadgeByContentProvider(paramContext, paramComponentName, paramInt);
    } else {
      executeBadgeByBroadcast(paramContext, paramComponentName, paramInt);
    } 
  }
  
  public List<String> getSupportLaunchers() {
    return Arrays.asList(new String[] { "com.sonyericsson.home", "com.sonymobile.home" });
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/me/leolin/shortcutbadger/impl/SonyHomeBadger.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */