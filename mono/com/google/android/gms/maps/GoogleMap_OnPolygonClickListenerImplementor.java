package mono.com.google.android.gms.maps;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Polygon;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class GoogleMap_OnPolygonClickListenerImplementor implements IGCUserPeer, GoogleMap.OnPolygonClickListener {
  public static final String __md_methods = "n_onPolygonClick:(Lcom/google/android/gms/maps/model/Polygon;)V:GetOnPolygonClick_Lcom_google_android_gms_maps_model_Polygon_Handler:Android.Gms.Maps.GoogleMap/IOnPolygonClickListenerInvoker, Xamarin.GooglePlayServices.Maps\n";
  
  private ArrayList refList;
  
  static {
    Runtime.register("Android.Gms.Maps.GoogleMap+IOnPolygonClickListenerImplementor, Xamarin.GooglePlayServices.Maps", GoogleMap_OnPolygonClickListenerImplementor.class, "n_onPolygonClick:(Lcom/google/android/gms/maps/model/Polygon;)V:GetOnPolygonClick_Lcom_google_android_gms_maps_model_Polygon_Handler:Android.Gms.Maps.GoogleMap/IOnPolygonClickListenerInvoker, Xamarin.GooglePlayServices.Maps\n");
  }
  
  public GoogleMap_OnPolygonClickListenerImplementor() {
    if (getClass() == GoogleMap_OnPolygonClickListenerImplementor.class)
      TypeManager.Activate("Android.Gms.Maps.GoogleMap+IOnPolygonClickListenerImplementor, Xamarin.GooglePlayServices.Maps", "", this, new Object[0]); 
  }
  
  private native void n_onPolygonClick(Polygon paramPolygon);
  
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
  
  public void onPolygonClick(Polygon paramPolygon) {
    n_onPolygonClick(paramPolygon);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/mono/com/google/android/gms/maps/GoogleMap_OnPolygonClickListenerImplementor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */