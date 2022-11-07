package mono.android.media;

import android.media.MediaPlayer;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class MediaPlayer_OnSeekCompleteListenerImplementor implements IGCUserPeer, MediaPlayer.OnSeekCompleteListener {
  public static final String __md_methods = "n_onSeekComplete:(Landroid/media/MediaPlayer;)V:GetOnSeekComplete_Landroid_media_MediaPlayer_Handler:Android.Media.MediaPlayer/IOnSeekCompleteListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n";
  
  private ArrayList refList;
  
  static {
    Runtime.register("Android.Media.MediaPlayer+IOnSeekCompleteListenerImplementor, Mono.Android", MediaPlayer_OnSeekCompleteListenerImplementor.class, "n_onSeekComplete:(Landroid/media/MediaPlayer;)V:GetOnSeekComplete_Landroid_media_MediaPlayer_Handler:Android.Media.MediaPlayer/IOnSeekCompleteListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n");
  }
  
  public MediaPlayer_OnSeekCompleteListenerImplementor() {
    if (getClass() == MediaPlayer_OnSeekCompleteListenerImplementor.class)
      TypeManager.Activate("Android.Media.MediaPlayer+IOnSeekCompleteListenerImplementor, Mono.Android", "", this, new Object[0]); 
  }
  
  private native void n_onSeekComplete(MediaPlayer paramMediaPlayer);
  
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
  
  public void onSeekComplete(MediaPlayer paramMediaPlayer) {
    n_onSeekComplete(paramMediaPlayer);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/mono/android/media/MediaPlayer_OnSeekCompleteListenerImplementor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */