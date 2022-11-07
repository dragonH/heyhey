package mono.android.net;

import android.net.ConnectivityManager;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class ConnectivityManager_OnNetworkActiveListenerImplementor implements IGCUserPeer, ConnectivityManager.OnNetworkActiveListener {
  public static final String __md_methods = "n_onNetworkActive:()V:GetOnNetworkActiveHandler:Android.Net.ConnectivityManager/IOnNetworkActiveListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n";
  
  private ArrayList refList;
  
  static {
    Runtime.register("Android.Net.ConnectivityManager+IOnNetworkActiveListenerImplementor, Mono.Android", ConnectivityManager_OnNetworkActiveListenerImplementor.class, "n_onNetworkActive:()V:GetOnNetworkActiveHandler:Android.Net.ConnectivityManager/IOnNetworkActiveListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n");
  }
  
  public ConnectivityManager_OnNetworkActiveListenerImplementor() {
    if (getClass() == ConnectivityManager_OnNetworkActiveListenerImplementor.class)
      TypeManager.Activate("Android.Net.ConnectivityManager+IOnNetworkActiveListenerImplementor, Mono.Android", "", this, new Object[0]); 
  }
  
  private native void n_onNetworkActive();
  
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
  
  public void onNetworkActive() {
    n_onNetworkActive();
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/mono/android/net/ConnectivityManager_OnNetworkActiveListenerImplementor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */