package mono.android.widget;

import android.widget.SlidingDrawer;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class SlidingDrawer_OnDrawerOpenListenerImplementor implements IGCUserPeer, SlidingDrawer.OnDrawerOpenListener {
  public static final String __md_methods = "n_onDrawerOpened:()V:GetOnDrawerOpenedHandler:Android.Widget.SlidingDrawer/IOnDrawerOpenListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n";
  
  private ArrayList refList;
  
  static {
    Runtime.register("Android.Widget.SlidingDrawer+IOnDrawerOpenListenerImplementor, Mono.Android", SlidingDrawer_OnDrawerOpenListenerImplementor.class, "n_onDrawerOpened:()V:GetOnDrawerOpenedHandler:Android.Widget.SlidingDrawer/IOnDrawerOpenListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n");
  }
  
  public SlidingDrawer_OnDrawerOpenListenerImplementor() {
    if (getClass() == SlidingDrawer_OnDrawerOpenListenerImplementor.class)
      TypeManager.Activate("Android.Widget.SlidingDrawer+IOnDrawerOpenListenerImplementor, Mono.Android", "", this, new Object[0]); 
  }
  
  private native void n_onDrawerOpened();
  
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
  
  public void onDrawerOpened() {
    n_onDrawerOpened();
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/mono/android/widget/SlidingDrawer_OnDrawerOpenListenerImplementor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */