package mono.android.media;

import android.media.MediaCodec;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class MediaCodec_OnFrameRenderedListenerImplementor implements IGCUserPeer, MediaCodec.OnFrameRenderedListener {
  public static final String __md_methods = "n_onFrameRendered:(Landroid/media/MediaCodec;JJ)V:GetOnFrameRendered_Landroid_media_MediaCodec_JJHandler:Android.Media.MediaCodec/IOnFrameRenderedListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n";
  
  private ArrayList refList;
  
  static {
    Runtime.register("Android.Media.MediaCodec+IOnFrameRenderedListenerImplementor, Mono.Android", MediaCodec_OnFrameRenderedListenerImplementor.class, "n_onFrameRendered:(Landroid/media/MediaCodec;JJ)V:GetOnFrameRendered_Landroid_media_MediaCodec_JJHandler:Android.Media.MediaCodec/IOnFrameRenderedListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n");
  }
  
  public MediaCodec_OnFrameRenderedListenerImplementor() {
    if (getClass() == MediaCodec_OnFrameRenderedListenerImplementor.class)
      TypeManager.Activate("Android.Media.MediaCodec+IOnFrameRenderedListenerImplementor, Mono.Android", "", this, new Object[0]); 
  }
  
  private native void n_onFrameRendered(MediaCodec paramMediaCodec, long paramLong1, long paramLong2);
  
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
  
  public void onFrameRendered(MediaCodec paramMediaCodec, long paramLong1, long paramLong2) {
    n_onFrameRendered(paramMediaCodec, paramLong1, paramLong2);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/mono/android/media/MediaCodec_OnFrameRenderedListenerImplementor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */