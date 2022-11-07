package mono.android.support.v4.widget;

import android.support.v4.widget.SwipeRefreshLayout;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class SwipeRefreshLayout_OnRefreshListenerImplementor implements IGCUserPeer, SwipeRefreshLayout.OnRefreshListener {
  public static final String __md_methods = "n_onRefresh:()V:GetOnRefreshHandler:Android.Support.V4.Widget.SwipeRefreshLayout/IOnRefreshListenerInvoker, Xamarin.Android.Support.Core.UI\n";
  
  private ArrayList refList;
  
  static {
    Runtime.register("Android.Support.V4.Widget.SwipeRefreshLayout+IOnRefreshListenerImplementor, Xamarin.Android.Support.Core.UI", SwipeRefreshLayout_OnRefreshListenerImplementor.class, "n_onRefresh:()V:GetOnRefreshHandler:Android.Support.V4.Widget.SwipeRefreshLayout/IOnRefreshListenerInvoker, Xamarin.Android.Support.Core.UI\n");
  }
  
  public SwipeRefreshLayout_OnRefreshListenerImplementor() {
    if (getClass() == SwipeRefreshLayout_OnRefreshListenerImplementor.class)
      TypeManager.Activate("Android.Support.V4.Widget.SwipeRefreshLayout+IOnRefreshListenerImplementor, Xamarin.Android.Support.Core.UI", "", this, new Object[0]); 
  }
  
  private native void n_onRefresh();
  
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
  
  public void onRefresh() {
    n_onRefresh();
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/mono/android/support/v4/widget/SwipeRefreshLayout_OnRefreshListenerImplementor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */