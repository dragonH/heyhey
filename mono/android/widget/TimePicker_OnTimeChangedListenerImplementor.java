package mono.android.widget;

import android.widget.TimePicker;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class TimePicker_OnTimeChangedListenerImplementor implements IGCUserPeer, TimePicker.OnTimeChangedListener {
  public static final String __md_methods = "n_onTimeChanged:(Landroid/widget/TimePicker;II)V:GetOnTimeChanged_Landroid_widget_TimePicker_IIHandler:Android.Widget.TimePicker/IOnTimeChangedListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n";
  
  private ArrayList refList;
  
  static {
    Runtime.register("Android.Widget.TimePicker+IOnTimeChangedListenerImplementor, Mono.Android", TimePicker_OnTimeChangedListenerImplementor.class, "n_onTimeChanged:(Landroid/widget/TimePicker;II)V:GetOnTimeChanged_Landroid_widget_TimePicker_IIHandler:Android.Widget.TimePicker/IOnTimeChangedListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n");
  }
  
  public TimePicker_OnTimeChangedListenerImplementor() {
    if (getClass() == TimePicker_OnTimeChangedListenerImplementor.class)
      TypeManager.Activate("Android.Widget.TimePicker+IOnTimeChangedListenerImplementor, Mono.Android", "", this, new Object[0]); 
  }
  
  private native void n_onTimeChanged(TimePicker paramTimePicker, int paramInt1, int paramInt2);
  
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
  
  public void onTimeChanged(TimePicker paramTimePicker, int paramInt1, int paramInt2) {
    n_onTimeChanged(paramTimePicker, paramInt1, paramInt2);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/mono/android/widget/TimePicker_OnTimeChangedListenerImplementor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */