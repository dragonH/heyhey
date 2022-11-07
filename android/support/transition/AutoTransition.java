package android.support.transition;

import android.content.Context;
import android.util.AttributeSet;

public class AutoTransition extends TransitionSet {
  public AutoTransition() {
    init();
  }
  
  public AutoTransition(Context paramContext, AttributeSet paramAttributeSet) {
    super(paramContext, paramAttributeSet);
    init();
  }
  
  private void init() {
    setOrdering(1);
    addTransition(new Fade(2)).addTransition(new ChangeBounds()).addTransition(new Fade(1));
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/android/support/transition/AutoTransition.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */