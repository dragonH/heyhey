package mono.android.media;

import android.media.ImageWriter;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class ImageWriter_OnImageReleasedListenerImplementor implements IGCUserPeer, ImageWriter.OnImageReleasedListener {
  public static final String __md_methods = "n_onImageReleased:(Landroid/media/ImageWriter;)V:GetOnImageReleased_Landroid_media_ImageWriter_Handler:Android.Media.ImageWriter/IOnImageReleasedListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n";
  
  private ArrayList refList;
  
  static {
    Runtime.register("Android.Media.ImageWriter+IOnImageReleasedListenerImplementor, Mono.Android", ImageWriter_OnImageReleasedListenerImplementor.class, "n_onImageReleased:(Landroid/media/ImageWriter;)V:GetOnImageReleased_Landroid_media_ImageWriter_Handler:Android.Media.ImageWriter/IOnImageReleasedListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n");
  }
  
  public ImageWriter_OnImageReleasedListenerImplementor() {
    if (getClass() == ImageWriter_OnImageReleasedListenerImplementor.class)
      TypeManager.Activate("Android.Media.ImageWriter+IOnImageReleasedListenerImplementor, Mono.Android", "", this, new Object[0]); 
  }
  
  private native void n_onImageReleased(ImageWriter paramImageWriter);
  
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
  
  public void onImageReleased(ImageWriter paramImageWriter) {
    n_onImageReleased(paramImageWriter);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/mono/android/media/ImageWriter_OnImageReleasedListenerImplementor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */