package mono.android.support.v4.widget;

import android.support.v4.widget.SearchViewCompat;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class SearchViewCompat_OnCloseListenerImplementor implements IGCUserPeer, SearchViewCompat.OnCloseListener {
  public static final String __md_methods = "n_onClose:()Z:GetOnCloseHandler:Android.Support.V4.Widget.SearchViewCompat/IOnCloseListenerInvoker, Xamarin.Android.Support.Compat\n";
  
  private ArrayList refList;
  
  static {
    Runtime.register("Android.Support.V4.Widget.SearchViewCompat+IOnCloseListenerImplementor, Xamarin.Android.Support.Compat", SearchViewCompat_OnCloseListenerImplementor.class, "n_onClose:()Z:GetOnCloseHandler:Android.Support.V4.Widget.SearchViewCompat/IOnCloseListenerInvoker, Xamarin.Android.Support.Compat\n");
  }
  
  public SearchViewCompat_OnCloseListenerImplementor() {
    if (getClass() == SearchViewCompat_OnCloseListenerImplementor.class)
      TypeManager.Activate("Android.Support.V4.Widget.SearchViewCompat+IOnCloseListenerImplementor, Xamarin.Android.Support.Compat", "", this, new Object[0]); 
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


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/mono/android/support/v4/widget/SearchViewCompat_OnCloseListenerImplementor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */