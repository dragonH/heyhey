package mono.android.net.wifi.p2p;

import android.net.wifi.p2p.WifiP2pManager;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class WifiP2pManager_ChannelListenerImplementor implements IGCUserPeer, WifiP2pManager.ChannelListener {
  public static final String __md_methods = "n_onChannelDisconnected:()V:GetOnChannelDisconnectedHandler:Android.Net.Wifi.P2p.WifiP2pManager/IChannelListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n";
  
  private ArrayList refList;
  
  static {
    Runtime.register("Android.Net.Wifi.P2p.WifiP2pManager+IChannelListenerImplementor, Mono.Android", WifiP2pManager_ChannelListenerImplementor.class, "n_onChannelDisconnected:()V:GetOnChannelDisconnectedHandler:Android.Net.Wifi.P2p.WifiP2pManager/IChannelListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n");
  }
  
  public WifiP2pManager_ChannelListenerImplementor() {
    if (getClass() == WifiP2pManager_ChannelListenerImplementor.class)
      TypeManager.Activate("Android.Net.Wifi.P2p.WifiP2pManager+IChannelListenerImplementor, Mono.Android", "", this, new Object[0]); 
  }
  
  private native void n_onChannelDisconnected();
  
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
  
  public void onChannelDisconnected() {
    n_onChannelDisconnected();
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/mono/android/net/wifi/p2p/WifiP2pManager_ChannelListenerImplementor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */