package android.support.transition;

import android.graphics.Matrix;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;

class GhostViewUtils {
  private static final GhostViewImpl.Creator CREATOR;
  
  static {
    if (Build.VERSION.SDK_INT >= 21) {
      CREATOR = new GhostViewApi21.Creator();
    } else {
      CREATOR = new GhostViewApi14.Creator();
    } 
  }
  
  static GhostViewImpl addGhost(View paramView, ViewGroup paramViewGroup, Matrix paramMatrix) {
    return CREATOR.addGhost(paramView, paramViewGroup, paramMatrix);
  }
  
  static void removeGhost(View paramView) {
    CREATOR.removeGhost(paramView);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/android/support/transition/GhostViewUtils.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */