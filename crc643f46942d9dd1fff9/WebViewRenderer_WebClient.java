package crc643f46942d9dd1fff9;

import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class WebViewRenderer_WebClient extends WebViewClient implements IGCUserPeer {
  public static final String __md_methods = "n_onPageFinished:(Landroid/webkit/WebView;Ljava/lang/String;)V:GetOnPageFinished_Landroid_webkit_WebView_Ljava_lang_String_Handler\nn_onReceivedError:(Landroid/webkit/WebView;ILjava/lang/String;Ljava/lang/String;)V:GetOnReceivedError_Landroid_webkit_WebView_ILjava_lang_String_Ljava_lang_String_Handler\nn_onReceivedError:(Landroid/webkit/WebView;Landroid/webkit/WebResourceRequest;Landroid/webkit/WebResourceError;)V:GetOnReceivedError_Landroid_webkit_WebView_Landroid_webkit_WebResourceRequest_Landroid_webkit_WebResourceError_Handler\nn_shouldOverrideUrlLoading:(Landroid/webkit/WebView;Ljava/lang/String;)Z:GetShouldOverrideUrlLoading_Landroid_webkit_WebView_Ljava_lang_String_Handler\n";
  
  private ArrayList refList;
  
  static {
    Runtime.register("Xamarin.Forms.Platform.Android.WebViewRenderer+WebClient, Xamarin.Forms.Platform.Android", WebViewRenderer_WebClient.class, "n_onPageFinished:(Landroid/webkit/WebView;Ljava/lang/String;)V:GetOnPageFinished_Landroid_webkit_WebView_Ljava_lang_String_Handler\nn_onReceivedError:(Landroid/webkit/WebView;ILjava/lang/String;Ljava/lang/String;)V:GetOnReceivedError_Landroid_webkit_WebView_ILjava_lang_String_Ljava_lang_String_Handler\nn_onReceivedError:(Landroid/webkit/WebView;Landroid/webkit/WebResourceRequest;Landroid/webkit/WebResourceError;)V:GetOnReceivedError_Landroid_webkit_WebView_Landroid_webkit_WebResourceRequest_Landroid_webkit_WebResourceError_Handler\nn_shouldOverrideUrlLoading:(Landroid/webkit/WebView;Ljava/lang/String;)Z:GetShouldOverrideUrlLoading_Landroid_webkit_WebView_Ljava_lang_String_Handler\n");
  }
  
  public WebViewRenderer_WebClient() {
    if (getClass() == WebViewRenderer_WebClient.class)
      TypeManager.Activate("Xamarin.Forms.Platform.Android.WebViewRenderer+WebClient, Xamarin.Forms.Platform.Android", "", this, new Object[0]); 
  }
  
  public WebViewRenderer_WebClient(WebViewRenderer paramWebViewRenderer) {
    if (getClass() == WebViewRenderer_WebClient.class)
      TypeManager.Activate("Xamarin.Forms.Platform.Android.WebViewRenderer+WebClient, Xamarin.Forms.Platform.Android", "Xamarin.Forms.Platform.Android.WebViewRenderer, Xamarin.Forms.Platform.Android", this, new Object[] { paramWebViewRenderer }); 
  }
  
  private native void n_onPageFinished(WebView paramWebView, String paramString);
  
  private native void n_onReceivedError(WebView paramWebView, int paramInt, String paramString1, String paramString2);
  
  private native void n_onReceivedError(WebView paramWebView, WebResourceRequest paramWebResourceRequest, WebResourceError paramWebResourceError);
  
  private native boolean n_shouldOverrideUrlLoading(WebView paramWebView, String paramString);
  
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
  
  public void onPageFinished(WebView paramWebView, String paramString) {
    n_onPageFinished(paramWebView, paramString);
  }
  
  public void onReceivedError(WebView paramWebView, int paramInt, String paramString1, String paramString2) {
    n_onReceivedError(paramWebView, paramInt, paramString1, paramString2);
  }
  
  public void onReceivedError(WebView paramWebView, WebResourceRequest paramWebResourceRequest, WebResourceError paramWebResourceError) {
    n_onReceivedError(paramWebView, paramWebResourceRequest, paramWebResourceError);
  }
  
  public boolean shouldOverrideUrlLoading(WebView paramWebView, String paramString) {
    return n_shouldOverrideUrlLoading(paramWebView, paramString);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/crc643f46942d9dd1fff9/WebViewRenderer_WebClient.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */