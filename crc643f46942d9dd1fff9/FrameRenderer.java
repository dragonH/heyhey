package crc643f46942d9dd1fff9;

import android.content.Context;
import android.util.AttributeSet;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class FrameRenderer extends VisualElementRenderer_1 implements IGCUserPeer {
  public static final String __md_methods = "";
  
  private ArrayList refList;
  
  static {
    Runtime.register("Xamarin.Forms.Platform.Android.FrameRenderer, Xamarin.Forms.Platform.Android", FrameRenderer.class, "");
  }
  
  public FrameRenderer(Context paramContext) {
    super(paramContext);
    if (getClass() == FrameRenderer.class)
      TypeManager.Activate("Xamarin.Forms.Platform.Android.FrameRenderer, Xamarin.Forms.Platform.Android", "Android.Content.Context, Mono.Android", this, new Object[] { paramContext }); 
  }
  
  public FrameRenderer(Context paramContext, AttributeSet paramAttributeSet) {
    super(paramContext, paramAttributeSet);
    if (getClass() == FrameRenderer.class)
      TypeManager.Activate("Xamarin.Forms.Platform.Android.FrameRenderer, Xamarin.Forms.Platform.Android", "Android.Content.Context, Mono.Android:Android.Util.IAttributeSet, Mono.Android", this, new Object[] { paramContext, paramAttributeSet }); 
  }
  
  public FrameRenderer(Context paramContext, AttributeSet paramAttributeSet, int paramInt) {
    super(paramContext, paramAttributeSet, paramInt);
    if (getClass() == FrameRenderer.class)
      TypeManager.Activate("Xamarin.Forms.Platform.Android.FrameRenderer, Xamarin.Forms.Platform.Android", "Android.Content.Context, Mono.Android:Android.Util.IAttributeSet, Mono.Android:System.Int32, mscorlib", this, new Object[] { paramContext, paramAttributeSet, Integer.valueOf(paramInt) }); 
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


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/crc643f46942d9dd1fff9/FrameRenderer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */