package crc64cea48322b3427ae9;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class ConnectivityChangeBroadcastReceiver extends BroadcastReceiver implements IGCUserPeer {
  public static final String __md_methods = "n_onReceive:(Landroid/content/Context;Landroid/content/Intent;)V:GetOnReceive_Landroid_content_Context_Landroid_content_Intent_Handler\n";
  
  private ArrayList refList;
  
  static {
    Runtime.register("Plugin.Connectivity.ConnectivityChangeBroadcastReceiver, Plugin.Connectivity", ConnectivityChangeBroadcastReceiver.class, "n_onReceive:(Landroid/content/Context;Landroid/content/Intent;)V:GetOnReceive_Landroid_content_Context_Landroid_content_Intent_Handler\n");
  }
  
  public ConnectivityChangeBroadcastReceiver() {
    if (getClass() == ConnectivityChangeBroadcastReceiver.class)
      TypeManager.Activate("Plugin.Connectivity.ConnectivityChangeBroadcastReceiver, Plugin.Connectivity", "", this, new Object[0]); 
  }
  
  private native void n_onReceive(Context paramContext, Intent paramIntent);
  
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
  
  public void onReceive(Context paramContext, Intent paramIntent) {
    n_onReceive(paramContext, paramIntent);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/crc64cea48322b3427ae9/ConnectivityChangeBroadcastReceiver.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */