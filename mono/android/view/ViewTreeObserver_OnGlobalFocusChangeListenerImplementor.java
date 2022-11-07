package mono.android.view;

import android.view.View;
import android.view.ViewTreeObserver;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class ViewTreeObserver_OnGlobalFocusChangeListenerImplementor implements IGCUserPeer, ViewTreeObserver.OnGlobalFocusChangeListener {
  public static final String __md_methods = "n_onGlobalFocusChanged:(Landroid/view/View;Landroid/view/View;)V:GetOnGlobalFocusChanged_Landroid_view_View_Landroid_view_View_Handler:Android.Views.ViewTreeObserver/IOnGlobalFocusChangeListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n";
  
  private ArrayList refList;
  
  static {
    Runtime.register("Android.Views.ViewTreeObserver+IOnGlobalFocusChangeListenerImplementor, Mono.Android", ViewTreeObserver_OnGlobalFocusChangeListenerImplementor.class, "n_onGlobalFocusChanged:(Landroid/view/View;Landroid/view/View;)V:GetOnGlobalFocusChanged_Landroid_view_View_Landroid_view_View_Handler:Android.Views.ViewTreeObserver/IOnGlobalFocusChangeListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n");
  }
  
  public ViewTreeObserver_OnGlobalFocusChangeListenerImplementor() {
    if (getClass() == ViewTreeObserver_OnGlobalFocusChangeListenerImplementor.class)
      TypeManager.Activate("Android.Views.ViewTreeObserver+IOnGlobalFocusChangeListenerImplementor, Mono.Android", "", this, new Object[0]); 
  }
  
  private native void n_onGlobalFocusChanged(View paramView1, View paramView2);
  
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
  
  public void onGlobalFocusChanged(View paramView1, View paramView2) {
    n_onGlobalFocusChanged(paramView1, paramView2);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/mono/android/view/ViewTreeObserver_OnGlobalFocusChangeListenerImplementor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */