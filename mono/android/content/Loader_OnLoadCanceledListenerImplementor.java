package mono.android.content;

import android.content.Loader;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class Loader_OnLoadCanceledListenerImplementor implements IGCUserPeer, Loader.OnLoadCanceledListener {
  public static final String __md_methods = "n_onLoadCanceled:(Landroid/content/Loader;)V:GetOnLoadCanceled_Landroid_content_Loader_Handler:Android.Content.Loader/IOnLoadCanceledListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n";
  
  private ArrayList refList;
  
  static {
    Runtime.register("Android.Content.Loader+IOnLoadCanceledListenerImplementor, Mono.Android", Loader_OnLoadCanceledListenerImplementor.class, "n_onLoadCanceled:(Landroid/content/Loader;)V:GetOnLoadCanceled_Landroid_content_Loader_Handler:Android.Content.Loader/IOnLoadCanceledListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n");
  }
  
  public Loader_OnLoadCanceledListenerImplementor() {
    if (getClass() == Loader_OnLoadCanceledListenerImplementor.class)
      TypeManager.Activate("Android.Content.Loader+IOnLoadCanceledListenerImplementor, Mono.Android", "", this, new Object[0]); 
  }
  
  private native void n_onLoadCanceled(Loader paramLoader);
  
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
  
  public void onLoadCanceled(Loader paramLoader) {
    n_onLoadCanceled(paramLoader);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/mono/android/content/Loader_OnLoadCanceledListenerImplementor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */