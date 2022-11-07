package mono.android.widget;

import android.widget.Chronometer;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class Chronometer_OnChronometerTickListenerImplementor implements IGCUserPeer, Chronometer.OnChronometerTickListener {
  public static final String __md_methods = "n_onChronometerTick:(Landroid/widget/Chronometer;)V:GetOnChronometerTick_Landroid_widget_Chronometer_Handler:Android.Widget.Chronometer/IOnChronometerTickListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n";
  
  private ArrayList refList;
  
  static {
    Runtime.register("Android.Widget.Chronometer+IOnChronometerTickListenerImplementor, Mono.Android", Chronometer_OnChronometerTickListenerImplementor.class, "n_onChronometerTick:(Landroid/widget/Chronometer;)V:GetOnChronometerTick_Landroid_widget_Chronometer_Handler:Android.Widget.Chronometer/IOnChronometerTickListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n");
  }
  
  public Chronometer_OnChronometerTickListenerImplementor() {
    if (getClass() == Chronometer_OnChronometerTickListenerImplementor.class)
      TypeManager.Activate("Android.Widget.Chronometer+IOnChronometerTickListenerImplementor, Mono.Android", "", this, new Object[0]); 
  }
  
  private native void n_onChronometerTick(Chronometer paramChronometer);
  
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
  
  public void onChronometerTick(Chronometer paramChronometer) {
    n_onChronometerTick(paramChronometer);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/mono/android/widget/Chronometer_OnChronometerTickListenerImplementor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */