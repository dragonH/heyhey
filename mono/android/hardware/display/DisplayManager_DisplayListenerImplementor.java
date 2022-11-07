package mono.android.hardware.display;

import android.hardware.display.DisplayManager;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class DisplayManager_DisplayListenerImplementor implements IGCUserPeer, DisplayManager.DisplayListener {
  public static final String __md_methods = "n_onDisplayAdded:(I)V:GetOnDisplayAdded_IHandler:Android.Hardware.Display.DisplayManager/IDisplayListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\nn_onDisplayChanged:(I)V:GetOnDisplayChanged_IHandler:Android.Hardware.Display.DisplayManager/IDisplayListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\nn_onDisplayRemoved:(I)V:GetOnDisplayRemoved_IHandler:Android.Hardware.Display.DisplayManager/IDisplayListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n";
  
  private ArrayList refList;
  
  static {
    Runtime.register("Android.Hardware.Display.DisplayManager+IDisplayListenerImplementor, Mono.Android", DisplayManager_DisplayListenerImplementor.class, "n_onDisplayAdded:(I)V:GetOnDisplayAdded_IHandler:Android.Hardware.Display.DisplayManager/IDisplayListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\nn_onDisplayChanged:(I)V:GetOnDisplayChanged_IHandler:Android.Hardware.Display.DisplayManager/IDisplayListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\nn_onDisplayRemoved:(I)V:GetOnDisplayRemoved_IHandler:Android.Hardware.Display.DisplayManager/IDisplayListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n");
  }
  
  public DisplayManager_DisplayListenerImplementor() {
    if (getClass() == DisplayManager_DisplayListenerImplementor.class)
      TypeManager.Activate("Android.Hardware.Display.DisplayManager+IDisplayListenerImplementor, Mono.Android", "", this, new Object[0]); 
  }
  
  private native void n_onDisplayAdded(int paramInt);
  
  private native void n_onDisplayChanged(int paramInt);
  
  private native void n_onDisplayRemoved(int paramInt);
  
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
  
  public void onDisplayAdded(int paramInt) {
    n_onDisplayAdded(paramInt);
  }
  
  public void onDisplayChanged(int paramInt) {
    n_onDisplayChanged(paramInt);
  }
  
  public void onDisplayRemoved(int paramInt) {
    n_onDisplayRemoved(paramInt);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/mono/android/hardware/display/DisplayManager_DisplayListenerImplementor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */