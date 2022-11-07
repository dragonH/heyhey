package mono.android.hardware;

import android.hardware.Camera;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class Camera_OnZoomChangeListenerImplementor implements IGCUserPeer, Camera.OnZoomChangeListener {
  public static final String __md_methods = "n_onZoomChange:(IZLandroid/hardware/Camera;)V:GetOnZoomChange_IZLandroid_hardware_Camera_Handler:Android.Hardware.Camera/IOnZoomChangeListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n";
  
  private ArrayList refList;
  
  static {
    Runtime.register("Android.Hardware.Camera+IOnZoomChangeListenerImplementor, Mono.Android", Camera_OnZoomChangeListenerImplementor.class, "n_onZoomChange:(IZLandroid/hardware/Camera;)V:GetOnZoomChange_IZLandroid_hardware_Camera_Handler:Android.Hardware.Camera/IOnZoomChangeListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n");
  }
  
  public Camera_OnZoomChangeListenerImplementor() {
    if (getClass() == Camera_OnZoomChangeListenerImplementor.class)
      TypeManager.Activate("Android.Hardware.Camera+IOnZoomChangeListenerImplementor, Mono.Android", "", this, new Object[0]); 
  }
  
  private native void n_onZoomChange(int paramInt, boolean paramBoolean, Camera paramCamera);
  
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
  
  public void onZoomChange(int paramInt, boolean paramBoolean, Camera paramCamera) {
    n_onZoomChange(paramInt, paramBoolean, paramCamera);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/mono/android/hardware/Camera_OnZoomChangeListenerImplementor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */