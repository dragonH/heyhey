package mono.android.support.v7.widget;

import android.support.v7.widget.SearchView;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class SearchView_OnCloseListenerImplementor implements IGCUserPeer, SearchView.OnCloseListener {
  public static final String __md_methods = "n_onClose:()Z:GetOnCloseHandler:Android.Support.V7.Widget.SearchView/IOnCloseListenerInvoker, Xamarin.Android.Support.v7.AppCompat\n";
  
  private ArrayList refList;
  
  static {
    Runtime.register("Android.Support.V7.Widget.SearchView+IOnCloseListenerImplementor, Xamarin.Android.Support.v7.AppCompat", SearchView_OnCloseListenerImplementor.class, "n_onClose:()Z:GetOnCloseHandler:Android.Support.V7.Widget.SearchView/IOnCloseListenerInvoker, Xamarin.Android.Support.v7.AppCompat\n");
  }
  
  public SearchView_OnCloseListenerImplementor() {
    if (getClass() == SearchView_OnCloseListenerImplementor.class)
      TypeManager.Activate("Android.Support.V7.Widget.SearchView+IOnCloseListenerImplementor, Xamarin.Android.Support.v7.AppCompat", "", this, new Object[0]); 
  }
  
  private native boolean n_onClose();
  
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
  
  public boolean onClose() {
    return n_onClose();
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/mono/android/support/v7/widget/SearchView_OnCloseListenerImplementor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */