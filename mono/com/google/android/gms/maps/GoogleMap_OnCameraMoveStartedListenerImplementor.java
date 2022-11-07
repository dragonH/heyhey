package mono.com.google.android.gms.maps;

import com.google.android.gms.maps.GoogleMap;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class GoogleMap_OnCameraMoveStartedListenerImplementor implements IGCUserPeer, GoogleMap.OnCameraMoveStartedListener {
  public static final String __md_methods = "n_onCameraMoveStarted:(I)V:GetOnCameraMoveStarted_IHandler:Android.Gms.Maps.GoogleMap/IOnCameraMoveStartedListenerInvoker, Xamarin.GooglePlayServices.Maps\n";
  
  private ArrayList refList;
  
  static {
    Runtime.register("Android.Gms.Maps.GoogleMap+IOnCameraMoveStartedListenerImplementor, Xamarin.GooglePlayServices.Maps", GoogleMap_OnCameraMoveStartedListenerImplementor.class, "n_onCameraMoveStarted:(I)V:GetOnCameraMoveStarted_IHandler:Android.Gms.Maps.GoogleMap/IOnCameraMoveStartedListenerInvoker, Xamarin.GooglePlayServices.Maps\n");
  }
  
  public GoogleMap_OnCameraMoveStartedListenerImplementor() {
    if (getClass() == GoogleMap_OnCameraMoveStartedListenerImplementor.class)
      TypeManager.Activate("Android.Gms.Maps.GoogleMap+IOnCameraMoveStartedListenerImplementor, Xamarin.GooglePlayServices.Maps", "", this, new Object[0]); 
  }
  
  private native void n_onCameraMoveStarted(int paramInt);
  
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
  
  public void onCameraMoveStarted(int paramInt) {
    n_onCameraMoveStarted(paramInt);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/mono/com/google/android/gms/maps/GoogleMap_OnCameraMoveStartedListenerImplementor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */