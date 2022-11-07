package mono.android.widget;

import android.widget.SlidingDrawer;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class SlidingDrawer_OnDrawerScrollListenerImplementor implements IGCUserPeer, SlidingDrawer.OnDrawerScrollListener {
  public static final String __md_methods = "n_onScrollEnded:()V:GetOnScrollEndedHandler:Android.Widget.SlidingDrawer/IOnDrawerScrollListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\nn_onScrollStarted:()V:GetOnScrollStartedHandler:Android.Widget.SlidingDrawer/IOnDrawerScrollListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n";
  
  private ArrayList refList;
  
  static {
    Runtime.register("Android.Widget.SlidingDrawer+IOnDrawerScrollListenerImplementor, Mono.Android", SlidingDrawer_OnDrawerScrollListenerImplementor.class, "n_onScrollEnded:()V:GetOnScrollEndedHandler:Android.Widget.SlidingDrawer/IOnDrawerScrollListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\nn_onScrollStarted:()V:GetOnScrollStartedHandler:Android.Widget.SlidingDrawer/IOnDrawerScrollListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n");
  }
  
  public SlidingDrawer_OnDrawerScrollListenerImplementor() {
    if (getClass() == SlidingDrawer_OnDrawerScrollListenerImplementor.class)
      TypeManager.Activate("Android.Widget.SlidingDrawer+IOnDrawerScrollListenerImplementor, Mono.Android", "", this, new Object[0]); 
  }
  
  private native void n_onScrollEnded();
  
  private native void n_onScrollStarted();
  
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
  
  public void onScrollEnded() {
    n_onScrollEnded();
  }
  
  public void onScrollStarted() {
    n_onScrollStarted();
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/mono/android/widget/SlidingDrawer_OnDrawerScrollListenerImplementor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */