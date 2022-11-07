package mono.com.google.android.gms.maps;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class GoogleMap_OnInfoWindowLongClickListenerImplementor implements IGCUserPeer, GoogleMap.OnInfoWindowLongClickListener {
  public static final String __md_methods = "n_onInfoWindowLongClick:(Lcom/google/android/gms/maps/model/Marker;)V:GetOnInfoWindowLongClick_Lcom_google_android_gms_maps_model_Marker_Handler:Android.Gms.Maps.GoogleMap/IOnInfoWindowLongClickListenerInvoker, Xamarin.GooglePlayServices.Maps\n";
  
  private ArrayList refList;
  
  static {
    Runtime.register("Android.Gms.Maps.GoogleMap+IOnInfoWindowLongClickListenerImplementor, Xamarin.GooglePlayServices.Maps", GoogleMap_OnInfoWindowLongClickListenerImplementor.class, "n_onInfoWindowLongClick:(Lcom/google/android/gms/maps/model/Marker;)V:GetOnInfoWindowLongClick_Lcom_google_android_gms_maps_model_Marker_Handler:Android.Gms.Maps.GoogleMap/IOnInfoWindowLongClickListenerInvoker, Xamarin.GooglePlayServices.Maps\n");
  }
  
  public GoogleMap_OnInfoWindowLongClickListenerImplementor() {
    if (getClass() == GoogleMap_OnInfoWindowLongClickListenerImplementor.class)
      TypeManager.Activate("Android.Gms.Maps.GoogleMap+IOnInfoWindowLongClickListenerImplementor, Xamarin.GooglePlayServices.Maps", "", this, new Object[0]); 
  }
  
  private native void n_onInfoWindowLongClick(Marker paramMarker);
  
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
  
  public void onInfoWindowLongClick(Marker paramMarker) {
    n_onInfoWindowLongClick(paramMarker);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/mono/com/google/android/gms/maps/GoogleMap_OnInfoWindowLongClickListenerImplementor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */