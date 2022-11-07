package mono.android.graphics;

import android.graphics.ImageDecoder;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class ImageDecoder_OnHeaderDecodedListenerImplementor implements IGCUserPeer, ImageDecoder.OnHeaderDecodedListener {
  public static final String __md_methods = "n_onHeaderDecoded:(Landroid/graphics/ImageDecoder;Landroid/graphics/ImageDecoder$ImageInfo;Landroid/graphics/ImageDecoder$Source;)V:GetOnHeaderDecoded_Landroid_graphics_ImageDecoder_Landroid_graphics_ImageDecoder_ImageInfo_Landroid_graphics_ImageDecoder_Source_Handler:Android.Graphics.ImageDecoder/IOnHeaderDecodedListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n";
  
  private ArrayList refList;
  
  static {
    Runtime.register("Android.Graphics.ImageDecoder+IOnHeaderDecodedListenerImplementor, Mono.Android", ImageDecoder_OnHeaderDecodedListenerImplementor.class, "n_onHeaderDecoded:(Landroid/graphics/ImageDecoder;Landroid/graphics/ImageDecoder$ImageInfo;Landroid/graphics/ImageDecoder$Source;)V:GetOnHeaderDecoded_Landroid_graphics_ImageDecoder_Landroid_graphics_ImageDecoder_ImageInfo_Landroid_graphics_ImageDecoder_Source_Handler:Android.Graphics.ImageDecoder/IOnHeaderDecodedListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n");
  }
  
  public ImageDecoder_OnHeaderDecodedListenerImplementor() {
    if (getClass() == ImageDecoder_OnHeaderDecodedListenerImplementor.class)
      TypeManager.Activate("Android.Graphics.ImageDecoder+IOnHeaderDecodedListenerImplementor, Mono.Android", "", this, new Object[0]); 
  }
  
  private native void n_onHeaderDecoded(ImageDecoder paramImageDecoder, ImageDecoder.ImageInfo paramImageInfo, ImageDecoder.Source paramSource);
  
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
  
  public void onHeaderDecoded(ImageDecoder paramImageDecoder, ImageDecoder.ImageInfo paramImageInfo, ImageDecoder.Source paramSource) {
    n_onHeaderDecoded(paramImageDecoder, paramImageInfo, paramSource);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/mono/android/graphics/ImageDecoder_OnHeaderDecodedListenerImplementor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */