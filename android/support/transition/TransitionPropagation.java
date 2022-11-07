package android.support.transition;

import android.view.ViewGroup;

public abstract class TransitionPropagation {
  public abstract void captureValues(TransitionValues paramTransitionValues);
  
  public abstract String[] getPropagationProperties();
  
  public abstract long getStartDelay(ViewGroup paramViewGroup, Transition paramTransition, TransitionValues paramTransitionValues1, TransitionValues paramTransitionValues2);
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/android/support/transition/TransitionPropagation.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */