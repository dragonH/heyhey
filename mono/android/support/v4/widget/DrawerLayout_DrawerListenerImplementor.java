package mono.android.support.v4.widget;

import android.support.v4.widget.DrawerLayout;
import android.view.View;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class DrawerLayout_DrawerListenerImplementor implements IGCUserPeer, DrawerLayout.DrawerListener {
  public static final String __md_methods = "n_onDrawerClosed:(Landroid/view/View;)V:GetOnDrawerClosed_Landroid_view_View_Handler:Android.Support.V4.Widget.DrawerLayout/IDrawerListenerInvoker, Xamarin.Android.Support.Core.UI\nn_onDrawerOpened:(Landroid/view/View;)V:GetOnDrawerOpened_Landroid_view_View_Handler:Android.Support.V4.Widget.DrawerLayout/IDrawerListenerInvoker, Xamarin.Android.Support.Core.UI\nn_onDrawerSlide:(Landroid/view/View;F)V:GetOnDrawerSlide_Landroid_view_View_FHandler:Android.Support.V4.Widget.DrawerLayout/IDrawerListenerInvoker, Xamarin.Android.Support.Core.UI\nn_onDrawerStateChanged:(I)V:GetOnDrawerStateChanged_IHandler:Android.Support.V4.Widget.DrawerLayout/IDrawerListenerInvoker, Xamarin.Android.Support.Core.UI\n";
  
  private ArrayList refList;
  
  static {
    Runtime.register("Android.Support.V4.Widget.DrawerLayout+IDrawerListenerImplementor, Xamarin.Android.Support.Core.UI", DrawerLayout_DrawerListenerImplementor.class, "n_onDrawerClosed:(Landroid/view/View;)V:GetOnDrawerClosed_Landroid_view_View_Handler:Android.Support.V4.Widget.DrawerLayout/IDrawerListenerInvoker, Xamarin.Android.Support.Core.UI\nn_onDrawerOpened:(Landroid/view/View;)V:GetOnDrawerOpened_Landroid_view_View_Handler:Android.Support.V4.Widget.DrawerLayout/IDrawerListenerInvoker, Xamarin.Android.Support.Core.UI\nn_onDrawerSlide:(Landroid/view/View;F)V:GetOnDrawerSlide_Landroid_view_View_FHandler:Android.Support.V4.Widget.DrawerLayout/IDrawerListenerInvoker, Xamarin.Android.Support.Core.UI\nn_onDrawerStateChanged:(I)V:GetOnDrawerStateChanged_IHandler:Android.Support.V4.Widget.DrawerLayout/IDrawerListenerInvoker, Xamarin.Android.Support.Core.UI\n");
  }
  
  public DrawerLayout_DrawerListenerImplementor() {
    if (getClass() == DrawerLayout_DrawerListenerImplementor.class)
      TypeManager.Activate("Android.Support.V4.Widget.DrawerLayout+IDrawerListenerImplementor, Xamarin.Android.Support.Core.UI", "", this, new Object[0]); 
  }
  
  private native void n_onDrawerClosed(View paramView);
  
  private native void n_onDrawerOpened(View paramView);
  
  private native void n_onDrawerSlide(View paramView, float paramFloat);
  
  private native void n_onDrawerStateChanged(int paramInt);
  
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
  
  public void onDrawerClosed(View paramView) {
    n_onDrawerClosed(paramView);
  }
  
  public void onDrawerOpened(View paramView) {
    n_onDrawerOpened(paramView);
  }
  
  public void onDrawerSlide(View paramView, float paramFloat) {
    n_onDrawerSlide(paramView, paramFloat);
  }
  
  public void onDrawerStateChanged(int paramInt) {
    n_onDrawerStateChanged(paramInt);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/mono/android/support/v4/widget/DrawerLayout_DrawerListenerImplementor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */