package crc64720bb2db43a66fe9;

import android.content.Context;
import android.util.AttributeSet;
import crc643f46942d9dd1fff9.MasterDetailContainer;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class MasterDetailContainer extends MasterDetailContainer implements IGCUserPeer {
  public static final String __md_methods = "n_onLayout:(ZIIII)V:GetOnLayout_ZIIIIHandler\n";
  
  private ArrayList refList;
  
  static {
    Runtime.register("Xamarin.Forms.Platform.Android.AppCompat.MasterDetailContainer, Xamarin.Forms.Platform.Android", MasterDetailContainer.class, "n_onLayout:(ZIIII)V:GetOnLayout_ZIIIIHandler\n");
  }
  
  public MasterDetailContainer(Context paramContext) {
    super(paramContext);
    if (getClass() == MasterDetailContainer.class)
      TypeManager.Activate("Xamarin.Forms.Platform.Android.AppCompat.MasterDetailContainer, Xamarin.Forms.Platform.Android", "Android.Content.Context, Mono.Android", this, new Object[] { paramContext }); 
  }
  
  public MasterDetailContainer(Context paramContext, AttributeSet paramAttributeSet) {
    super(paramContext, paramAttributeSet);
    if (getClass() == MasterDetailContainer.class)
      TypeManager.Activate("Xamarin.Forms.Platform.Android.AppCompat.MasterDetailContainer, Xamarin.Forms.Platform.Android", "Android.Content.Context, Mono.Android:Android.Util.IAttributeSet, Mono.Android", this, new Object[] { paramContext, paramAttributeSet }); 
  }
  
  public MasterDetailContainer(Context paramContext, AttributeSet paramAttributeSet, int paramInt) {
    super(paramContext, paramAttributeSet, paramInt);
    if (getClass() == MasterDetailContainer.class)
      TypeManager.Activate("Xamarin.Forms.Platform.Android.AppCompat.MasterDetailContainer, Xamarin.Forms.Platform.Android", "Android.Content.Context, Mono.Android:Android.Util.IAttributeSet, Mono.Android:System.Int32, mscorlib", this, new Object[] { paramContext, paramAttributeSet, Integer.valueOf(paramInt) }); 
  }
  
  private native void n_onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4);
  
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
  
  public void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    n_onLayout(paramBoolean, paramInt1, paramInt2, paramInt3, paramInt4);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/crc64720bb2db43a66fe9/MasterDetailContainer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */