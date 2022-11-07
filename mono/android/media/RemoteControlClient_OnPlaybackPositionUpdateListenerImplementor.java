package mono.android.media;

import android.media.RemoteControlClient;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class RemoteControlClient_OnPlaybackPositionUpdateListenerImplementor implements IGCUserPeer, RemoteControlClient.OnPlaybackPositionUpdateListener {
  public static final String __md_methods = "n_onPlaybackPositionUpdate:(J)V:GetOnPlaybackPositionUpdate_JHandler:Android.Media.RemoteControlClient/IOnPlaybackPositionUpdateListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n";
  
  private ArrayList refList;
  
  static {
    Runtime.register("Android.Media.RemoteControlClient+IOnPlaybackPositionUpdateListenerImplementor, Mono.Android", RemoteControlClient_OnPlaybackPositionUpdateListenerImplementor.class, "n_onPlaybackPositionUpdate:(J)V:GetOnPlaybackPositionUpdate_JHandler:Android.Media.RemoteControlClient/IOnPlaybackPositionUpdateListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n");
  }
  
  public RemoteControlClient_OnPlaybackPositionUpdateListenerImplementor() {
    if (getClass() == RemoteControlClient_OnPlaybackPositionUpdateListenerImplementor.class)
      TypeManager.Activate("Android.Media.RemoteControlClient+IOnPlaybackPositionUpdateListenerImplementor, Mono.Android", "", this, new Object[0]); 
  }
  
  private native void n_onPlaybackPositionUpdate(long paramLong);
  
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
  
  public void onPlaybackPositionUpdate(long paramLong) {
    n_onPlaybackPositionUpdate(paramLong);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/mono/android/media/RemoteControlClient_OnPlaybackPositionUpdateListenerImplementor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */