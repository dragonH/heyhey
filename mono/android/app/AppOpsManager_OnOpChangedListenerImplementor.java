package mono.android.app;

import android.app.AppOpsManager;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class AppOpsManager_OnOpChangedListenerImplementor implements IGCUserPeer, AppOpsManager.OnOpChangedListener {
  public static final String __md_methods = "n_onOpChanged:(Ljava/lang/String;Ljava/lang/String;)V:GetOnOpChanged_Ljava_lang_String_Ljava_lang_String_Handler:Android.App.AppOpsManager/IOnOpChangedListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n";
  
  private ArrayList refList;
  
  static {
    Runtime.register("Android.App.AppOpsManager+IOnOpChangedListenerImplementor, Mono.Android", AppOpsManager_OnOpChangedListenerImplementor.class, "n_onOpChanged:(Ljava/lang/String;Ljava/lang/String;)V:GetOnOpChanged_Ljava_lang_String_Ljava_lang_String_Handler:Android.App.AppOpsManager/IOnOpChangedListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n");
  }
  
  public AppOpsManager_OnOpChangedListenerImplementor() {
    if (getClass() == AppOpsManager_OnOpChangedListenerImplementor.class)
      TypeManager.Activate("Android.App.AppOpsManager+IOnOpChangedListenerImplementor, Mono.Android", "", this, new Object[0]); 
  }
  
  private native void n_onOpChanged(String paramString1, String paramString2);
  
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
  
  public void onOpChanged(String paramString1, String paramString2) {
    n_onOpChanged(paramString1, paramString2);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/mono/android/app/AppOpsManager_OnOpChangedListenerImplementor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */