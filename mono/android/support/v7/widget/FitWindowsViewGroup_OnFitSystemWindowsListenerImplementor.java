package mono.android.support.v7.widget;

import android.graphics.Rect;
import android.support.v7.widget.FitWindowsViewGroup;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class FitWindowsViewGroup_OnFitSystemWindowsListenerImplementor implements IGCUserPeer, FitWindowsViewGroup.OnFitSystemWindowsListener {
  public static final String __md_methods = "n_onFitSystemWindows:(Landroid/graphics/Rect;)V:GetOnFitSystemWindows_Landroid_graphics_Rect_Handler:Android.Support.V7.Widget.IFitWindowsViewGroupOnFitSystemWindowsListenerInvoker, Xamarin.Android.Support.v7.AppCompat\n";
  
  private ArrayList refList;
  
  static {
    Runtime.register("Android.Support.V7.Widget.IFitWindowsViewGroupOnFitSystemWindowsListenerImplementor, Xamarin.Android.Support.v7.AppCompat", FitWindowsViewGroup_OnFitSystemWindowsListenerImplementor.class, "n_onFitSystemWindows:(Landroid/graphics/Rect;)V:GetOnFitSystemWindows_Landroid_graphics_Rect_Handler:Android.Support.V7.Widget.IFitWindowsViewGroupOnFitSystemWindowsListenerInvoker, Xamarin.Android.Support.v7.AppCompat\n");
  }
  
  public FitWindowsViewGroup_OnFitSystemWindowsListenerImplementor() {
    if (getClass() == FitWindowsViewGroup_OnFitSystemWindowsListenerImplementor.class)
      TypeManager.Activate("Android.Support.V7.Widget.IFitWindowsViewGroupOnFitSystemWindowsListenerImplementor, Xamarin.Android.Support.v7.AppCompat", "", this, new Object[0]); 
  }
  
  private native void n_onFitSystemWindows(Rect paramRect);
  
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
  
  public void onFitSystemWindows(Rect paramRect) {
    n_onFitSystemWindows(paramRect);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/mono/android/support/v7/widget/FitWindowsViewGroup_OnFitSystemWindowsListenerImplementor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */