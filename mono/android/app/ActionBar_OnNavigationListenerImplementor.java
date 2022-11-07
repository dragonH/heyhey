package mono.android.app;

import android.app.ActionBar;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class ActionBar_OnNavigationListenerImplementor implements IGCUserPeer, ActionBar.OnNavigationListener {
  public static final String __md_methods = "n_onNavigationItemSelected:(IJ)Z:GetOnNavigationItemSelected_IJHandler:Android.App.ActionBar/IOnNavigationListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n";
  
  private ArrayList refList;
  
  static {
    Runtime.register("Android.App.ActionBar+IOnNavigationListenerImplementor, Mono.Android", ActionBar_OnNavigationListenerImplementor.class, "n_onNavigationItemSelected:(IJ)Z:GetOnNavigationItemSelected_IJHandler:Android.App.ActionBar/IOnNavigationListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n");
  }
  
  public ActionBar_OnNavigationListenerImplementor() {
    if (getClass() == ActionBar_OnNavigationListenerImplementor.class)
      TypeManager.Activate("Android.App.ActionBar+IOnNavigationListenerImplementor, Mono.Android", "", this, new Object[0]); 
  }
  
  private native boolean n_onNavigationItemSelected(int paramInt, long paramLong);
  
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
  
  public boolean onNavigationItemSelected(int paramInt, long paramLong) {
    return n_onNavigationItemSelected(paramInt, paramLong);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/mono/android/app/ActionBar_OnNavigationListenerImplementor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */