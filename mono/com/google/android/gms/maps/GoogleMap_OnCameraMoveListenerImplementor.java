package mono.com.google.android.gms.maps;

import com.google.android.gms.maps.GoogleMap;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class GoogleMap_OnCameraMoveListenerImplementor implements IGCUserPeer, GoogleMap.OnCameraMoveListener {
  public static final String __md_methods = "n_onCameraMove:()V:GetOnCameraMoveHandler:Android.Gms.Maps.GoogleMap/IOnCameraMoveListenerInvoker, Xamarin.GooglePlayServices.Maps\n";
  
  private ArrayList refList;
  
  static {
    Runtime.register("Android.Gms.Maps.GoogleMap+IOnCameraMoveListenerImplementor, Xamarin.GooglePlayServices.Maps", GoogleMap_OnCameraMoveListenerImplementor.class, "n_onCameraMove:()V:GetOnCameraMoveHandler:Android.Gms.Maps.GoogleMap/IOnCameraMoveListenerInvoker, Xamarin.GooglePlayServices.Maps\n");
  }
  
  public GoogleMap_OnCameraMoveListenerImplementor() {
    if (getClass() == GoogleMap_OnCameraMoveListenerImplementor.class)
      TypeManager.Activate("Android.Gms.Maps.GoogleMap+IOnCameraMoveListenerImplementor, Xamarin.GooglePlayServices.Maps", "", this, new Object[0]); 
  }
  
  private native void n_onCameraMove();
  
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
  
  public void onCameraMove() {
    n_onCameraMove();
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/mono/com/google/android/gms/maps/GoogleMap_OnCameraMoveListenerImplementor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */