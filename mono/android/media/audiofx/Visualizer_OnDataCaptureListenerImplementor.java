package mono.android.media.audiofx;

import android.media.audiofx.Visualizer;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class Visualizer_OnDataCaptureListenerImplementor implements IGCUserPeer, Visualizer.OnDataCaptureListener {
  public static final String __md_methods = "n_onFftDataCapture:(Landroid/media/audiofx/Visualizer;[BI)V:GetOnFftDataCapture_Landroid_media_audiofx_Visualizer_arrayBIHandler:Android.Media.Audiofx.Visualizer/IOnDataCaptureListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\nn_onWaveFormDataCapture:(Landroid/media/audiofx/Visualizer;[BI)V:GetOnWaveFormDataCapture_Landroid_media_audiofx_Visualizer_arrayBIHandler:Android.Media.Audiofx.Visualizer/IOnDataCaptureListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n";
  
  private ArrayList refList;
  
  static {
    Runtime.register("Android.Media.Audiofx.Visualizer+IOnDataCaptureListenerImplementor, Mono.Android", Visualizer_OnDataCaptureListenerImplementor.class, "n_onFftDataCapture:(Landroid/media/audiofx/Visualizer;[BI)V:GetOnFftDataCapture_Landroid_media_audiofx_Visualizer_arrayBIHandler:Android.Media.Audiofx.Visualizer/IOnDataCaptureListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\nn_onWaveFormDataCapture:(Landroid/media/audiofx/Visualizer;[BI)V:GetOnWaveFormDataCapture_Landroid_media_audiofx_Visualizer_arrayBIHandler:Android.Media.Audiofx.Visualizer/IOnDataCaptureListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n");
  }
  
  public Visualizer_OnDataCaptureListenerImplementor() {
    if (getClass() == Visualizer_OnDataCaptureListenerImplementor.class)
      TypeManager.Activate("Android.Media.Audiofx.Visualizer+IOnDataCaptureListenerImplementor, Mono.Android", "", this, new Object[0]); 
  }
  
  private native void n_onFftDataCapture(Visualizer paramVisualizer, byte[] paramArrayOfbyte, int paramInt);
  
  private native void n_onWaveFormDataCapture(Visualizer paramVisualizer, byte[] paramArrayOfbyte, int paramInt);
  
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
  
  public void onFftDataCapture(Visualizer paramVisualizer, byte[] paramArrayOfbyte, int paramInt) {
    n_onFftDataCapture(paramVisualizer, paramArrayOfbyte, paramInt);
  }
  
  public void onWaveFormDataCapture(Visualizer paramVisualizer, byte[] paramArrayOfbyte, int paramInt) {
    n_onWaveFormDataCapture(paramVisualizer, paramArrayOfbyte, paramInt);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/mono/android/media/audiofx/Visualizer_OnDataCaptureListenerImplementor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */