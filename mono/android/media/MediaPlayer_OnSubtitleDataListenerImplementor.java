package mono.android.media;

import android.media.MediaPlayer;
import android.media.SubtitleData;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class MediaPlayer_OnSubtitleDataListenerImplementor implements IGCUserPeer, MediaPlayer.OnSubtitleDataListener {
  public static final String __md_methods = "n_onSubtitleData:(Landroid/media/MediaPlayer;Landroid/media/SubtitleData;)V:GetOnSubtitleData_Landroid_media_MediaPlayer_Landroid_media_SubtitleData_Handler:Android.Media.MediaPlayer/IOnSubtitleDataListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n";
  
  private ArrayList refList;
  
  static {
    Runtime.register("Android.Media.MediaPlayer+IOnSubtitleDataListenerImplementor, Mono.Android", MediaPlayer_OnSubtitleDataListenerImplementor.class, "n_onSubtitleData:(Landroid/media/MediaPlayer;Landroid/media/SubtitleData;)V:GetOnSubtitleData_Landroid_media_MediaPlayer_Landroid_media_SubtitleData_Handler:Android.Media.MediaPlayer/IOnSubtitleDataListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n");
  }
  
  public MediaPlayer_OnSubtitleDataListenerImplementor() {
    if (getClass() == MediaPlayer_OnSubtitleDataListenerImplementor.class)
      TypeManager.Activate("Android.Media.MediaPlayer+IOnSubtitleDataListenerImplementor, Mono.Android", "", this, new Object[0]); 
  }
  
  private native void n_onSubtitleData(MediaPlayer paramMediaPlayer, SubtitleData paramSubtitleData);
  
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
  
  public void onSubtitleData(MediaPlayer paramMediaPlayer, SubtitleData paramSubtitleData) {
    n_onSubtitleData(paramMediaPlayer, paramSubtitleData);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/mono/android/media/MediaPlayer_OnSubtitleDataListenerImplementor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */