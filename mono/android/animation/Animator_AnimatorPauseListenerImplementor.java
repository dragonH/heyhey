package mono.android.animation;

import android.animation.Animator;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class Animator_AnimatorPauseListenerImplementor implements IGCUserPeer, Animator.AnimatorPauseListener {
  public static final String __md_methods = "n_onAnimationPause:(Landroid/animation/Animator;)V:GetOnAnimationPause_Landroid_animation_Animator_Handler:Android.Animation.Animator/IAnimatorPauseListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\nn_onAnimationResume:(Landroid/animation/Animator;)V:GetOnAnimationResume_Landroid_animation_Animator_Handler:Android.Animation.Animator/IAnimatorPauseListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n";
  
  private ArrayList refList;
  
  static {
    Runtime.register("Android.Animation.Animator+IAnimatorPauseListenerImplementor, Mono.Android", Animator_AnimatorPauseListenerImplementor.class, "n_onAnimationPause:(Landroid/animation/Animator;)V:GetOnAnimationPause_Landroid_animation_Animator_Handler:Android.Animation.Animator/IAnimatorPauseListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\nn_onAnimationResume:(Landroid/animation/Animator;)V:GetOnAnimationResume_Landroid_animation_Animator_Handler:Android.Animation.Animator/IAnimatorPauseListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n");
  }
  
  public Animator_AnimatorPauseListenerImplementor() {
    if (getClass() == Animator_AnimatorPauseListenerImplementor.class)
      TypeManager.Activate("Android.Animation.Animator+IAnimatorPauseListenerImplementor, Mono.Android", "", this, new Object[0]); 
  }
  
  private native void n_onAnimationPause(Animator paramAnimator);
  
  private native void n_onAnimationResume(Animator paramAnimator);
  
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
  
  public void onAnimationPause(Animator paramAnimator) {
    n_onAnimationPause(paramAnimator);
  }
  
  public void onAnimationResume(Animator paramAnimator) {
    n_onAnimationResume(paramAnimator);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/mono/android/animation/Animator_AnimatorPauseListenerImplementor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */