package mono.android.view;

import android.view.View;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class View_OnContextClickListenerImplementor implements IGCUserPeer, View.OnContextClickListener {
  public static final String __md_methods = "n_onContextClick:(Landroid/view/View;)Z:GetOnContextClick_Landroid_view_View_Handler:Android.Views.View/IOnContextClickListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n";
  
  private ArrayList refList;
  
  static {
    Runtime.register("Android.Views.View+IOnContextClickListenerImplementor, Mono.Android", View_OnContextClickListenerImplementor.class, "n_onContextClick:(Landroid/view/View;)Z:GetOnContextClick_Landroid_view_View_Handler:Android.Views.View/IOnContextClickListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n");
  }
  
  public View_OnContextClickListenerImplementor() {
    if (getClass() == View_OnContextClickListenerImplementor.class)
      TypeManager.Activate("Android.Views.View+IOnContextClickListenerImplementor, Mono.Android", "", this, new Object[0]); 
  }
  
  private native boolean n_onContextClick(View paramView);
  
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
  
  public boolean onContextClick(View paramView) {
    return n_onContextClick(paramView);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/mono/android/view/View_OnContextClickListenerImplementor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */