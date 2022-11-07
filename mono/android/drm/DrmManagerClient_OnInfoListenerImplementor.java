package mono.android.drm;

import android.drm.DrmInfoEvent;
import android.drm.DrmManagerClient;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class DrmManagerClient_OnInfoListenerImplementor implements IGCUserPeer, DrmManagerClient.OnInfoListener {
  public static final String __md_methods = "n_onInfo:(Landroid/drm/DrmManagerClient;Landroid/drm/DrmInfoEvent;)V:GetOnInfo_Landroid_drm_DrmManagerClient_Landroid_drm_DrmInfoEvent_Handler:Android.Drm.DrmManagerClient/IOnInfoListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n";
  
  private ArrayList refList;
  
  static {
    Runtime.register("Android.Drm.DrmManagerClient+IOnInfoListenerImplementor, Mono.Android", DrmManagerClient_OnInfoListenerImplementor.class, "n_onInfo:(Landroid/drm/DrmManagerClient;Landroid/drm/DrmInfoEvent;)V:GetOnInfo_Landroid_drm_DrmManagerClient_Landroid_drm_DrmInfoEvent_Handler:Android.Drm.DrmManagerClient/IOnInfoListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n");
  }
  
  public DrmManagerClient_OnInfoListenerImplementor() {
    if (getClass() == DrmManagerClient_OnInfoListenerImplementor.class)
      TypeManager.Activate("Android.Drm.DrmManagerClient+IOnInfoListenerImplementor, Mono.Android", "", this, new Object[0]); 
  }
  
  private native void n_onInfo(DrmManagerClient paramDrmManagerClient, DrmInfoEvent paramDrmInfoEvent);
  
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
  
  public void onInfo(DrmManagerClient paramDrmManagerClient, DrmInfoEvent paramDrmInfoEvent) {
    n_onInfo(paramDrmManagerClient, paramDrmInfoEvent);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/mono/android/drm/DrmManagerClient_OnInfoListenerImplementor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */