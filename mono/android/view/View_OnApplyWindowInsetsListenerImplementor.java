package mono.android.view;

import android.view.View;
import android.view.WindowInsets;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class View_OnApplyWindowInsetsListenerImplementor implements IGCUserPeer, View.OnApplyWindowInsetsListener {
  public static final String __md_methods = "n_onApplyWindowInsets:(Landroid/view/View;Landroid/view/WindowInsets;)Landroid/view/WindowInsets;:GetOnApplyWindowInsets_Landroid_view_View_Landroid_view_WindowInsets_Handler:Android.Views.View/IOnApplyWindowInsetsListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n";
  
  private ArrayList refList;
  
  static {
    Runtime.register("Android.Views.View+IOnApplyWindowInsetsListenerImplementor, Mono.Android", View_OnApplyWindowInsetsListenerImplementor.class, "n_onApplyWindowInsets:(Landroid/view/View;Landroid/view/WindowInsets;)Landroid/view/WindowInsets;:GetOnApplyWindowInsets_Landroid_view_View_Landroid_view_WindowInsets_Handler:Android.Views.View/IOnApplyWindowInsetsListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n");
  }
  
  public View_OnApplyWindowInsetsListenerImplementor() {
    if (getClass() == View_OnApplyWindowInsetsListenerImplementor.class)
      TypeManager.Activate("Android.Views.View+IOnApplyWindowInsetsListenerImplementor, Mono.Android", "", this, new Object[0]); 
  }
  
  private native WindowInsets n_onApplyWindowInsets(View paramView, WindowInsets paramWindowInsets);
  
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
  
  public WindowInsets onApplyWindowInsets(View paramView, WindowInsets paramWindowInsets) {
    return n_onApplyWindowInsets(paramView, paramWindowInsets);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/mono/android/view/View_OnApplyWindowInsetsListenerImplementor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */