package mono.android.support.v7.widget;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class RecyclerView_OnChildAttachStateChangeListenerImplementor implements IGCUserPeer, RecyclerView.OnChildAttachStateChangeListener {
  public static final String __md_methods = "n_onChildViewAttachedToWindow:(Landroid/view/View;)V:GetOnChildViewAttachedToWindow_Landroid_view_View_Handler:Android.Support.V7.Widget.RecyclerView/IOnChildAttachStateChangeListenerInvoker, Xamarin.Android.Support.v7.RecyclerView\nn_onChildViewDetachedFromWindow:(Landroid/view/View;)V:GetOnChildViewDetachedFromWindow_Landroid_view_View_Handler:Android.Support.V7.Widget.RecyclerView/IOnChildAttachStateChangeListenerInvoker, Xamarin.Android.Support.v7.RecyclerView\n";
  
  private ArrayList refList;
  
  static {
    Runtime.register("Android.Support.V7.Widget.RecyclerView+IOnChildAttachStateChangeListenerImplementor, Xamarin.Android.Support.v7.RecyclerView", RecyclerView_OnChildAttachStateChangeListenerImplementor.class, "n_onChildViewAttachedToWindow:(Landroid/view/View;)V:GetOnChildViewAttachedToWindow_Landroid_view_View_Handler:Android.Support.V7.Widget.RecyclerView/IOnChildAttachStateChangeListenerInvoker, Xamarin.Android.Support.v7.RecyclerView\nn_onChildViewDetachedFromWindow:(Landroid/view/View;)V:GetOnChildViewDetachedFromWindow_Landroid_view_View_Handler:Android.Support.V7.Widget.RecyclerView/IOnChildAttachStateChangeListenerInvoker, Xamarin.Android.Support.v7.RecyclerView\n");
  }
  
  public RecyclerView_OnChildAttachStateChangeListenerImplementor() {
    if (getClass() == RecyclerView_OnChildAttachStateChangeListenerImplementor.class)
      TypeManager.Activate("Android.Support.V7.Widget.RecyclerView+IOnChildAttachStateChangeListenerImplementor, Xamarin.Android.Support.v7.RecyclerView", "", this, new Object[0]); 
  }
  
  private native void n_onChildViewAttachedToWindow(View paramView);
  
  private native void n_onChildViewDetachedFromWindow(View paramView);
  
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
  
  public void onChildViewAttachedToWindow(View paramView) {
    n_onChildViewAttachedToWindow(paramView);
  }
  
  public void onChildViewDetachedFromWindow(View paramView) {
    n_onChildViewDetachedFromWindow(paramView);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/mono/android/support/v7/widget/RecyclerView_OnChildAttachStateChangeListenerImplementor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */