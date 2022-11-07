package mono.android.net.wifi.p2p;

import android.net.wifi.p2p.WifiP2pInfo;
import android.net.wifi.p2p.WifiP2pManager;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class WifiP2pManager_ConnectionInfoListenerImplementor implements IGCUserPeer, WifiP2pManager.ConnectionInfoListener {
  public static final String __md_methods = "n_onConnectionInfoAvailable:(Landroid/net/wifi/p2p/WifiP2pInfo;)V:GetOnConnectionInfoAvailable_Landroid_net_wifi_p2p_WifiP2pInfo_Handler:Android.Net.Wifi.P2p.WifiP2pManager/IConnectionInfoListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n";
  
  private ArrayList refList;
  
  static {
    Runtime.register("Android.Net.Wifi.P2p.WifiP2pManager+IConnectionInfoListenerImplementor, Mono.Android", WifiP2pManager_ConnectionInfoListenerImplementor.class, "n_onConnectionInfoAvailable:(Landroid/net/wifi/p2p/WifiP2pInfo;)V:GetOnConnectionInfoAvailable_Landroid_net_wifi_p2p_WifiP2pInfo_Handler:Android.Net.Wifi.P2p.WifiP2pManager/IConnectionInfoListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n");
  }
  
  public WifiP2pManager_ConnectionInfoListenerImplementor() {
    if (getClass() == WifiP2pManager_ConnectionInfoListenerImplementor.class)
      TypeManager.Activate("Android.Net.Wifi.P2p.WifiP2pManager+IConnectionInfoListenerImplementor, Mono.Android", "", this, new Object[0]); 
  }
  
  private native void n_onConnectionInfoAvailable(WifiP2pInfo paramWifiP2pInfo);
  
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
  
  public void onConnectionInfoAvailable(WifiP2pInfo paramWifiP2pInfo) {
    n_onConnectionInfoAvailable(paramWifiP2pInfo);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/mono/android/net/wifi/p2p/WifiP2pManager_ConnectionInfoListenerImplementor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */