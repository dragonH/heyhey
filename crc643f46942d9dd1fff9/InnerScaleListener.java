package crc643f46942d9dd1fff9;

import android.view.ScaleGestureDetector;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class InnerScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener implements IGCUserPeer {
  public static final String __md_methods = "n_onScale:(Landroid/view/ScaleGestureDetector;)Z:GetOnScale_Landroid_view_ScaleGestureDetector_Handler\nn_onScaleBegin:(Landroid/view/ScaleGestureDetector;)Z:GetOnScaleBegin_Landroid_view_ScaleGestureDetector_Handler\nn_onScaleEnd:(Landroid/view/ScaleGestureDetector;)V:GetOnScaleEnd_Landroid_view_ScaleGestureDetector_Handler\n";
  
  private ArrayList refList;
  
  static {
    Runtime.register("Xamarin.Forms.Platform.Android.InnerScaleListener, Xamarin.Forms.Platform.Android", InnerScaleListener.class, "n_onScale:(Landroid/view/ScaleGestureDetector;)Z:GetOnScale_Landroid_view_ScaleGestureDetector_Handler\nn_onScaleBegin:(Landroid/view/ScaleGestureDetector;)Z:GetOnScaleBegin_Landroid_view_ScaleGestureDetector_Handler\nn_onScaleEnd:(Landroid/view/ScaleGestureDetector;)V:GetOnScaleEnd_Landroid_view_ScaleGestureDetector_Handler\n");
  }
  
  public InnerScaleListener() {
    if (getClass() == InnerScaleListener.class)
      TypeManager.Activate("Xamarin.Forms.Platform.Android.InnerScaleListener, Xamarin.Forms.Platform.Android", "", this, new Object[0]); 
  }
  
  private native boolean n_onScale(ScaleGestureDetector paramScaleGestureDetector);
  
  private native boolean n_onScaleBegin(ScaleGestureDetector paramScaleGestureDetector);
  
  private native void n_onScaleEnd(ScaleGestureDetector paramScaleGestureDetector);
  
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
  
  public boolean onScale(ScaleGestureDetector paramScaleGestureDetector) {
    return n_onScale(paramScaleGestureDetector);
  }
  
  public boolean onScaleBegin(ScaleGestureDetector paramScaleGestureDetector) {
    return n_onScaleBegin(paramScaleGestureDetector);
  }
  
  public void onScaleEnd(ScaleGestureDetector paramScaleGestureDetector) {
    n_onScaleEnd(paramScaleGestureDetector);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/crc643f46942d9dd1fff9/InnerScaleListener.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */