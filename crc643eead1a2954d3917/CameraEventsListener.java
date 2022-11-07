package crc643eead1a2954d3917;

import android.hardware.Camera;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class CameraEventsListener implements IGCUserPeer, Camera.PreviewCallback, Camera.AutoFocusCallback {
  public static final String __md_methods = "n_onPreviewFrame:([BLandroid/hardware/Camera;)V:GetOnPreviewFrame_arrayBLandroid_hardware_Camera_Handler:ApxLabs.FastAndroidCamera.INonMarshalingPreviewCallbackInvoker, FastAndroidCamera, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\nn_onAutoFocus:(ZLandroid/hardware/Camera;)V:GetOnAutoFocus_ZLandroid_hardware_Camera_Handler:Android.Hardware.Camera/IAutoFocusCallbackInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n";
  
  private ArrayList refList;
  
  static {
    Runtime.register("ZXing.Mobile.CameraAccess.CameraEventsListener, ZXingNetMobile", CameraEventsListener.class, "n_onPreviewFrame:([BLandroid/hardware/Camera;)V:GetOnPreviewFrame_arrayBLandroid_hardware_Camera_Handler:ApxLabs.FastAndroidCamera.INonMarshalingPreviewCallbackInvoker, FastAndroidCamera, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\nn_onAutoFocus:(ZLandroid/hardware/Camera;)V:GetOnAutoFocus_ZLandroid_hardware_Camera_Handler:Android.Hardware.Camera/IAutoFocusCallbackInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n");
  }
  
  public CameraEventsListener() {
    if (getClass() == CameraEventsListener.class)
      TypeManager.Activate("ZXing.Mobile.CameraAccess.CameraEventsListener, ZXingNetMobile", "", this, new Object[0]); 
  }
  
  private native void n_onAutoFocus(boolean paramBoolean, Camera paramCamera);
  
  private native void n_onPreviewFrame(byte[] paramArrayOfbyte, Camera paramCamera);
  
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
  
  public void onAutoFocus(boolean paramBoolean, Camera paramCamera) {
    n_onAutoFocus(paramBoolean, paramCamera);
  }
  
  public void onPreviewFrame(byte[] paramArrayOfbyte, Camera paramCamera) {
    n_onPreviewFrame(paramArrayOfbyte, paramCamera);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/crc643eead1a2954d3917/CameraEventsListener.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */