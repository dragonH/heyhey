package mono.android.media;

import android.media.MediaDrm;
import java.util.ArrayList;
import java.util.List;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class MediaDrm_OnKeyStatusChangeListenerImplementor implements IGCUserPeer, MediaDrm.OnKeyStatusChangeListener {
  public static final String __md_methods = "n_onKeyStatusChange:(Landroid/media/MediaDrm;[BLjava/util/List;Z)V:GetOnKeyStatusChange_Landroid_media_MediaDrm_arrayBLjava_util_List_ZHandler:Android.Media.MediaDrm/IOnKeyStatusChangeListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n";
  
  private ArrayList refList;
  
  static {
    Runtime.register("Android.Media.MediaDrm+IOnKeyStatusChangeListenerImplementor, Mono.Android", MediaDrm_OnKeyStatusChangeListenerImplementor.class, "n_onKeyStatusChange:(Landroid/media/MediaDrm;[BLjava/util/List;Z)V:GetOnKeyStatusChange_Landroid_media_MediaDrm_arrayBLjava_util_List_ZHandler:Android.Media.MediaDrm/IOnKeyStatusChangeListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n");
  }
  
  public MediaDrm_OnKeyStatusChangeListenerImplementor() {
    if (getClass() == MediaDrm_OnKeyStatusChangeListenerImplementor.class)
      TypeManager.Activate("Android.Media.MediaDrm+IOnKeyStatusChangeListenerImplementor, Mono.Android", "", this, new Object[0]); 
  }
  
  private native void n_onKeyStatusChange(MediaDrm paramMediaDrm, byte[] paramArrayOfbyte, List paramList, boolean paramBoolean);
  
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
  
  public void onKeyStatusChange(MediaDrm paramMediaDrm, byte[] paramArrayOfbyte, List paramList, boolean paramBoolean) {
    n_onKeyStatusChange(paramMediaDrm, paramArrayOfbyte, paramList, paramBoolean);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/mono/android/media/MediaDrm_OnKeyStatusChangeListenerImplementor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */