package mono.android.app;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class Application_OnProvideAssistDataListenerImplementor implements IGCUserPeer, Application.OnProvideAssistDataListener {
  public static final String __md_methods = "n_onProvideAssistData:(Landroid/app/Activity;Landroid/os/Bundle;)V:GetOnProvideAssistData_Landroid_app_Activity_Landroid_os_Bundle_Handler:Android.App.Application/IOnProvideAssistDataListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n";
  
  private ArrayList refList;
  
  static {
    Runtime.register("Android.App.Application+IOnProvideAssistDataListenerImplementor, Mono.Android", Application_OnProvideAssistDataListenerImplementor.class, "n_onProvideAssistData:(Landroid/app/Activity;Landroid/os/Bundle;)V:GetOnProvideAssistData_Landroid_app_Activity_Landroid_os_Bundle_Handler:Android.App.Application/IOnProvideAssistDataListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n");
  }
  
  public Application_OnProvideAssistDataListenerImplementor() {
    if (getClass() == Application_OnProvideAssistDataListenerImplementor.class)
      TypeManager.Activate("Android.App.Application+IOnProvideAssistDataListenerImplementor, Mono.Android", "", this, new Object[0]); 
  }
  
  private native void n_onProvideAssistData(Activity paramActivity, Bundle paramBundle);
  
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
  
  public void onProvideAssistData(Activity paramActivity, Bundle paramBundle) {
    n_onProvideAssistData(paramActivity, paramBundle);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/mono/android/app/Application_OnProvideAssistDataListenerImplementor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */