package mono.android.view;

import android.view.MotionEvent;
import android.view.View;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class View_OnCapturedPointerListenerImplementor implements IGCUserPeer, View.OnCapturedPointerListener {
  public static final String __md_methods = "n_onCapturedPointer:(Landroid/view/View;Landroid/view/MotionEvent;)Z:GetOnCapturedPointer_Landroid_view_View_Landroid_view_MotionEvent_Handler:Android.Views.View/IOnCapturedPointerListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n";
  
  private ArrayList refList;
  
  static {
    Runtime.register("Android.Views.View+IOnCapturedPointerListenerImplementor, Mono.Android", View_OnCapturedPointerListenerImplementor.class, "n_onCapturedPointer:(Landroid/view/View;Landroid/view/MotionEvent;)Z:GetOnCapturedPointer_Landroid_view_View_Landroid_view_MotionEvent_Handler:Android.Views.View/IOnCapturedPointerListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n");
  }
  
  public View_OnCapturedPointerListenerImplementor() {
    if (getClass() == View_OnCapturedPointerListenerImplementor.class)
      TypeManager.Activate("Android.Views.View+IOnCapturedPointerListenerImplementor, Mono.Android", "", this, new Object[0]); 
  }
  
  private native boolean n_onCapturedPointer(View paramView, MotionEvent paramMotionEvent);
  
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
  
  public boolean onCapturedPointer(View paramView, MotionEvent paramMotionEvent) {
    return n_onCapturedPointer(paramView, paramMotionEvent);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/mono/android/view/View_OnCapturedPointerListenerImplementor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */