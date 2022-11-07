package mono.android.media;

import android.media.MediaPlayer;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class MediaPlayer_OnDrmPreparedListenerImplementor implements IGCUserPeer, MediaPlayer.OnDrmPreparedListener {
  public static final String __md_methods = "n_onDrmPrepared:(Landroid/media/MediaPlayer;I)V:GetOnDrmPrepared_Landroid_media_MediaPlayer_IHandler:Android.Media.MediaPlayer/IOnDrmPreparedListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n";
  
  private ArrayList refList;
  
  static {
    Runtime.register("Android.Media.MediaPlayer+IOnDrmPreparedListenerImplementor, Mono.Android", MediaPlayer_OnDrmPreparedListenerImplementor.class, "n_onDrmPrepared:(Landroid/media/MediaPlayer;I)V:GetOnDrmPrepared_Landroid_media_MediaPlayer_IHandler:Android.Media.MediaPlayer/IOnDrmPreparedListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n");
  }
  
  public MediaPlayer_OnDrmPreparedListenerImplementor() {
    if (getClass() == MediaPlayer_OnDrmPreparedListenerImplementor.class)
      TypeManager.Activate("Android.Media.MediaPlayer+IOnDrmPreparedListenerImplementor, Mono.Android", "", this, new Object[0]); 
  }
  
  private native void n_onDrmPrepared(MediaPlayer paramMediaPlayer, int paramInt);
  
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
  
  public void onDrmPrepared(MediaPlayer paramMediaPlayer, int paramInt) {
    n_onDrmPrepared(paramMediaPlayer, paramInt);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/mono/android/media/MediaPlayer_OnDrmPreparedListenerImplementor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */