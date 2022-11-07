package mono.android.widget;

import android.widget.PopupWindow;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class PopupWindow_OnDismissListenerImplementor implements IGCUserPeer, PopupWindow.OnDismissListener {
  public static final String __md_methods = "n_onDismiss:()V:GetOnDismissHandler:Android.Widget.PopupWindow/IOnDismissListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n";
  
  private ArrayList refList;
  
  static {
    Runtime.register("Android.Widget.PopupWindow+IOnDismissListenerImplementor, Mono.Android", PopupWindow_OnDismissListenerImplementor.class, "n_onDismiss:()V:GetOnDismissHandler:Android.Widget.PopupWindow/IOnDismissListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n");
  }
  
  public PopupWindow_OnDismissListenerImplementor() {
    if (getClass() == PopupWindow_OnDismissListenerImplementor.class)
      TypeManager.Activate("Android.Widget.PopupWindow+IOnDismissListenerImplementor, Mono.Android", "", this, new Object[0]); 
  }
  
  private native void n_onDismiss();
  
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
  
  public void onDismiss() {
    n_onDismiss();
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/mono/android/widget/PopupWindow_OnDismissListenerImplementor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */