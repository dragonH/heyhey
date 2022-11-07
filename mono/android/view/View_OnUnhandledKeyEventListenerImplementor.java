package mono.android.view;

import android.view.KeyEvent;
import android.view.View;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class View_OnUnhandledKeyEventListenerImplementor implements IGCUserPeer, View.OnUnhandledKeyEventListener {
  public static final String __md_methods = "n_onUnhandledKeyEvent:(Landroid/view/View;Landroid/view/KeyEvent;)Z:GetOnUnhandledKeyEvent_Landroid_view_View_Landroid_view_KeyEvent_Handler:Android.Views.View/IOnUnhandledKeyEventListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n";
  
  private ArrayList refList;
  
  static {
    Runtime.register("Android.Views.View+IOnUnhandledKeyEventListenerImplementor, Mono.Android", View_OnUnhandledKeyEventListenerImplementor.class, "n_onUnhandledKeyEvent:(Landroid/view/View;Landroid/view/KeyEvent;)Z:GetOnUnhandledKeyEvent_Landroid_view_View_Landroid_view_KeyEvent_Handler:Android.Views.View/IOnUnhandledKeyEventListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n");
  }
  
  public View_OnUnhandledKeyEventListenerImplementor() {
    if (getClass() == View_OnUnhandledKeyEventListenerImplementor.class)
      TypeManager.Activate("Android.Views.View+IOnUnhandledKeyEventListenerImplementor, Mono.Android", "", this, new Object[0]); 
  }
  
  private native boolean n_onUnhandledKeyEvent(View paramView, KeyEvent paramKeyEvent);
  
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
  
  public boolean onUnhandledKeyEvent(View paramView, KeyEvent paramKeyEvent) {
    return n_onUnhandledKeyEvent(paramView, paramKeyEvent);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/mono/android/view/View_OnUnhandledKeyEventListenerImplementor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */