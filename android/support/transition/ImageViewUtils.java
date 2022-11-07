package android.support.transition;

import android.animation.Animator;
import android.graphics.Matrix;
import android.os.Build;
import android.widget.ImageView;

class ImageViewUtils {
  private static final ImageViewUtilsImpl IMPL;
  
  static {
    if (Build.VERSION.SDK_INT >= 21) {
      IMPL = new ImageViewUtilsApi21();
    } else {
      IMPL = new ImageViewUtilsApi14();
    } 
  }
  
  static void animateTransform(ImageView paramImageView, Matrix paramMatrix) {
    IMPL.animateTransform(paramImageView, paramMatrix);
  }
  
  static void reserveEndAnimateTransform(ImageView paramImageView, Animator paramAnimator) {
    IMPL.reserveEndAnimateTransform(paramImageView, paramAnimator);
  }
  
  static void startAnimateTransform(ImageView paramImageView) {
    IMPL.startAnimateTransform(paramImageView);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/android/support/transition/ImageViewUtils.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */