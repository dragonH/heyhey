package mono.android.app;

import android.app.SearchManager;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class SearchManager_OnCancelListenerImplementor implements IGCUserPeer, SearchManager.OnCancelListener {
  public static final String __md_methods = "n_onCancel:()V:GetOnCancelHandler:Android.App.SearchManager/IOnCancelListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n";
  
  private ArrayList refList;
  
  static {
    Runtime.register("Android.App.SearchManager+IOnCancelListenerImplementor, Mono.Android", SearchManager_OnCancelListenerImplementor.class, "n_onCancel:()V:GetOnCancelHandler:Android.App.SearchManager/IOnCancelListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n");
  }
  
  public SearchManager_OnCancelListenerImplementor() {
    if (getClass() == SearchManager_OnCancelListenerImplementor.class)
      TypeManager.Activate("Android.App.SearchManager+IOnCancelListenerImplementor, Mono.Android", "", this, new Object[0]); 
  }
  
  private native void n_onCancel();
  
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
  
  public void onCancel() {
    n_onCancel();
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/mono/android/app/SearchManager_OnCancelListenerImplementor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */