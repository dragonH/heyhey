package mono.android.media;

import android.media.MediaDrm;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class MediaDrm_OnExpirationUpdateListenerImplementor implements IGCUserPeer, MediaDrm.OnExpirationUpdateListener {
  public static final String __md_methods = "n_onExpirationUpdate:(Landroid/media/MediaDrm;[BJ)V:GetOnExpirationUpdate_Landroid_media_MediaDrm_arrayBJHandler:Android.Media.MediaDrm/IOnExpirationUpdateListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n";
  
  private ArrayList refList;
  
  static {
    Runtime.register("Android.Media.MediaDrm+IOnExpirationUpdateListenerImplementor, Mono.Android", MediaDrm_OnExpirationUpdateListenerImplementor.class, "n_onExpirationUpdate:(Landroid/media/MediaDrm;[BJ)V:GetOnExpirationUpdate_Landroid_media_MediaDrm_arrayBJHandler:Android.Media.MediaDrm/IOnExpirationUpdateListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n");
  }
  
  public MediaDrm_OnExpirationUpdateListenerImplementor() {
    if (getClass() == MediaDrm_OnExpirationUpdateListenerImplementor.class)
      TypeManager.Activate("Android.Media.MediaDrm+IOnExpirationUpdateListenerImplementor, Mono.Android", "", this, new Object[0]); 
  }
  
  private native void n_onExpirationUpdate(MediaDrm paramMediaDrm, byte[] paramArrayOfbyte, long paramLong);
  
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
  
  public void onExpirationUpdate(MediaDrm paramMediaDrm, byte[] paramArrayOfbyte, long paramLong) {
    n_onExpirationUpdate(paramMediaDrm, paramArrayOfbyte, paramLong);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/mono/android/media/MediaDrm_OnExpirationUpdateListenerImplementor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */