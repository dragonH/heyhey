package android.support.transition;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.Build;
import android.support.annotation.NonNull;

class AnimatorUtils {
  private static final AnimatorUtilsImpl IMPL;
  
  static {
    if (Build.VERSION.SDK_INT >= 19) {
      IMPL = new AnimatorUtilsApi19();
    } else {
      IMPL = new AnimatorUtilsApi14();
    } 
  }
  
  static void addPauseListener(@NonNull Animator paramAnimator, @NonNull AnimatorListenerAdapter paramAnimatorListenerAdapter) {
    IMPL.addPauseListener(paramAnimator, paramAnimatorListenerAdapter);
  }
  
  static void pause(@NonNull Animator paramAnimator) {
    IMPL.pause(paramAnimator);
  }
  
  static void resume(@NonNull Animator paramAnimator) {
    IMPL.resume(paramAnimator);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/android/support/transition/AnimatorUtils.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */