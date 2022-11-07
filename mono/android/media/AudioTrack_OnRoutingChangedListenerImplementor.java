package mono.android.media;

import android.media.AudioTrack;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class AudioTrack_OnRoutingChangedListenerImplementor implements IGCUserPeer, AudioTrack.OnRoutingChangedListener {
  public static final String __md_methods = "n_onRoutingChanged:(Landroid/media/AudioTrack;)V:GetOnRoutingChanged_Landroid_media_AudioTrack_Handler:Android.Media.AudioTrack/IOnRoutingChangedListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n";
  
  private ArrayList refList;
  
  static {
    Runtime.register("Android.Media.AudioTrack+IOnRoutingChangedListenerImplementor, Mono.Android", AudioTrack_OnRoutingChangedListenerImplementor.class, "n_onRoutingChanged:(Landroid/media/AudioTrack;)V:GetOnRoutingChanged_Landroid_media_AudioTrack_Handler:Android.Media.AudioTrack/IOnRoutingChangedListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n");
  }
  
  public AudioTrack_OnRoutingChangedListenerImplementor() {
    if (getClass() == AudioTrack_OnRoutingChangedListenerImplementor.class)
      TypeManager.Activate("Android.Media.AudioTrack+IOnRoutingChangedListenerImplementor, Mono.Android", "", this, new Object[0]); 
  }
  
  private native void n_onRoutingChanged(AudioTrack paramAudioTrack);
  
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
  
  public void onRoutingChanged(AudioTrack paramAudioTrack) {
    n_onRoutingChanged(paramAudioTrack);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/mono/android/media/AudioTrack_OnRoutingChangedListenerImplementor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */