package mono.android.view;

import android.view.MotionEvent;
import android.view.View;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class View_OnHoverListenerImplementor implements IGCUserPeer, View.OnHoverListener {
  public static final String __md_methods = "n_onHover:(Landroid/view/View;Landroid/view/MotionEvent;)Z:GetOnHover_Landroid_view_View_Landroid_view_MotionEvent_Handler:Android.Views.View/IOnHoverListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n";
  
  private ArrayList refList;
  
  static {
    Runtime.register("Android.Views.View+IOnHoverListenerImplementor, Mono.Android", View_OnHoverListenerImplementor.class, "n_onHover:(Landroid/view/View;Landroid/view/MotionEvent;)Z:GetOnHover_Landroid_view_View_Landroid_view_MotionEvent_Handler:Android.Views.View/IOnHoverListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n");
  }
  
  public View_OnHoverListenerImplementor() {
    if (getClass() == View_OnHoverListenerImplementor.class)
      TypeManager.Activate("Android.Views.View+IOnHoverListenerImplementor, Mono.Android", "", this, new Object[0]); 
  }
  
  private native boolean n_onHover(View paramView, MotionEvent paramMotionEvent);
  
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
  
  public boolean onHover(View paramView, MotionEvent paramMotionEvent) {
    return n_onHover(paramView, paramMotionEvent);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/mono/android/view/View_OnHoverListenerImplementor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */