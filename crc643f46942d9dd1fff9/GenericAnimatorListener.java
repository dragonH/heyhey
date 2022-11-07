package crc643f46942d9dd1fff9;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class GenericAnimatorListener extends AnimatorListenerAdapter implements IGCUserPeer {
  public static final String __md_methods = "n_onAnimationCancel:(Landroid/animation/Animator;)V:GetOnAnimationCancel_Landroid_animation_Animator_Handler\nn_onAnimationEnd:(Landroid/animation/Animator;)V:GetOnAnimationEnd_Landroid_animation_Animator_Handler\nn_onAnimationRepeat:(Landroid/animation/Animator;)V:GetOnAnimationRepeat_Landroid_animation_Animator_Handler\nn_finalize:()V:GetJavaFinalizeHandler\n";
  
  private ArrayList refList;
  
  static {
    Runtime.register("Xamarin.Forms.Platform.Android.GenericAnimatorListener, Xamarin.Forms.Platform.Android", GenericAnimatorListener.class, "n_onAnimationCancel:(Landroid/animation/Animator;)V:GetOnAnimationCancel_Landroid_animation_Animator_Handler\nn_onAnimationEnd:(Landroid/animation/Animator;)V:GetOnAnimationEnd_Landroid_animation_Animator_Handler\nn_onAnimationRepeat:(Landroid/animation/Animator;)V:GetOnAnimationRepeat_Landroid_animation_Animator_Handler\nn_finalize:()V:GetJavaFinalizeHandler\n");
  }
  
  public GenericAnimatorListener() {
    if (getClass() == GenericAnimatorListener.class)
      TypeManager.Activate("Xamarin.Forms.Platform.Android.GenericAnimatorListener, Xamarin.Forms.Platform.Android", "", this, new Object[0]); 
  }
  
  private native void n_finalize();
  
  private native void n_onAnimationCancel(Animator paramAnimator);
  
  private native void n_onAnimationEnd(Animator paramAnimator);
  
  private native void n_onAnimationRepeat(Animator paramAnimator);
  
  public void finalize() {
    n_finalize();
  }
  
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
  
  public void onAnimationCancel(Animator paramAnimator) {
    n_onAnimationCancel(paramAnimator);
  }
  
  public void onAnimationEnd(Animator paramAnimator) {
    n_onAnimationEnd(paramAnimator);
  }
  
  public void onAnimationRepeat(Animator paramAnimator) {
    n_onAnimationRepeat(paramAnimator);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/crc643f46942d9dd1fff9/GenericAnimatorListener.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */