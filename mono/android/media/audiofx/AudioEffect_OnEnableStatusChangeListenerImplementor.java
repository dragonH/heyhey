package mono.android.media.audiofx;

import android.media.audiofx.AudioEffect;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class AudioEffect_OnEnableStatusChangeListenerImplementor implements IGCUserPeer, AudioEffect.OnEnableStatusChangeListener {
  public static final String __md_methods = "n_onEnableStatusChange:(Landroid/media/audiofx/AudioEffect;Z)V:GetOnEnableStatusChange_Landroid_media_audiofx_AudioEffect_ZHandler:Android.Media.Audiofx.AudioEffect/IOnEnableStatusChangeListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n";
  
  private ArrayList refList;
  
  static {
    Runtime.register("Android.Media.Audiofx.AudioEffect+IOnEnableStatusChangeListenerImplementor, Mono.Android", AudioEffect_OnEnableStatusChangeListenerImplementor.class, "n_onEnableStatusChange:(Landroid/media/audiofx/AudioEffect;Z)V:GetOnEnableStatusChange_Landroid_media_audiofx_AudioEffect_ZHandler:Android.Media.Audiofx.AudioEffect/IOnEnableStatusChangeListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n");
  }
  
  public AudioEffect_OnEnableStatusChangeListenerImplementor() {
    if (getClass() == AudioEffect_OnEnableStatusChangeListenerImplementor.class)
      TypeManager.Activate("Android.Media.Audiofx.AudioEffect+IOnEnableStatusChangeListenerImplementor, Mono.Android", "", this, new Object[0]); 
  }
  
  private native void n_onEnableStatusChange(AudioEffect paramAudioEffect, boolean paramBoolean);
  
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
  
  public void onEnableStatusChange(AudioEffect paramAudioEffect, boolean paramBoolean) {
    n_onEnableStatusChange(paramAudioEffect, paramBoolean);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/mono/android/media/audiofx/AudioEffect_OnEnableStatusChangeListenerImplementor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */