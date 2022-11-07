package mono.android.widget;

import android.view.View;
import android.widget.AbsListView;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class AbsListView_RecyclerListenerImplementor implements IGCUserPeer, AbsListView.RecyclerListener {
  public static final String __md_methods = "n_onMovedToScrapHeap:(Landroid/view/View;)V:GetOnMovedToScrapHeap_Landroid_view_View_Handler:Android.Widget.AbsListView/IRecyclerListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n";
  
  private ArrayList refList;
  
  static {
    Runtime.register("Android.Widget.AbsListView+IRecyclerListenerImplementor, Mono.Android", AbsListView_RecyclerListenerImplementor.class, "n_onMovedToScrapHeap:(Landroid/view/View;)V:GetOnMovedToScrapHeap_Landroid_view_View_Handler:Android.Widget.AbsListView/IRecyclerListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n");
  }
  
  public AbsListView_RecyclerListenerImplementor() {
    if (getClass() == AbsListView_RecyclerListenerImplementor.class)
      TypeManager.Activate("Android.Widget.AbsListView+IRecyclerListenerImplementor, Mono.Android", "", this, new Object[0]); 
  }
  
  private native void n_onMovedToScrapHeap(View paramView);
  
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
  
  public void onMovedToScrapHeap(View paramView) {
    n_onMovedToScrapHeap(paramView);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/mono/android/widget/AbsListView_RecyclerListenerImplementor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */