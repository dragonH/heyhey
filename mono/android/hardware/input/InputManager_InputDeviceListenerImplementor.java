package mono.android.hardware.input;

import android.hardware.input.InputManager;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class InputManager_InputDeviceListenerImplementor implements IGCUserPeer, InputManager.InputDeviceListener {
  public static final String __md_methods = "n_onInputDeviceAdded:(I)V:GetOnInputDeviceAdded_IHandler:Android.Hardware.Input.InputManager/IInputDeviceListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\nn_onInputDeviceChanged:(I)V:GetOnInputDeviceChanged_IHandler:Android.Hardware.Input.InputManager/IInputDeviceListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\nn_onInputDeviceRemoved:(I)V:GetOnInputDeviceRemoved_IHandler:Android.Hardware.Input.InputManager/IInputDeviceListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n";
  
  private ArrayList refList;
  
  static {
    Runtime.register("Android.Hardware.Input.InputManager+IInputDeviceListenerImplementor, Mono.Android", InputManager_InputDeviceListenerImplementor.class, "n_onInputDeviceAdded:(I)V:GetOnInputDeviceAdded_IHandler:Android.Hardware.Input.InputManager/IInputDeviceListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\nn_onInputDeviceChanged:(I)V:GetOnInputDeviceChanged_IHandler:Android.Hardware.Input.InputManager/IInputDeviceListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\nn_onInputDeviceRemoved:(I)V:GetOnInputDeviceRemoved_IHandler:Android.Hardware.Input.InputManager/IInputDeviceListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n");
  }
  
  public InputManager_InputDeviceListenerImplementor() {
    if (getClass() == InputManager_InputDeviceListenerImplementor.class)
      TypeManager.Activate("Android.Hardware.Input.InputManager+IInputDeviceListenerImplementor, Mono.Android", "", this, new Object[0]); 
  }
  
  private native void n_onInputDeviceAdded(int paramInt);
  
  private native void n_onInputDeviceChanged(int paramInt);
  
  private native void n_onInputDeviceRemoved(int paramInt);
  
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
  
  public void onInputDeviceAdded(int paramInt) {
    n_onInputDeviceAdded(paramInt);
  }
  
  public void onInputDeviceChanged(int paramInt) {
    n_onInputDeviceChanged(paramInt);
  }
  
  public void onInputDeviceRemoved(int paramInt) {
    n_onInputDeviceRemoved(paramInt);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/mono/android/hardware/input/InputManager_InputDeviceListenerImplementor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */