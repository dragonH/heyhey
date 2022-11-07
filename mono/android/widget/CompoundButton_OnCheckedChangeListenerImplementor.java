package mono.android.widget;

import android.widget.CompoundButton;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class CompoundButton_OnCheckedChangeListenerImplementor implements IGCUserPeer, CompoundButton.OnCheckedChangeListener {
  public static final String __md_methods = "n_onCheckedChanged:(Landroid/widget/CompoundButton;Z)V:GetOnCheckedChanged_Landroid_widget_CompoundButton_ZHandler:Android.Widget.CompoundButton/IOnCheckedChangeListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n";
  
  private ArrayList refList;
  
  static {
    Runtime.register("Android.Widget.CompoundButton+IOnCheckedChangeListenerImplementor, Mono.Android", CompoundButton_OnCheckedChangeListenerImplementor.class, "n_onCheckedChanged:(Landroid/widget/CompoundButton;Z)V:GetOnCheckedChanged_Landroid_widget_CompoundButton_ZHandler:Android.Widget.CompoundButton/IOnCheckedChangeListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n");
  }
  
  public CompoundButton_OnCheckedChangeListenerImplementor() {
    if (getClass() == CompoundButton_OnCheckedChangeListenerImplementor.class)
      TypeManager.Activate("Android.Widget.CompoundButton+IOnCheckedChangeListenerImplementor, Mono.Android", "", this, new Object[0]); 
  }
  
  private native void n_onCheckedChanged(CompoundButton paramCompoundButton, boolean paramBoolean);
  
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
  
  public void onCheckedChanged(CompoundButton paramCompoundButton, boolean paramBoolean) {
    n_onCheckedChanged(paramCompoundButton, paramBoolean);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/mono/android/widget/CompoundButton_OnCheckedChangeListenerImplementor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */