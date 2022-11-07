package mono.android.view;

import android.view.GestureDetector;
import android.view.MotionEvent;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class GestureDetector_OnContextClickListenerImplementor implements IGCUserPeer, GestureDetector.OnContextClickListener {
  public static final String __md_methods = "n_onContextClick:(Landroid/view/MotionEvent;)Z:GetOnContextClick_Landroid_view_MotionEvent_Handler:Android.Views.GestureDetector/IOnContextClickListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n";
  
  private ArrayList refList;
  
  static {
    Runtime.register("Android.Views.GestureDetector+IOnContextClickListenerImplementor, Mono.Android", GestureDetector_OnContextClickListenerImplementor.class, "n_onContextClick:(Landroid/view/MotionEvent;)Z:GetOnContextClick_Landroid_view_MotionEvent_Handler:Android.Views.GestureDetector/IOnContextClickListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n");
  }
  
  public GestureDetector_OnContextClickListenerImplementor() {
    if (getClass() == GestureDetector_OnContextClickListenerImplementor.class)
      TypeManager.Activate("Android.Views.GestureDetector+IOnContextClickListenerImplementor, Mono.Android", "", this, new Object[0]); 
  }
  
  private native boolean n_onContextClick(MotionEvent paramMotionEvent);
  
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
  
  public boolean onContextClick(MotionEvent paramMotionEvent) {
    return n_onContextClick(paramMotionEvent);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/mono/android/view/GestureDetector_OnContextClickListenerImplementor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */