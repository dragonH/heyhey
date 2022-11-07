package mono.android.support.v7.widget;

import android.support.v7.widget.RecyclerView;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class RecyclerView_RecyclerListenerImplementor implements IGCUserPeer, RecyclerView.RecyclerListener {
  public static final String __md_methods = "n_onViewRecycled:(Landroid/support/v7/widget/RecyclerView$ViewHolder;)V:GetOnViewRecycled_Landroid_support_v7_widget_RecyclerView_ViewHolder_Handler:Android.Support.V7.Widget.RecyclerView/IRecyclerListenerInvoker, Xamarin.Android.Support.v7.RecyclerView\n";
  
  private ArrayList refList;
  
  static {
    Runtime.register("Android.Support.V7.Widget.RecyclerView+IRecyclerListenerImplementor, Xamarin.Android.Support.v7.RecyclerView", RecyclerView_RecyclerListenerImplementor.class, "n_onViewRecycled:(Landroid/support/v7/widget/RecyclerView$ViewHolder;)V:GetOnViewRecycled_Landroid_support_v7_widget_RecyclerView_ViewHolder_Handler:Android.Support.V7.Widget.RecyclerView/IRecyclerListenerInvoker, Xamarin.Android.Support.v7.RecyclerView\n");
  }
  
  public RecyclerView_RecyclerListenerImplementor() {
    if (getClass() == RecyclerView_RecyclerListenerImplementor.class)
      TypeManager.Activate("Android.Support.V7.Widget.RecyclerView+IRecyclerListenerImplementor, Xamarin.Android.Support.v7.RecyclerView", "", this, new Object[0]); 
  }
  
  private native void n_onViewRecycled(RecyclerView.ViewHolder paramViewHolder);
  
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
  
  public void onViewRecycled(RecyclerView.ViewHolder paramViewHolder) {
    n_onViewRecycled(paramViewHolder);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/mono/android/support/v7/widget/RecyclerView_RecyclerListenerImplementor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */