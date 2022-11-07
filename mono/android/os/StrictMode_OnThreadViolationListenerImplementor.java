package mono.android.os;

import android.os.StrictMode;
import android.os.strictmode.Violation;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class StrictMode_OnThreadViolationListenerImplementor implements IGCUserPeer, StrictMode.OnThreadViolationListener {
  public static final String __md_methods = "n_onThreadViolation:(Landroid/os/strictmode/Violation;)V:GetOnThreadViolation_Landroid_os_strictmode_Violation_Handler:Android.OS.StrictMode/IOnThreadViolationListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n";
  
  private ArrayList refList;
  
  static {
    Runtime.register("Android.OS.StrictMode+IOnThreadViolationListenerImplementor, Mono.Android", StrictMode_OnThreadViolationListenerImplementor.class, "n_onThreadViolation:(Landroid/os/strictmode/Violation;)V:GetOnThreadViolation_Landroid_os_strictmode_Violation_Handler:Android.OS.StrictMode/IOnThreadViolationListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n");
  }
  
  public StrictMode_OnThreadViolationListenerImplementor() {
    if (getClass() == StrictMode_OnThreadViolationListenerImplementor.class)
      TypeManager.Activate("Android.OS.StrictMode+IOnThreadViolationListenerImplementor, Mono.Android", "", this, new Object[0]); 
  }
  
  private native void n_onThreadViolation(Violation paramViolation);
  
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
  
  public void onThreadViolation(Violation paramViolation) {
    n_onThreadViolation(paramViolation);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/mono/android/os/StrictMode_OnThreadViolationListenerImplementor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */