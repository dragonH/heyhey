package mono.android.widget;

import android.widget.ExpandableListView;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class ExpandableListView_OnGroupExpandListenerImplementor implements IGCUserPeer, ExpandableListView.OnGroupExpandListener {
  public static final String __md_methods = "n_onGroupExpand:(I)V:GetOnGroupExpand_IHandler:Android.Widget.ExpandableListView/IOnGroupExpandListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n";
  
  private ArrayList refList;
  
  static {
    Runtime.register("Android.Widget.ExpandableListView+IOnGroupExpandListenerImplementor, Mono.Android", ExpandableListView_OnGroupExpandListenerImplementor.class, "n_onGroupExpand:(I)V:GetOnGroupExpand_IHandler:Android.Widget.ExpandableListView/IOnGroupExpandListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n");
  }
  
  public ExpandableListView_OnGroupExpandListenerImplementor() {
    if (getClass() == ExpandableListView_OnGroupExpandListenerImplementor.class)
      TypeManager.Activate("Android.Widget.ExpandableListView+IOnGroupExpandListenerImplementor, Mono.Android", "", this, new Object[0]); 
  }
  
  private native void n_onGroupExpand(int paramInt);
  
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
  
  public void onGroupExpand(int paramInt) {
    n_onGroupExpand(paramInt);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/mono/android/widget/ExpandableListView_OnGroupExpandListenerImplementor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */