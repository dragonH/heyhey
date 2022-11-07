package mono.android.drm;

import android.drm.DrmEvent;
import android.drm.DrmManagerClient;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class DrmManagerClient_OnEventListenerImplementor implements IGCUserPeer, DrmManagerClient.OnEventListener {
  public static final String __md_methods = "n_onEvent:(Landroid/drm/DrmManagerClient;Landroid/drm/DrmEvent;)V:GetOnEvent_Landroid_drm_DrmManagerClient_Landroid_drm_DrmEvent_Handler:Android.Drm.DrmManagerClient/IOnEventListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n";
  
  private ArrayList refList;
  
  static {
    Runtime.register("Android.Drm.DrmManagerClient+IOnEventListenerImplementor, Mono.Android", DrmManagerClient_OnEventListenerImplementor.class, "n_onEvent:(Landroid/drm/DrmManagerClient;Landroid/drm/DrmEvent;)V:GetOnEvent_Landroid_drm_DrmManagerClient_Landroid_drm_DrmEvent_Handler:Android.Drm.DrmManagerClient/IOnEventListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n");
  }
  
  public DrmManagerClient_OnEventListenerImplementor() {
    if (getClass() == DrmManagerClient_OnEventListenerImplementor.class)
      TypeManager.Activate("Android.Drm.DrmManagerClient+IOnEventListenerImplementor, Mono.Android", "", this, new Object[0]); 
  }
  
  private native void n_onEvent(DrmManagerClient paramDrmManagerClient, DrmEvent paramDrmEvent);
  
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
  
  public void onEvent(DrmManagerClient paramDrmManagerClient, DrmEvent paramDrmEvent) {
    n_onEvent(paramDrmManagerClient, paramDrmEvent);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/mono/android/drm/DrmManagerClient_OnEventListenerImplementor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */