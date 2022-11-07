package mono.android.widget;

import android.widget.Filter;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class Filter_FilterListenerImplementor implements IGCUserPeer, Filter.FilterListener {
  public static final String __md_methods = "n_onFilterComplete:(I)V:GetOnFilterComplete_IHandler:Android.Widget.Filter/IFilterListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n";
  
  private ArrayList refList;
  
  static {
    Runtime.register("Android.Widget.Filter+IFilterListenerImplementor, Mono.Android", Filter_FilterListenerImplementor.class, "n_onFilterComplete:(I)V:GetOnFilterComplete_IHandler:Android.Widget.Filter/IFilterListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n");
  }
  
  public Filter_FilterListenerImplementor() {
    if (getClass() == Filter_FilterListenerImplementor.class)
      TypeManager.Activate("Android.Widget.Filter+IFilterListenerImplementor, Mono.Android", "", this, new Object[0]); 
  }
  
  private native void n_onFilterComplete(int paramInt);
  
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
  
  public void onFilterComplete(int paramInt) {
    n_onFilterComplete(paramInt);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/mono/android/widget/Filter_FilterListenerImplementor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */