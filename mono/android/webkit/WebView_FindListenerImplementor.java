package mono.android.webkit;

import android.webkit.WebView;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class WebView_FindListenerImplementor implements IGCUserPeer, WebView.FindListener {
  public static final String __md_methods = "n_onFindResultReceived:(IIZ)V:GetOnFindResultReceived_IIZHandler:Android.Webkit.WebView/IFindListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n";
  
  private ArrayList refList;
  
  static {
    Runtime.register("Android.Webkit.WebView+IFindListenerImplementor, Mono.Android", WebView_FindListenerImplementor.class, "n_onFindResultReceived:(IIZ)V:GetOnFindResultReceived_IIZHandler:Android.Webkit.WebView/IFindListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n");
  }
  
  public WebView_FindListenerImplementor() {
    if (getClass() == WebView_FindListenerImplementor.class)
      TypeManager.Activate("Android.Webkit.WebView+IFindListenerImplementor, Mono.Android", "", this, new Object[0]); 
  }
  
  private native void n_onFindResultReceived(int paramInt1, int paramInt2, boolean paramBoolean);
  
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
  
  public void onFindResultReceived(int paramInt1, int paramInt2, boolean paramBoolean) {
    n_onFindResultReceived(paramInt1, paramInt2, paramBoolean);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/mono/android/webkit/WebView_FindListenerImplementor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */