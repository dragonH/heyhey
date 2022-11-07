package mono.android.support.transition;

import android.support.transition.Transition;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class Transition_TransitionListenerImplementor implements IGCUserPeer, Transition.TransitionListener {
  public static final String __md_methods = "n_onTransitionCancel:(Landroid/support/transition/Transition;)V:GetOnTransitionCancel_Landroid_support_transition_Transition_Handler:Android.Support.Transitions.Transition/ITransitionListenerInvoker, Xamarin.Android.Support.Transition\nn_onTransitionEnd:(Landroid/support/transition/Transition;)V:GetOnTransitionEnd_Landroid_support_transition_Transition_Handler:Android.Support.Transitions.Transition/ITransitionListenerInvoker, Xamarin.Android.Support.Transition\nn_onTransitionPause:(Landroid/support/transition/Transition;)V:GetOnTransitionPause_Landroid_support_transition_Transition_Handler:Android.Support.Transitions.Transition/ITransitionListenerInvoker, Xamarin.Android.Support.Transition\nn_onTransitionResume:(Landroid/support/transition/Transition;)V:GetOnTransitionResume_Landroid_support_transition_Transition_Handler:Android.Support.Transitions.Transition/ITransitionListenerInvoker, Xamarin.Android.Support.Transition\nn_onTransitionStart:(Landroid/support/transition/Transition;)V:GetOnTransitionStart_Landroid_support_transition_Transition_Handler:Android.Support.Transitions.Transition/ITransitionListenerInvoker, Xamarin.Android.Support.Transition\n";
  
  private ArrayList refList;
  
  static {
    Runtime.register("Android.Support.Transitions.Transition+ITransitionListenerImplementor, Xamarin.Android.Support.Transition", Transition_TransitionListenerImplementor.class, "n_onTransitionCancel:(Landroid/support/transition/Transition;)V:GetOnTransitionCancel_Landroid_support_transition_Transition_Handler:Android.Support.Transitions.Transition/ITransitionListenerInvoker, Xamarin.Android.Support.Transition\nn_onTransitionEnd:(Landroid/support/transition/Transition;)V:GetOnTransitionEnd_Landroid_support_transition_Transition_Handler:Android.Support.Transitions.Transition/ITransitionListenerInvoker, Xamarin.Android.Support.Transition\nn_onTransitionPause:(Landroid/support/transition/Transition;)V:GetOnTransitionPause_Landroid_support_transition_Transition_Handler:Android.Support.Transitions.Transition/ITransitionListenerInvoker, Xamarin.Android.Support.Transition\nn_onTransitionResume:(Landroid/support/transition/Transition;)V:GetOnTransitionResume_Landroid_support_transition_Transition_Handler:Android.Support.Transitions.Transition/ITransitionListenerInvoker, Xamarin.Android.Support.Transition\nn_onTransitionStart:(Landroid/support/transition/Transition;)V:GetOnTransitionStart_Landroid_support_transition_Transition_Handler:Android.Support.Transitions.Transition/ITransitionListenerInvoker, Xamarin.Android.Support.Transition\n");
  }
  
  public Transition_TransitionListenerImplementor() {
    if (getClass() == Transition_TransitionListenerImplementor.class)
      TypeManager.Activate("Android.Support.Transitions.Transition+ITransitionListenerImplementor, Xamarin.Android.Support.Transition", "", this, new Object[0]); 
  }
  
  private native void n_onTransitionCancel(Transition paramTransition);
  
  private native void n_onTransitionEnd(Transition paramTransition);
  
  private native void n_onTransitionPause(Transition paramTransition);
  
  private native void n_onTransitionResume(Transition paramTransition);
  
  private native void n_onTransitionStart(Transition paramTransition);
  
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
  
  public void onTransitionCancel(Transition paramTransition) {
    n_onTransitionCancel(paramTransition);
  }
  
  public void onTransitionEnd(Transition paramTransition) {
    n_onTransitionEnd(paramTransition);
  }
  
  public void onTransitionPause(Transition paramTransition) {
    n_onTransitionPause(paramTransition);
  }
  
  public void onTransitionResume(Transition paramTransition) {
    n_onTransitionResume(paramTransition);
  }
  
  public void onTransitionStart(Transition paramTransition) {
    n_onTransitionStart(paramTransition);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/mono/android/support/transition/Transition_TransitionListenerImplementor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */