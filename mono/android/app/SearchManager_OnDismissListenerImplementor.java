package mono.android.app;

import android.app.SearchManager;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class SearchManager_OnDismissListenerImplementor implements IGCUserPeer, SearchManager.OnDismissListener {
  public static final String __md_methods = "n_onDismiss:()V:GetOnDismissHandler:Android.App.SearchManager/IOnDismissListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n";
  
  private ArrayList refList;
  
  static {
    Runtime.register("Android.App.SearchManager+IOnDismissListenerImplementor, Mono.Android", SearchManager_OnDismissListenerImplementor.class, "n_onDismiss:()V:GetOnDismissHandler:Android.App.SearchManager/IOnDismissListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n");
  }
  
  public SearchManager_OnDismissListenerImplementor() {
    if (getClass() == SearchManager_OnDismissListenerImplementor.class)
      TypeManager.Activate("Android.App.SearchManager+IOnDismissListenerImplementor, Mono.Android", "", this, new Object[0]); 
  }
  
  private native void n_onDismiss();
  
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
  
  public void onDismiss() {
    n_onDismiss();
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/mono/android/app/SearchManager_OnDismissListenerImplementor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */