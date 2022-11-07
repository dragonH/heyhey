package mono.android.media;

import android.media.MediaRecorder;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class MediaRecorder_OnInfoListenerImplementor implements IGCUserPeer, MediaRecorder.OnInfoListener {
  public static final String __md_methods = "n_onInfo:(Landroid/media/MediaRecorder;II)V:GetOnInfo_Landroid_media_MediaRecorder_IIHandler:Android.Media.MediaRecorder/IOnInfoListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n";
  
  private ArrayList refList;
  
  static {
    Runtime.register("Android.Media.MediaRecorder+IOnInfoListenerImplementor, Mono.Android", MediaRecorder_OnInfoListenerImplementor.class, "n_onInfo:(Landroid/media/MediaRecorder;II)V:GetOnInfo_Landroid_media_MediaRecorder_IIHandler:Android.Media.MediaRecorder/IOnInfoListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n");
  }
  
  public MediaRecorder_OnInfoListenerImplementor() {
    if (getClass() == MediaRecorder_OnInfoListenerImplementor.class)
      TypeManager.Activate("Android.Media.MediaRecorder+IOnInfoListenerImplementor, Mono.Android", "", this, new Object[0]); 
  }
  
  private native void n_onInfo(MediaRecorder paramMediaRecorder, int paramInt1, int paramInt2);
  
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
  
  public void onInfo(MediaRecorder paramMediaRecorder, int paramInt1, int paramInt2) {
    n_onInfo(paramMediaRecorder, paramInt1, paramInt2);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/mono/android/media/MediaRecorder_OnInfoListenerImplementor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */