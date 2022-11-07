package mono.android.media.audiofx;

import android.media.audiofx.PresetReverb;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class PresetReverb_OnParameterChangeListenerImplementor implements IGCUserPeer, PresetReverb.OnParameterChangeListener {
  public static final String __md_methods = "n_onParameterChange:(Landroid/media/audiofx/PresetReverb;IIS)V:GetOnParameterChange_Landroid_media_audiofx_PresetReverb_IISHandler:Android.Media.Audiofx.PresetReverb/IOnParameterChangeListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n";
  
  private ArrayList refList;
  
  static {
    Runtime.register("Android.Media.Audiofx.PresetReverb+IOnParameterChangeListenerImplementor, Mono.Android", PresetReverb_OnParameterChangeListenerImplementor.class, "n_onParameterChange:(Landroid/media/audiofx/PresetReverb;IIS)V:GetOnParameterChange_Landroid_media_audiofx_PresetReverb_IISHandler:Android.Media.Audiofx.PresetReverb/IOnParameterChangeListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n");
  }
  
  public PresetReverb_OnParameterChangeListenerImplementor() {
    if (getClass() == PresetReverb_OnParameterChangeListenerImplementor.class)
      TypeManager.Activate("Android.Media.Audiofx.PresetReverb+IOnParameterChangeListenerImplementor, Mono.Android", "", this, new Object[0]); 
  }
  
  private native void n_onParameterChange(PresetReverb paramPresetReverb, int paramInt1, int paramInt2, short paramShort);
  
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
  
  public void onParameterChange(PresetReverb paramPresetReverb, int paramInt1, int paramInt2, short paramShort) {
    n_onParameterChange(paramPresetReverb, paramInt1, paramInt2, paramShort);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/mono/android/media/audiofx/PresetReverb_OnParameterChangeListenerImplementor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */