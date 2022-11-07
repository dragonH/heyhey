package mono.android.media;

import android.media.AudioManager;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class AudioManager_OnAudioFocusChangeListenerImplementor implements IGCUserPeer, AudioManager.OnAudioFocusChangeListener {
  public static final String __md_methods = "n_onAudioFocusChange:(I)V:GetOnAudioFocusChange_IHandler:Android.Media.AudioManager/IOnAudioFocusChangeListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n";
  
  private ArrayList refList;
  
  static {
    Runtime.register("Android.Media.AudioManager+IOnAudioFocusChangeListenerImplementor, Mono.Android", AudioManager_OnAudioFocusChangeListenerImplementor.class, "n_onAudioFocusChange:(I)V:GetOnAudioFocusChange_IHandler:Android.Media.AudioManager/IOnAudioFocusChangeListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n");
  }
  
  public AudioManager_OnAudioFocusChangeListenerImplementor() {
    if (getClass() == AudioManager_OnAudioFocusChangeListenerImplementor.class)
      TypeManager.Activate("Android.Media.AudioManager+IOnAudioFocusChangeListenerImplementor, Mono.Android", "", this, new Object[0]); 
  }
  
  private native void n_onAudioFocusChange(int paramInt);
  
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
  
  public void onAudioFocusChange(int paramInt) {
    n_onAudioFocusChange(paramInt);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/mono/android/media/AudioManager_OnAudioFocusChangeListenerImplementor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */