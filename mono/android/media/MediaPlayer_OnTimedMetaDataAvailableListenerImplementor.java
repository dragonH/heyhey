package mono.android.media;

import android.media.MediaPlayer;
import android.media.TimedMetaData;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class MediaPlayer_OnTimedMetaDataAvailableListenerImplementor implements IGCUserPeer, MediaPlayer.OnTimedMetaDataAvailableListener {
  public static final String __md_methods = "n_onTimedMetaDataAvailable:(Landroid/media/MediaPlayer;Landroid/media/TimedMetaData;)V:GetOnTimedMetaDataAvailable_Landroid_media_MediaPlayer_Landroid_media_TimedMetaData_Handler:Android.Media.MediaPlayer/IOnTimedMetaDataAvailableListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n";
  
  private ArrayList refList;
  
  static {
    Runtime.register("Android.Media.MediaPlayer+IOnTimedMetaDataAvailableListenerImplementor, Mono.Android", MediaPlayer_OnTimedMetaDataAvailableListenerImplementor.class, "n_onTimedMetaDataAvailable:(Landroid/media/MediaPlayer;Landroid/media/TimedMetaData;)V:GetOnTimedMetaDataAvailable_Landroid_media_MediaPlayer_Landroid_media_TimedMetaData_Handler:Android.Media.MediaPlayer/IOnTimedMetaDataAvailableListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n");
  }
  
  public MediaPlayer_OnTimedMetaDataAvailableListenerImplementor() {
    if (getClass() == MediaPlayer_OnTimedMetaDataAvailableListenerImplementor.class)
      TypeManager.Activate("Android.Media.MediaPlayer+IOnTimedMetaDataAvailableListenerImplementor, Mono.Android", "", this, new Object[0]); 
  }
  
  private native void n_onTimedMetaDataAvailable(MediaPlayer paramMediaPlayer, TimedMetaData paramTimedMetaData);
  
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
  
  public void onTimedMetaDataAvailable(MediaPlayer paramMediaPlayer, TimedMetaData paramTimedMetaData) {
    n_onTimedMetaDataAvailable(paramMediaPlayer, paramTimedMetaData);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/mono/android/media/MediaPlayer_OnTimedMetaDataAvailableListenerImplementor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */