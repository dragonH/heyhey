package mono.android.view;

import android.graphics.Rect;
import android.view.Window;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class Window_OnRestrictedCaptionAreaChangedListenerImplementor implements IGCUserPeer, Window.OnRestrictedCaptionAreaChangedListener {
  public static final String __md_methods = "n_onRestrictedCaptionAreaChanged:(Landroid/graphics/Rect;)V:GetOnRestrictedCaptionAreaChanged_Landroid_graphics_Rect_Handler:Android.Views.Window/IOnRestrictedCaptionAreaChangedListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n";
  
  private ArrayList refList;
  
  static {
    Runtime.register("Android.Views.Window+IOnRestrictedCaptionAreaChangedListenerImplementor, Mono.Android", Window_OnRestrictedCaptionAreaChangedListenerImplementor.class, "n_onRestrictedCaptionAreaChanged:(Landroid/graphics/Rect;)V:GetOnRestrictedCaptionAreaChanged_Landroid_graphics_Rect_Handler:Android.Views.Window/IOnRestrictedCaptionAreaChangedListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n");
  }
  
  public Window_OnRestrictedCaptionAreaChangedListenerImplementor() {
    if (getClass() == Window_OnRestrictedCaptionAreaChangedListenerImplementor.class)
      TypeManager.Activate("Android.Views.Window+IOnRestrictedCaptionAreaChangedListenerImplementor, Mono.Android", "", this, new Object[0]); 
  }
  
  private native void n_onRestrictedCaptionAreaChanged(Rect paramRect);
  
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
  
  public void onRestrictedCaptionAreaChanged(Rect paramRect) {
    n_onRestrictedCaptionAreaChanged(paramRect);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/mono/android/view/Window_OnRestrictedCaptionAreaChangedListenerImplementor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */