package mono.android.media.tv;

import android.media.tv.TvView;
import android.view.InputEvent;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class TvView_OnUnhandledInputEventListenerImplementor implements IGCUserPeer, TvView.OnUnhandledInputEventListener {
  public static final String __md_methods = "n_onUnhandledInputEvent:(Landroid/view/InputEvent;)Z:GetOnUnhandledInputEvent_Landroid_view_InputEvent_Handler:Android.Media.TV.TvView/IOnUnhandledInputEventListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n";
  
  private ArrayList refList;
  
  static {
    Runtime.register("Android.Media.TV.TvView+IOnUnhandledInputEventListenerImplementor, Mono.Android", TvView_OnUnhandledInputEventListenerImplementor.class, "n_onUnhandledInputEvent:(Landroid/view/InputEvent;)Z:GetOnUnhandledInputEvent_Landroid_view_InputEvent_Handler:Android.Media.TV.TvView/IOnUnhandledInputEventListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n");
  }
  
  public TvView_OnUnhandledInputEventListenerImplementor() {
    if (getClass() == TvView_OnUnhandledInputEventListenerImplementor.class)
      TypeManager.Activate("Android.Media.TV.TvView+IOnUnhandledInputEventListenerImplementor, Mono.Android", "", this, new Object[0]); 
  }
  
  private native boolean n_onUnhandledInputEvent(InputEvent paramInputEvent);
  
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
  
  public boolean onUnhandledInputEvent(InputEvent paramInputEvent) {
    return n_onUnhandledInputEvent(paramInputEvent);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/mono/android/media/tv/TvView_OnUnhandledInputEventListenerImplementor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */