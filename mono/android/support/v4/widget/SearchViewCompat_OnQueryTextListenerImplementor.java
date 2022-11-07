package mono.android.support.v4.widget;

import android.support.v4.widget.SearchViewCompat;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class SearchViewCompat_OnQueryTextListenerImplementor implements IGCUserPeer, SearchViewCompat.OnQueryTextListener {
  public static final String __md_methods = "n_onQueryTextChange:(Ljava/lang/String;)Z:GetOnQueryTextChange_Ljava_lang_String_Handler:Android.Support.V4.Widget.SearchViewCompat/IOnQueryTextListenerInvoker, Xamarin.Android.Support.Compat\nn_onQueryTextSubmit:(Ljava/lang/String;)Z:GetOnQueryTextSubmit_Ljava_lang_String_Handler:Android.Support.V4.Widget.SearchViewCompat/IOnQueryTextListenerInvoker, Xamarin.Android.Support.Compat\n";
  
  private ArrayList refList;
  
  static {
    Runtime.register("Android.Support.V4.Widget.SearchViewCompat+IOnQueryTextListenerImplementor, Xamarin.Android.Support.Compat", SearchViewCompat_OnQueryTextListenerImplementor.class, "n_onQueryTextChange:(Ljava/lang/String;)Z:GetOnQueryTextChange_Ljava_lang_String_Handler:Android.Support.V4.Widget.SearchViewCompat/IOnQueryTextListenerInvoker, Xamarin.Android.Support.Compat\nn_onQueryTextSubmit:(Ljava/lang/String;)Z:GetOnQueryTextSubmit_Ljava_lang_String_Handler:Android.Support.V4.Widget.SearchViewCompat/IOnQueryTextListenerInvoker, Xamarin.Android.Support.Compat\n");
  }
  
  public SearchViewCompat_OnQueryTextListenerImplementor() {
    if (getClass() == SearchViewCompat_OnQueryTextListenerImplementor.class)
      TypeManager.Activate("Android.Support.V4.Widget.SearchViewCompat+IOnQueryTextListenerImplementor, Xamarin.Android.Support.Compat", "", this, new Object[0]); 
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


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/mono/android/support/v4/widget/SearchViewCompat_OnQueryTextListenerImplementor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */