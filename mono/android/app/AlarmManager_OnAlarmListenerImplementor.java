package mono.android.app;

import android.app.AlarmManager;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class AlarmManager_OnAlarmListenerImplementor implements IGCUserPeer, AlarmManager.OnAlarmListener {
  public static final String __md_methods = "n_onAlarm:()V:GetOnAlarmHandler:Android.App.AlarmManager/IOnAlarmListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n";
  
  private ArrayList refList;
  
  static {
    Runtime.register("Android.App.AlarmManager+IOnAlarmListenerImplementor, Mono.Android", AlarmManager_OnAlarmListenerImplementor.class, "n_onAlarm:()V:GetOnAlarmHandler:Android.App.AlarmManager/IOnAlarmListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n");
  }
  
  public AlarmManager_OnAlarmListenerImplementor() {
    if (getClass() == AlarmManager_OnAlarmListenerImplementor.class)
      TypeManager.Activate("Android.App.AlarmManager+IOnAlarmListenerImplementor, Mono.Android", "", this, new Object[0]); 
  }
  
  private native void n_onAlarm();
  
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
  
  public void onAlarm() {
    n_onAlarm();
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/mono/android/app/AlarmManager_OnAlarmListenerImplementor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */