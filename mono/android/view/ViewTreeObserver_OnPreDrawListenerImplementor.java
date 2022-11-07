package mono.android.view;

import android.view.ViewTreeObserver;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class ViewTreeObserver_OnPreDrawListenerImplementor implements IGCUserPeer, ViewTreeObserver.OnPreDrawListener {
  public static final String __md_methods = "n_onPreDraw:()Z:GetOnPreDrawHandler:Android.Views.ViewTreeObserver/IOnPreDrawListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n";
  
  private ArrayList refList;
  
  static {
    Runtime.register("Android.Views.ViewTreeObserver+IOnPreDrawListenerImplementor, Mono.Android", ViewTreeObserver_OnPreDrawListenerImplementor.class, "n_onPreDraw:()Z:GetOnPreDrawHandler:Android.Views.ViewTreeObserver/IOnPreDrawListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n");
  }
  
  public ViewTreeObserver_OnPreDrawListenerImplementor() {
    if (getClass() == ViewTreeObserver_OnPreDrawListenerImplementor.class)
      TypeManager.Activate("Android.Views.ViewTreeObserver+IOnPreDrawListenerImplementor, Mono.Android", "", this, new Object[0]); 
  }
  
  private native boolean n_onPreDraw();
  
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
  
  public boolean onPreDraw() {
    return n_onPreDraw();
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/mono/android/view/ViewTreeObserver_OnPreDrawListenerImplementor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */