package crc6480997b3ef81bf9b2;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class ZxingOverlayView extends View implements IGCUserPeer {
  public static final String __md_methods = "n_onDraw:(Landroid/graphics/Canvas;)V:GetOnDraw_Landroid_graphics_Canvas_Handler\n";
  
  private ArrayList refList;
  
  static {
    Runtime.register("ZXing.Mobile.ZxingOverlayView, ZXingNetMobile", ZxingOverlayView.class, "n_onDraw:(Landroid/graphics/Canvas;)V:GetOnDraw_Landroid_graphics_Canvas_Handler\n");
  }
  
  public ZxingOverlayView(Context paramContext) {
    super(paramContext);
    if (getClass() == ZxingOverlayView.class)
      TypeManager.Activate("ZXing.Mobile.ZxingOverlayView, ZXingNetMobile", "Android.Content.Context, Mono.Android", this, new Object[] { paramContext }); 
  }
  
  public ZxingOverlayView(Context paramContext, AttributeSet paramAttributeSet) {
    super(paramContext, paramAttributeSet);
    if (getClass() == ZxingOverlayView.class)
      TypeManager.Activate("ZXing.Mobile.ZxingOverlayView, ZXingNetMobile", "Android.Content.Context, Mono.Android:Android.Util.IAttributeSet, Mono.Android", this, new Object[] { paramContext, paramAttributeSet }); 
  }
  
  public ZxingOverlayView(Context paramContext, AttributeSet paramAttributeSet, int paramInt) {
    super(paramContext, paramAttributeSet, paramInt);
    if (getClass() == ZxingOverlayView.class)
      TypeManager.Activate("ZXing.Mobile.ZxingOverlayView, ZXingNetMobile", "Android.Content.Context, Mono.Android:Android.Util.IAttributeSet, Mono.Android:System.Int32, mscorlib", this, new Object[] { paramContext, paramAttributeSet, Integer.valueOf(paramInt) }); 
  }
  
  private native void n_onDraw(Canvas paramCanvas);
  
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
  
  public void onDraw(Canvas paramCanvas) {
    n_onDraw(paramCanvas);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/crc6480997b3ef81bf9b2/ZxingOverlayView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */