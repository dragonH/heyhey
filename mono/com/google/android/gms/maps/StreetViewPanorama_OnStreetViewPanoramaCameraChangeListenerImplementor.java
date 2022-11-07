package mono.com.google.android.gms.maps;

import com.google.android.gms.maps.StreetViewPanorama;
import com.google.android.gms.maps.model.StreetViewPanoramaCamera;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class StreetViewPanorama_OnStreetViewPanoramaCameraChangeListenerImplementor implements IGCUserPeer, StreetViewPanorama.OnStreetViewPanoramaCameraChangeListener {
  public static final String __md_methods = "n_onStreetViewPanoramaCameraChange:(Lcom/google/android/gms/maps/model/StreetViewPanoramaCamera;)V:GetOnStreetViewPanoramaCameraChange_Lcom_google_android_gms_maps_model_StreetViewPanoramaCamera_Handler:Android.Gms.Maps.StreetViewPanorama/IOnStreetViewPanoramaCameraChangeListenerInvoker, Xamarin.GooglePlayServices.Maps\n";
  
  private ArrayList refList;
  
  static {
    Runtime.register("Android.Gms.Maps.StreetViewPanorama+IOnStreetViewPanoramaCameraChangeListenerImplementor, Xamarin.GooglePlayServices.Maps", StreetViewPanorama_OnStreetViewPanoramaCameraChangeListenerImplementor.class, "n_onStreetViewPanoramaCameraChange:(Lcom/google/android/gms/maps/model/StreetViewPanoramaCamera;)V:GetOnStreetViewPanoramaCameraChange_Lcom_google_android_gms_maps_model_StreetViewPanoramaCamera_Handler:Android.Gms.Maps.StreetViewPanorama/IOnStreetViewPanoramaCameraChangeListenerInvoker, Xamarin.GooglePlayServices.Maps\n");
  }
  
  public StreetViewPanorama_OnStreetViewPanoramaCameraChangeListenerImplementor() {
    if (getClass() == StreetViewPanorama_OnStreetViewPanoramaCameraChangeListenerImplementor.class)
      TypeManager.Activate("Android.Gms.Maps.StreetViewPanorama+IOnStreetViewPanoramaCameraChangeListenerImplementor, Xamarin.GooglePlayServices.Maps", "", this, new Object[0]); 
  }
  
  private native void n_onStreetViewPanoramaCameraChange(StreetViewPanoramaCamera paramStreetViewPanoramaCamera);
  
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
  
  public void onStreetViewPanoramaCameraChange(StreetViewPanoramaCamera paramStreetViewPanoramaCamera) {
    n_onStreetViewPanoramaCameraChange(paramStreetViewPanoramaCamera);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/mono/com/google/android/gms/maps/StreetViewPanorama_OnStreetViewPanoramaCameraChangeListenerImplementor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */