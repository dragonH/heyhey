package mono.android.view;

import android.view.View;
import android.view.ViewStub;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class ViewStub_OnInflateListenerImplementor implements IGCUserPeer, ViewStub.OnInflateListener {
  public static final String __md_methods = "n_onInflate:(Landroid/view/ViewStub;Landroid/view/View;)V:GetOnInflate_Landroid_view_ViewStub_Landroid_view_View_Handler:Android.Views.ViewStub/IOnInflateListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n";
  
  private ArrayList refList;
  
  static {
    Runtime.register("Android.Views.ViewStub+IOnInflateListenerImplementor, Mono.Android", ViewStub_OnInflateListenerImplementor.class, "n_onInflate:(Landroid/view/ViewStub;Landroid/view/View;)V:GetOnInflate_Landroid_view_ViewStub_Landroid_view_View_Handler:Android.Views.ViewStub/IOnInflateListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n");
  }
  
  public ViewStub_OnInflateListenerImplementor() {
    if (getClass() == ViewStub_OnInflateListenerImplementor.class)
      TypeManager.Activate("Android.Views.ViewStub+IOnInflateListenerImplementor, Mono.Android", "", this, new Object[0]); 
  }
  
  private native void n_onInflate(ViewStub paramViewStub, View paramView);
  
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
  
  public void onInflate(ViewStub paramViewStub, View paramView) {
    n_onInflate(paramViewStub, paramView);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/mono/android/view/ViewStub_OnInflateListenerImplementor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */