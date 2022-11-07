package mono.android.widget;

import android.widget.TabHost;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class TabHost_OnTabChangeListenerImplementor implements IGCUserPeer, TabHost.OnTabChangeListener {
  public static final String __md_methods = "n_onTabChanged:(Ljava/lang/String;)V:GetOnTabChanged_Ljava_lang_String_Handler:Android.Widget.TabHost/IOnTabChangeListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n";
  
  private ArrayList refList;
  
  static {
    Runtime.register("Android.Widget.TabHost+IOnTabChangeListenerImplementor, Mono.Android", TabHost_OnTabChangeListenerImplementor.class, "n_onTabChanged:(Ljava/lang/String;)V:GetOnTabChanged_Ljava_lang_String_Handler:Android.Widget.TabHost/IOnTabChangeListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n");
  }
  
  public TabHost_OnTabChangeListenerImplementor() {
    if (getClass() == TabHost_OnTabChangeListenerImplementor.class)
      TypeManager.Activate("Android.Widget.TabHost+IOnTabChangeListenerImplementor, Mono.Android", "", this, new Object[0]); 
  }
  
  private native void n_onTabChanged(String paramString);
  
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
  
  public void onTabChanged(String paramString) {
    n_onTabChanged(paramString);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/mono/android/widget/TabHost_OnTabChangeListenerImplementor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */