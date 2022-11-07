package mono.com.google.android.gms.maps;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Polyline;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class GoogleMap_OnPolylineClickListenerImplementor implements IGCUserPeer, GoogleMap.OnPolylineClickListener {
  public static final String __md_methods = "n_onPolylineClick:(Lcom/google/android/gms/maps/model/Polyline;)V:GetOnPolylineClick_Lcom_google_android_gms_maps_model_Polyline_Handler:Android.Gms.Maps.GoogleMap/IOnPolylineClickListenerInvoker, Xamarin.GooglePlayServices.Maps\n";
  
  private ArrayList refList;
  
  static {
    Runtime.register("Android.Gms.Maps.GoogleMap+IOnPolylineClickListenerImplementor, Xamarin.GooglePlayServices.Maps", GoogleMap_OnPolylineClickListenerImplementor.class, "n_onPolylineClick:(Lcom/google/android/gms/maps/model/Polyline;)V:GetOnPolylineClick_Lcom_google_android_gms_maps_model_Polyline_Handler:Android.Gms.Maps.GoogleMap/IOnPolylineClickListenerInvoker, Xamarin.GooglePlayServices.Maps\n");
  }
  
  public GoogleMap_OnPolylineClickListenerImplementor() {
    if (getClass() == GoogleMap_OnPolylineClickListenerImplementor.class)
      TypeManager.Activate("Android.Gms.Maps.GoogleMap+IOnPolylineClickListenerImplementor, Xamarin.GooglePlayServices.Maps", "", this, new Object[0]); 
  }
  
  private native void n_onPolylineClick(Polyline paramPolyline);
  
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
  
  public void onPolylineClick(Polyline paramPolyline) {
    n_onPolylineClick(paramPolyline);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/mono/com/google/android/gms/maps/GoogleMap_OnPolylineClickListenerImplementor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */