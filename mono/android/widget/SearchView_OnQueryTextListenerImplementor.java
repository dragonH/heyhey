package mono.android.widget;

import android.widget.SearchView;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class SearchView_OnQueryTextListenerImplementor implements IGCUserPeer, SearchView.OnQueryTextListener {
  public static final String __md_methods = "n_onQueryTextChange:(Ljava/lang/String;)Z:GetOnQueryTextChange_Ljava_lang_String_Handler:Android.Widget.SearchView/IOnQueryTextListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\nn_onQueryTextSubmit:(Ljava/lang/String;)Z:GetOnQueryTextSubmit_Ljava_lang_String_Handler:Android.Widget.SearchView/IOnQueryTextListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n";
  
  private ArrayList refList;
  
  static {
    Runtime.register("Android.Widget.SearchView+IOnQueryTextListenerImplementor, Mono.Android", SearchView_OnQueryTextListenerImplementor.class, "n_onQueryTextChange:(Ljava/lang/String;)Z:GetOnQueryTextChange_Ljava_lang_String_Handler:Android.Widget.SearchView/IOnQueryTextListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\nn_onQueryTextSubmit:(Ljava/lang/String;)Z:GetOnQueryTextSubmit_Ljava_lang_String_Handler:Android.Widget.SearchView/IOnQueryTextListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n");
  }
  
  public SearchView_OnQueryTextListenerImplementor() {
    if (getClass() == SearchView_OnQueryTextListenerImplementor.class)
      TypeManager.Activate("Android.Widget.SearchView+IOnQueryTextListenerImplementor, Mono.Android", "", this, new Object[0]); 
  }
  
  private native boolean n_onQueryTextChange(String paramString);
  
  private native boolean n_onQueryTextSubmit(String paramString);
  
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
  
  public boolean onQueryTextChange(String paramString) {
    return n_onQueryTextChange(paramString);
  }
  
  public boolean onQueryTextSubmit(String paramString) {
    return n_onQueryTextSubmit(paramString);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/mono/android/widget/SearchView_OnQueryTextListenerImplementor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */