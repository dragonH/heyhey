package mono.android.support.v7.widget;

import android.support.v7.widget.PopupMenu;
import android.view.MenuItem;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class PopupMenu_OnMenuItemClickListenerImplementor implements IGCUserPeer, PopupMenu.OnMenuItemClickListener {
  public static final String __md_methods = "n_onMenuItemClick:(Landroid/view/MenuItem;)Z:GetOnMenuItemClick_Landroid_view_MenuItem_Handler:Android.Support.V7.Widget.PopupMenu/IOnMenuItemClickListenerInvoker, Xamarin.Android.Support.v7.AppCompat\n";
  
  private ArrayList refList;
  
  static {
    Runtime.register("Android.Support.V7.Widget.PopupMenu+IOnMenuItemClickListenerImplementor, Xamarin.Android.Support.v7.AppCompat", PopupMenu_OnMenuItemClickListenerImplementor.class, "n_onMenuItemClick:(Landroid/view/MenuItem;)Z:GetOnMenuItemClick_Landroid_view_MenuItem_Handler:Android.Support.V7.Widget.PopupMenu/IOnMenuItemClickListenerInvoker, Xamarin.Android.Support.v7.AppCompat\n");
  }
  
  public PopupMenu_OnMenuItemClickListenerImplementor() {
    if (getClass() == PopupMenu_OnMenuItemClickListenerImplementor.class)
      TypeManager.Activate("Android.Support.V7.Widget.PopupMenu+IOnMenuItemClickListenerImplementor, Xamarin.Android.Support.v7.AppCompat", "", this, new Object[0]); 
  }
  
  private native boolean n_onMenuItemClick(MenuItem paramMenuItem);
  
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
  
  public boolean onMenuItemClick(MenuItem paramMenuItem) {
    return n_onMenuItemClick(paramMenuItem);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/mono/android/support/v7/widget/PopupMenu_OnMenuItemClickListenerImplementor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */