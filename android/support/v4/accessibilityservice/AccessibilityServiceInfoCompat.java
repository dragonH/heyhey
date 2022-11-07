package android.support.v4.accessibilityservice;

import android.accessibilityservice.AccessibilityServiceInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Build;
import android.support.annotation.RequiresApi;

public final class AccessibilityServiceInfoCompat {
  public static final int CAPABILITY_CAN_FILTER_KEY_EVENTS = 8;
  
  public static final int CAPABILITY_CAN_REQUEST_ENHANCED_WEB_ACCESSIBILITY = 4;
  
  public static final int CAPABILITY_CAN_REQUEST_TOUCH_EXPLORATION = 2;
  
  public static final int CAPABILITY_CAN_RETRIEVE_WINDOW_CONTENT = 1;
  
  @Deprecated
  public static final int DEFAULT = 1;
  
  public static final int FEEDBACK_ALL_MASK = -1;
  
  public static final int FEEDBACK_BRAILLE = 32;
  
  public static final int FLAG_INCLUDE_NOT_IMPORTANT_VIEWS = 2;
  
  public static final int FLAG_REPORT_VIEW_IDS = 16;
  
  public static final int FLAG_REQUEST_ENHANCED_WEB_ACCESSIBILITY = 8;
  
  public static final int FLAG_REQUEST_FILTER_KEY_EVENTS = 32;
  
  public static final int FLAG_REQUEST_TOUCH_EXPLORATION_MODE = 4;
  
  private static final AccessibilityServiceInfoBaseImpl IMPL;
  
  static {
    if (Build.VERSION.SDK_INT >= 18) {
      IMPL = new AccessibilityServiceInfoApi18Impl();
    } else {
      IMPL = new AccessibilityServiceInfoApi16Impl();
    } 
  }
  
  public static String capabilityToString(int paramInt) {
    return (paramInt != 1) ? ((paramInt != 2) ? ((paramInt != 4) ? ((paramInt != 8) ? "UNKNOWN" : "CAPABILITY_CAN_FILTER_KEY_EVENTS") : "CAPABILITY_CAN_REQUEST_ENHANCED_WEB_ACCESSIBILITY") : "CAPABILITY_CAN_REQUEST_TOUCH_EXPLORATION") : "CAPABILITY_CAN_RETRIEVE_WINDOW_CONTENT";
  }
  
  public static String feedbackTypeToString(int paramInt) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("[");
    while (paramInt > 0) {
      int i = 1 << Integer.numberOfTrailingZeros(paramInt);
      paramInt &= i ^ 0xFFFFFFFF;
      if (stringBuilder.length() > 1)
        stringBuilder.append(", "); 
      if (i != 1) {
        if (i != 2) {
          if (i != 4) {
            if (i != 8) {
              if (i != 16)
                continue; 
              stringBuilder.append("FEEDBACK_GENERIC");
              continue;
            } 
            stringBuilder.append("FEEDBACK_VISUAL");
            continue;
          } 
          stringBuilder.append("FEEDBACK_AUDIBLE");
          continue;
        } 
        stringBuilder.append("FEEDBACK_HAPTIC");
        continue;
      } 
      stringBuilder.append("FEEDBACK_SPOKEN");
    } 
    stringBuilder.append("]");
    return stringBuilder.toString();
  }
  
  public static String flagToString(int paramInt) {
    return (paramInt != 1) ? ((paramInt != 2) ? ((paramInt != 4) ? ((paramInt != 8) ? ((paramInt != 16) ? ((paramInt != 32) ? null : "FLAG_REQUEST_FILTER_KEY_EVENTS") : "FLAG_REPORT_VIEW_IDS") : "FLAG_REQUEST_ENHANCED_WEB_ACCESSIBILITY") : "FLAG_REQUEST_TOUCH_EXPLORATION_MODE") : "FLAG_INCLUDE_NOT_IMPORTANT_VIEWS") : "DEFAULT";
  }
  
  @Deprecated
  public static boolean getCanRetrieveWindowContent(AccessibilityServiceInfo paramAccessibilityServiceInfo) {
    return paramAccessibilityServiceInfo.getCanRetrieveWindowContent();
  }
  
  public static int getCapabilities(AccessibilityServiceInfo paramAccessibilityServiceInfo) {
    return IMPL.getCapabilities(paramAccessibilityServiceInfo);
  }
  
  @Deprecated
  public static String getDescription(AccessibilityServiceInfo paramAccessibilityServiceInfo) {
    return paramAccessibilityServiceInfo.getDescription();
  }
  
  @Deprecated
  public static String getId(AccessibilityServiceInfo paramAccessibilityServiceInfo) {
    return paramAccessibilityServiceInfo.getId();
  }
  
  @Deprecated
  public static ResolveInfo getResolveInfo(AccessibilityServiceInfo paramAccessibilityServiceInfo) {
    return paramAccessibilityServiceInfo.getResolveInfo();
  }
  
  @Deprecated
  public static String getSettingsActivityName(AccessibilityServiceInfo paramAccessibilityServiceInfo) {
    return paramAccessibilityServiceInfo.getSettingsActivityName();
  }
  
  public static String loadDescription(AccessibilityServiceInfo paramAccessibilityServiceInfo, PackageManager paramPackageManager) {
    return IMPL.loadDescription(paramAccessibilityServiceInfo, paramPackageManager);
  }
  
  @RequiresApi(16)
  static class AccessibilityServiceInfoApi16Impl extends AccessibilityServiceInfoBaseImpl {
    public String loadDescription(AccessibilityServiceInfo param1AccessibilityServiceInfo, PackageManager param1PackageManager) {
      return param1AccessibilityServiceInfo.loadDescription(param1PackageManager);
    }
  }
  
  @RequiresApi(18)
  static class AccessibilityServiceInfoApi18Impl extends AccessibilityServiceInfoApi16Impl {
    public int getCapabilities(AccessibilityServiceInfo param1AccessibilityServiceInfo) {
      return param1AccessibilityServiceInfo.getCapabilities();
    }
  }
  
  static class AccessibilityServiceInfoBaseImpl {
    public int getCapabilities(AccessibilityServiceInfo param1AccessibilityServiceInfo) {
      return AccessibilityServiceInfoCompat.getCanRetrieveWindowContent(param1AccessibilityServiceInfo) ? 1 : 0;
    }
    
    public String loadDescription(AccessibilityServiceInfo param1AccessibilityServiceInfo, PackageManager param1PackageManager) {
      return null;
    }
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/android/support/v4/accessibilityservice/AccessibilityServiceInfoCompat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */