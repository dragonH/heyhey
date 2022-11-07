package mono.android.support.v4.widget;

import android.support.v4.widget.SlidingPaneLayout;
import android.view.View;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class SlidingPaneLayout_PanelSlideListenerImplementor implements IGCUserPeer, SlidingPaneLayout.PanelSlideListener {
  public static final String __md_methods = "n_onPanelClosed:(Landroid/view/View;)V:GetOnPanelClosed_Landroid_view_View_Handler:Android.Support.V4.Widget.SlidingPaneLayout/IPanelSlideListenerInvoker, Xamarin.Android.Support.Core.UI\nn_onPanelOpened:(Landroid/view/View;)V:GetOnPanelOpened_Landroid_view_View_Handler:Android.Support.V4.Widget.SlidingPaneLayout/IPanelSlideListenerInvoker, Xamarin.Android.Support.Core.UI\nn_onPanelSlide:(Landroid/view/View;F)V:GetOnPanelSlide_Landroid_view_View_FHandler:Android.Support.V4.Widget.SlidingPaneLayout/IPanelSlideListenerInvoker, Xamarin.Android.Support.Core.UI\n";
  
  private ArrayList refList;
  
  static {
    Runtime.register("Android.Support.V4.Widget.SlidingPaneLayout+IPanelSlideListenerImplementor, Xamarin.Android.Support.Core.UI", SlidingPaneLayout_PanelSlideListenerImplementor.class, "n_onPanelClosed:(Landroid/view/View;)V:GetOnPanelClosed_Landroid_view_View_Handler:Android.Support.V4.Widget.SlidingPaneLayout/IPanelSlideListenerInvoker, Xamarin.Android.Support.Core.UI\nn_onPanelOpened:(Landroid/view/View;)V:GetOnPanelOpened_Landroid_view_View_Handler:Android.Support.V4.Widget.SlidingPaneLayout/IPanelSlideListenerInvoker, Xamarin.Android.Support.Core.UI\nn_onPanelSlide:(Landroid/view/View;F)V:GetOnPanelSlide_Landroid_view_View_FHandler:Android.Support.V4.Widget.SlidingPaneLayout/IPanelSlideListenerInvoker, Xamarin.Android.Support.Core.UI\n");
  }
  
  public SlidingPaneLayout_PanelSlideListenerImplementor() {
    if (getClass() == SlidingPaneLayout_PanelSlideListenerImplementor.class)
      TypeManager.Activate("Android.Support.V4.Widget.SlidingPaneLayout+IPanelSlideListenerImplementor, Xamarin.Android.Support.Core.UI", "", this, new Object[0]); 
  }
  
  private native void n_onPanelClosed(View paramView);
  
  private native void n_onPanelOpened(View paramView);
  
  private native void n_onPanelSlide(View paramView, float paramFloat);
  
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
  
  public void onPanelClosed(View paramView) {
    n_onPanelClosed(paramView);
  }
  
  public void onPanelOpened(View paramView) {
    n_onPanelOpened(paramView);
  }
  
  public void onPanelSlide(View paramView, float paramFloat) {
    n_onPanelSlide(paramView, paramFloat);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/mono/android/support/v4/widget/SlidingPaneLayout_PanelSlideListenerImplementor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */