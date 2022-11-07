package mono.android.widget;

import android.view.View;
import android.widget.AdapterView;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class AdapterView_OnItemClickListenerImplementor implements IGCUserPeer, AdapterView.OnItemClickListener {
  public static final String __md_methods = "n_onItemClick:(Landroid/widget/AdapterView;Landroid/view/View;IJ)V:GetOnItemClick_Landroid_widget_AdapterView_Landroid_view_View_IJHandler:Android.Widget.AdapterView/IOnItemClickListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n";
  
  private ArrayList refList;
  
  static {
    Runtime.register("Android.Widget.AdapterView+IOnItemClickListenerImplementor, Mono.Android", AdapterView_OnItemClickListenerImplementor.class, "n_onItemClick:(Landroid/widget/AdapterView;Landroid/view/View;IJ)V:GetOnItemClick_Landroid_widget_AdapterView_Landroid_view_View_IJHandler:Android.Widget.AdapterView/IOnItemClickListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n");
  }
  
  public AdapterView_OnItemClickListenerImplementor() {
    if (getClass() == AdapterView_OnItemClickListenerImplementor.class)
      TypeManager.Activate("Android.Widget.AdapterView+IOnItemClickListenerImplementor, Mono.Android", "", this, new Object[0]); 
  }
  
  private native void n_onItemClick(AdapterView paramAdapterView, View paramView, int paramInt, long paramLong);
  
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
  
  public void onItemClick(AdapterView paramAdapterView, View paramView, int paramInt, long paramLong) {
    n_onItemClick(paramAdapterView, paramView, paramInt, paramLong);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/mono/android/widget/AdapterView_OnItemClickListenerImplementor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */