package mono.android.media;

import android.media.MediaPlayer;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class MediaPlayer_OnBufferingUpdateListenerImplementor implements IGCUserPeer, MediaPlayer.OnBufferingUpdateListener {
  public static final String __md_methods = "n_onBufferingUpdate:(Landroid/media/MediaPlayer;I)V:GetOnBufferingUpdate_Landroid_media_MediaPlayer_IHandler:Android.Media.MediaPlayer/IOnBufferingUpdateListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n";
  
  private ArrayList refList;
  
  static {
    Runtime.register("Android.Media.MediaPlayer+IOnBufferingUpdateListenerImplementor, Mono.Android", MediaPlayer_OnBufferingUpdateListenerImplementor.class, "n_onBufferingUpdate:(Landroid/media/MediaPlayer;I)V:GetOnBufferingUpdate_Landroid_media_MediaPlayer_IHandler:Android.Media.MediaPlayer/IOnBufferingUpdateListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n");
  }
  
  public MediaPlayer_OnBufferingUpdateListenerImplementor() {
    if (getClass() == MediaPlayer_OnBufferingUpdateListenerImplementor.class)
      TypeManager.Activate("Android.Media.MediaPlayer+IOnBufferingUpdateListenerImplementor, Mono.Android", "", this, new Object[0]); 
  }
  
  private native void n_onBufferingUpdate(MediaPlayer paramMediaPlayer, int paramInt);
  
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
  
  public void onBufferingUpdate(MediaPlayer paramMediaPlayer, int paramInt) {
    n_onBufferingUpdate(paramMediaPlayer, paramInt);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/mono/android/media/MediaPlayer_OnBufferingUpdateListenerImplementor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */