package mono.android.widget;

import android.widget.SearchView;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class SearchView_OnSuggestionListenerImplementor implements IGCUserPeer, SearchView.OnSuggestionListener {
  public static final String __md_methods = "n_onSuggestionClick:(I)Z:GetOnSuggestionClick_IHandler:Android.Widget.SearchView/IOnSuggestionListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\nn_onSuggestionSelect:(I)Z:GetOnSuggestionSelect_IHandler:Android.Widget.SearchView/IOnSuggestionListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n";
  
  private ArrayList refList;
  
  static {
    Runtime.register("Android.Widget.SearchView+IOnSuggestionListenerImplementor, Mono.Android", SearchView_OnSuggestionListenerImplementor.class, "n_onSuggestionClick:(I)Z:GetOnSuggestionClick_IHandler:Android.Widget.SearchView/IOnSuggestionListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\nn_onSuggestionSelect:(I)Z:GetOnSuggestionSelect_IHandler:Android.Widget.SearchView/IOnSuggestionListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n");
  }
  
  public SearchView_OnSuggestionListenerImplementor() {
    if (getClass() == SearchView_OnSuggestionListenerImplementor.class)
      TypeManager.Activate("Android.Widget.SearchView+IOnSuggestionListenerImplementor, Mono.Android", "", this, new Object[0]); 
  }
  
  private native boolean n_onSuggestionClick(int paramInt);
  
  private native boolean n_onSuggestionSelect(int paramInt);
  
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
  
  public boolean onSuggestionClick(int paramInt) {
    return n_onSuggestionClick(paramInt);
  }
  
  public boolean onSuggestionSelect(int paramInt) {
    return n_onSuggestionSelect(paramInt);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/mono/android/widget/SearchView_OnSuggestionListenerImplementor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */