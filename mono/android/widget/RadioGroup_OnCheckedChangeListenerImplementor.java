package mono.android.widget;

import android.widget.RadioGroup;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class RadioGroup_OnCheckedChangeListenerImplementor implements IGCUserPeer, RadioGroup.OnCheckedChangeListener {
  public static final String __md_methods = "n_onCheckedChanged:(Landroid/widget/RadioGroup;I)V:GetOnCheckedChanged_Landroid_widget_RadioGroup_IHandler:Android.Widget.RadioGroup/IOnCheckedChangeListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n";
  
  private ArrayList refList;
  
  static {
    Runtime.register("Android.Widget.RadioGroup+IOnCheckedChangeListenerImplementor, Mono.Android", RadioGroup_OnCheckedChangeListenerImplementor.class, "n_onCheckedChanged:(Landroid/widget/RadioGroup;I)V:GetOnCheckedChanged_Landroid_widget_RadioGroup_IHandler:Android.Widget.RadioGroup/IOnCheckedChangeListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n");
  }
  
  public RadioGroup_OnCheckedChangeListenerImplementor() {
    if (getClass() == RadioGroup_OnCheckedChangeListenerImplementor.class)
      TypeManager.Activate("Android.Widget.RadioGroup+IOnCheckedChangeListenerImplementor, Mono.Android", "", this, new Object[0]); 
  }
  
  private native void n_onCheckedChanged(RadioGroup paramRadioGroup, int paramInt);
  
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
  
  public void onCheckedChanged(RadioGroup paramRadioGroup, int paramInt) {
    n_onCheckedChanged(paramRadioGroup, paramInt);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/mono/android/widget/RadioGroup_OnCheckedChangeListenerImplementor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */