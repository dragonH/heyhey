package mono.android.content;

import android.content.Loader;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class Loader_OnLoadCompleteListenerImplementor implements IGCUserPeer, Loader.OnLoadCompleteListener {
  public static final String __md_methods = "n_onLoadComplete:(Landroid/content/Loader;Ljava/lang/Object;)V:GetOnLoadComplete_Landroid_content_Loader_Ljava_lang_Object_Handler:Android.Content.Loader/IOnLoadCompleteListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n";
  
  private ArrayList refList;
  
  static {
    Runtime.register("Android.Content.Loader+IOnLoadCompleteListenerImplementor, Mono.Android", Loader_OnLoadCompleteListenerImplementor.class, "n_onLoadComplete:(Landroid/content/Loader;Ljava/lang/Object;)V:GetOnLoadComplete_Landroid_content_Loader_Ljava_lang_Object_Handler:Android.Content.Loader/IOnLoadCompleteListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n");
  }
  
  public Loader_OnLoadCompleteListenerImplementor() {
    if (getClass() == Loader_OnLoadCompleteListenerImplementor.class)
      TypeManager.Activate("Android.Content.Loader+IOnLoadCompleteListenerImplementor, Mono.Android", "", this, new Object[0]); 
  }
  
  private native void n_onLoadComplete(Loader paramLoader, Object paramObject);
  
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
  
  public void onLoadComplete(Loader paramLoader, Object paramObject) {
    n_onLoadComplete(paramLoader, paramObject);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/mono/android/content/Loader_OnLoadCompleteListenerImplementor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */