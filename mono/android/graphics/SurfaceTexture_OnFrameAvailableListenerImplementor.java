package mono.android.graphics;

import android.graphics.SurfaceTexture;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class SurfaceTexture_OnFrameAvailableListenerImplementor implements IGCUserPeer, SurfaceTexture.OnFrameAvailableListener {
  public static final String __md_methods = "n_onFrameAvailable:(Landroid/graphics/SurfaceTexture;)V:GetOnFrameAvailable_Landroid_graphics_SurfaceTexture_Handler:Android.Graphics.SurfaceTexture/IOnFrameAvailableListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n";
  
  private ArrayList refList;
  
  static {
    Runtime.register("Android.Graphics.SurfaceTexture+IOnFrameAvailableListenerImplementor, Mono.Android", SurfaceTexture_OnFrameAvailableListenerImplementor.class, "n_onFrameAvailable:(Landroid/graphics/SurfaceTexture;)V:GetOnFrameAvailable_Landroid_graphics_SurfaceTexture_Handler:Android.Graphics.SurfaceTexture/IOnFrameAvailableListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n");
  }
  
  public SurfaceTexture_OnFrameAvailableListenerImplementor() {
    if (getClass() == SurfaceTexture_OnFrameAvailableListenerImplementor.class)
      TypeManager.Activate("Android.Graphics.SurfaceTexture+IOnFrameAvailableListenerImplementor, Mono.Android", "", this, new Object[0]); 
  }
  
  private native void n_onFrameAvailable(SurfaceTexture paramSurfaceTexture);
  
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
  
  public void onFrameAvailable(SurfaceTexture paramSurfaceTexture) {
    n_onFrameAvailable(paramSurfaceTexture);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/mono/android/graphics/SurfaceTexture_OnFrameAvailableListenerImplementor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */