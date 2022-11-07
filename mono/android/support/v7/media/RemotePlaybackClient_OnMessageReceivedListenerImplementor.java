package mono.android.support.v7.media;

import android.os.Bundle;
import android.support.v7.media.RemotePlaybackClient;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class RemotePlaybackClient_OnMessageReceivedListenerImplementor implements IGCUserPeer, RemotePlaybackClient.OnMessageReceivedListener {
  public static final String __md_methods = "n_onMessageReceived:(Ljava/lang/String;Landroid/os/Bundle;)V:GetOnMessageReceived_Ljava_lang_String_Landroid_os_Bundle_Handler:Android.Support.V7.Media.RemotePlaybackClient/IOnMessageReceivedListenerInvoker, Xamarin.Android.Support.v7.MediaRouter\n";
  
  private ArrayList refList;
  
  static {
    Runtime.register("Android.Support.V7.Media.RemotePlaybackClient+IOnMessageReceivedListenerImplementor, Xamarin.Android.Support.v7.MediaRouter", RemotePlaybackClient_OnMessageReceivedListenerImplementor.class, "n_onMessageReceived:(Ljava/lang/String;Landroid/os/Bundle;)V:GetOnMessageReceived_Ljava_lang_String_Landroid_os_Bundle_Handler:Android.Support.V7.Media.RemotePlaybackClient/IOnMessageReceivedListenerInvoker, Xamarin.Android.Support.v7.MediaRouter\n");
  }
  
  public RemotePlaybackClient_OnMessageReceivedListenerImplementor() {
    if (getClass() == RemotePlaybackClient_OnMessageReceivedListenerImplementor.class)
      TypeManager.Activate("Android.Support.V7.Media.RemotePlaybackClient+IOnMessageReceivedListenerImplementor, Xamarin.Android.Support.v7.MediaRouter", "", this, new Object[0]); 
  }
  
  private native void n_onMessageReceived(String paramString, Bundle paramBundle);
  
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
  
  public void onMessageReceived(String paramString, Bundle paramBundle) {
    n_onMessageReceived(paramString, paramBundle);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/mono/android/support/v7/media/RemotePlaybackClient_OnMessageReceivedListenerImplementor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */