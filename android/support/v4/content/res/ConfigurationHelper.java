package android.support.v4.content.res;

import android.content.res.Resources;
import android.os.Build;
import android.support.annotation.NonNull;

public final class ConfigurationHelper {
  public static int getDensityDpi(@NonNull Resources paramResources) {
    return (Build.VERSION.SDK_INT >= 17) ? (paramResources.getConfiguration()).densityDpi : (paramResources.getDisplayMetrics()).densityDpi;
  }
  
  @Deprecated
  public static int getScreenHeightDp(@NonNull Resources paramResources) {
    return (paramResources.getConfiguration()).screenHeightDp;
  }
  
  @Deprecated
  public static int getScreenWidthDp(@NonNull Resources paramResources) {
    return (paramResources.getConfiguration()).screenWidthDp;
  }
  
  @Deprecated
  public static int getSmallestScreenWidthDp(@NonNull Resources paramResources) {
    return (paramResources.getConfiguration()).smallestScreenWidthDp;
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/android/support/v4/content/res/ConfigurationHelper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */