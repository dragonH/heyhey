package mono.android.support.design.widget;

import android.support.design.widget.SwipeDismissBehavior;
import android.view.View;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class SwipeDismissBehavior_OnDismissListenerImplementor implements IGCUserPeer, SwipeDismissBehavior.OnDismissListener {
  public static final String __md_methods = "n_onDismiss:(Landroid/view/View;)V:GetOnDismiss_Landroid_view_View_Handler:Android.Support.Design.Widget.SwipeDismissBehavior/IOnDismissListenerInvoker, Xamarin.Android.Support.Design\nn_onDragStateChanged:(I)V:GetOnDragStateChanged_IHandler:Android.Support.Design.Widget.SwipeDismissBehavior/IOnDismissListenerInvoker, Xamarin.Android.Support.Design\n";
  
  private ArrayList refList;
  
  static {
    Runtime.register("Android.Support.Design.Widget.SwipeDismissBehavior+IOnDismissListenerImplementor, Xamarin.Android.Support.Design", SwipeDismissBehavior_OnDismissListenerImplementor.class, "n_onDismiss:(Landroid/view/View;)V:GetOnDismiss_Landroid_view_View_Handler:Android.Support.Design.Widget.SwipeDismissBehavior/IOnDismissListenerInvoker, Xamarin.Android.Support.Design\nn_onDragStateChanged:(I)V:GetOnDragStateChanged_IHandler:Android.Support.Design.Widget.SwipeDismissBehavior/IOnDismissListenerInvoker, Xamarin.Android.Support.Design\n");
  }
  
  public SwipeDismissBehavior_OnDismissListenerImplementor() {
    if (getClass() == SwipeDismissBehavior_OnDismissListenerImplementor.class)
      TypeManager.Activate("Android.Support.Design.Widget.SwipeDismissBehavior+IOnDismissListenerImplementor, Xamarin.Android.Support.Design", "", this, new Object[0]); 
  }
  
  private native void n_onDismiss(View paramView);
  
  private native void n_onDragStateChanged(int paramInt);
  
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
  
  public void onDismiss(View paramView) {
    n_onDismiss(paramView);
  }
  
  public void onDragStateChanged(int paramInt) {
    n_onDragStateChanged(paramInt);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/mono/android/support/design/widget/SwipeDismissBehavior_OnDismissListenerImplementor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */