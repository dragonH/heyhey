package android.support.design.internal;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo;
import android.support.transition.Transition;
import android.support.transition.TransitionValues;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.Map;

@RequiresApi(14)
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public class TextScale extends Transition {
  private static final String PROPNAME_SCALE = "android:textscale:scale";
  
  private void captureValues(TransitionValues paramTransitionValues) {
    View view = paramTransitionValues.view;
    if (view instanceof TextView) {
      TextView textView = (TextView)view;
      paramTransitionValues.values.put("android:textscale:scale", Float.valueOf(textView.getScaleX()));
    } 
  }
  
  public void captureEndValues(TransitionValues paramTransitionValues) {
    captureValues(paramTransitionValues);
  }
  
  public void captureStartValues(TransitionValues paramTransitionValues) {
    captureValues(paramTransitionValues);
  }
  
  public Animator createAnimator(ViewGroup paramViewGroup, TransitionValues paramTransitionValues1, TransitionValues paramTransitionValues2) {
    ValueAnimator valueAnimator;
    ViewGroup viewGroup = null;
    paramViewGroup = viewGroup;
    if (paramTransitionValues1 != null) {
      paramViewGroup = viewGroup;
      if (paramTransitionValues2 != null) {
        paramViewGroup = viewGroup;
        if (paramTransitionValues1.view instanceof TextView) {
          ViewGroup viewGroup1;
          View view = paramTransitionValues2.view;
          if (!(view instanceof TextView)) {
            viewGroup1 = viewGroup;
          } else {
            float f2;
            final TextView view = (TextView)viewGroup1;
            Map map1 = paramTransitionValues1.values;
            Map map2 = paramTransitionValues2.values;
            paramTransitionValues2 = (TransitionValues)map1.get("android:textscale:scale");
            float f1 = 1.0F;
            if (paramTransitionValues2 != null) {
              f2 = ((Float)map1.get("android:textscale:scale")).floatValue();
            } else {
              f2 = 1.0F;
            } 
            if (map2.get("android:textscale:scale") != null)
              f1 = ((Float)map2.get("android:textscale:scale")).floatValue(); 
            if (f2 == f1)
              return null; 
            valueAnimator = ValueAnimator.ofFloat(new float[] { f2, f1 });
            valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                  public void onAnimationUpdate(ValueAnimator param1ValueAnimator) {
                    float f = ((Float)param1ValueAnimator.getAnimatedValue()).floatValue();
                    view.setScaleX(f);
                    view.setScaleY(f);
                  }
                });
          } 
        } 
      } 
    } 
    return (Animator)valueAnimator;
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/android/support/design/internal/TextScale.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */