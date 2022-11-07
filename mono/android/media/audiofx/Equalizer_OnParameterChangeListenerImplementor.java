package mono.android.media.audiofx;

import android.media.audiofx.Equalizer;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class Equalizer_OnParameterChangeListenerImplementor implements IGCUserPeer, Equalizer.OnParameterChangeListener {
  public static final String __md_methods = "n_onParameterChange:(Landroid/media/audiofx/Equalizer;IIII)V:GetOnParameterChange_Landroid_media_audiofx_Equalizer_IIIIHandler:Android.Media.Audiofx.Equalizer/IOnParameterChangeListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n";
  
  private ArrayList refList;
  
  static {
    Runtime.register("Android.Media.Audiofx.Equalizer+IOnParameterChangeListenerImplementor, Mono.Android", Equalizer_OnParameterChangeListenerImplementor.class, "n_onParameterChange:(Landroid/media/audiofx/Equalizer;IIII)V:GetOnParameterChange_Landroid_media_audiofx_Equalizer_IIIIHandler:Android.Media.Audiofx.Equalizer/IOnParameterChangeListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n");
  }
  
  public Equalizer_OnParameterChangeListenerImplementor() {
    if (getClass() == Equalizer_OnParameterChangeListenerImplementor.class)
      TypeManager.Activate("Android.Media.Audiofx.Equalizer+IOnParameterChangeListenerImplementor, Mono.Android", "", this, new Object[0]); 
  }
  
  private native void n_onParameterChange(Equalizer paramEqualizer, int paramInt1, int paramInt2, int paramInt3, int paramInt4);
  
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
  
  public void onParameterChange(Equalizer paramEqualizer, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    n_onParameterChange(paramEqualizer, paramInt1, paramInt2, paramInt3, paramInt4);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/mono/android/media/audiofx/Equalizer_OnParameterChangeListenerImplementor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */