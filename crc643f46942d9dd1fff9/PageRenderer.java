package crc643f46942d9dd1fff9;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class PageRenderer extends VisualElementRenderer_1 implements IGCUserPeer {
  public static final String __md_methods = "n_onTouchEvent:(Landroid/view/MotionEvent;)Z:GetOnTouchEvent_Landroid_view_MotionEvent_Handler\nn_onAttachedToWindow:()V:GetOnAttachedToWindowHandler\nn_onDetachedFromWindow:()V:GetOnDetachedFromWindowHandler\n";
  
  private ArrayList refList;
  
  static {
    Runtime.register("Xamarin.Forms.Platform.Android.PageRenderer, Xamarin.Forms.Platform.Android", PageRenderer.class, "n_onTouchEvent:(Landroid/view/MotionEvent;)Z:GetOnTouchEvent_Landroid_view_MotionEvent_Handler\nn_onAttachedToWindow:()V:GetOnAttachedToWindowHandler\nn_onDetachedFromWindow:()V:GetOnDetachedFromWindowHandler\n");
  }
  
  public PageRenderer(Context paramContext) {
    super(paramContext);
    if (getClass() == PageRenderer.class)
      TypeManager.Activate("Xamarin.Forms.Platform.Android.PageRenderer, Xamarin.Forms.Platform.Android", "Android.Content.Context, Mono.Android", this, new Object[] { paramContext }); 
  }
  
  public PageRenderer(Context paramContext, AttributeSet paramAttributeSet) {
    super(paramContext, paramAttributeSet);
    if (getClass() == PageRenderer.class)
      TypeManager.Activate("Xamarin.Forms.Platform.Android.PageRenderer, Xamarin.Forms.Platform.Android", "Android.Content.Context, Mono.Android:Android.Util.IAttributeSet, Mono.Android", this, new Object[] { paramContext, paramAttributeSet }); 
  }
  
  public PageRenderer(Context paramContext, AttributeSet paramAttributeSet, int paramInt) {
    super(paramContext, paramAttributeSet, paramInt);
    if (getClass() == PageRenderer.class)
      TypeManager.Activate("Xamarin.Forms.Platform.Android.PageRenderer, Xamarin.Forms.Platform.Android", "Android.Content.Context, Mono.Android:Android.Util.IAttributeSet, Mono.Android:System.Int32, mscorlib", this, new Object[] { paramContext, paramAttributeSet, Integer.valueOf(paramInt) }); 
  }
  
  private native void n_onAttachedToWindow();
  
  private native void n_onDetachedFromWindow();
  
  private native boolean n_onTouchEvent(MotionEvent paramMotionEvent);
  
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
  
  public void onDetachedFromWindow() {
    n_onDetachedFromWindow();
  }
  
  public boolean onTouchEvent(MotionEvent paramMotionEvent) {
    return n_onTouchEvent(paramMotionEvent);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/crc643f46942d9dd1fff9/PageRenderer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */