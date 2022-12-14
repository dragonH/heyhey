package mono.android.view;

import android.view.ViewTreeObserver;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class ViewTreeObserver_OnGlobalLayoutListenerImplementor implements IGCUserPeer, ViewTreeObserver.OnGlobalLayoutListener {
  public static final String __md_methods = "n_onGlobalLayout:()V:GetOnGlobalLayoutHandler:Android.Views.ViewTreeObserver/IOnGlobalLayoutListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n";
  
  private ArrayList refList;
  
  static {
    Runtime.register("Android.Views.ViewTreeObserver+IOnGlobalLayoutListenerImplementor, Mono.Android", ViewTreeObserver_OnGlobalLayoutListenerImplementor.class, "n_onGlobalLayout:()V:GetOnGlobalLayoutHandler:Android.Views.ViewTreeObserver/IOnGlobalLayoutListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n");
  }
  
  public ViewTreeObserver_OnGlobalLayoutListenerImplementor() {
    if (getClass() == ViewTreeObserver_OnGlobalLayoutListenerImplementor.class)
      TypeManager.Activate("Android.Views.ViewTreeObserver+IOnGlobalLayoutListenerImplementor, Mono.Android", "", this, new Object[0]); 
  }
  
  private native void n_onGlobalLayout();
  
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
  
  public void onGlobalLayout() {
    n_onGlobalLayout();
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/mono/android/view/ViewTreeObserver_OnGlobalLayoutListenerImplementor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */