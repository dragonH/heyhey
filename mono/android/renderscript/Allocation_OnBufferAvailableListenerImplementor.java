package mono.android.renderscript;

import android.renderscript.Allocation;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class Allocation_OnBufferAvailableListenerImplementor implements IGCUserPeer, Allocation.OnBufferAvailableListener {
  public static final String __md_methods = "n_onBufferAvailable:(Landroid/renderscript/Allocation;)V:GetOnBufferAvailable_Landroid_renderscript_Allocation_Handler:Android.Renderscripts.Allocation/IOnBufferAvailableListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n";
  
  private ArrayList refList;
  
  static {
    Runtime.register("Android.Renderscripts.Allocation+IOnBufferAvailableListenerImplementor, Mono.Android", Allocation_OnBufferAvailableListenerImplementor.class, "n_onBufferAvailable:(Landroid/renderscript/Allocation;)V:GetOnBufferAvailable_Landroid_renderscript_Allocation_Handler:Android.Renderscripts.Allocation/IOnBufferAvailableListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n");
  }
  
  public Allocation_OnBufferAvailableListenerImplementor() {
    if (getClass() == Allocation_OnBufferAvailableListenerImplementor.class)
      TypeManager.Activate("Android.Renderscripts.Allocation+IOnBufferAvailableListenerImplementor, Mono.Android", "", this, new Object[0]); 
  }
  
  private native void n_onBufferAvailable(Allocation paramAllocation);
  
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
  
  public void onBufferAvailable(Allocation paramAllocation) {
    n_onBufferAvailable(paramAllocation);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/mono/android/renderscript/Allocation_OnBufferAvailableListenerImplementor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */