package mono.android.widget;

import android.widget.ZoomButtonsController;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class ZoomButtonsController_OnZoomListenerImplementor implements IGCUserPeer, ZoomButtonsController.OnZoomListener {
  public static final String __md_methods = "n_onVisibilityChanged:(Z)V:GetOnVisibilityChanged_ZHandler:Android.Widget.ZoomButtonsController/IOnZoomListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\nn_onZoom:(Z)V:GetOnZoom_ZHandler:Android.Widget.ZoomButtonsController/IOnZoomListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n";
  
  private ArrayList refList;
  
  static {
    Runtime.register("Android.Widget.ZoomButtonsController+IOnZoomListenerImplementor, Mono.Android", ZoomButtonsController_OnZoomListenerImplementor.class, "n_onVisibilityChanged:(Z)V:GetOnVisibilityChanged_ZHandler:Android.Widget.ZoomButtonsController/IOnZoomListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\nn_onZoom:(Z)V:GetOnZoom_ZHandler:Android.Widget.ZoomButtonsController/IOnZoomListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n");
  }
  
  public ZoomButtonsController_OnZoomListenerImplementor() {
    if (getClass() == ZoomButtonsController_OnZoomListenerImplementor.class)
      TypeManager.Activate("Android.Widget.ZoomButtonsController+IOnZoomListenerImplementor, Mono.Android", "", this, new Object[0]); 
  }
  
  private native void n_onVisibilityChanged(boolean paramBoolean);
  
  private native void n_onZoom(boolean paramBoolean);
  
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
  
  public void onVisibilityChanged(boolean paramBoolean) {
    n_onVisibilityChanged(paramBoolean);
  }
  
  public void onZoom(boolean paramBoolean) {
    n_onZoom(paramBoolean);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/mono/android/widget/ZoomButtonsController_OnZoomListenerImplementor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */