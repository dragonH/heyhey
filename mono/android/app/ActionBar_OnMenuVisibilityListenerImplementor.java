package mono.android.app;

import android.app.ActionBar;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class ActionBar_OnMenuVisibilityListenerImplementor implements IGCUserPeer, ActionBar.OnMenuVisibilityListener {
  public static final String __md_methods = "n_onMenuVisibilityChanged:(Z)V:GetOnMenuVisibilityChanged_ZHandler:Android.App.ActionBar/IOnMenuVisibilityListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n";
  
  private ArrayList refList;
  
  static {
    Runtime.register("Android.App.ActionBar+IOnMenuVisibilityListenerImplementor, Mono.Android", ActionBar_OnMenuVisibilityListenerImplementor.class, "n_onMenuVisibilityChanged:(Z)V:GetOnMenuVisibilityChanged_ZHandler:Android.App.ActionBar/IOnMenuVisibilityListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n");
  }
  
  public ActionBar_OnMenuVisibilityListenerImplementor() {
    if (getClass() == ActionBar_OnMenuVisibilityListenerImplementor.class)
      TypeManager.Activate("Android.App.ActionBar+IOnMenuVisibilityListenerImplementor, Mono.Android", "", this, new Object[0]); 
  }
  
  private native void n_onMenuVisibilityChanged(boolean paramBoolean);
  
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
  
  public void onMenuVisibilityChanged(boolean paramBoolean) {
    n_onMenuVisibilityChanged(paramBoolean);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/mono/android/app/ActionBar_OnMenuVisibilityListenerImplementor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */