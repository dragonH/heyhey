package mono.android.graphics;

import android.graphics.ImageDecoder;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class ImageDecoder_OnPartialImageListenerImplementor implements IGCUserPeer, ImageDecoder.OnPartialImageListener {
  public static final String __md_methods = "n_onPartialImage:(Landroid/graphics/ImageDecoder$DecodeException;)Z:GetOnPartialImage_Landroid_graphics_ImageDecoder_DecodeException_Handler:Android.Graphics.ImageDecoder/IOnPartialImageListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n";
  
  private ArrayList refList;
  
  static {
    Runtime.register("Android.Graphics.ImageDecoder+IOnPartialImageListenerImplementor, Mono.Android", ImageDecoder_OnPartialImageListenerImplementor.class, "n_onPartialImage:(Landroid/graphics/ImageDecoder$DecodeException;)Z:GetOnPartialImage_Landroid_graphics_ImageDecoder_DecodeException_Handler:Android.Graphics.ImageDecoder/IOnPartialImageListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n");
  }
  
  public ImageDecoder_OnPartialImageListenerImplementor() {
    if (getClass() == ImageDecoder_OnPartialImageListenerImplementor.class)
      TypeManager.Activate("Android.Graphics.ImageDecoder+IOnPartialImageListenerImplementor, Mono.Android", "", this, new Object[0]); 
  }
  
  private native boolean n_onPartialImage(ImageDecoder.DecodeException paramDecodeException);
  
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
  
  public boolean onPartialImage(ImageDecoder.DecodeException paramDecodeException) {
    return n_onPartialImage(paramDecodeException);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/mono/android/graphics/ImageDecoder_OnPartialImageListenerImplementor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */