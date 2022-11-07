package mono.android.hardware;

import android.hardware.SensorListener;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class SensorListenerImplementor implements IGCUserPeer, SensorListener {
  public static final String __md_methods = "n_onAccuracyChanged:(II)V:GetOnAccuracyChanged_IIHandler:Android.Hardware.ISensorListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\nn_onSensorChanged:(I[F)V:GetOnSensorChanged_IarrayFHandler:Android.Hardware.ISensorListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n";
  
  private ArrayList refList;
  
  static {
    Runtime.register("Android.Hardware.ISensorListenerImplementor, Mono.Android", SensorListenerImplementor.class, "n_onAccuracyChanged:(II)V:GetOnAccuracyChanged_IIHandler:Android.Hardware.ISensorListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\nn_onSensorChanged:(I[F)V:GetOnSensorChanged_IarrayFHandler:Android.Hardware.ISensorListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n");
  }
  
  public SensorListenerImplementor() {
    if (getClass() == SensorListenerImplementor.class)
      TypeManager.Activate("Android.Hardware.ISensorListenerImplementor, Mono.Android", "", this, new Object[0]); 
  }
  
  private native void n_onAccuracyChanged(int paramInt1, int paramInt2);
  
  private native void n_onSensorChanged(int paramInt, float[] paramArrayOffloat);
  
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
  
  public void onAccuracyChanged(int paramInt1, int paramInt2) {
    n_onAccuracyChanged(paramInt1, paramInt2);
  }
  
  public void onSensorChanged(int paramInt, float[] paramArrayOffloat) {
    n_onSensorChanged(paramInt, paramArrayOffloat);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/mono/android/hardware/SensorListenerImplementor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */