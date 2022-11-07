package mono.android.graphics.drawable;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class Icon_OnDrawableLoadedListenerImplementor implements IGCUserPeer, Icon.OnDrawableLoadedListener {
  public static final String __md_methods = "n_onDrawableLoaded:(Landroid/graphics/drawable/Drawable;)V:GetOnDrawableLoaded_Landroid_graphics_drawable_Drawable_Handler:Android.Graphics.Drawables.Icon/IOnDrawableLoadedListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n";
  
  private ArrayList refList;
  
  static {
    Runtime.register("Android.Graphics.Drawables.Icon+IOnDrawableLoadedListenerImplementor, Mono.Android", Icon_OnDrawableLoadedListenerImplementor.class, "n_onDrawableLoaded:(Landroid/graphics/drawable/Drawable;)V:GetOnDrawableLoaded_Landroid_graphics_drawable_Drawable_Handler:Android.Graphics.Drawables.Icon/IOnDrawableLoadedListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n");
  }
  
  public Icon_OnDrawableLoadedListenerImplementor() {
    if (getClass() == Icon_OnDrawableLoadedListenerImplementor.class)
      TypeManager.Activate("Android.Graphics.Drawables.Icon+IOnDrawableLoadedListenerImplementor, Mono.Android", "", this, new Object[0]); 
  }
  
  private native void n_onDrawableLoaded(Drawable paramDrawable);
  
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
  
  public void onDrawableLoaded(Drawable paramDrawable) {
    n_onDrawableLoaded(paramDrawable);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/mono/android/graphics/drawable/Icon_OnDrawableLoadedListenerImplementor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */