package mono.android.media;

import android.media.MediaPlayer;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class MediaPlayer_OnDrmInfoListenerImplementor implements IGCUserPeer, MediaPlayer.OnDrmInfoListener {
  public static final String __md_methods = "n_onDrmInfo:(Landroid/media/MediaPlayer;Landroid/media/MediaPlayer$DrmInfo;)V:GetOnDrmInfo_Landroid_media_MediaPlayer_Landroid_media_MediaPlayer_DrmInfo_Handler:Android.Media.MediaPlayer/IOnDrmInfoListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n";
  
  private ArrayList refList;
  
  static {
    Runtime.register("Android.Media.MediaPlayer+IOnDrmInfoListenerImplementor, Mono.Android", MediaPlayer_OnDrmInfoListenerImplementor.class, "n_onDrmInfo:(Landroid/media/MediaPlayer;Landroid/media/MediaPlayer$DrmInfo;)V:GetOnDrmInfo_Landroid_media_MediaPlayer_Landroid_media_MediaPlayer_DrmInfo_Handler:Android.Media.MediaPlayer/IOnDrmInfoListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n");
  }
  
  public MediaPlayer_OnDrmInfoListenerImplementor() {
    if (getClass() == MediaPlayer_OnDrmInfoListenerImplementor.class)
      TypeManager.Activate("Android.Media.MediaPlayer+IOnDrmInfoListenerImplementor, Mono.Android", "", this, new Object[0]); 
  }
  
  private native void n_onDrmInfo(MediaPlayer paramMediaPlayer, MediaPlayer.DrmInfo paramDrmInfo);
  
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
  
  public void onDrmInfo(MediaPlayer paramMediaPlayer, MediaPlayer.DrmInfo paramDrmInfo) {
    n_onDrmInfo(paramMediaPlayer, paramDrmInfo);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/mono/android/media/MediaPlayer_OnDrmInfoListenerImplementor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */