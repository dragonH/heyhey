package crc643f46942d9dd1fff9;

import android.view.View;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class VisualElementTracker_AttachTracker implements IGCUserPeer, View.OnAttachStateChangeListener {
  public static final String __md_methods = "n_onViewAttachedToWindow:(Landroid/view/View;)V:GetOnViewAttachedToWindow_Landroid_view_View_Handler:Android.Views.View/IOnAttachStateChangeListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\nn_onViewDetachedFromWindow:(Landroid/view/View;)V:GetOnViewDetachedFromWindow_Landroid_view_View_Handler:Android.Views.View/IOnAttachStateChangeListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n";
  
  private ArrayList refList;
  
  static {
    Runtime.register("Xamarin.Forms.Platform.Android.VisualElementTracker+AttachTracker, Xamarin.Forms.Platform.Android", VisualElementTracker_AttachTracker.class, "n_onViewAttachedToWindow:(Landroid/view/View;)V:GetOnViewAttachedToWindow_Landroid_view_View_Handler:Android.Views.View/IOnAttachStateChangeListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\nn_onViewDetachedFromWindow:(Landroid/view/View;)V:GetOnViewDetachedFromWindow_Landroid_view_View_Handler:Android.Views.View/IOnAttachStateChangeListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n");
  }
  
  public VisualElementTracker_AttachTracker() {
    if (getClass() == VisualElementTracker_AttachTracker.class)
      TypeManager.Activate("Xamarin.Forms.Platform.Android.VisualElementTracker+AttachTracker, Xamarin.Forms.Platform.Android", "", this, new Object[0]); 
  }
  
  private native void n_onViewAttachedToWindow(View paramView);
  
  private native void n_onViewDetachedFromWindow(View paramView);
  
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
  
  public void onViewAttachedToWindow(View paramView) {
    n_onViewAttachedToWindow(paramView);
  }
  
  public void onViewDetachedFromWindow(View paramView) {
    n_onViewDetachedFromWindow(paramView);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/crc643f46942d9dd1fff9/VisualElementTracker_AttachTracker.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */