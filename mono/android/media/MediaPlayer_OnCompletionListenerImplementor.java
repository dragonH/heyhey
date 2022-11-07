package mono.android.media;

import android.media.MediaPlayer;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class MediaPlayer_OnCompletionListenerImplementor implements IGCUserPeer, MediaPlayer.OnCompletionListener {
  public static final String __md_methods = "n_onCompletion:(Landroid/media/MediaPlayer;)V:GetOnCompletion_Landroid_media_MediaPlayer_Handler:Android.Media.MediaPlayer/IOnCompletionListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n";
  
  private ArrayList refList;
  
  static {
    Runtime.register("Android.Media.MediaPlayer+IOnCompletionListenerImplementor, Mono.Android", MediaPlayer_OnCompletionListenerImplementor.class, "n_onCompletion:(Landroid/media/MediaPlayer;)V:GetOnCompletion_Landroid_media_MediaPlayer_Handler:Android.Media.MediaPlayer/IOnCompletionListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n");
  }
  
  public MediaPlayer_OnCompletionListenerImplementor() {
    if (getClass() == MediaPlayer_OnCompletionListenerImplementor.class)
      TypeManager.Activate("Android.Media.MediaPlayer+IOnCompletionListenerImplementor, Mono.Android", "", this, new Object[0]); 
  }
  
  private native void n_onCompletion(MediaPlayer paramMediaPlayer);
  
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
  
  public void onCompletion(MediaPlayer paramMediaPlayer) {
    n_onCompletion(paramMediaPlayer);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/mono/android/media/MediaPlayer_OnCompletionListenerImplementor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */