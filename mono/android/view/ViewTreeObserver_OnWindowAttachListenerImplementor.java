package mono.android.view;

import android.view.ViewTreeObserver;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class ViewTreeObserver_OnWindowAttachListenerImplementor implements IGCUserPeer, ViewTreeObserver.OnWindowAttachListener {
  public static final String __md_methods = "n_onWindowAttached:()V:GetOnWindowAttachedHandler:Android.Views.ViewTreeObserver/IOnWindowAttachListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\nn_onWindowDetached:()V:GetOnWindowDetachedHandler:Android.Views.ViewTreeObserver/IOnWindowAttachListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n";
  
  private ArrayList refList;
  
  static {
    Runtime.register("Android.Views.ViewTreeObserver+IOnWindowAttachListenerImplementor, Mono.Android", ViewTreeObserver_OnWindowAttachListenerImplementor.class, "n_onWindowAttached:()V:GetOnWindowAttachedHandler:Android.Views.ViewTreeObserver/IOnWindowAttachListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\nn_onWindowDetached:()V:GetOnWindowDetachedHandler:Android.Views.ViewTreeObserver/IOnWindowAttachListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n");
  }
  
  public ViewTreeObserver_OnWindowAttachListenerImplementor() {
    if (getClass() == ViewTreeObserver_OnWindowAttachListenerImplementor.class)
      TypeManager.Activate("Android.Views.ViewTreeObserver+IOnWindowAttachListenerImplementor, Mono.Android", "", this, new Object[0]); 
  }
  
  private native void n_onWindowAttached();
  
  private native void n_onWindowDetached();
  
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
  
  public void onWindowAttached() {
    n_onWindowAttached();
  }
  
  public void onWindowDetached() {
    n_onWindowDetached();
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/mono/android/view/ViewTreeObserver_OnWindowAttachListenerImplementor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */