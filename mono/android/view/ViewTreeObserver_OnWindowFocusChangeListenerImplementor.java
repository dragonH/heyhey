package mono.android.view;

import android.view.ViewTreeObserver;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class ViewTreeObserver_OnWindowFocusChangeListenerImplementor implements IGCUserPeer, ViewTreeObserver.OnWindowFocusChangeListener {
  public static final String __md_methods = "n_onWindowFocusChanged:(Z)V:GetOnWindowFocusChanged_ZHandler:Android.Views.ViewTreeObserver/IOnWindowFocusChangeListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n";
  
  private ArrayList refList;
  
  static {
    Runtime.register("Android.Views.ViewTreeObserver+IOnWindowFocusChangeListenerImplementor, Mono.Android", ViewTreeObserver_OnWindowFocusChangeListenerImplementor.class, "n_onWindowFocusChanged:(Z)V:GetOnWindowFocusChanged_ZHandler:Android.Views.ViewTreeObserver/IOnWindowFocusChangeListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n");
  }
  
  public ViewTreeObserver_OnWindowFocusChangeListenerImplementor() {
    if (getClass() == ViewTreeObserver_OnWindowFocusChangeListenerImplementor.class)
      TypeManager.Activate("Android.Views.ViewTreeObserver+IOnWindowFocusChangeListenerImplementor, Mono.Android", "", this, new Object[0]); 
  }
  
  private native void n_onWindowFocusChanged(boolean paramBoolean);
  
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
  
  public void onWindowFocusChanged(boolean paramBoolean) {
    n_onWindowFocusChanged(paramBoolean);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/mono/android/view/ViewTreeObserver_OnWindowFocusChangeListenerImplementor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */