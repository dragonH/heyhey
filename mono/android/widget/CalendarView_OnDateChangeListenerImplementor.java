package mono.android.widget;

import android.widget.CalendarView;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class CalendarView_OnDateChangeListenerImplementor implements IGCUserPeer, CalendarView.OnDateChangeListener {
  public static final String __md_methods = "n_onSelectedDayChange:(Landroid/widget/CalendarView;III)V:GetOnSelectedDayChange_Landroid_widget_CalendarView_IIIHandler:Android.Widget.CalendarView/IOnDateChangeListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n";
  
  private ArrayList refList;
  
  static {
    Runtime.register("Android.Widget.CalendarView+IOnDateChangeListenerImplementor, Mono.Android", CalendarView_OnDateChangeListenerImplementor.class, "n_onSelectedDayChange:(Landroid/widget/CalendarView;III)V:GetOnSelectedDayChange_Landroid_widget_CalendarView_IIIHandler:Android.Widget.CalendarView/IOnDateChangeListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n");
  }
  
  public CalendarView_OnDateChangeListenerImplementor() {
    if (getClass() == CalendarView_OnDateChangeListenerImplementor.class)
      TypeManager.Activate("Android.Widget.CalendarView+IOnDateChangeListenerImplementor, Mono.Android", "", this, new Object[0]); 
  }
  
  private native void n_onSelectedDayChange(CalendarView paramCalendarView, int paramInt1, int paramInt2, int paramInt3);
  
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
  
  public void onSelectedDayChange(CalendarView paramCalendarView, int paramInt1, int paramInt2, int paramInt3) {
    n_onSelectedDayChange(paramCalendarView, paramInt1, paramInt2, paramInt3);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/mono/android/widget/CalendarView_OnDateChangeListenerImplementor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */