package mono.android.media;

import android.media.MediaCas;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class MediaCas_EventListenerImplementor implements IGCUserPeer, MediaCas.EventListener {
  public static final String __md_methods = "n_onEvent:(Landroid/media/MediaCas;II[B)V:GetOnEvent_Landroid_media_MediaCas_IIarrayBHandler:Android.Media.MediaCas/IEventListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n";
  
  private ArrayList refList;
  
  static {
    Runtime.register("Android.Media.MediaCas+IEventListenerImplementor, Mono.Android", MediaCas_EventListenerImplementor.class, "n_onEvent:(Landroid/media/MediaCas;II[B)V:GetOnEvent_Landroid_media_MediaCas_IIarrayBHandler:Android.Media.MediaCas/IEventListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n");
  }
  
  public MediaCas_EventListenerImplementor() {
    if (getClass() == MediaCas_EventListenerImplementor.class)
      TypeManager.Activate("Android.Media.MediaCas+IEventListenerImplementor, Mono.Android", "", this, new Object[0]); 
  }
  
  private native void n_onEvent(MediaCas paramMediaCas, int paramInt1, int paramInt2, byte[] paramArrayOfbyte);
  
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
  
  public void onEvent(MediaCas paramMediaCas, int paramInt1, int paramInt2, byte[] paramArrayOfbyte) {
    n_onEvent(paramMediaCas, paramInt1, paramInt2, paramArrayOfbyte);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/mono/android/media/MediaCas_EventListenerImplementor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */