package crc643f46942d9dd1fff9;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class FrameRenderer_FrameDrawable extends Drawable implements IGCUserPeer {
  public static final String __md_methods = "n_isStateful:()Z:GetIsStatefulHandler\nn_getOpacity:()I:GetGetOpacityHandler\nn_draw:(Landroid/graphics/Canvas;)V:GetDraw_Landroid_graphics_Canvas_Handler\nn_setAlpha:(I)V:GetSetAlpha_IHandler\nn_setColorFilter:(Landroid/graphics/ColorFilter;)V:GetSetColorFilter_Landroid_graphics_ColorFilter_Handler\nn_onStateChange:([I)Z:GetOnStateChange_arrayIHandler\n";
  
  private ArrayList refList;
  
  static {
    Runtime.register("Xamarin.Forms.Platform.Android.FrameRenderer+FrameDrawable, Xamarin.Forms.Platform.Android", FrameRenderer_FrameDrawable.class, "n_isStateful:()Z:GetIsStatefulHandler\nn_getOpacity:()I:GetGetOpacityHandler\nn_draw:(Landroid/graphics/Canvas;)V:GetDraw_Landroid_graphics_Canvas_Handler\nn_setAlpha:(I)V:GetSetAlpha_IHandler\nn_setColorFilter:(Landroid/graphics/ColorFilter;)V:GetSetColorFilter_Landroid_graphics_ColorFilter_Handler\nn_onStateChange:([I)Z:GetOnStateChange_arrayIHandler\n");
  }
  
  public FrameRenderer_FrameDrawable() {
    if (getClass() == FrameRenderer_FrameDrawable.class)
      TypeManager.Activate("Xamarin.Forms.Platform.Android.FrameRenderer+FrameDrawable, Xamarin.Forms.Platform.Android", "", this, new Object[0]); 
  }
  
  private native void n_draw(Canvas paramCanvas);
  
  private native int n_getOpacity();
  
  private native boolean n_isStateful();
  
  private native boolean n_onStateChange(int[] paramArrayOfint);
  
  private native void n_setAlpha(int paramInt);
  
  private native void n_setColorFilter(ColorFilter paramColorFilter);
  
  public void draw(Canvas paramCanvas) {
    n_draw(paramCanvas);
  }
  
  public int getOpacity() {
    return n_getOpacity();
  }
  
  public boolean isStateful() {
    return n_isStateful();
  }
  
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
  
  public boolean onStateChange(int[] paramArrayOfint) {
    return n_onStateChange(paramArrayOfint);
  }
  
  public void setAlpha(int paramInt) {
    n_setAlpha(paramInt);
  }
  
  public void setColorFilter(ColorFilter paramColorFilter) {
    n_setColorFilter(paramColorFilter);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/crc643f46942d9dd1fff9/FrameRenderer_FrameDrawable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */