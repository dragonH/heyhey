package mono.android.widget;

import android.widget.ExpandableListView;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class ExpandableListView_OnGroupCollapseListenerImplementor implements IGCUserPeer, ExpandableListView.OnGroupCollapseListener {
  public static final String __md_methods = "n_onGroupCollapse:(I)V:GetOnGroupCollapse_IHandler:Android.Widget.ExpandableListView/IOnGroupCollapseListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n";
  
  private ArrayList refList;
  
  static {
    Runtime.register("Android.Widget.ExpandableListView+IOnGroupCollapseListenerImplementor, Mono.Android", ExpandableListView_OnGroupCollapseListenerImplementor.class, "n_onGroupCollapse:(I)V:GetOnGroupCollapse_IHandler:Android.Widget.ExpandableListView/IOnGroupCollapseListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n");
  }
  
  public ExpandableListView_OnGroupCollapseListenerImplementor() {
    if (getClass() == ExpandableListView_OnGroupCollapseListenerImplementor.class)
      TypeManager.Activate("Android.Widget.ExpandableListView+IOnGroupCollapseListenerImplementor, Mono.Android", "", this, new Object[0]); 
  }
  
  private native void n_onGroupCollapse(int paramInt);
  
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
  
  public void onGroupCollapse(int paramInt) {
    n_onGroupCollapse(paramInt);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/mono/android/widget/ExpandableListView_OnGroupCollapseListenerImplementor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */