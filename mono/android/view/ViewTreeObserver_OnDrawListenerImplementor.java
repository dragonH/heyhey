package mono.android.view;

import android.view.ViewTreeObserver;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class ViewTreeObserver_OnDrawListenerImplementor implements IGCUserPeer, ViewTreeObserver.OnDrawListener {
  public static final String __md_methods = "n_onDraw:()V:GetOnDrawHandler:Android.Views.ViewTreeObserver/IOnDrawListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n";
  
  private ArrayList refList;
  
  static {
    Runtime.register("Android.Views.ViewTreeObserver+IOnDrawListenerImplementor, Mono.Android", ViewTreeObserver_OnDrawListenerImplementor.class, "n_onDraw:()V:GetOnDrawHandler:Android.Views.ViewTreeObserver/IOnDrawListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n");
  }
  
  public ViewTreeObserver_OnDrawListenerImplementor() {
    if (getClass() == ViewTreeObserver_OnDrawListenerImplementor.class)
      TypeManager.Activate("Android.Views.ViewTreeObserver+IOnDrawListenerImplementor, Mono.Android", "", this, new Object[0]); 
  }
  
  private native void n_onDraw();
  
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
  
  public void onDraw() {
    n_onDraw();
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/mono/android/view/ViewTreeObserver_OnDrawListenerImplementor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */