package mono.android.support.v4.content;

import android.support.v4.content.Loader;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class Loader_OnLoadCanceledListenerImplementor implements IGCUserPeer, Loader.OnLoadCanceledListener {
  public static final String __md_methods = "n_onLoadCanceled:(Landroid/support/v4/content/Loader;)V:GetOnLoadCanceled_Landroid_support_v4_content_Loader_Handler:Android.Support.V4.Content.Loader/IOnLoadCanceledListenerInvoker, Xamarin.Android.Support.Core.Utils\n";
  
  private ArrayList refList;
  
  static {
    Runtime.register("Android.Support.V4.Content.Loader+IOnLoadCanceledListenerImplementor, Xamarin.Android.Support.Core.Utils", Loader_OnLoadCanceledListenerImplementor.class, "n_onLoadCanceled:(Landroid/support/v4/content/Loader;)V:GetOnLoadCanceled_Landroid_support_v4_content_Loader_Handler:Android.Support.V4.Content.Loader/IOnLoadCanceledListenerInvoker, Xamarin.Android.Support.Core.Utils\n");
  }
  
  public Loader_OnLoadCanceledListenerImplementor() {
    if (getClass() == Loader_OnLoadCanceledListenerImplementor.class)
      TypeManager.Activate("Android.Support.V4.Content.Loader+IOnLoadCanceledListenerImplementor, Xamarin.Android.Support.Core.Utils", "", this, new Object[0]); 
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


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/mono/android/support/v4/content/Loader_OnLoadCanceledListenerImplementor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */