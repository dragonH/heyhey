package mono.android.support.v4.media.session;

import android.support.v4.media.session.MediaSessionCompat;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class MediaSessionCompat_OnActiveChangeListenerImplementor implements IGCUserPeer, MediaSessionCompat.OnActiveChangeListener {
  public static final String __md_methods = "n_onActiveChanged:()V:GetOnActiveChangedHandler:Android.Support.V4.Media.Session.MediaSessionCompat/IOnActiveChangeListenerInvoker, Xamarin.Android.Support.Media.Compat\n";
  
  private ArrayList refList;
  
  static {
    Runtime.register("Android.Support.V4.Media.Session.MediaSessionCompat+IOnActiveChangeListenerImplementor, Xamarin.Android.Support.Media.Compat", MediaSessionCompat_OnActiveChangeListenerImplementor.class, "n_onActiveChanged:()V:GetOnActiveChangedHandler:Android.Support.V4.Media.Session.MediaSessionCompat/IOnActiveChangeListenerInvoker, Xamarin.Android.Support.Media.Compat\n");
  }
  
  public MediaSessionCompat_OnActiveChangeListenerImplementor() {
    if (getClass() == MediaSessionCompat_OnActiveChangeListenerImplementor.class)
      TypeManager.Activate("Android.Support.V4.Media.Session.MediaSessionCompat+IOnActiveChangeListenerImplementor, Xamarin.Android.Support.Media.Compat", "", this, new Object[0]); 
  }
  
  private native void n_onActiveChanged();
  
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
  
  public void onActiveChanged() {
    n_onActiveChanged();
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/mono/android/support/v4/media/session/MediaSessionCompat_OnActiveChangeListenerImplementor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */