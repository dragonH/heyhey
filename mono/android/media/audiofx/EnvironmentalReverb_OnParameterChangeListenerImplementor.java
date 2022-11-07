package mono.android.media.audiofx;

import android.media.audiofx.EnvironmentalReverb;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class EnvironmentalReverb_OnParameterChangeListenerImplementor implements IGCUserPeer, EnvironmentalReverb.OnParameterChangeListener {
  public static final String __md_methods = "n_onParameterChange:(Landroid/media/audiofx/EnvironmentalReverb;III)V:GetOnParameterChange_Landroid_media_audiofx_EnvironmentalReverb_IIIHandler:Android.Media.Audiofx.EnvironmentalReverb/IOnParameterChangeListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n";
  
  private ArrayList refList;
  
  static {
    Runtime.register("Android.Media.Audiofx.EnvironmentalReverb+IOnParameterChangeListenerImplementor, Mono.Android", EnvironmentalReverb_OnParameterChangeListenerImplementor.class, "n_onParameterChange:(Landroid/media/audiofx/EnvironmentalReverb;III)V:GetOnParameterChange_Landroid_media_audiofx_EnvironmentalReverb_IIIHandler:Android.Media.Audiofx.EnvironmentalReverb/IOnParameterChangeListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n");
  }
  
  public EnvironmentalReverb_OnParameterChangeListenerImplementor() {
    if (getClass() == EnvironmentalReverb_OnParameterChangeListenerImplementor.class)
      TypeManager.Activate("Android.Media.Audiofx.EnvironmentalReverb+IOnParameterChangeListenerImplementor, Mono.Android", "", this, new Object[0]); 
  }
  
  private native void n_onParameterChange(EnvironmentalReverb paramEnvironmentalReverb, int paramInt1, int paramInt2, int paramInt3);
  
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
  
  public void onParameterChange(EnvironmentalReverb paramEnvironmentalReverb, int paramInt1, int paramInt2, int paramInt3) {
    n_onParameterChange(paramEnvironmentalReverb, paramInt1, paramInt2, paramInt3);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/mono/android/media/audiofx/EnvironmentalReverb_OnParameterChangeListenerImplementor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */