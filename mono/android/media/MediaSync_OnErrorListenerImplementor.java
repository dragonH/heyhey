package mono.android.media;

import android.media.MediaSync;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class MediaSync_OnErrorListenerImplementor implements IGCUserPeer, MediaSync.OnErrorListener {
  public static final String __md_methods = "n_onError:(Landroid/media/MediaSync;II)V:GetOnError_Landroid_media_MediaSync_IIHandler:Android.Media.MediaSync/IOnErrorListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n";
  
  private ArrayList refList;
  
  static {
    Runtime.register("Android.Media.MediaSync+IOnErrorListenerImplementor, Mono.Android", MediaSync_OnErrorListenerImplementor.class, "n_onError:(Landroid/media/MediaSync;II)V:GetOnError_Landroid_media_MediaSync_IIHandler:Android.Media.MediaSync/IOnErrorListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n");
  }
  
  public MediaSync_OnErrorListenerImplementor() {
    if (getClass() == MediaSync_OnErrorListenerImplementor.class)
      TypeManager.Activate("Android.Media.MediaSync+IOnErrorListenerImplementor, Mono.Android", "", this, new Object[0]); 
  }
  
  private native void n_onError(MediaSync paramMediaSync, int paramInt1, int paramInt2);
  
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
  
  public void onError(MediaSync paramMediaSync, int paramInt1, int paramInt2) {
    n_onError(paramMediaSync, paramInt1, paramInt2);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/mono/android/media/MediaSync_OnErrorListenerImplementor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */