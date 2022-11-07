package mono.android.app;

import android.app.DatePickerDialog;
import android.widget.DatePicker;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class DatePickerDialog_OnDateSetListenerImplementor implements IGCUserPeer, DatePickerDialog.OnDateSetListener {
  public static final String __md_methods = "n_onDateSet:(Landroid/widget/DatePicker;III)V:GetOnDateSet_Landroid_widget_DatePicker_IIIHandler:Android.App.DatePickerDialog/IOnDateSetListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n";
  
  private ArrayList refList;
  
  static {
    Runtime.register("Android.App.DatePickerDialog+IOnDateSetListenerImplementor, Mono.Android", DatePickerDialog_OnDateSetListenerImplementor.class, "n_onDateSet:(Landroid/widget/DatePicker;III)V:GetOnDateSet_Landroid_widget_DatePicker_IIIHandler:Android.App.DatePickerDialog/IOnDateSetListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n");
  }
  
  public DatePickerDialog_OnDateSetListenerImplementor() {
    if (getClass() == DatePickerDialog_OnDateSetListenerImplementor.class)
      TypeManager.Activate("Android.App.DatePickerDialog+IOnDateSetListenerImplementor, Mono.Android", "", this, new Object[0]); 
  }
  
  private native void n_onDateSet(DatePicker paramDatePicker, int paramInt1, int paramInt2, int paramInt3);
  
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
  
  public void onDateSet(DatePicker paramDatePicker, int paramInt1, int paramInt2, int paramInt3) {
    n_onDateSet(paramDatePicker, paramInt1, paramInt2, paramInt3);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/mono/android/app/DatePickerDialog_OnDateSetListenerImplementor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */