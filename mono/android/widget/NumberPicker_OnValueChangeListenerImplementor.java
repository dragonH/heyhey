package mono.android.widget;

import android.widget.NumberPicker;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class NumberPicker_OnValueChangeListenerImplementor implements IGCUserPeer, NumberPicker.OnValueChangeListener {
  public static final String __md_methods = "n_onValueChange:(Landroid/widget/NumberPicker;II)V:GetOnValueChange_Landroid_widget_NumberPicker_IIHandler:Android.Widget.NumberPicker/IOnValueChangeListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n";
  
  private ArrayList refList;
  
  static {
    Runtime.register("Android.Widget.NumberPicker+IOnValueChangeListenerImplementor, Mono.Android", NumberPicker_OnValueChangeListenerImplementor.class, "n_onValueChange:(Landroid/widget/NumberPicker;II)V:GetOnValueChange_Landroid_widget_NumberPicker_IIHandler:Android.Widget.NumberPicker/IOnValueChangeListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n");
  }
  
  public NumberPicker_OnValueChangeListenerImplementor() {
    if (getClass() == NumberPicker_OnValueChangeListenerImplementor.class)
      TypeManager.Activate("Android.Widget.NumberPicker+IOnValueChangeListenerImplementor, Mono.Android", "", this, new Object[0]); 
  }
  
  private native void n_onValueChange(NumberPicker paramNumberPicker, int paramInt1, int paramInt2);
  
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
  
  public void onValueChange(NumberPicker paramNumberPicker, int paramInt1, int paramInt2) {
    n_onValueChange(paramNumberPicker, paramInt1, paramInt2);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/mono/android/widget/NumberPicker_OnValueChangeListenerImplementor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */