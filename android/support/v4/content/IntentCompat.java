package android.support.v4.content;

import android.content.ComponentName;
import android.content.Intent;
import android.support.annotation.RequiresApi;

public final class IntentCompat {
  @Deprecated
  public static final String ACTION_EXTERNAL_APPLICATIONS_AVAILABLE = "android.intent.action.EXTERNAL_APPLICATIONS_AVAILABLE";
  
  @Deprecated
  public static final String ACTION_EXTERNAL_APPLICATIONS_UNAVAILABLE = "android.intent.action.EXTERNAL_APPLICATIONS_UNAVAILABLE";
  
  public static final String CATEGORY_LEANBACK_LAUNCHER = "android.intent.category.LEANBACK_LAUNCHER";
  
  @Deprecated
  public static final String EXTRA_CHANGED_PACKAGE_LIST = "android.intent.extra.changed_package_list";
  
  @Deprecated
  public static final String EXTRA_CHANGED_UID_LIST = "android.intent.extra.changed_uid_list";
  
  public static final String EXTRA_HTML_TEXT = "android.intent.extra.HTML_TEXT";
  
  public static final String EXTRA_START_PLAYBACK = "android.intent.extra.START_PLAYBACK";
  
  @Deprecated
  public static final int FLAG_ACTIVITY_CLEAR_TASK = 32768;
  
  @Deprecated
  public static final int FLAG_ACTIVITY_TASK_ON_HOME = 16384;
  
  private static final IntentCompatBaseImpl IMPL = new IntentCompatApi15Impl();
  
  @Deprecated
  public static Intent makeMainActivity(ComponentName paramComponentName) {
    return Intent.makeMainActivity(paramComponentName);
  }
  
  public static Intent makeMainSelectorActivity(String paramString1, String paramString2) {
    return IMPL.makeMainSelectorActivity(paramString1, paramString2);
  }
  
  @Deprecated
  public static Intent makeRestartActivityTask(ComponentName paramComponentName) {
    return Intent.makeRestartActivityTask(paramComponentName);
  }
  
  @RequiresApi(15)
  static class IntentCompatApi15Impl extends IntentCompatBaseImpl {
    public Intent makeMainSelectorActivity(String param1String1, String param1String2) {
      return Intent.makeMainSelectorActivity(param1String1, param1String2);
    }
  }
  
  static class IntentCompatBaseImpl {
    public Intent makeMainSelectorActivity(String param1String1, String param1String2) {
      Intent intent = new Intent(param1String1);
      intent.addCategory(param1String2);
      return intent;
    }
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/android/support/v4/content/IntentCompat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */