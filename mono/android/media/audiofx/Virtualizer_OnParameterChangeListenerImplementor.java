package mono.android.media.audiofx;

import android.media.audiofx.Virtualizer;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class Virtualizer_OnParameterChangeListenerImplementor implements IGCUserPeer, Virtualizer.OnParameterChangeListener {
  public static final String __md_methods = "n_onParameterChange:(Landroid/media/audiofx/Virtualizer;IIS)V:GetOnParameterChange_Landroid_media_audiofx_Virtualizer_IISHandler:Android.Media.Audiofx.Virtualizer/IOnParameterChangeListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n";
  
  private ArrayList refList;
  
  static {
    Runtime.register("Android.Media.Audiofx.Virtualizer+IOnParameterChangeListenerImplementor, Mono.Android", Virtualizer_OnParameterChangeListenerImplementor.class, "n_onParameterChange:(Landroid/media/audiofx/Virtualizer;IIS)V:GetOnParameterChange_Landroid_media_audiofx_Virtualizer_IISHandler:Android.Media.Audiofx.Virtualizer/IOnParameterChangeListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n");
  }
  
  public Virtualizer_OnParameterChangeListenerImplementor() {
    if (getClass() == Virtualizer_OnParameterChangeListenerImplementor.class)
      TypeManager.Activate("Android.Media.Audiofx.Virtualizer+IOnParameterChangeListenerImplementor, Mono.Android", "", this, new Object[0]); 
  }
  
  private native void n_onParameterChange(Virtualizer paramVirtualizer, int paramInt1, int paramInt2, short paramShort);
  
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
  
  public void onParameterChange(Virtualizer paramVirtualizer, int paramInt1, int paramInt2, short paramShort) {
    n_onParameterChange(paramVirtualizer, paramInt1, paramInt2, paramShort);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/mono/android/media/audiofx/Virtualizer_OnParameterChangeListenerImplementor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */