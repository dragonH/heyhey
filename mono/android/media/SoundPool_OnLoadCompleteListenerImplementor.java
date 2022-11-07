package mono.android.media;

import android.media.SoundPool;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class SoundPool_OnLoadCompleteListenerImplementor implements IGCUserPeer, SoundPool.OnLoadCompleteListener {
  public static final String __md_methods = "n_onLoadComplete:(Landroid/media/SoundPool;II)V:GetOnLoadComplete_Landroid_media_SoundPool_IIHandler:Android.Media.SoundPool/IOnLoadCompleteListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n";
  
  private ArrayList refList;
  
  static {
    Runtime.register("Android.Media.SoundPool+IOnLoadCompleteListenerImplementor, Mono.Android", SoundPool_OnLoadCompleteListenerImplementor.class, "n_onLoadComplete:(Landroid/media/SoundPool;II)V:GetOnLoadComplete_Landroid_media_SoundPool_IIHandler:Android.Media.SoundPool/IOnLoadCompleteListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n");
  }
  
  public SoundPool_OnLoadCompleteListenerImplementor() {
    if (getClass() == SoundPool_OnLoadCompleteListenerImplementor.class)
      TypeManager.Activate("Android.Media.SoundPool+IOnLoadCompleteListenerImplementor, Mono.Android", "", this, new Object[0]); 
  }
  
  private native void n_onLoadComplete(SoundPool paramSoundPool, int paramInt1, int paramInt2);
  
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
  
  public void onLoadComplete(SoundPool paramSoundPool, int paramInt1, int paramInt2) {
    n_onLoadComplete(paramSoundPool, paramInt1, paramInt2);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/mono/android/media/SoundPool_OnLoadCompleteListenerImplementor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */