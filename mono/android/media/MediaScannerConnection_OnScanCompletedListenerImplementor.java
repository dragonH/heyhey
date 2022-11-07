package mono.android.media;

import android.media.MediaScannerConnection;
import android.net.Uri;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class MediaScannerConnection_OnScanCompletedListenerImplementor implements IGCUserPeer, MediaScannerConnection.OnScanCompletedListener {
  public static final String __md_methods = "n_onScanCompleted:(Ljava/lang/String;Landroid/net/Uri;)V:GetOnScanCompleted_Ljava_lang_String_Landroid_net_Uri_Handler:Android.Media.MediaScannerConnection/IOnScanCompletedListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n";
  
  private ArrayList refList;
  
  static {
    Runtime.register("Android.Media.MediaScannerConnection+IOnScanCompletedListenerImplementor, Mono.Android", MediaScannerConnection_OnScanCompletedListenerImplementor.class, "n_onScanCompleted:(Ljava/lang/String;Landroid/net/Uri;)V:GetOnScanCompleted_Ljava_lang_String_Landroid_net_Uri_Handler:Android.Media.MediaScannerConnection/IOnScanCompletedListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n");
  }
  
  public MediaScannerConnection_OnScanCompletedListenerImplementor() {
    if (getClass() == MediaScannerConnection_OnScanCompletedListenerImplementor.class)
      TypeManager.Activate("Android.Media.MediaScannerConnection+IOnScanCompletedListenerImplementor, Mono.Android", "", this, new Object[0]); 
  }
  
  private native void n_onScanCompleted(String paramString, Uri paramUri);
  
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
  
  public void onScanCompleted(String paramString, Uri paramUri) {
    n_onScanCompleted(paramString, paramUri);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/mono/android/media/MediaScannerConnection_OnScanCompletedListenerImplementor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */