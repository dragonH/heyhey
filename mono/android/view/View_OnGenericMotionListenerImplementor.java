package mono.android.view;

import android.view.MotionEvent;
import android.view.View;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class View_OnGenericMotionListenerImplementor implements IGCUserPeer, View.OnGenericMotionListener {
  public static final String __md_methods = "n_onGenericMotion:(Landroid/view/View;Landroid/view/MotionEvent;)Z:GetOnGenericMotion_Landroid_view_View_Landroid_view_MotionEvent_Handler:Android.Views.View/IOnGenericMotionListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n";
  
  private ArrayList refList;
  
  static {
    Runtime.register("Android.Views.View+IOnGenericMotionListenerImplementor, Mono.Android", View_OnGenericMotionListenerImplementor.class, "n_onGenericMotion:(Landroid/view/View;Landroid/view/MotionEvent;)Z:GetOnGenericMotion_Landroid_view_View_Landroid_view_MotionEvent_Handler:Android.Views.View/IOnGenericMotionListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n");
  }
  
  public View_OnGenericMotionListenerImplementor() {
    if (getClass() == View_OnGenericMotionListenerImplementor.class)
      TypeManager.Activate("Android.Views.View+IOnGenericMotionListenerImplementor, Mono.Android", "", this, new Object[0]); 
  }
  
  private native boolean n_onGenericMotion(View paramView, MotionEvent paramMotionEvent);
  
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
  
  public boolean onGenericMotion(View paramView, MotionEvent paramMotionEvent) {
    return n_onGenericMotion(paramView, paramMotionEvent);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/mono/android/view/View_OnGenericMotionListenerImplementor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */