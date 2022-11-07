package mono.android.view;

import android.view.FrameMetrics;
import android.view.Window;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class Window_OnFrameMetricsAvailableListenerImplementor implements IGCUserPeer, Window.OnFrameMetricsAvailableListener {
  public static final String __md_methods = "n_onFrameMetricsAvailable:(Landroid/view/Window;Landroid/view/FrameMetrics;I)V:GetOnFrameMetricsAvailable_Landroid_view_Window_Landroid_view_FrameMetrics_IHandler:Android.Views.Window/IOnFrameMetricsAvailableListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n";
  
  private ArrayList refList;
  
  static {
    Runtime.register("Android.Views.Window+IOnFrameMetricsAvailableListenerImplementor, Mono.Android", Window_OnFrameMetricsAvailableListenerImplementor.class, "n_onFrameMetricsAvailable:(Landroid/view/Window;Landroid/view/FrameMetrics;I)V:GetOnFrameMetricsAvailable_Landroid_view_Window_Landroid_view_FrameMetrics_IHandler:Android.Views.Window/IOnFrameMetricsAvailableListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n");
  }
  
  public Window_OnFrameMetricsAvailableListenerImplementor() {
    if (getClass() == Window_OnFrameMetricsAvailableListenerImplementor.class)
      TypeManager.Activate("Android.Views.Window+IOnFrameMetricsAvailableListenerImplementor, Mono.Android", "", this, new Object[0]); 
  }
  
  private native void n_onFrameMetricsAvailable(Window paramWindow, FrameMetrics paramFrameMetrics, int paramInt);
  
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
  
  public void onFrameMetricsAvailable(Window paramWindow, FrameMetrics paramFrameMetrics, int paramInt) {
    n_onFrameMetricsAvailable(paramWindow, paramFrameMetrics, paramInt);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/mono/android/view/Window_OnFrameMetricsAvailableListenerImplementor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */