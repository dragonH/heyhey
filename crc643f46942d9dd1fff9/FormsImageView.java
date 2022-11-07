package crc643f46942d9dd1fff9;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class FormsImageView extends ImageView implements IGCUserPeer {
  public static final String __md_methods = "n_invalidate:()V:GetInvalidateHandler\n";
  
  private ArrayList refList;
  
  static {
    Runtime.register("Xamarin.Forms.Platform.Android.FormsImageView, Xamarin.Forms.Platform.Android", FormsImageView.class, "n_invalidate:()V:GetInvalidateHandler\n");
  }
  
  public FormsImageView(Context paramContext) {
    super(paramContext);
    if (getClass() == FormsImageView.class)
      TypeManager.Activate("Xamarin.Forms.Platform.Android.FormsImageView, Xamarin.Forms.Platform.Android", "Android.Content.Context, Mono.Android", this, new Object[] { paramContext }); 
  }
  
  public FormsImageView(Context paramContext, AttributeSet paramAttributeSet) {
    super(paramContext, paramAttributeSet);
    if (getClass() == FormsImageView.class)
      TypeManager.Activate("Xamarin.Forms.Platform.Android.FormsImageView, Xamarin.Forms.Platform.Android", "Android.Content.Context, Mono.Android:Android.Util.IAttributeSet, Mono.Android", this, new Object[] { paramContext, paramAttributeSet }); 
  }
  
  public FormsImageView(Context paramContext, AttributeSet paramAttributeSet, int paramInt) {
    super(paramContext, paramAttributeSet, paramInt);
    if (getClass() == FormsImageView.class)
      TypeManager.Activate("Xamarin.Forms.Platform.Android.FormsImageView, Xamarin.Forms.Platform.Android", "Android.Content.Context, Mono.Android:Android.Util.IAttributeSet, Mono.Android:System.Int32, mscorlib", this, new Object[] { paramContext, paramAttributeSet, Integer.valueOf(paramInt) }); 
  }
  
  private native void n_invalidate();
  
  public void invalidate() {
    n_invalidate();
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


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/crc643f46942d9dd1fff9/FormsImageView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */