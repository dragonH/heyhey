package mono.android.support.v4.view;

import android.support.v4.view.ActionProvider;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class ActionProvider_SubUiVisibilityListenerImplementor implements IGCUserPeer, ActionProvider.SubUiVisibilityListener {
  public static final String __md_methods = "n_onSubUiVisibilityChanged:(Z)V:GetOnSubUiVisibilityChanged_ZHandler:Android.Support.V4.View.ActionProvider/ISubUiVisibilityListenerInvoker, Xamarin.Android.Support.Compat\n";
  
  private ArrayList refList;
  
  static {
    Runtime.register("Android.Support.V4.View.ActionProvider+ISubUiVisibilityListenerImplementor, Xamarin.Android.Support.Compat", ActionProvider_SubUiVisibilityListenerImplementor.class, "n_onSubUiVisibilityChanged:(Z)V:GetOnSubUiVisibilityChanged_ZHandler:Android.Support.V4.View.ActionProvider/ISubUiVisibilityListenerInvoker, Xamarin.Android.Support.Compat\n");
  }
  
  public ActionProvider_SubUiVisibilityListenerImplementor() {
    if (getClass() == ActionProvider_SubUiVisibilityListenerImplementor.class)
      TypeManager.Activate("Android.Support.V4.View.ActionProvider+ISubUiVisibilityListenerImplementor, Xamarin.Android.Support.Compat", "", this, new Object[0]); 
  }
  
  private native void n_onSubUiVisibilityChanged(boolean paramBoolean);
  
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
  
  public void onSubUiVisibilityChanged(boolean paramBoolean) {
    n_onSubUiVisibilityChanged(paramBoolean);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/mono/android/support/v4/view/ActionProvider_SubUiVisibilityListenerImplementor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */