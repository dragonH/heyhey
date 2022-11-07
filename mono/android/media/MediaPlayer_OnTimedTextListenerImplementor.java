package mono.android.media;

import android.media.MediaPlayer;
import android.media.TimedText;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class MediaPlayer_OnTimedTextListenerImplementor implements IGCUserPeer, MediaPlayer.OnTimedTextListener {
  public static final String __md_methods = "n_onTimedText:(Landroid/media/MediaPlayer;Landroid/media/TimedText;)V:GetOnTimedText_Landroid_media_MediaPlayer_Landroid_media_TimedText_Handler:Android.Media.MediaPlayer/IOnTimedTextListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n";
  
  private ArrayList refList;
  
  static {
    Runtime.register("Android.Media.MediaPlayer+IOnTimedTextListenerImplementor, Mono.Android", MediaPlayer_OnTimedTextListenerImplementor.class, "n_onTimedText:(Landroid/media/MediaPlayer;Landroid/media/TimedText;)V:GetOnTimedText_Landroid_media_MediaPlayer_Landroid_media_TimedText_Handler:Android.Media.MediaPlayer/IOnTimedTextListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n");
  }
  
  public MediaPlayer_OnTimedTextListenerImplementor() {
    if (getClass() == MediaPlayer_OnTimedTextListenerImplementor.class)
      TypeManager.Activate("Android.Media.MediaPlayer+IOnTimedTextListenerImplementor, Mono.Android", "", this, new Object[0]); 
  }
  
  private native void n_onTimedText(MediaPlayer paramMediaPlayer, TimedText paramTimedText);
  
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
  
  public void onTimedText(MediaPlayer paramMediaPlayer, TimedText paramTimedText) {
    n_onTimedText(paramMediaPlayer, paramTimedText);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/mono/android/media/MediaPlayer_OnTimedTextListenerImplementor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */