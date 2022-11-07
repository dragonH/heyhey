package mono.android.support.design.widget;

import android.support.design.widget.BottomNavigationView;
import android.view.MenuItem;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class BottomNavigationView_OnNavigationItemReselectedListenerImplementor implements IGCUserPeer, BottomNavigationView.OnNavigationItemReselectedListener {
  public static final String __md_methods = "n_onNavigationItemReselected:(Landroid/view/MenuItem;)V:GetOnNavigationItemReselected_Landroid_view_MenuItem_Handler:Android.Support.Design.Widget.BottomNavigationView/IOnNavigationItemReselectedListenerInvoker, Xamarin.Android.Support.Design\n";
  
  private ArrayList refList;
  
  static {
    Runtime.register("Android.Support.Design.Widget.BottomNavigationView+IOnNavigationItemReselectedListenerImplementor, Xamarin.Android.Support.Design", BottomNavigationView_OnNavigationItemReselectedListenerImplementor.class, "n_onNavigationItemReselected:(Landroid/view/MenuItem;)V:GetOnNavigationItemReselected_Landroid_view_MenuItem_Handler:Android.Support.Design.Widget.BottomNavigationView/IOnNavigationItemReselectedListenerInvoker, Xamarin.Android.Support.Design\n");
  }
  
  public BottomNavigationView_OnNavigationItemReselectedListenerImplementor() {
    if (getClass() == BottomNavigationView_OnNavigationItemReselectedListenerImplementor.class)
      TypeManager.Activate("Android.Support.Design.Widget.BottomNavigationView+IOnNavigationItemReselectedListenerImplementor, Xamarin.Android.Support.Design", "", this, new Object[0]); 
  }
  
  private native void n_onNavigationItemReselected(MenuItem paramMenuItem);
  
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
  
  public void onNavigationItemReselected(MenuItem paramMenuItem) {
    n_onNavigationItemReselected(paramMenuItem);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/mono/android/support/design/widget/BottomNavigationView_OnNavigationItemReselectedListenerImplementor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */