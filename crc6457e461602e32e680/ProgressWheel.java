package crc6457e461602e32e680;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class ProgressWheel extends View implements IGCUserPeer {
  public static final String __md_methods = "n_onAttachedToWindow:()V:GetOnAttachedToWindowHandler\nn_onDraw:(Landroid/graphics/Canvas;)V:GetOnDraw_Landroid_graphics_Canvas_Handler\n";
  
  private ArrayList refList;
  
  static {
    Runtime.register("AndroidHUD.ProgressWheel, AndHUD", ProgressWheel.class, "n_onAttachedToWindow:()V:GetOnAttachedToWindowHandler\nn_onDraw:(Landroid/graphics/Canvas;)V:GetOnDraw_Landroid_graphics_Canvas_Handler\n");
  }
  
  public ProgressWheel(Context paramContext) {
    super(paramContext);
    if (getClass() == ProgressWheel.class)
      TypeManager.Activate("AndroidHUD.ProgressWheel, AndHUD", "Android.Content.Context, Mono.Android", this, new Object[] { paramContext }); 
  }
  
  public ProgressWheel(Context paramContext, AttributeSet paramAttributeSet) {
    super(paramContext, paramAttributeSet);
    if (getClass() == ProgressWheel.class)
      TypeManager.Activate("AndroidHUD.ProgressWheel, AndHUD", "Android.Content.Context, Mono.Android:Android.Util.IAttributeSet, Mono.Android", this, new Object[] { paramContext, paramAttributeSet }); 
  }
  
  public ProgressWheel(Context paramContext, AttributeSet paramAttributeSet, int paramInt) {
    super(paramContext, paramAttributeSet, paramInt);
    if (getClass() == ProgressWheel.class)
      TypeManager.Activate("AndroidHUD.ProgressWheel, AndHUD", "Android.Content.Context, Mono.Android:Android.Util.IAttributeSet, Mono.Android:System.Int32, mscorlib", this, new Object[] { paramContext, paramAttributeSet, Integer.valueOf(paramInt) }); 
  }
  
  private native void n_onAttachedToWindow();
  
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
  
  public void onAttachedToWindow() {
    n_onAttachedToWindow();
  }
  
  public void onDraw(Canvas paramCanvas) {
    n_onDraw(paramCanvas);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/crc6457e461602e32e680/ProgressWheel.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */