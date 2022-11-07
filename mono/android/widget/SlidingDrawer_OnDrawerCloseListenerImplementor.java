package mono.android.widget;

import android.widget.SlidingDrawer;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class SlidingDrawer_OnDrawerCloseListenerImplementor implements IGCUserPeer, SlidingDrawer.OnDrawerCloseListener {
  public static final String __md_methods = "n_onDrawerClosed:()V:GetOnDrawerClosedHandler:Android.Widget.SlidingDrawer/IOnDrawerCloseListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n";
  
  private ArrayList refList;
  
  static {
    Runtime.register("Android.Widget.SlidingDrawer+IOnDrawerCloseListenerImplementor, Mono.Android", SlidingDrawer_OnDrawerCloseListenerImplementor.class, "n_onDrawerClosed:()V:GetOnDrawerClosedHandler:Android.Widget.SlidingDrawer/IOnDrawerCloseListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n");
  }
  
  public SlidingDrawer_OnDrawerCloseListenerImplementor() {
    if (getClass() == SlidingDrawer_OnDrawerCloseListenerImplementor.class)
      TypeManager.Activate("Android.Widget.SlidingDrawer+IOnDrawerCloseListenerImplementor, Mono.Android", "", this, new Object[0]); 
  }
  
  private native void n_onDrawerClosed();
  
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
  
  public void onDrawerClosed() {
    n_onDrawerClosed();
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/mono/android/widget/SlidingDrawer_OnDrawerCloseListenerImplementor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */