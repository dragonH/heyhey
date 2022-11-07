package mono.android.media;

import android.media.AudioRecord;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class AudioRecord_OnRoutingChangedListenerImplementor implements IGCUserPeer, AudioRecord.OnRoutingChangedListener {
  public static final String __md_methods = "n_onRoutingChanged:(Landroid/media/AudioRecord;)V:GetOnRoutingChanged_Landroid_media_AudioRecord_Handler:Android.Media.AudioRecord/IOnRoutingChangedListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n";
  
  private ArrayList refList;
  
  static {
    Runtime.register("Android.Media.AudioRecord+IOnRoutingChangedListenerImplementor, Mono.Android", AudioRecord_OnRoutingChangedListenerImplementor.class, "n_onRoutingChanged:(Landroid/media/AudioRecord;)V:GetOnRoutingChanged_Landroid_media_AudioRecord_Handler:Android.Media.AudioRecord/IOnRoutingChangedListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n");
  }
  
  public AudioRecord_OnRoutingChangedListenerImplementor() {
    if (getClass() == AudioRecord_OnRoutingChangedListenerImplementor.class)
      TypeManager.Activate("Android.Media.AudioRecord+IOnRoutingChangedListenerImplementor, Mono.Android", "", this, new Object[0]); 
  }
  
  private native void n_onRoutingChanged(AudioRecord paramAudioRecord);
  
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
  
  public void onRoutingChanged(AudioRecord paramAudioRecord) {
    n_onRoutingChanged(paramAudioRecord);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/mono/android/media/AudioRecord_OnRoutingChangedListenerImplementor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */