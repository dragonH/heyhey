package mono.com.microsoft.appcenter.utils;

import com.microsoft.appcenter.utils.NetworkStateHelper;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class NetworkStateHelper_ListenerImplementor implements IGCUserPeer, NetworkStateHelper.Listener {
  public static final String __md_methods = "n_onNetworkStateUpdated:(Z)V:GetOnNetworkStateUpdated_ZHandler:Com.Microsoft.Appcenter.Utils.NetworkStateHelper/IListenerInvoker, Microsoft.AppCenter.Android.Bindings\n";
  
  private ArrayList refList;
  
  static {
    Runtime.register("Com.Microsoft.Appcenter.Utils.NetworkStateHelper+IListenerImplementor, Microsoft.AppCenter.Android.Bindings", NetworkStateHelper_ListenerImplementor.class, "n_onNetworkStateUpdated:(Z)V:GetOnNetworkStateUpdated_ZHandler:Com.Microsoft.Appcenter.Utils.NetworkStateHelper/IListenerInvoker, Microsoft.AppCenter.Android.Bindings\n");
  }
  
  public NetworkStateHelper_ListenerImplementor() {
    if (getClass() == NetworkStateHelper_ListenerImplementor.class)
      TypeManager.Activate("Com.Microsoft.Appcenter.Utils.NetworkStateHelper+IListenerImplementor, Microsoft.AppCenter.Android.Bindings", "", this, new Object[0]); 
  }
  
  private native void n_onNetworkStateUpdated(boolean paramBoolean);
  
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
  
  public void onNetworkStateUpdated(boolean paramBoolean) {
    n_onNetworkStateUpdated(paramBoolean);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/mono/com/microsoft/appcenter/utils/NetworkStateHelper_ListenerImplementor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */