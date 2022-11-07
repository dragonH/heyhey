package mono.android.media;

import android.media.MediaDrm;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class MediaDrm_OnEventListenerImplementor implements IGCUserPeer, MediaDrm.OnEventListener {
  public static final String __md_methods = "n_onEvent:(Landroid/media/MediaDrm;[BII[B)V:GetOnEvent_Landroid_media_MediaDrm_arrayBIIarrayBHandler:Android.Media.MediaDrm/IOnEventListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n";
  
  private ArrayList refList;
  
  static {
    Runtime.register("Android.Media.MediaDrm+IOnEventListenerImplementor, Mono.Android", MediaDrm_OnEventListenerImplementor.class, "n_onEvent:(Landroid/media/MediaDrm;[BII[B)V:GetOnEvent_Landroid_media_MediaDrm_arrayBIIarrayBHandler:Android.Media.MediaDrm/IOnEventListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n");
  }
  
  public MediaDrm_OnEventListenerImplementor() {
    if (getClass() == MediaDrm_OnEventListenerImplementor.class)
      TypeManager.Activate("Android.Media.MediaDrm+IOnEventListenerImplementor, Mono.Android", "", this, new Object[0]); 
  }
  
  private native void n_onEvent(MediaDrm paramMediaDrm, byte[] paramArrayOfbyte1, int paramInt1, int paramInt2, byte[] paramArrayOfbyte2);
  
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
  
  public void onEvent(MediaDrm paramMediaDrm, byte[] paramArrayOfbyte1, int paramInt1, int paramInt2, byte[] paramArrayOfbyte2) {
    n_onEvent(paramMediaDrm, paramArrayOfbyte1, paramInt1, paramInt2, paramArrayOfbyte2);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/mono/android/media/MediaDrm_OnEventListenerImplementor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */