package mono.android.support.v7.graphics;

import android.support.v7.graphics.Palette;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class Palette_PaletteAsyncListenerImplementor implements IGCUserPeer, Palette.PaletteAsyncListener {
  public static final String __md_methods = "n_onGenerated:(Landroid/support/v7/graphics/Palette;)V:GetOnGenerated_Landroid_support_v7_graphics_Palette_Handler:Android.Support.V7.Graphics.Palette/IPaletteAsyncListenerInvoker, Xamarin.Android.Support.v7.Palette\n";
  
  private ArrayList refList;
  
  static {
    Runtime.register("Android.Support.V7.Graphics.Palette+IPaletteAsyncListenerImplementor, Xamarin.Android.Support.v7.Palette", Palette_PaletteAsyncListenerImplementor.class, "n_onGenerated:(Landroid/support/v7/graphics/Palette;)V:GetOnGenerated_Landroid_support_v7_graphics_Palette_Handler:Android.Support.V7.Graphics.Palette/IPaletteAsyncListenerInvoker, Xamarin.Android.Support.v7.Palette\n");
  }
  
  public Palette_PaletteAsyncListenerImplementor() {
    if (getClass() == Palette_PaletteAsyncListenerImplementor.class)
      TypeManager.Activate("Android.Support.V7.Graphics.Palette+IPaletteAsyncListenerImplementor, Xamarin.Android.Support.v7.Palette", "", this, new Object[0]); 
  }
  
  private native void n_onGenerated(Palette paramPalette);
  
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
  
  public void onGenerated(Palette paramPalette) {
    n_onGenerated(paramPalette);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/mono/android/support/v7/graphics/Palette_PaletteAsyncListenerImplementor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */