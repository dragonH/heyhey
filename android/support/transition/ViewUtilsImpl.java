package android.support.transition;

import android.graphics.Matrix;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.view.View;

@RequiresApi(14)
interface ViewUtilsImpl {
  void clearNonTransitionAlpha(@NonNull View paramView);
  
  ViewOverlayImpl getOverlay(@NonNull View paramView);
  
  float getTransitionAlpha(@NonNull View paramView);
  
  WindowIdImpl getWindowId(@NonNull View paramView);
  
  void saveNonTransitionAlpha(@NonNull View paramView);
  
  void setAnimationMatrix(@NonNull View paramView, Matrix paramMatrix);
  
  void setLeftTopRightBottom(View paramView, int paramInt1, int paramInt2, int paramInt3, int paramInt4);
  
  void setTransitionAlpha(@NonNull View paramView, float paramFloat);
  
  void transformMatrixToGlobal(@NonNull View paramView, @NonNull Matrix paramMatrix);
  
  void transformMatrixToLocal(@NonNull View paramView, @NonNull Matrix paramMatrix);
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/android/support/transition/ViewUtilsImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */