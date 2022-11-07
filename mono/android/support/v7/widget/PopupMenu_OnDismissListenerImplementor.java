package mono.android.support.v7.widget;

import android.support.v7.widget.PopupMenu;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class PopupMenu_OnDismissListenerImplementor implements IGCUserPeer, PopupMenu.OnDismissListener {
  public static final String __md_methods = "n_onDismiss:(Landroid/support/v7/widget/PopupMenu;)V:GetOnDismiss_Landroid_support_v7_widget_PopupMenu_Handler:Android.Support.V7.Widget.PopupMenu/IOnDismissListenerInvoker, Xamarin.Android.Support.v7.AppCompat\n";
  
  private ArrayList refList;
  
  static {
    Runtime.register("Android.Support.V7.Widget.PopupMenu+IOnDismissListenerImplementor, Xamarin.Android.Support.v7.AppCompat", PopupMenu_OnDismissListenerImplementor.class, "n_onDismiss:(Landroid/support/v7/widget/PopupMenu;)V:GetOnDismiss_Landroid_support_v7_widget_PopupMenu_Handler:Android.Support.V7.Widget.PopupMenu/IOnDismissListenerInvoker, Xamarin.Android.Support.v7.AppCompat\n");
  }
  
  public PopupMenu_OnDismissListenerImplementor() {
    if (getClass() == PopupMenu_OnDismissListenerImplementor.class)
      TypeManager.Activate("Android.Support.V7.Widget.PopupMenu+IOnDismissListenerImplementor, Xamarin.Android.Support.v7.AppCompat", "", this, new Object[0]); 
  }
  
  private native void n_onDismiss(PopupMenu paramPopupMenu);
  
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
  
  public void onDismiss(PopupMenu paramPopupMenu) {
    n_onDismiss(paramPopupMenu);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/mono/android/support/v7/widget/PopupMenu_OnDismissListenerImplementor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */