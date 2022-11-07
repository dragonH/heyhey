package mono.android.media.session;

import android.media.session.MediaSessionManager;
import java.util.ArrayList;
import java.util.List;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class MediaSessionManager_OnActiveSessionsChangedListenerImplementor implements IGCUserPeer, MediaSessionManager.OnActiveSessionsChangedListener {
  public static final String __md_methods = "n_onActiveSessionsChanged:(Ljava/util/List;)V:GetOnActiveSessionsChanged_Ljava_util_List_Handler:Android.Media.Session.MediaSessionManager/IOnActiveSessionsChangedListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n";
  
  private ArrayList refList;
  
  static {
    Runtime.register("Android.Media.Session.MediaSessionManager+IOnActiveSessionsChangedListenerImplementor, Mono.Android", MediaSessionManager_OnActiveSessionsChangedListenerImplementor.class, "n_onActiveSessionsChanged:(Ljava/util/List;)V:GetOnActiveSessionsChanged_Ljava_util_List_Handler:Android.Media.Session.MediaSessionManager/IOnActiveSessionsChangedListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n");
  }
  
  public MediaSessionManager_OnActiveSessionsChangedListenerImplementor() {
    if (getClass() == MediaSessionManager_OnActiveSessionsChangedListenerImplementor.class)
      TypeManager.Activate("Android.Media.Session.MediaSessionManager+IOnActiveSessionsChangedListenerImplementor, Mono.Android", "", this, new Object[0]); 
  }
  
  private native void n_onActiveSessionsChanged(List paramList);
  
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
  
  public void onActiveSessionsChanged(List paramList) {
    n_onActiveSessionsChanged(paramList);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/mono/android/media/session/MediaSessionManager_OnActiveSessionsChangedListenerImplementor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */