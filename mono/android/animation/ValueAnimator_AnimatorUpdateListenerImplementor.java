package mono.android.animation;

import android.animation.ValueAnimator;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class ValueAnimator_AnimatorUpdateListenerImplementor implements IGCUserPeer, ValueAnimator.AnimatorUpdateListener {
  public static final String __md_methods = "n_onAnimationUpdate:(Landroid/animation/ValueAnimator;)V:GetOnAnimationUpdate_Landroid_animation_ValueAnimator_Handler:Android.Animation.ValueAnimator/IAnimatorUpdateListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n";
  
  private ArrayList refList;
  
  static {
    Runtime.register("Android.Animation.ValueAnimator+IAnimatorUpdateListenerImplementor, Mono.Android", ValueAnimator_AnimatorUpdateListenerImplementor.class, "n_onAnimationUpdate:(Landroid/animation/ValueAnimator;)V:GetOnAnimationUpdate_Landroid_animation_ValueAnimator_Handler:Android.Animation.ValueAnimator/IAnimatorUpdateListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n");
  }
  
  public ValueAnimator_AnimatorUpdateListenerImplementor() {
    if (getClass() == ValueAnimator_AnimatorUpdateListenerImplementor.class)
      TypeManager.Activate("Android.Animation.ValueAnimator+IAnimatorUpdateListenerImplementor, Mono.Android", "", this, new Object[0]); 
  }
  
  private native void n_onAnimationUpdate(ValueAnimator paramValueAnimator);
  
  public void monodroidAddReference(Object paramObject) {
    if (this.refList == null)
      this.refList = new ArrayList(); 
    this.refList.add(paramObject);
  }
  
  public void monodroidClearReferences() {
    ArrayList arrayList = this.refList;
    if (arrayList != null)
      arrayList.clear(); 
  }
  
  public void onAnimationUpdate(ValueAnimator paramValueAnimator) {
    n_onAnimationUpdate(paramValueAnimator);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/mono/android/animation/ValueAnimator_AnimatorUpdateListenerImplementor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */