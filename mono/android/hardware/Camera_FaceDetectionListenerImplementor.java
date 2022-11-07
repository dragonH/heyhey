package mono.android.hardware;

import android.hardware.Camera;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class Camera_FaceDetectionListenerImplementor implements IGCUserPeer, Camera.FaceDetectionListener {
  public static final String __md_methods = "n_onFaceDetection:([Landroid/hardware/Camera$Face;Landroid/hardware/Camera;)V:GetOnFaceDetection_arrayLandroid_hardware_Camera_Face_Landroid_hardware_Camera_Handler:Android.Hardware.Camera/IFaceDetectionListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n";
  
  private ArrayList refList;
  
  static {
    Runtime.register("Android.Hardware.Camera+IFaceDetectionListenerImplementor, Mono.Android", Camera_FaceDetectionListenerImplementor.class, "n_onFaceDetection:([Landroid/hardware/Camera$Face;Landroid/hardware/Camera;)V:GetOnFaceDetection_arrayLandroid_hardware_Camera_Face_Landroid_hardware_Camera_Handler:Android.Hardware.Camera/IFaceDetectionListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n");
  }
  
  public Camera_FaceDetectionListenerImplementor() {
    if (getClass() == Camera_FaceDetectionListenerImplementor.class)
      TypeManager.Activate("Android.Hardware.Camera+IFaceDetectionListenerImplementor, Mono.Android", "", this, new Object[0]); 
  }
  
  private native void n_onFaceDetection(Camera.Face[] paramArrayOfFace, Camera paramCamera);
  
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
  
  public void onFaceDetection(Camera.Face[] paramArrayOfFace, Camera paramCamera) {
    n_onFaceDetection(paramArrayOfFace, paramCamera);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/mono/android/hardware/Camera_FaceDetectionListenerImplementor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */