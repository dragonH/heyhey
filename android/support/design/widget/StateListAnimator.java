package android.support.design.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.util.StateSet;
import java.util.ArrayList;

final class StateListAnimator {
  private final Animator.AnimatorListener mAnimationListener = (Animator.AnimatorListener)new AnimatorListenerAdapter() {
      public void onAnimationEnd(Animator param1Animator) {
        StateListAnimator stateListAnimator = StateListAnimator.this;
        if (stateListAnimator.mRunningAnimator == param1Animator)
          stateListAnimator.mRunningAnimator = null; 
      }
    };
  
  private Tuple mLastMatch = null;
  
  ValueAnimator mRunningAnimator = null;
  
  private final ArrayList<Tuple> mTuples = new ArrayList<Tuple>();
  
  private void cancel() {
    ValueAnimator valueAnimator = this.mRunningAnimator;
    if (valueAnimator != null) {
      valueAnimator.cancel();
      this.mRunningAnimator = null;
    } 
  }
  
  private void start(Tuple paramTuple) {
    ValueAnimator valueAnimator = paramTuple.mAnimator;
    this.mRunningAnimator = valueAnimator;
    valueAnimator.start();
  }
  
  public void addState(int[] paramArrayOfint, ValueAnimator paramValueAnimator) {
    Tuple tuple = new Tuple(paramArrayOfint, paramValueAnimator);
    paramValueAnimator.addListener(this.mAnimationListener);
    this.mTuples.add(tuple);
  }
  
  public void jumpToCurrentState() {
    ValueAnimator valueAnimator = this.mRunningAnimator;
    if (valueAnimator != null) {
      valueAnimator.end();
      this.mRunningAnimator = null;
    } 
  }
  
  void setState(int[] paramArrayOfint) {
    int i = this.mTuples.size();
    byte b = 0;
    while (true) {
      if (b < i) {
        Tuple tuple1 = this.mTuples.get(b);
        if (StateSet.stateSetMatches(tuple1.mSpecs, paramArrayOfint)) {
          Tuple tuple2 = tuple1;
          break;
        } 
        b++;
        continue;
      } 
      paramArrayOfint = null;
      break;
    } 
    Tuple tuple = this.mLastMatch;
    if (paramArrayOfint == tuple)
      return; 
    if (tuple != null)
      cancel(); 
    this.mLastMatch = (Tuple)paramArrayOfint;
    if (paramArrayOfint != null)
      start((Tuple)paramArrayOfint); 
  }
  
  static class Tuple {
    final ValueAnimator mAnimator;
    
    final int[] mSpecs;
    
    Tuple(int[] param1ArrayOfint, ValueAnimator param1ValueAnimator) {
      this.mSpecs = param1ArrayOfint;
      this.mAnimator = param1ValueAnimator;
    }
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/android/support/design/widget/StateListAnimator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */