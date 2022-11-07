package mono.com.google.android.gms.common.images;

import android.graphics.drawable.Drawable;
import android.net.Uri;
import com.google.android.gms.common.images.ImageManager;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class ImageManager_OnImageLoadedListenerImplementor implements IGCUserPeer, ImageManager.OnImageLoadedListener {
  public static final String __md_methods = "n_onImageLoaded:(Landroid/net/Uri;Landroid/graphics/drawable/Drawable;Z)V:GetOnImageLoaded_Landroid_net_Uri_Landroid_graphics_drawable_Drawable_ZHandler:Android.Gms.Common.Images.ImageManager/IOnImageLoadedListenerInvoker, Xamarin.GooglePlayServices.Base\n";
  
  private ArrayList refList;
  
  static {
    Runtime.register("Android.Gms.Common.Images.ImageManager+IOnImageLoadedListenerImplementor, Xamarin.GooglePlayServices.Base", ImageManager_OnImageLoadedListenerImplementor.class, "n_onImageLoaded:(Landroid/net/Uri;Landroid/graphics/drawable/Drawable;Z)V:GetOnImageLoaded_Landroid_net_Uri_Landroid_graphics_drawable_Drawable_ZHandler:Android.Gms.Common.Images.ImageManager/IOnImageLoadedListenerInvoker, Xamarin.GooglePlayServices.Base\n");
  }
  
  public ImageManager_OnImageLoadedListenerImplementor() {
    if (getClass() == ImageManager_OnImageLoadedListenerImplementor.class)
      TypeManager.Activate("Android.Gms.Common.Images.ImageManager+IOnImageLoadedListenerImplementor, Xamarin.GooglePlayServices.Base", "", this, new Object[0]); 
  }
  
  private native void n_onImageLoaded(Uri paramUri, Drawable paramDrawable, boolean paramBoolean);
  
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
  
  public void onImageLoaded(Uri paramUri, Drawable paramDrawable, boolean paramBoolean) {
    n_onImageLoaded(paramUri, paramDrawable, paramBoolean);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/mono/com/google/android/gms/common/images/ImageManager_OnImageLoadedListenerImplementor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */