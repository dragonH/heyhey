package mono.android.view;

import android.view.ViewTreeObserver;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class ViewTreeObserver_OnTouchModeChangeListenerImplementor implements IGCUserPeer, ViewTreeObserver.OnTouchModeChangeListener {
  public static final String __md_methods = "n_onTouchModeChanged:(Z)V:GetOnTouchModeChanged_ZHandler:Android.Views.ViewTreeObserver/IOnTouchModeChangeListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n";
  
  private ArrayList refList;
  
  static {
    Runtime.register("Android.Views.ViewTreeObserver+IOnTouchModeChangeListenerImplementor, Mono.Android", ViewTreeObserver_OnTouchModeChangeListenerImplementor.class, "n_onTouchModeChanged:(Z)V:GetOnTouchModeChanged_ZHandler:Android.Views.ViewTreeObserver/IOnTouchModeChangeListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n");
  }
  
  public ViewTreeObserver_OnTouchModeChangeListenerImplementor() {
    if (getClass() == ViewTreeObserver_OnTouchModeChangeListenerImplementor.class)
      TypeManager.Activate("Android.Views.ViewTreeObserver+IOnTouchModeChangeListenerImplementor, Mono.Android", "", this, new Object[0]); 
  }
  
  private native void n_onTouchModeChanged(boolean paramBoolean);
  
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
  
  public void onTouchModeChanged(boolean paramBoolean) {
    n_onTouchModeChanged(paramBoolean);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/mono/android/view/ViewTreeObserver_OnTouchModeChangeListenerImplementor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */