package mono.android.widget;

import android.view.MenuItem;
import android.widget.Toolbar;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class Toolbar_OnMenuItemClickListenerImplementor implements IGCUserPeer, Toolbar.OnMenuItemClickListener {
  public static final String __md_methods = "n_onMenuItemClick:(Landroid/view/MenuItem;)Z:GetOnMenuItemClick_Landroid_view_MenuItem_Handler:Android.Widget.Toolbar/IOnMenuItemClickListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n";
  
  private ArrayList refList;
  
  static {
    Runtime.register("Android.Widget.Toolbar+IOnMenuItemClickListenerImplementor, Mono.Android", Toolbar_OnMenuItemClickListenerImplementor.class, "n_onMenuItemClick:(Landroid/view/MenuItem;)Z:GetOnMenuItemClick_Landroid_view_MenuItem_Handler:Android.Widget.Toolbar/IOnMenuItemClickListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n");
  }
  
  public Toolbar_OnMenuItemClickListenerImplementor() {
    if (getClass() == Toolbar_OnMenuItemClickListenerImplementor.class)
      TypeManager.Activate("Android.Widget.Toolbar+IOnMenuItemClickListenerImplementor, Mono.Android", "", this, new Object[0]); 
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


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/mono/android/widget/Toolbar_OnMenuItemClickListenerImplementor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */