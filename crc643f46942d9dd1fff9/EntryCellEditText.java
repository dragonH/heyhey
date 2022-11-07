package crc643f46942d9dd1fff9;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.widget.EditText;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class EntryCellEditText extends EditText implements IGCUserPeer {
  public static final String __md_methods = "n_onKeyPreIme:(ILandroid/view/KeyEvent;)Z:GetOnKeyPreIme_ILandroid_view_KeyEvent_Handler\nn_onFocusChanged:(ZILandroid/graphics/Rect;)V:GetOnFocusChanged_ZILandroid_graphics_Rect_Handler\n";
  
  private ArrayList refList;
  
  static {
    Runtime.register("Xamarin.Forms.Platform.Android.EntryCellEditText, Xamarin.Forms.Platform.Android", EntryCellEditText.class, "n_onKeyPreIme:(ILandroid/view/KeyEvent;)Z:GetOnKeyPreIme_ILandroid_view_KeyEvent_Handler\nn_onFocusChanged:(ZILandroid/graphics/Rect;)V:GetOnFocusChanged_ZILandroid_graphics_Rect_Handler\n");
  }
  
  public EntryCellEditText(Context paramContext) {
    super(paramContext);
    if (getClass() == EntryCellEditText.class)
      TypeManager.Activate("Xamarin.Forms.Platform.Android.EntryCellEditText, Xamarin.Forms.Platform.Android", "Android.Content.Context, Mono.Android", this, new Object[] { paramContext }); 
  }
  
  public EntryCellEditText(Context paramContext, AttributeSet paramAttributeSet) {
    super(paramContext, paramAttributeSet);
    if (getClass() == EntryCellEditText.class)
      TypeManager.Activate("Xamarin.Forms.Platform.Android.EntryCellEditText, Xamarin.Forms.Platform.Android", "Android.Content.Context, Mono.Android:Android.Util.IAttributeSet, Mono.Android", this, new Object[] { paramContext, paramAttributeSet }); 
  }
  
  public EntryCellEditText(Context paramContext, AttributeSet paramAttributeSet, int paramInt) {
    super(paramContext, paramAttributeSet, paramInt);
    if (getClass() == EntryCellEditText.class)
      TypeManager.Activate("Xamarin.Forms.Platform.Android.EntryCellEditText, Xamarin.Forms.Platform.Android", "Android.Content.Context, Mono.Android:Android.Util.IAttributeSet, Mono.Android:System.Int32, mscorlib", this, new Object[] { paramContext, paramAttributeSet, Integer.valueOf(paramInt) }); 
  }
  
  private native void n_onFocusChanged(boolean paramBoolean, int paramInt, Rect paramRect);
  
  private native boolean n_onKeyPreIme(int paramInt, KeyEvent paramKeyEvent);
  
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
  
  public void onFocusChanged(boolean paramBoolean, int paramInt, Rect paramRect) {
    n_onFocusChanged(paramBoolean, paramInt, paramRect);
  }
  
  public boolean onKeyPreIme(int paramInt, KeyEvent paramKeyEvent) {
    return n_onKeyPreIme(paramInt, paramKeyEvent);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/crc643f46942d9dd1fff9/EntryCellEditText.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */