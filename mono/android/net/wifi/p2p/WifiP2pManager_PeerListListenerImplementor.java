package mono.android.net.wifi.p2p;

import android.net.wifi.p2p.WifiP2pDeviceList;
import android.net.wifi.p2p.WifiP2pManager;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class WifiP2pManager_PeerListListenerImplementor implements IGCUserPeer, WifiP2pManager.PeerListListener {
  public static final String __md_methods = "n_onPeersAvailable:(Landroid/net/wifi/p2p/WifiP2pDeviceList;)V:GetOnPeersAvailable_Landroid_net_wifi_p2p_WifiP2pDeviceList_Handler:Android.Net.Wifi.P2p.WifiP2pManager/IPeerListListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n";
  
  private ArrayList refList;
  
  static {
    Runtime.register("Android.Net.Wifi.P2p.WifiP2pManager+IPeerListListenerImplementor, Mono.Android", WifiP2pManager_PeerListListenerImplementor.class, "n_onPeersAvailable:(Landroid/net/wifi/p2p/WifiP2pDeviceList;)V:GetOnPeersAvailable_Landroid_net_wifi_p2p_WifiP2pDeviceList_Handler:Android.Net.Wifi.P2p.WifiP2pManager/IPeerListListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n");
  }
  
  public WifiP2pManager_PeerListListenerImplementor() {
    if (getClass() == WifiP2pManager_PeerListListenerImplementor.class)
      TypeManager.Activate("Android.Net.Wifi.P2p.WifiP2pManager+IPeerListListenerImplementor, Mono.Android", "", this, new Object[0]); 
  }
  
  private native void n_onPeersAvailable(WifiP2pDeviceList paramWifiP2pDeviceList);
  
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
  
  public void onPeersAvailable(WifiP2pDeviceList paramWifiP2pDeviceList) {
    n_onPeersAvailable(paramWifiP2pDeviceList);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/mono/android/net/wifi/p2p/WifiP2pManager_PeerListListenerImplementor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */