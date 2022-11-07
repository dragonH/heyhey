package mono.android.media;

import android.media.MediaPlayer;
import android.media.MediaTimestamp;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class MediaPlayer_OnMediaTimeDiscontinuityListenerImplementor implements IGCUserPeer, MediaPlayer.OnMediaTimeDiscontinuityListener {
  public static final String __md_methods = "n_onMediaTimeDiscontinuity:(Landroid/media/MediaPlayer;Landroid/media/MediaTimestamp;)V:GetOnMediaTimeDiscontinuity_Landroid_media_MediaPlayer_Landroid_media_MediaTimestamp_Handler:Android.Media.MediaPlayer/IOnMediaTimeDiscontinuityListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n";
  
  private ArrayList refList;
  
  static {
    Runtime.register("Android.Media.MediaPlayer+IOnMediaTimeDiscontinuityListenerImplementor, Mono.Android", MediaPlayer_OnMediaTimeDiscontinuityListenerImplementor.class, "n_onMediaTimeDiscontinuity:(Landroid/media/MediaPlayer;Landroid/media/MediaTimestamp;)V:GetOnMediaTimeDiscontinuity_Landroid_media_MediaPlayer_Landroid_media_MediaTimestamp_Handler:Android.Media.MediaPlayer/IOnMediaTimeDiscontinuityListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n");
  }
  
  public MediaPlayer_OnMediaTimeDiscontinuityListenerImplementor() {
    if (getClass() == MediaPlayer_OnMediaTimeDiscontinuityListenerImplementor.class)
      TypeManager.Activate("Android.Media.MediaPlayer+IOnMediaTimeDiscontinuityListenerImplementor, Mono.Android", "", this, new Object[0]); 
  }
  
  private native void n_onMediaTimeDiscontinuity(MediaPlayer paramMediaPlayer, MediaTimestamp paramMediaTimestamp);
  
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
  
  public void onMediaTimeDiscontinuity(MediaPlayer paramMediaPlayer, MediaTimestamp paramMediaTimestamp) {
    n_onMediaTimeDiscontinuity(paramMediaPlayer, paramMediaTimestamp);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/mono/android/media/MediaPlayer_OnMediaTimeDiscontinuityListenerImplementor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */