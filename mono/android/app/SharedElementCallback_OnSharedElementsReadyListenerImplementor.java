package mono.android.app;

import android.app.SharedElementCallback;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class SharedElementCallback_OnSharedElementsReadyListenerImplementor implements IGCUserPeer, SharedElementCallback.OnSharedElementsReadyListener {
  public static final String __md_methods = "n_onSharedElementsReady:()V:GetOnSharedElementsReadyHandler:Android.App.SharedElementCallback/IOnSharedElementsReadyListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n";
  
  private ArrayList refList;
  
  static {
    Runtime.register("Android.App.SharedElementCallback+IOnSharedElementsReadyListenerImplementor, Mono.Android", SharedElementCallback_OnSharedElementsReadyListenerImplementor.class, "n_onSharedElementsReady:()V:GetOnSharedElementsReadyHandler:Android.App.SharedElementCallback/IOnSharedElementsReadyListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n");
  }
  
  public SharedElementCallback_OnSharedElementsReadyListenerImplementor() {
    if (getClass() == SharedElementCallback_OnSharedElementsReadyListenerImplementor.class)
      TypeManager.Activate("Android.App.SharedElementCallback+IOnSharedElementsReadyListenerImplementor, Mono.Android", "", this, new Object[0]); 
  }
  
  private native void n_onSharedElementsReady();
  
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
  
  public void onSharedElementsReady() {
    n_onSharedElementsReady();
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/mono/android/app/SharedElementCallback_OnSharedElementsReadyListenerImplementor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */