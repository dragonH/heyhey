package mono.android.support.design.widget;

import android.support.design.widget.AppBarLayout;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class AppBarLayout_OnOffsetChangedListenerImplementor implements IGCUserPeer, AppBarLayout.OnOffsetChangedListener {
  public static final String __md_methods = "n_onOffsetChanged:(Landroid/support/design/widget/AppBarLayout;I)V:GetOnOffsetChanged_Landroid_support_design_widget_AppBarLayout_IHandler:Android.Support.Design.Widget.AppBarLayout/IOnOffsetChangedListenerInvoker, Xamarin.Android.Support.Design\n";
  
  private ArrayList refList;
  
  static {
    Runtime.register("Android.Support.Design.Widget.AppBarLayout+IOnOffsetChangedListenerImplementor, Xamarin.Android.Support.Design", AppBarLayout_OnOffsetChangedListenerImplementor.class, "n_onOffsetChanged:(Landroid/support/design/widget/AppBarLayout;I)V:GetOnOffsetChanged_Landroid_support_design_widget_AppBarLayout_IHandler:Android.Support.Design.Widget.AppBarLayout/IOnOffsetChangedListenerInvoker, Xamarin.Android.Support.Design\n");
  }
  
  public AppBarLayout_OnOffsetChangedListenerImplementor() {
    if (getClass() == AppBarLayout_OnOffsetChangedListenerImplementor.class)
      TypeManager.Activate("Android.Support.Design.Widget.AppBarLayout+IOnOffsetChangedListenerImplementor, Xamarin.Android.Support.Design", "", this, new Object[0]); 
  }
  
  private native void n_onOffsetChanged(AppBarLayout paramAppBarLayout, int paramInt);
  
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
  
  public void onOffsetChanged(AppBarLayout paramAppBarLayout, int paramInt) {
    n_onOffsetChanged(paramAppBarLayout, paramInt);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/mono/android/support/design/widget/AppBarLayout_OnOffsetChangedListenerImplementor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */