package mono.android.widget;

import android.view.View;
import android.widget.ExpandableListView;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class ExpandableListView_OnGroupClickListenerImplementor implements IGCUserPeer, ExpandableListView.OnGroupClickListener {
  public static final String __md_methods = "n_onGroupClick:(Landroid/widget/ExpandableListView;Landroid/view/View;IJ)Z:GetOnGroupClick_Landroid_widget_ExpandableListView_Landroid_view_View_IJHandler:Android.Widget.ExpandableListView/IOnGroupClickListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n";
  
  private ArrayList refList;
  
  static {
    Runtime.register("Android.Widget.ExpandableListView+IOnGroupClickListenerImplementor, Mono.Android", ExpandableListView_OnGroupClickListenerImplementor.class, "n_onGroupClick:(Landroid/widget/ExpandableListView;Landroid/view/View;IJ)Z:GetOnGroupClick_Landroid_widget_ExpandableListView_Landroid_view_View_IJHandler:Android.Widget.ExpandableListView/IOnGroupClickListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n");
  }
  
  public ExpandableListView_OnGroupClickListenerImplementor() {
    if (getClass() == ExpandableListView_OnGroupClickListenerImplementor.class)
      TypeManager.Activate("Android.Widget.ExpandableListView+IOnGroupClickListenerImplementor, Mono.Android", "", this, new Object[0]); 
  }
  
  private native boolean n_onGroupClick(ExpandableListView paramExpandableListView, View paramView, int paramInt, long paramLong);
  
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
  
  public boolean onGroupClick(ExpandableListView paramExpandableListView, View paramView, int paramInt, long paramLong) {
    return n_onGroupClick(paramExpandableListView, paramView, paramInt, paramLong);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/mono/android/widget/ExpandableListView_OnGroupClickListenerImplementor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */