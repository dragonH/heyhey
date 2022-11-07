package mono.android.app;

import android.app.TimePickerDialog;
import android.widget.TimePicker;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class TimePickerDialog_OnTimeSetListenerImplementor implements IGCUserPeer, TimePickerDialog.OnTimeSetListener {
  public static final String __md_methods = "n_onTimeSet:(Landroid/widget/TimePicker;II)V:GetOnTimeSet_Landroid_widget_TimePicker_IIHandler:Android.App.TimePickerDialog/IOnTimeSetListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n";
  
  private ArrayList refList;
  
  static {
    Runtime.register("Android.App.TimePickerDialog+IOnTimeSetListenerImplementor, Mono.Android", TimePickerDialog_OnTimeSetListenerImplementor.class, "n_onTimeSet:(Landroid/widget/TimePicker;II)V:GetOnTimeSet_Landroid_widget_TimePicker_IIHandler:Android.App.TimePickerDialog/IOnTimeSetListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n");
  }
  
  public TimePickerDialog_OnTimeSetListenerImplementor() {
    if (getClass() == TimePickerDialog_OnTimeSetListenerImplementor.class)
      TypeManager.Activate("Android.App.TimePickerDialog+IOnTimeSetListenerImplementor, Mono.Android", "", this, new Object[0]); 
  }
  
  private native void n_onTimeSet(TimePicker paramTimePicker, int paramInt1, int paramInt2);
  
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
  
  public void onTimeSet(TimePicker paramTimePicker, int paramInt1, int paramInt2) {
    n_onTimeSet(paramTimePicker, paramInt1, paramInt2);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/mono/android/app/TimePickerDialog_OnTimeSetListenerImplementor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */