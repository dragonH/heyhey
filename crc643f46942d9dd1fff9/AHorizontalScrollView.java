package crc643f46942d9dd1fff9;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.HorizontalScrollView;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class AHorizontalScrollView extends HorizontalScrollView implements IGCUserPeer {
  public static final String __md_methods = "n_onInterceptTouchEvent:(Landroid/view/MotionEvent;)Z:GetOnInterceptTouchEvent_Landroid_view_MotionEvent_Handler\nn_onTouchEvent:(Landroid/view/MotionEvent;)Z:GetOnTouchEvent_Landroid_view_MotionEvent_Handler\nn_onScrollChanged:(IIII)V:GetOnScrollChanged_IIIIHandler\n";
  
  private ArrayList refList;
  
  static {
    Runtime.register("Xamarin.Forms.Platform.Android.AHorizontalScrollView, Xamarin.Forms.Platform.Android", AHorizontalScrollView.class, "n_onInterceptTouchEvent:(Landroid/view/MotionEvent;)Z:GetOnInterceptTouchEvent_Landroid_view_MotionEvent_Handler\nn_onTouchEvent:(Landroid/view/MotionEvent;)Z:GetOnTouchEvent_Landroid_view_MotionEvent_Handler\nn_onScrollChanged:(IIII)V:GetOnScrollChanged_IIIIHandler\n");
  }
  
  public AHorizontalScrollView(Context paramContext) {
    super(paramContext);
    if (getClass() == AHorizontalScrollView.class)
      TypeManager.Activate("Xamarin.Forms.Platform.Android.AHorizontalScrollView, Xamarin.Forms.Platform.Android", "Android.Content.Context, Mono.Android", this, new Object[] { paramContext }); 
  }
  
  public AHorizontalScrollView(Context paramContext, AttributeSet paramAttributeSet) {
    super(paramContext, paramAttributeSet);
    if (getClass() == AHorizontalScrollView.class)
      TypeManager.Activate("Xamarin.Forms.Platform.Android.AHorizontalScrollView, Xamarin.Forms.Platform.Android", "Android.Content.Context, Mono.Android:Android.Util.IAttributeSet, Mono.Android", this, new Object[] { paramContext, paramAttributeSet }); 
  }
  
  public AHorizontalScrollView(Context paramContext, AttributeSet paramAttributeSet, int paramInt) {
    super(paramContext, paramAttributeSet, paramInt);
    if (getClass() == AHorizontalScrollView.class)
      TypeManager.Activate("Xamarin.Forms.Platform.Android.AHorizontalScrollView, Xamarin.Forms.Platform.Android", "Android.Content.Context, Mono.Android:Android.Util.IAttributeSet, Mono.Android:System.Int32, mscorlib", this, new Object[] { paramContext, paramAttributeSet, Integer.valueOf(paramInt) }); 
  }
  
  private native boolean n_onInterceptTouchEvent(MotionEvent paramMotionEvent);
  
  private native void n_onScrollChanged(int paramInt1, int paramInt2, int paramInt3, int paramInt4);
  
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
  
  public boolean onInterceptTouchEvent(MotionEvent paramMotionEvent) {
    return n_onInterceptTouchEvent(paramMotionEvent);
  }
  
  public void onScrollChanged(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    n_onScrollChanged(paramInt1, paramInt2, paramInt3, paramInt4);
  }
  
  public boolean onTouchEvent(MotionEvent paramMotionEvent) {
    return n_onTouchEvent(paramMotionEvent);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/crc643f46942d9dd1fff9/AHorizontalScrollView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */