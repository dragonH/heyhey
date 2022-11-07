package android.support.v4.app;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

public final class NavUtils {
  public static final String PARENT_ACTIVITY = "android.support.PARENT_ACTIVITY";
  
  private static final String TAG = "NavUtils";
  
  public static Intent getParentActivityIntent(Activity paramActivity) {
    Intent intent = paramActivity.getParentActivityIntent();
    if (intent != null)
      return intent; 
    String str = getParentActivityName(paramActivity);
    if (str == null)
      return null; 
    ComponentName componentName = new ComponentName((Context)paramActivity, str);
    try {
      Intent intent1;
      if (getParentActivityName((Context)paramActivity, componentName) == null) {
        intent1 = Intent.makeMainActivity(componentName);
      } else {
        intent1 = new Intent();
        this();
        intent1 = intent1.setComponent(componentName);
      } 
      return intent1;
    } catch (android.content.pm.PackageManager.NameNotFoundException nameNotFoundException) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("getParentActivityIntent: bad parentActivityName '");
      stringBuilder.append(str);
      stringBuilder.append("' in manifest");
      Log.e("NavUtils", stringBuilder.toString());
      return null;
    } 
  }
  
  public static Intent getParentActivityIntent(Context paramContext, ComponentName paramComponentName) throws PackageManager.NameNotFoundException {
    Intent intent;
    String str = getParentActivityName(paramContext, paramComponentName);
    if (str == null)
      return null; 
    paramComponentName = new ComponentName(paramComponentName.getPackageName(), str);
    if (getParentActivityName(paramContext, paramComponentName) == null) {
      intent = Intent.makeMainActivity(paramComponentName);
    } else {
      intent = (new Intent()).setComponent(paramComponentName);
    } 
    return intent;
  }
  
  public static Intent getParentActivityIntent(Context paramContext, Class<?> paramClass) throws PackageManager.NameNotFoundException {
    Intent intent;
    String str = getParentActivityName(paramContext, new ComponentName(paramContext, paramClass));
    if (str == null)
      return null; 
    ComponentName componentName = new ComponentName(paramContext, str);
    if (getParentActivityName(paramContext, componentName) == null) {
      intent = Intent.makeMainActivity(componentName);
    } else {
      intent = (new Intent()).setComponent(componentName);
    } 
    return intent;
  }
  
  @Nullable
  public static String getParentActivityName(Activity paramActivity) {
    try {
      return getParentActivityName((Context)paramActivity, paramActivity.getComponentName());
    } catch (android.content.pm.PackageManager.NameNotFoundException nameNotFoundException) {
      throw new IllegalArgumentException(nameNotFoundException);
    } 
  }
  
  @Nullable
  public static String getParentActivityName(Context paramContext, ComponentName paramComponentName) throws PackageManager.NameNotFoundException {
    ActivityInfo activityInfo = paramContext.getPackageManager().getActivityInfo(paramComponentName, 128);
    String str2 = activityInfo.parentActivityName;
    if (str2 != null)
      return str2; 
    Bundle bundle = activityInfo.metaData;
    if (bundle == null)
      return null; 
    String str3 = bundle.getString("android.support.PARENT_ACTIVITY");
    if (str3 == null)
      return null; 
    String str1 = str3;
    if (str3.charAt(0) == '.') {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append(paramContext.getPackageName());
      stringBuilder.append(str3);
      str1 = stringBuilder.toString();
    } 
    return str1;
  }
  
  public static void navigateUpFromSameTask(Activity paramActivity) {
    Intent intent = getParentActivityIntent(paramActivity);
    if (intent != null) {
      navigateUpTo(paramActivity, intent);
      return;
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Activity ");
    stringBuilder.append(paramActivity.getClass().getSimpleName());
    stringBuilder.append(" does not have a parent activity name specified.");
    stringBuilder.append(" (Did you forget to add the android.support.PARENT_ACTIVITY <meta-data> ");
    stringBuilder.append(" element in your manifest?)");
    throw new IllegalArgumentException(stringBuilder.toString());
  }
  
  public static void navigateUpTo(Activity paramActivity, Intent paramIntent) {
    paramActivity.navigateUpTo(paramIntent);
  }
  
  public static boolean shouldUpRecreateTask(Activity paramActivity, Intent paramIntent) {
    return paramActivity.shouldUpRecreateTask(paramIntent);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/android/support/v4/app/NavUtils.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */