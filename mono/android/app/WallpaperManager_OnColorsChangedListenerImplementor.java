package mono.android.app;

import android.app.WallpaperColors;
import android.app.WallpaperManager;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class WallpaperManager_OnColorsChangedListenerImplementor implements IGCUserPeer, WallpaperManager.OnColorsChangedListener {
  public static final String __md_methods = "n_onColorsChanged:(Landroid/app/WallpaperColors;I)V:GetOnColorsChanged_Landroid_app_WallpaperColors_IHandler:Android.App.WallpaperManager/IOnColorsChangedListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n";
  
  private ArrayList refList;
  
  static {
    Runtime.register("Android.App.WallpaperManager+IOnColorsChangedListenerImplementor, Mono.Android", WallpaperManager_OnColorsChangedListenerImplementor.class, "n_onColorsChanged:(Landroid/app/WallpaperColors;I)V:GetOnColorsChanged_Landroid_app_WallpaperColors_IHandler:Android.App.WallpaperManager/IOnColorsChangedListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n");
  }
  
  public WallpaperManager_OnColorsChangedListenerImplementor() {
    if (getClass() == WallpaperManager_OnColorsChangedListenerImplementor.class)
      TypeManager.Activate("Android.App.WallpaperManager+IOnColorsChangedListenerImplementor, Mono.Android", "", this, new Object[0]); 
  }
  
  private native void n_onColorsChanged(WallpaperColors paramWallpaperColors, int paramInt);
  
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
  
  public void onColorsChanged(WallpaperColors paramWallpaperColors, int paramInt) {
    n_onColorsChanged(paramWallpaperColors, paramInt);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/mono/android/app/WallpaperManager_OnColorsChangedListenerImplementor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */