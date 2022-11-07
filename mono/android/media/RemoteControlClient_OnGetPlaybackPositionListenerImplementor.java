package mono.android.media;

import android.media.RemoteControlClient;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class RemoteControlClient_OnGetPlaybackPositionListenerImplementor implements IGCUserPeer, RemoteControlClient.OnGetPlaybackPositionListener {
  public static final String __md_methods = "n_onGetPlaybackPosition:()J:GetOnGetPlaybackPositionHandler:Android.Media.RemoteControlClient/IOnGetPlaybackPositionListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n";
  
  private ArrayList refList;
  
  static {
    Runtime.register("Android.Media.RemoteControlClient+IOnGetPlaybackPositionListenerImplementor, Mono.Android", RemoteControlClient_OnGetPlaybackPositionListenerImplementor.class, "n_onGetPlaybackPosition:()J:GetOnGetPlaybackPositionHandler:Android.Media.RemoteControlClient/IOnGetPlaybackPositionListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n");
  }
  
  public RemoteControlClient_OnGetPlaybackPositionListenerImplementor() {
    if (getClass() == RemoteControlClient_OnGetPlaybackPositionListenerImplementor.class)
      TypeManager.Activate("Android.Media.RemoteControlClient+IOnGetPlaybackPositionListenerImplementor, Mono.Android", "", this, new Object[0]); 
  }
  
  private native long n_onGetPlaybackPosition();
  
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
  
  public long onGetPlaybackPosition() {
    return n_onGetPlaybackPosition();
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/mono/android/media/RemoteControlClient_OnGetPlaybackPositionListenerImplementor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */