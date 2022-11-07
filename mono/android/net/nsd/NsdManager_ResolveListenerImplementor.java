package mono.android.net.nsd;

import android.net.nsd.NsdManager;
import android.net.nsd.NsdServiceInfo;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class NsdManager_ResolveListenerImplementor implements IGCUserPeer, NsdManager.ResolveListener {
  public static final String __md_methods = "n_onResolveFailed:(Landroid/net/nsd/NsdServiceInfo;I)V:GetOnResolveFailed_Landroid_net_nsd_NsdServiceInfo_IHandler:Android.Net.Nsd.NsdManager/IResolveListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\nn_onServiceResolved:(Landroid/net/nsd/NsdServiceInfo;)V:GetOnServiceResolved_Landroid_net_nsd_NsdServiceInfo_Handler:Android.Net.Nsd.NsdManager/IResolveListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n";
  
  private ArrayList refList;
  
  static {
    Runtime.register("Android.Net.Nsd.NsdManager+IResolveListenerImplementor, Mono.Android", NsdManager_ResolveListenerImplementor.class, "n_onResolveFailed:(Landroid/net/nsd/NsdServiceInfo;I)V:GetOnResolveFailed_Landroid_net_nsd_NsdServiceInfo_IHandler:Android.Net.Nsd.NsdManager/IResolveListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\nn_onServiceResolved:(Landroid/net/nsd/NsdServiceInfo;)V:GetOnServiceResolved_Landroid_net_nsd_NsdServiceInfo_Handler:Android.Net.Nsd.NsdManager/IResolveListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n");
  }
  
  public NsdManager_ResolveListenerImplementor() {
    if (getClass() == NsdManager_ResolveListenerImplementor.class)
      TypeManager.Activate("Android.Net.Nsd.NsdManager+IResolveListenerImplementor, Mono.Android", "", this, new Object[0]); 
  }
  
  private native void n_onResolveFailed(NsdServiceInfo paramNsdServiceInfo, int paramInt);
  
  private native void n_onServiceResolved(NsdServiceInfo paramNsdServiceInfo);
  
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
  
  public void onResolveFailed(NsdServiceInfo paramNsdServiceInfo, int paramInt) {
    n_onResolveFailed(paramNsdServiceInfo, paramInt);
  }
  
  public void onServiceResolved(NsdServiceInfo paramNsdServiceInfo) {
    n_onServiceResolved(paramNsdServiceInfo);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/mono/android/net/nsd/NsdManager_ResolveListenerImplementor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */