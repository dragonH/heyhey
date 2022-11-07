package mono.android.webkit;

import android.graphics.Picture;
import android.webkit.WebView;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class WebView_PictureListenerImplementor implements IGCUserPeer, WebView.PictureListener {
  public static final String __md_methods = "n_onNewPicture:(Landroid/webkit/WebView;Landroid/graphics/Picture;)V:GetOnNewPicture_Landroid_webkit_WebView_Landroid_graphics_Picture_Handler:Android.Webkit.WebView/IPictureListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n";
  
  private ArrayList refList;
  
  static {
    Runtime.register("Android.Webkit.WebView+IPictureListenerImplementor, Mono.Android", WebView_PictureListenerImplementor.class, "n_onNewPicture:(Landroid/webkit/WebView;Landroid/graphics/Picture;)V:GetOnNewPicture_Landroid_webkit_WebView_Landroid_graphics_Picture_Handler:Android.Webkit.WebView/IPictureListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n");
  }
  
  public WebView_PictureListenerImplementor() {
    if (getClass() == WebView_PictureListenerImplementor.class)
      TypeManager.Activate("Android.Webkit.WebView+IPictureListenerImplementor, Mono.Android", "", this, new Object[0]); 
  }
  
  private native void n_onNewPicture(WebView paramWebView, Picture paramPicture);
  
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
  
  public void onNewPicture(WebView paramWebView, Picture paramPicture) {
    n_onNewPicture(paramWebView, paramPicture);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/mono/android/webkit/WebView_PictureListenerImplementor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */