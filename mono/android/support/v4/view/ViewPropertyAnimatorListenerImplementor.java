package mono.android.support.v4.view;

import android.support.v4.view.ViewPropertyAnimatorListener;
import android.view.View;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class ViewPropertyAnimatorListenerImplementor implements IGCUserPeer, ViewPropertyAnimatorListener {
  public static final String __md_methods = "n_onAnimationCancel:(Landroid/view/View;)V:GetOnAnimationCancel_Landroid_view_View_Handler:Android.Support.V4.View.IViewPropertyAnimatorListenerInvoker, Xamarin.Android.Support.Compat\nn_onAnimationEnd:(Landroid/view/View;)V:GetOnAnimationEnd_Landroid_view_View_Handler:Android.Support.V4.View.IViewPropertyAnimatorListenerInvoker, Xamarin.Android.Support.Compat\nn_onAnimationStart:(Landroid/view/View;)V:GetOnAnimationStart_Landroid_view_View_Handler:Android.Support.V4.View.IViewPropertyAnimatorListenerInvoker, Xamarin.Android.Support.Compat\n";
  
  private ArrayList refList;
  
  static {
    Runtime.register("Android.Support.V4.View.IViewPropertyAnimatorListenerImplementor, Xamarin.Android.Support.Compat", ViewPropertyAnimatorListenerImplementor.class, "n_onAnimationCancel:(Landroid/view/View;)V:GetOnAnimationCancel_Landroid_view_View_Handler:Android.Support.V4.View.IViewPropertyAnimatorListenerInvoker, Xamarin.Android.Support.Compat\nn_onAnimationEnd:(Landroid/view/View;)V:GetOnAnimationEnd_Landroid_view_View_Handler:Android.Support.V4.View.IViewPropertyAnimatorListenerInvoker, Xamarin.Android.Support.Compat\nn_onAnimationStart:(Landroid/view/View;)V:GetOnAnimationStart_Landroid_view_View_Handler:Android.Support.V4.View.IViewPropertyAnimatorListenerInvoker, Xamarin.Android.Support.Compat\n");
  }
  
  public ViewPropertyAnimatorListenerImplementor() {
    if (getClass() == ViewPropertyAnimatorListenerImplementor.class)
      TypeManager.Activate("Android.Support.V4.View.IViewPropertyAnimatorListenerImplementor, Xamarin.Android.Support.Compat", "", this, new Object[0]); 
  }
  
  private native void n_onAnimationCancel(View paramView);
  
  private native void n_onAnimationEnd(View paramView);
  
  private native void n_onAnimationStart(View paramView);
  
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
  
  public void onAnimationCancel(View paramView) {
    n_onAnimationCancel(paramView);
  }
  
  public void onAnimationEnd(View paramView) {
    n_onAnimationEnd(paramView);
  }
  
  public void onAnimationStart(View paramView) {
    n_onAnimationStart(paramView);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/mono/android/support/v4/view/ViewPropertyAnimatorListenerImplementor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */