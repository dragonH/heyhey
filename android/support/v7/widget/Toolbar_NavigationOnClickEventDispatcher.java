package android.support.v7.widget;

import android.view.View;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class Toolbar_NavigationOnClickEventDispatcher implements IGCUserPeer, View.OnClickListener {
  public static final String __md_methods = "n_onClick:(Landroid/view/View;)V:GetOnClick_Landroid_view_View_Handler:Android.Views.View/IOnClickListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n";
  
  private ArrayList refList;
  
  static {
    Runtime.register("Android.Support.V7.Widget.Toolbar+NavigationOnClickEventDispatcher, Xamarin.Android.Support.v7.AppCompat", Toolbar_NavigationOnClickEventDispatcher.class, "n_onClick:(Landroid/view/View;)V:GetOnClick_Landroid_view_View_Handler:Android.Views.View/IOnClickListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n");
  }
  
  public Toolbar_NavigationOnClickEventDispatcher() {
    if (getClass() == Toolbar_NavigationOnClickEventDispatcher.class)
      TypeManager.Activate("Android.Support.V7.Widget.Toolbar+NavigationOnClickEventDispatcher, Xamarin.Android.Support.v7.AppCompat", "", this, new Object[0]); 
  }
  
  public Toolbar_NavigationOnClickEventDispatcher(Toolbar paramToolbar) {
    if (getClass() == Toolbar_NavigationOnClickEventDispatcher.class)
      TypeManager.Activate("Android.Support.V7.Widget.Toolbar+NavigationOnClickEventDispatcher, Xamarin.Android.Support.v7.AppCompat", "Android.Support.V7.Widget.Toolbar, Xamarin.Android.Support.v7.AppCompat", this, new Object[] { paramToolbar }); 
  }
  
  private native void n_onClick(View paramView);
  
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
  
  public void onClick(View paramView) {
    n_onClick(paramView);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/android/support/v7/widget/Toolbar_NavigationOnClickEventDispatcher.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */