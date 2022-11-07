package crc643f46942d9dd1fff9;

import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class FormsWebChromeClient extends WebChromeClient implements IGCUserPeer {
  public static final String __md_methods = "n_onShowFileChooser:(Landroid/webkit/WebView;Landroid/webkit/ValueCallback;Landroid/webkit/WebChromeClient$FileChooserParams;)Z:GetOnShowFileChooser_Landroid_webkit_WebView_Landroid_webkit_ValueCallback_Landroid_webkit_WebChromeClient_FileChooserParams_Handler\n";
  
  private ArrayList refList;
  
  static {
    Runtime.register("Xamarin.Forms.Platform.Android.FormsWebChromeClient, Xamarin.Forms.Platform.Android", FormsWebChromeClient.class, "n_onShowFileChooser:(Landroid/webkit/WebView;Landroid/webkit/ValueCallback;Landroid/webkit/WebChromeClient$FileChooserParams;)Z:GetOnShowFileChooser_Landroid_webkit_WebView_Landroid_webkit_ValueCallback_Landroid_webkit_WebChromeClient_FileChooserParams_Handler\n");
  }
  
  public FormsWebChromeClient() {
    if (getClass() == FormsWebChromeClient.class)
      TypeManager.Activate("Xamarin.Forms.Platform.Android.FormsWebChromeClient, Xamarin.Forms.Platform.Android", "", this, new Object[0]); 
  }
  
  private native boolean n_onShowFileChooser(WebView paramWebView, ValueCallback paramValueCallback, WebChromeClient.FileChooserParams paramFileChooserParams);
  
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
  
  public boolean onShowFileChooser(WebView paramWebView, ValueCallback paramValueCallback, WebChromeClient.FileChooserParams paramFileChooserParams) {
    return n_onShowFileChooser(paramWebView, paramValueCallback, paramFileChooserParams);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/crc643f46942d9dd1fff9/FormsWebChromeClient.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */