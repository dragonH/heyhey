package android.support.transition;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import java.util.ArrayList;

@RequiresApi(14)
class AnimatorUtilsApi14 implements AnimatorUtilsImpl {
  public void addPauseListener(@NonNull Animator paramAnimator, @NonNull AnimatorListenerAdapter paramAnimatorListenerAdapter) {}
  
  public void pause(@NonNull Animator paramAnimator) {
    ArrayList<Animator.AnimatorListener> arrayList = paramAnimator.getListeners();
    if (arrayList != null) {
      byte b = 0;
      int i = arrayList.size();
      while (b < i) {
        Animator.AnimatorListener animatorListener = arrayList.get(b);
        if (animatorListener instanceof AnimatorPauseListenerCompat)
          ((AnimatorPauseListenerCompat)animatorListener).onAnimationPause(paramAnimator); 
        b++;
      } 
    } 
  }
  
  public void resume(@NonNull Animator paramAnimator) {
    ArrayList<Animator.AnimatorListener> arrayList = paramAnimator.getListeners();
    if (arrayList != null) {
      byte b = 0;
      int i = arrayList.size();
      while (b < i) {
        Animator.AnimatorListener animatorListener = arrayList.get(b);
        if (animatorListener instanceof AnimatorPauseListenerCompat)
          ((AnimatorPauseListenerCompat)animatorListener).onAnimationResume(paramAnimator); 
        b++;
      } 
    } 
  }
  
  static interface AnimatorPauseListenerCompat {
    void onAnimationPause(Animator param1Animator);
    
    void onAnimationResume(Animator param1Animator);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/android/support/transition/AnimatorUtilsApi14.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */