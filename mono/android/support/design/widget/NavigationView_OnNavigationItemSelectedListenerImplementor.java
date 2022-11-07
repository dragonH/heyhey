package mono.android.support.design.widget;

import android.support.design.widget.NavigationView;
import android.view.MenuItem;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class NavigationView_OnNavigationItemSelectedListenerImplementor implements IGCUserPeer, NavigationView.OnNavigationItemSelectedListener {
  public static final String __md_methods = "n_onNavigationItemSelected:(Landroid/view/MenuItem;)Z:GetOnNavigationItemSelected_Landroid_view_MenuItem_Handler:Android.Support.Design.Widget.NavigationView/IOnNavigationItemSelectedListenerInvoker, Xamarin.Android.Support.Design\n";
  
  private ArrayList refList;
  
  static {
    Runtime.register("Android.Support.Design.Widget.NavigationView+IOnNavigationItemSelectedListenerImplementor, Xamarin.Android.Support.Design", NavigationView_OnNavigationItemSelectedListenerImplementor.class, "n_onNavigationItemSelected:(Landroid/view/MenuItem;)Z:GetOnNavigationItemSelected_Landroid_view_MenuItem_Handler:Android.Support.Design.Widget.NavigationView/IOnNavigationItemSelectedListenerInvoker, Xamarin.Android.Support.Design\n");
  }
  
  public NavigationView_OnNavigationItemSelectedListenerImplementor() {
    if (getClass() == NavigationView_OnNavigationItemSelectedListenerImplementor.class)
      TypeManager.Activate("Android.Support.Design.Widget.NavigationView+IOnNavigationItemSelectedListenerImplementor, Xamarin.Android.Support.Design", "", this, new Object[0]); 
  }
  
  private native boolean n_onNavigationItemSelected(MenuItem paramMenuItem);
  
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
  
  public boolean onNavigationItemSelected(MenuItem paramMenuItem) {
    return n_onNavigationItemSelected(paramMenuItem);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/mono/android/support/design/widget/NavigationView_OnNavigationItemSelectedListenerImplementor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */