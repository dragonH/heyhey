package mono.android.drm;

import android.drm.DrmErrorEvent;
import android.drm.DrmManagerClient;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class DrmManagerClient_OnErrorListenerImplementor implements IGCUserPeer, DrmManagerClient.OnErrorListener {
  public static final String __md_methods = "n_onError:(Landroid/drm/DrmManagerClient;Landroid/drm/DrmErrorEvent;)V:GetOnError_Landroid_drm_DrmManagerClient_Landroid_drm_DrmErrorEvent_Handler:Android.Drm.DrmManagerClient/IOnErrorListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n";
  
  private ArrayList refList;
  
  static {
    Runtime.register("Android.Drm.DrmManagerClient+IOnErrorListenerImplementor, Mono.Android", DrmManagerClient_OnErrorListenerImplementor.class, "n_onError:(Landroid/drm/DrmManagerClient;Landroid/drm/DrmErrorEvent;)V:GetOnError_Landroid_drm_DrmManagerClient_Landroid_drm_DrmErrorEvent_Handler:Android.Drm.DrmManagerClient/IOnErrorListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n");
  }
  
  public DrmManagerClient_OnErrorListenerImplementor() {
    if (getClass() == DrmManagerClient_OnErrorListenerImplementor.class)
      TypeManager.Activate("Android.Drm.DrmManagerClient+IOnErrorListenerImplementor, Mono.Android", "", this, new Object[0]); 
  }
  
  private native void n_onError(DrmManagerClient paramDrmManagerClient, DrmErrorEvent paramDrmErrorEvent);
  
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
  
  public void onError(DrmManagerClient paramDrmManagerClient, DrmErrorEvent paramDrmErrorEvent) {
    n_onError(paramDrmManagerClient, paramDrmErrorEvent);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/mono/android/drm/DrmManagerClient_OnErrorListenerImplementor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */