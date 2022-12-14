package android.support.transition;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.TimeInterpolator;
import android.view.View;

class TranslationAnimationCreator {
  static Animator createAnimation(View paramView, TransitionValues paramTransitionValues, int paramInt1, int paramInt2, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, TimeInterpolator paramTimeInterpolator) {
    float f3;
    float f1 = paramView.getTranslationX();
    float f2 = paramView.getTranslationY();
    int[] arrayOfInt = (int[])paramTransitionValues.view.getTag(R.id.transition_position);
    if (arrayOfInt != null) {
      f3 = (arrayOfInt[0] - paramInt1) + f1;
      paramFloat1 = (arrayOfInt[1] - paramInt2) + f2;
    } else {
      f3 = paramFloat1;
      paramFloat1 = paramFloat2;
    } 
    int i = Math.round(f3 - f1);
    int j = Math.round(paramFloat1 - f2);
    paramView.setTranslationX(f3);
    paramView.setTranslationY(paramFloat1);
    if (f3 == paramFloat3 && paramFloat1 == paramFloat4)
      return null; 
    ObjectAnimator objectAnimator = ObjectAnimator.ofPropertyValuesHolder(paramView, new PropertyValuesHolder[] { PropertyValuesHolder.ofFloat(View.TRANSLATION_X, new float[] { f3, paramFloat3 }), PropertyValuesHolder.ofFloat(View.TRANSLATION_Y, new float[] { paramFloat1, paramFloat4 }) });
    TransitionPositionListener transitionPositionListener = new TransitionPositionListener(paramView, paramTransitionValues.view, paramInt1 + i, paramInt2 + j, f1, f2);
    objectAnimator.addListener((Animator.AnimatorListener)transitionPositionListener);
    AnimatorUtils.addPauseListener((Animator)objectAnimator, transitionPositionListener);
    objectAnimator.setInterpolator(paramTimeInterpolator);
    return (Animator)objectAnimator;
  }
  
  private static class TransitionPositionListener extends AnimatorListenerAdapter {
    private final View mMovingView;
    
    private float mPausedX;
    
    private float mPausedY;
    
    private final int mStartX;
    
    private final int mStartY;
    
    private final float mTerminalX;
    
    private final float mTerminalY;
    
    private int[] mTransitionPosition;
    
    private final View mViewInHierarchy;
    
    private TransitionPositionListener(View param1View1, View param1View2, int param1Int1, int param1Int2, float param1Float1, float param1Float2) {
      this.mMovingView = param1View1;
      this.mViewInHierarchy = param1View2;
      this.mStartX = param1Int1 - Math.round(param1View1.getTranslationX());
      this.mStartY = param1Int2 - Math.round(param1View1.getTranslationY());
      this.mTerminalX = param1Float1;
      this.mTerminalY = param1Float2;
      param1Int1 = R.id.transition_position;
      int[] arrayOfInt = (int[])param1View2.getTag(param1Int1);
      this.mTransitionPosition = arrayOfInt;
      if (arrayOfInt != null)
        param1View2.setTag(param1Int1, null); 
    }
    
    public void onAnimationCancel(Animator param1Animator) {
      if (this.mTransitionPosition == null)
        this.mTransitionPosition = new int[2]; 
      this.mTransitionPosition[0] = Math.round(this.mStartX + this.mMovingView.getTranslationX());
      this.mTransitionPosition[1] = Math.round(this.mStartY + this.mMovingView.getTranslationY());
      this.mViewInHierarchy.setTag(R.id.transition_position, this.mTransitionPosition);
    }
    
    public void onAnimationEnd(Animator param1Animator) {
      this.mMovingView.setTranslationX(this.mTerminalX);
      this.mMovingView.setTranslationY(this.mTerminalY);
    }
    
    public void onAnimationPause(Animator param1Animator) {
      this.mPausedX = this.mMovingView.getTranslationX();
      this.mPausedY = this.mMovingView.getTranslationY();
      this.mMovingView.setTranslationX(this.mTerminalX);
      this.mMovingView.setTranslationY(this.mTerminalY);
    }
    
    public void onAnimationResume(Animator param1Animator) {
      this.mMovingView.setTranslationX(this.mPausedX);
      this.mMovingView.setTranslationY(this.mPausedY);
    }
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/android/support/transition/TranslationAnimationCreator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */