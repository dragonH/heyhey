package mono.android.net.wifi.p2p;

import android.net.wifi.p2p.WifiP2pDevice;
import android.net.wifi.p2p.WifiP2pManager;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class WifiP2pManager_DnsSdServiceResponseListenerImplementor implements IGCUserPeer, WifiP2pManager.DnsSdServiceResponseListener {
  public static final String __md_methods = "n_onDnsSdServiceAvailable:(Ljava/lang/String;Ljava/lang/String;Landroid/net/wifi/p2p/WifiP2pDevice;)V:GetOnDnsSdServiceAvailable_Ljava_lang_String_Ljava_lang_String_Landroid_net_wifi_p2p_WifiP2pDevice_Handler:Android.Net.Wifi.P2p.WifiP2pManager/IDnsSdServiceResponseListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n";
  
  private ArrayList refList;
  
  static {
    Runtime.register("Android.Net.Wifi.P2p.WifiP2pManager+IDnsSdServiceResponseListenerImplementor, Mono.Android", WifiP2pManager_DnsSdServiceResponseListenerImplementor.class, "n_onDnsSdServiceAvailable:(Ljava/lang/String;Ljava/lang/String;Landroid/net/wifi/p2p/WifiP2pDevice;)V:GetOnDnsSdServiceAvailable_Ljava_lang_String_Ljava_lang_String_Landroid_net_wifi_p2p_WifiP2pDevice_Handler:Android.Net.Wifi.P2p.WifiP2pManager/IDnsSdServiceResponseListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n");
  }
  
  public WifiP2pManager_DnsSdServiceResponseListenerImplementor() {
    if (getClass() == WifiP2pManager_DnsSdServiceResponseListenerImplementor.class)
      TypeManager.Activate("Android.Net.Wifi.P2p.WifiP2pManager+IDnsSdServiceResponseListenerImplementor, Mono.Android", "", this, new Object[0]); 
  }
  
  private native void n_onDnsSdServiceAvailable(String paramString1, String paramString2, WifiP2pDevice paramWifiP2pDevice);
  
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
  
  public void onDnsSdServiceAvailable(String paramString1, String paramString2, WifiP2pDevice paramWifiP2pDevice) {
    n_onDnsSdServiceAvailable(paramString1, paramString2, paramWifiP2pDevice);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/mono/android/net/wifi/p2p/WifiP2pManager_DnsSdServiceResponseListenerImplementor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */