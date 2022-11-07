package android.support.transition;

import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.view.View;

@RequiresApi(18)
class ViewUtilsApi18 extends ViewUtilsApi14 {
  public ViewOverlayImpl getOverlay(@NonNull View paramView) {
    return new ViewOverlayApi18(paramView);
  }
  
  public WindowIdImpl getWindowId(@NonNull View paramView) {
    return new WindowIdApi18(paramView);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/android/support/transition/ViewUtilsApi18.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */