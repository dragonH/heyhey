package mono.com.google.android.gms.maps;

import com.google.android.gms.maps.StreetViewPanorama;
import com.google.android.gms.maps.model.StreetViewPanoramaLocation;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class StreetViewPanorama_OnStreetViewPanoramaChangeListenerImplementor implements IGCUserPeer, StreetViewPanorama.OnStreetViewPanoramaChangeListener {
  public static final String __md_methods = "n_onStreetViewPanoramaChange:(Lcom/google/android/gms/maps/model/StreetViewPanoramaLocation;)V:GetOnStreetViewPanoramaChange_Lcom_google_android_gms_maps_model_StreetViewPanoramaLocation_Handler:Android.Gms.Maps.StreetViewPanorama/IOnStreetViewPanoramaChangeListenerInvoker, Xamarin.GooglePlayServices.Maps\n";
  
  private ArrayList refList;
  
  static {
    Runtime.register("Android.Gms.Maps.StreetViewPanorama+IOnStreetViewPanoramaChangeListenerImplementor, Xamarin.GooglePlayServices.Maps", StreetViewPanorama_OnStreetViewPanoramaChangeListenerImplementor.class, "n_onStreetViewPanoramaChange:(Lcom/google/android/gms/maps/model/StreetViewPanoramaLocation;)V:GetOnStreetViewPanoramaChange_Lcom_google_android_gms_maps_model_StreetViewPanoramaLocation_Handler:Android.Gms.Maps.StreetViewPanorama/IOnStreetViewPanoramaChangeListenerInvoker, Xamarin.GooglePlayServices.Maps\n");
  }
  
  public StreetViewPanorama_OnStreetViewPanoramaChangeListenerImplementor() {
    if (getClass() == StreetViewPanorama_OnStreetViewPanoramaChangeListenerImplementor.class)
      TypeManager.Activate("Android.Gms.Maps.StreetViewPanorama+IOnStreetViewPanoramaChangeListenerImplementor, Xamarin.GooglePlayServices.Maps", "", this, new Object[0]); 
  }
  
  private native void n_onStreetViewPanoramaChange(StreetViewPanoramaLocation paramStreetViewPanoramaLocation);
  
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
  
  public void onStreetViewPanoramaChange(StreetViewPanoramaLocation paramStreetViewPanoramaLocation) {
    n_onStreetViewPanoramaChange(paramStreetViewPanoramaLocation);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/mono/com/google/android/gms/maps/StreetViewPanorama_OnStreetViewPanoramaChangeListenerImplementor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */