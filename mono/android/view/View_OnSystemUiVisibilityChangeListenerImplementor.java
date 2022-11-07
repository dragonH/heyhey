package mono.android.view;

import android.view.View;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class View_OnSystemUiVisibilityChangeListenerImplementor implements IGCUserPeer, View.OnSystemUiVisibilityChangeListener {
  public static final String __md_methods = "n_onSystemUiVisibilityChange:(I)V:GetOnSystemUiVisibilityChange_IHandler:Android.Views.View/IOnSystemUiVisibilityChangeListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n";
  
  private ArrayList refList;
  
  static {
    Runtime.register("Android.Views.View+IOnSystemUiVisibilityChangeListenerImplementor, Mono.Android", View_OnSystemUiVisibilityChangeListenerImplementor.class, "n_onSystemUiVisibilityChange:(I)V:GetOnSystemUiVisibilityChange_IHandler:Android.Views.View/IOnSystemUiVisibilityChangeListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n");
  }
  
  public View_OnSystemUiVisibilityChangeListenerImplementor() {
    if (getClass() == View_OnSystemUiVisibilityChangeListenerImplementor.class)
      TypeManager.Activate("Android.Views.View+IOnSystemUiVisibilityChangeListenerImplementor, Mono.Android", "", this, new Object[0]); 
  }
  
  private native void n_onSystemUiVisibilityChange(int paramInt);
  
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
  
  public void onSystemUiVisibilityChange(int paramInt) {
    n_onSystemUiVisibilityChange(paramInt);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/mono/android/view/View_OnSystemUiVisibilityChangeListenerImplementor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */