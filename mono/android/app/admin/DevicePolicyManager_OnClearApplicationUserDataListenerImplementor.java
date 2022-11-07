package mono.android.app.admin;

import android.app.admin.DevicePolicyManager;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class DevicePolicyManager_OnClearApplicationUserDataListenerImplementor implements IGCUserPeer, DevicePolicyManager.OnClearApplicationUserDataListener {
  public static final String __md_methods = "n_onApplicationUserDataCleared:(Ljava/lang/String;Z)V:GetOnApplicationUserDataCleared_Ljava_lang_String_ZHandler:Android.App.Admin.DevicePolicyManager/IOnClearApplicationUserDataListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n";
  
  private ArrayList refList;
  
  static {
    Runtime.register("Android.App.Admin.DevicePolicyManager+IOnClearApplicationUserDataListenerImplementor, Mono.Android", DevicePolicyManager_OnClearApplicationUserDataListenerImplementor.class, "n_onApplicationUserDataCleared:(Ljava/lang/String;Z)V:GetOnApplicationUserDataCleared_Ljava_lang_String_ZHandler:Android.App.Admin.DevicePolicyManager/IOnClearApplicationUserDataListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n");
  }
  
  public DevicePolicyManager_OnClearApplicationUserDataListenerImplementor() {
    if (getClass() == DevicePolicyManager_OnClearApplicationUserDataListenerImplementor.class)
      TypeManager.Activate("Android.App.Admin.DevicePolicyManager+IOnClearApplicationUserDataListenerImplementor, Mono.Android", "", this, new Object[0]); 
  }
  
  private native void n_onApplicationUserDataCleared(String paramString, boolean paramBoolean);
  
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
  
  public void onApplicationUserDataCleared(String paramString, boolean paramBoolean) {
    n_onApplicationUserDataCleared(paramString, paramBoolean);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/mono/android/app/admin/DevicePolicyManager_OnClearApplicationUserDataListenerImplementor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */