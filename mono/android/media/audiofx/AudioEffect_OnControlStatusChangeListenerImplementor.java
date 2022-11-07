package mono.android.media.audiofx;

import android.media.audiofx.AudioEffect;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class AudioEffect_OnControlStatusChangeListenerImplementor implements IGCUserPeer, AudioEffect.OnControlStatusChangeListener {
  public static final String __md_methods = "n_onControlStatusChange:(Landroid/media/audiofx/AudioEffect;Z)V:GetOnControlStatusChange_Landroid_media_audiofx_AudioEffect_ZHandler:Android.Media.Audiofx.AudioEffect/IOnControlStatusChangeListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n";
  
  private ArrayList refList;
  
  static {
    Runtime.register("Android.Media.Audiofx.AudioEffect+IOnControlStatusChangeListenerImplementor, Mono.Android", AudioEffect_OnControlStatusChangeListenerImplementor.class, "n_onControlStatusChange:(Landroid/media/audiofx/AudioEffect;Z)V:GetOnControlStatusChange_Landroid_media_audiofx_AudioEffect_ZHandler:Android.Media.Audiofx.AudioEffect/IOnControlStatusChangeListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n");
  }
  
  public AudioEffect_OnControlStatusChangeListenerImplementor() {
    if (getClass() == AudioEffect_OnControlStatusChangeListenerImplementor.class)
      TypeManager.Activate("Android.Media.Audiofx.AudioEffect+IOnControlStatusChangeListenerImplementor, Mono.Android", "", this, new Object[0]); 
  }
  
  private native void n_onControlStatusChange(AudioEffect paramAudioEffect, boolean paramBoolean);
  
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
  
  public void onControlStatusChange(AudioEffect paramAudioEffect, boolean paramBoolean) {
    n_onControlStatusChange(paramAudioEffect, paramBoolean);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/mono/android/media/audiofx/AudioEffect_OnControlStatusChangeListenerImplementor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */