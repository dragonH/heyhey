package android.support.transition;

import android.os.Build;
import android.support.annotation.NonNull;
import android.view.ViewGroup;

class ViewGroupUtils {
  private static final ViewGroupUtilsImpl IMPL;
  
  static {
    if (Build.VERSION.SDK_INT >= 18) {
      IMPL = new ViewGroupUtilsApi18();
    } else {
      IMPL = new ViewGroupUtilsApi14();
    } 
  }
  
  static ViewGroupOverlayImpl getOverlay(@NonNull ViewGroup paramViewGroup) {
    return IMPL.getOverlay(paramViewGroup);
  }
  
  static void suppressLayout(@NonNull ViewGroup paramViewGroup, boolean paramBoolean) {
    IMPL.suppressLayout(paramViewGroup, paramBoolean);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/android/support/transition/ViewGroupUtils.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */