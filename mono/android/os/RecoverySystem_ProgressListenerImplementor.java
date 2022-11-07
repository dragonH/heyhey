package mono.android.os;

import android.os.RecoverySystem;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class RecoverySystem_ProgressListenerImplementor implements IGCUserPeer, RecoverySystem.ProgressListener {
  public static final String __md_methods = "n_onProgress:(I)V:GetOnProgress_IHandler:Android.OS.RecoverySystem/IProgressListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n";
  
  private ArrayList refList;
  
  static {
    Runtime.register("Android.OS.RecoverySystem+IProgressListenerImplementor, Mono.Android", RecoverySystem_ProgressListenerImplementor.class, "n_onProgress:(I)V:GetOnProgress_IHandler:Android.OS.RecoverySystem/IProgressListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n");
  }
  
  public RecoverySystem_ProgressListenerImplementor() {
    if (getClass() == RecoverySystem_ProgressListenerImplementor.class)
      TypeManager.Activate("Android.OS.RecoverySystem+IProgressListenerImplementor, Mono.Android", "", this, new Object[0]); 
  }
  
  private native void n_onProgress(int paramInt);
  
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
  
  public void onProgress(int paramInt) {
    n_onProgress(paramInt);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/mono/android/os/RecoverySystem_ProgressListenerImplementor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */