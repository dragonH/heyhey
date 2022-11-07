package mono.android.media;

import android.media.ImageReader;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class ImageReader_OnImageAvailableListenerImplementor implements IGCUserPeer, ImageReader.OnImageAvailableListener {
  public static final String __md_methods = "n_onImageAvailable:(Landroid/media/ImageReader;)V:GetOnImageAvailable_Landroid_media_ImageReader_Handler:Android.Media.ImageReader/IOnImageAvailableListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n";
  
  private ArrayList refList;
  
  static {
    Runtime.register("Android.Media.ImageReader+IOnImageAvailableListenerImplementor, Mono.Android", ImageReader_OnImageAvailableListenerImplementor.class, "n_onImageAvailable:(Landroid/media/ImageReader;)V:GetOnImageAvailable_Landroid_media_ImageReader_Handler:Android.Media.ImageReader/IOnImageAvailableListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n");
  }
  
  public ImageReader_OnImageAvailableListenerImplementor() {
    if (getClass() == ImageReader_OnImageAvailableListenerImplementor.class)
      TypeManager.Activate("Android.Media.ImageReader+IOnImageAvailableListenerImplementor, Mono.Android", "", this, new Object[0]); 
  }
  
  private native void n_onImageAvailable(ImageReader paramImageReader);
  
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
  
  public void onImageAvailable(ImageReader paramImageReader) {
    n_onImageAvailable(paramImageReader);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/mono/android/media/ImageReader_OnImageAvailableListenerImplementor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */