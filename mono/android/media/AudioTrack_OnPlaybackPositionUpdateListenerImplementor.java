package mono.android.media;

import android.media.AudioTrack;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class AudioTrack_OnPlaybackPositionUpdateListenerImplementor implements IGCUserPeer, AudioTrack.OnPlaybackPositionUpdateListener {
  public static final String __md_methods = "n_onMarkerReached:(Landroid/media/AudioTrack;)V:GetOnMarkerReached_Landroid_media_AudioTrack_Handler:Android.Media.AudioTrack/IOnPlaybackPositionUpdateListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\nn_onPeriodicNotification:(Landroid/media/AudioTrack;)V:GetOnPeriodicNotification_Landroid_media_AudioTrack_Handler:Android.Media.AudioTrack/IOnPlaybackPositionUpdateListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n";
  
  private ArrayList refList;
  
  static {
    Runtime.register("Android.Media.AudioTrack+IOnPlaybackPositionUpdateListenerImplementor, Mono.Android", AudioTrack_OnPlaybackPositionUpdateListenerImplementor.class, "n_onMarkerReached:(Landroid/media/AudioTrack;)V:GetOnMarkerReached_Landroid_media_AudioTrack_Handler:Android.Media.AudioTrack/IOnPlaybackPositionUpdateListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\nn_onPeriodicNotification:(Landroid/media/AudioTrack;)V:GetOnPeriodicNotification_Landroid_media_AudioTrack_Handler:Android.Media.AudioTrack/IOnPlaybackPositionUpdateListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n");
  }
  
  public AudioTrack_OnPlaybackPositionUpdateListenerImplementor() {
    if (getClass() == AudioTrack_OnPlaybackPositionUpdateListenerImplementor.class)
      TypeManager.Activate("Android.Media.AudioTrack+IOnPlaybackPositionUpdateListenerImplementor, Mono.Android", "", this, new Object[0]); 
  }
  
  private native void n_onMarkerReached(AudioTrack paramAudioTrack);
  
  private native void n_onPeriodicNotification(AudioTrack paramAudioTrack);
  
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
  
  public void onMarkerReached(AudioTrack paramAudioTrack) {
    n_onMarkerReached(paramAudioTrack);
  }
  
  public void onPeriodicNotification(AudioTrack paramAudioTrack) {
    n_onPeriodicNotification(paramAudioTrack);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/mono/android/media/AudioTrack_OnPlaybackPositionUpdateListenerImplementor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */