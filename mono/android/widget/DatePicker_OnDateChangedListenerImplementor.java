package mono.android.widget;

import android.widget.DatePicker;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class DatePicker_OnDateChangedListenerImplementor implements IGCUserPeer, DatePicker.OnDateChangedListener {
  public static final String __md_methods = "n_onDateChanged:(Landroid/widget/DatePicker;III)V:GetOnDateChanged_Landroid_widget_DatePicker_IIIHandler:Android.Widget.DatePicker/IOnDateChangedListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n";
  
  private ArrayList refList;
  
  static {
    Runtime.register("Android.Widget.DatePicker+IOnDateChangedListenerImplementor, Mono.Android", DatePicker_OnDateChangedListenerImplementor.class, "n_onDateChanged:(Landroid/widget/DatePicker;III)V:GetOnDateChanged_Landroid_widget_DatePicker_IIIHandler:Android.Widget.DatePicker/IOnDateChangedListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n");
  }
  
  public DatePicker_OnDateChangedListenerImplementor() {
    if (getClass() == DatePicker_OnDateChangedListenerImplementor.class)
      TypeManager.Activate("Android.Widget.DatePicker+IOnDateChangedListenerImplementor, Mono.Android", "", this, new Object[0]); 
  }
  
  private native void n_onDateChanged(DatePicker paramDatePicker, int paramInt1, int paramInt2, int paramInt3);
  
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
  
  public void onDateChanged(DatePicker paramDatePicker, int paramInt1, int paramInt2, int paramInt3) {
    n_onDateChanged(paramDatePicker, paramInt1, paramInt2, paramInt3);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/mono/android/widget/DatePicker_OnDateChangedListenerImplementor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */