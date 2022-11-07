package android.support.v4.view.accessibility;

import android.support.annotation.RequiresApi;
import android.view.accessibility.AccessibilityNodeInfo;

@RequiresApi(19)
class AccessibilityNodeInfoCompatKitKat {
  static class RangeInfo {
    static float getCurrent(Object param1Object) {
      return ((AccessibilityNodeInfo.RangeInfo)param1Object).getCurrent();
    }
    
    static float getMax(Object param1Object) {
      return ((AccessibilityNodeInfo.RangeInfo)param1Object).getMax();
    }
    
    static float getMin(Object param1Object) {
      return ((AccessibilityNodeInfo.RangeInfo)param1Object).getMin();
    }
    
    static int getType(Object param1Object) {
      return ((AccessibilityNodeInfo.RangeInfo)param1Object).getType();
    }
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/android/support/v4/view/accessibility/AccessibilityNodeInfoCompatKitKat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */