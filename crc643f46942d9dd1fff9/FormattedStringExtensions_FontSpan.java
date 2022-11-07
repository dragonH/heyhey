package crc643f46942d9dd1fff9;

import android.text.TextPaint;
import android.text.style.MetricAffectingSpan;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class FormattedStringExtensions_FontSpan extends MetricAffectingSpan implements IGCUserPeer {
  public static final String __md_methods = "n_updateDrawState:(Landroid/text/TextPaint;)V:GetUpdateDrawState_Landroid_text_TextPaint_Handler\nn_updateMeasureState:(Landroid/text/TextPaint;)V:GetUpdateMeasureState_Landroid_text_TextPaint_Handler\n";
  
  private ArrayList refList;
  
  static {
    Runtime.register("Xamarin.Forms.Platform.Android.FormattedStringExtensions+FontSpan, Xamarin.Forms.Platform.Android", FormattedStringExtensions_FontSpan.class, "n_updateDrawState:(Landroid/text/TextPaint;)V:GetUpdateDrawState_Landroid_text_TextPaint_Handler\nn_updateMeasureState:(Landroid/text/TextPaint;)V:GetUpdateMeasureState_Landroid_text_TextPaint_Handler\n");
  }
  
  public FormattedStringExtensions_FontSpan() {
    if (getClass() == FormattedStringExtensions_FontSpan.class)
      TypeManager.Activate("Xamarin.Forms.Platform.Android.FormattedStringExtensions+FontSpan, Xamarin.Forms.Platform.Android", "", this, new Object[0]); 
  }
  
  private native void n_updateDrawState(TextPaint paramTextPaint);
  
  private native void n_updateMeasureState(TextPaint paramTextPaint);
  
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
  
  public void updateDrawState(TextPaint paramTextPaint) {
    n_updateDrawState(paramTextPaint);
  }
  
  public void updateMeasureState(TextPaint paramTextPaint) {
    n_updateMeasureState(paramTextPaint);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/crc643f46942d9dd1fff9/FormattedStringExtensions_FontSpan.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */