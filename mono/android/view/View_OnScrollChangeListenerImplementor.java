package mono.android.view;

import android.view.View;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class View_OnScrollChangeListenerImplementor implements IGCUserPeer, View.OnScrollChangeListener {
  public static final String __md_methods = "n_onScrollChange:(Landroid/view/View;IIII)V:GetOnScrollChange_Landroid_view_View_IIIIHandler:Android.Views.View/IOnScrollChangeListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n";
  
  private ArrayList refList;
  
  static {
    Runtime.register("Android.Views.View+IOnScrollChangeListenerImplementor, Mono.Android", View_OnScrollChangeListenerImplementor.class, "n_onScrollChange:(Landroid/view/View;IIII)V:GetOnScrollChange_Landroid_view_View_IIIIHandler:Android.Views.View/IOnScrollChangeListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n");
  }
  
  public View_OnScrollChangeListenerImplementor() {
    if (getClass() == View_OnScrollChangeListenerImplementor.class)
      TypeManager.Activate("Android.Views.View+IOnScrollChangeListenerImplementor, Mono.Android", "", this, new Object[0]); 
  }
  
  private native void n_onScrollChange(View paramView, int paramInt1, int paramInt2, int paramInt3, int paramInt4);
  
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
  
  public void onScrollChange(View paramView, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    n_onScrollChange(paramView, paramInt1, paramInt2, paramInt3, paramInt4);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/mono/android/view/View_OnScrollChangeListenerImplementor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */