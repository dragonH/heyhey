package mono.android.webkit;

import android.graphics.Bitmap;
import android.webkit.WebIconDatabase;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class WebIconDatabase_IconListenerImplementor implements IGCUserPeer, WebIconDatabase.IconListener {
  public static final String __md_methods = "n_onReceivedIcon:(Ljava/lang/String;Landroid/graphics/Bitmap;)V:GetOnReceivedIcon_Ljava_lang_String_Landroid_graphics_Bitmap_Handler:Android.Webkit.WebIconDatabase/IIconListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n";
  
  private ArrayList refList;
  
  static {
    Runtime.register("Android.Webkit.WebIconDatabase+IIconListenerImplementor, Mono.Android", WebIconDatabase_IconListenerImplementor.class, "n_onReceivedIcon:(Ljava/lang/String;Landroid/graphics/Bitmap;)V:GetOnReceivedIcon_Ljava_lang_String_Landroid_graphics_Bitmap_Handler:Android.Webkit.WebIconDatabase/IIconListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n");
  }
  
  public WebIconDatabase_IconListenerImplementor() {
    if (getClass() == WebIconDatabase_IconListenerImplementor.class)
      TypeManager.Activate("Android.Webkit.WebIconDatabase+IIconListenerImplementor, Mono.Android", "", this, new Object[0]); 
  }
  
  private native void n_onReceivedIcon(String paramString, Bitmap paramBitmap);
  
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
  
  public void onReceivedIcon(String paramString, Bitmap paramBitmap) {
    n_onReceivedIcon(paramString, paramBitmap);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/mono/android/webkit/WebIconDatabase_IconListenerImplementor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */