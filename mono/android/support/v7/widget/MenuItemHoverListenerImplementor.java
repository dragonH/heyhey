package mono.android.support.v7.widget;

import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.widget.MenuItemHoverListener;
import android.view.MenuItem;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class MenuItemHoverListenerImplementor implements IGCUserPeer, MenuItemHoverListener {
  public static final String __md_methods = "n_onItemHoverEnter:(Landroid/support/v7/view/menu/MenuBuilder;Landroid/view/MenuItem;)V:GetOnItemHoverEnter_Landroid_support_v7_view_menu_MenuBuilder_Landroid_view_MenuItem_Handler:Android.Support.V7.Widget.IMenuItemHoverListenerInvoker, Xamarin.Android.Support.v7.AppCompat\nn_onItemHoverExit:(Landroid/support/v7/view/menu/MenuBuilder;Landroid/view/MenuItem;)V:GetOnItemHoverExit_Landroid_support_v7_view_menu_MenuBuilder_Landroid_view_MenuItem_Handler:Android.Support.V7.Widget.IMenuItemHoverListenerInvoker, Xamarin.Android.Support.v7.AppCompat\n";
  
  private ArrayList refList;
  
  static {
    Runtime.register("Android.Support.V7.Widget.IMenuItemHoverListenerImplementor, Xamarin.Android.Support.v7.AppCompat", MenuItemHoverListenerImplementor.class, "n_onItemHoverEnter:(Landroid/support/v7/view/menu/MenuBuilder;Landroid/view/MenuItem;)V:GetOnItemHoverEnter_Landroid_support_v7_view_menu_MenuBuilder_Landroid_view_MenuItem_Handler:Android.Support.V7.Widget.IMenuItemHoverListenerInvoker, Xamarin.Android.Support.v7.AppCompat\nn_onItemHoverExit:(Landroid/support/v7/view/menu/MenuBuilder;Landroid/view/MenuItem;)V:GetOnItemHoverExit_Landroid_support_v7_view_menu_MenuBuilder_Landroid_view_MenuItem_Handler:Android.Support.V7.Widget.IMenuItemHoverListenerInvoker, Xamarin.Android.Support.v7.AppCompat\n");
  }
  
  public MenuItemHoverListenerImplementor() {
    if (getClass() == MenuItemHoverListenerImplementor.class)
      TypeManager.Activate("Android.Support.V7.Widget.IMenuItemHoverListenerImplementor, Xamarin.Android.Support.v7.AppCompat", "", this, new Object[0]); 
  }
  
  private native void n_onItemHoverEnter(MenuBuilder paramMenuBuilder, MenuItem paramMenuItem);
  
  private native void n_onItemHoverExit(MenuBuilder paramMenuBuilder, MenuItem paramMenuItem);
  
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
  
  public void onItemHoverEnter(MenuBuilder paramMenuBuilder, MenuItem paramMenuItem) {
    n_onItemHoverEnter(paramMenuBuilder, paramMenuItem);
  }
  
  public void onItemHoverExit(MenuBuilder paramMenuBuilder, MenuItem paramMenuItem) {
    n_onItemHoverExit(paramMenuBuilder, paramMenuItem);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/mono/android/support/v7/widget/MenuItemHoverListenerImplementor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */