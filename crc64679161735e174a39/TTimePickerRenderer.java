package crc64679161735e174a39;

import android.content.Context;
import android.util.AttributeSet;
import crc643f46942d9dd1fff9.TimePickerRenderer;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class TTimePickerRenderer extends TimePickerRenderer implements IGCUserPeer {
  public static final String __md_methods = "";
  
  private ArrayList refList;
  
  static {
    Runtime.register("SCSAPPForms.Droid.Renderers.TTimePickerRenderer, SCS Mobile APP", TTimePickerRenderer.class, "");
  }
  
  public TTimePickerRenderer(Context paramContext) {
    super(paramContext);
    if (getClass() == TTimePickerRenderer.class)
      TypeManager.Activate("SCSAPPForms.Droid.Renderers.TTimePickerRenderer, SCS Mobile APP", "Android.Content.Context, Mono.Android", this, new Object[] { paramContext }); 
  }
  
  public TTimePickerRenderer(Context paramContext, AttributeSet paramAttributeSet) {
    super(paramContext, paramAttributeSet);
    if (getClass() == TTimePickerRenderer.class)
      TypeManager.Activate("SCSAPPForms.Droid.Renderers.TTimePickerRenderer, SCS Mobile APP", "Android.Content.Context, Mono.Android:Android.Util.IAttributeSet, Mono.Android", this, new Object[] { paramContext, paramAttributeSet }); 
  }
  
  public TTimePickerRenderer(Context paramContext, AttributeSet paramAttributeSet, int paramInt) {
    super(paramContext, paramAttributeSet, paramInt);
    if (getClass() == TTimePickerRenderer.class)
      TypeManager.Activate("SCSAPPForms.Droid.Renderers.TTimePickerRenderer, SCS Mobile APP", "Android.Content.Context, Mono.Android:Android.Util.IAttributeSet, Mono.Android:System.Int32, mscorlib", this, new Object[] { paramContext, paramAttributeSet, Integer.valueOf(paramInt) }); 
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
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/crc64679161735e174a39/TTimePickerRenderer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */