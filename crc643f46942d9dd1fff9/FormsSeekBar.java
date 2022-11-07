package crc643f46942d9dd1fff9;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.SeekBar;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class FormsSeekBar extends SeekBar implements IGCUserPeer {
  public static final String __md_methods = "n_onTouchEvent:(Landroid/view/MotionEvent;)Z:GetOnTouchEvent_Landroid_view_MotionEvent_Handler\nn_isPressed:()Z:GetIsPressedHandler\nn_setPressed:(Z)V:GetSetPressed_ZHandler\n";
  
  private ArrayList refList;
  
  static {
    Runtime.register("Xamarin.Forms.Platform.Android.FormsSeekBar, Xamarin.Forms.Platform.Android", FormsSeekBar.class, "n_onTouchEvent:(Landroid/view/MotionEvent;)Z:GetOnTouchEvent_Landroid_view_MotionEvent_Handler\nn_isPressed:()Z:GetIsPressedHandler\nn_setPressed:(Z)V:GetSetPressed_ZHandler\n");
  }
  
  public FormsSeekBar(Context paramContext) {
    super(paramContext);
    if (getClass() == FormsSeekBar.class)
      TypeManager.Activate("Xamarin.Forms.Platform.Android.FormsSeekBar, Xamarin.Forms.Platform.Android", "Android.Content.Context, Mono.Android", this, new Object[] { paramContext }); 
  }
  
  public FormsSeekBar(Context paramContext, AttributeSet paramAttributeSet) {
    super(paramContext, paramAttributeSet);
    if (getClass() == FormsSeekBar.class)
      TypeManager.Activate("Xamarin.Forms.Platform.Android.FormsSeekBar, Xamarin.Forms.Platform.Android", "Android.Content.Context, Mono.Android:Android.Util.IAttributeSet, Mono.Android", this, new Object[] { paramContext, paramAttributeSet }); 
  }
  
  public FormsSeekBar(Context paramContext, AttributeSet paramAttributeSet, int paramInt) {
    super(paramContext, paramAttributeSet, paramInt);
    if (getClass() == FormsSeekBar.class)
      TypeManager.Activate("Xamarin.Forms.Platform.Android.FormsSeekBar, Xamarin.Forms.Platform.Android", "Android.Content.Context, Mono.Android:Android.Util.IAttributeSet, Mono.Android:System.Int32, mscorlib", this, new Object[] { paramContext, paramAttributeSet, Integer.valueOf(paramInt) }); 
  }
  
  private native boolean n_isPressed();
  
  private native boolean n_onTouchEvent(MotionEvent paramMotionEvent);
  
  private native void n_setPressed(boolean paramBoolean);
  
  public boolean isPressed() {
    return n_isPressed();
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
  
  public boolean onTouchEvent(MotionEvent paramMotionEvent) {
    return n_onTouchEvent(paramMotionEvent);
  }
  
  public void setPressed(boolean paramBoolean) {
    n_setPressed(paramBoolean);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/crc643f46942d9dd1fff9/FormsSeekBar.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */