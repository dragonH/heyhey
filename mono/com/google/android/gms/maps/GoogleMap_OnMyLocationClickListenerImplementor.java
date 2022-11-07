package mono.com.google.android.gms.maps;

import android.location.Location;
import com.google.android.gms.maps.GoogleMap;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class GoogleMap_OnMyLocationClickListenerImplementor implements IGCUserPeer, GoogleMap.OnMyLocationClickListener {
  public static final String __md_methods = "n_onMyLocationClick:(Landroid/location/Location;)V:GetOnMyLocationClick_Landroid_location_Location_Handler:Android.Gms.Maps.GoogleMap/IOnMyLocationClickListenerInvoker, Xamarin.GooglePlayServices.Maps\n";
  
  private ArrayList refList;
  
  static {
    Runtime.register("Android.Gms.Maps.GoogleMap+IOnMyLocationClickListenerImplementor, Xamarin.GooglePlayServices.Maps", GoogleMap_OnMyLocationClickListenerImplementor.class, "n_onMyLocationClick:(Landroid/location/Location;)V:GetOnMyLocationClick_Landroid_location_Location_Handler:Android.Gms.Maps.GoogleMap/IOnMyLocationClickListenerInvoker, Xamarin.GooglePlayServices.Maps\n");
  }
  
  public GoogleMap_OnMyLocationClickListenerImplementor() {
    if (getClass() == GoogleMap_OnMyLocationClickListenerImplementor.class)
      TypeManager.Activate("Android.Gms.Maps.GoogleMap+IOnMyLocationClickListenerImplementor, Xamarin.GooglePlayServices.Maps", "", this, new Object[0]); 
  }
  
  private native void n_onMyLocationClick(Location paramLocation);
  
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
  
  public void onMyLocationClick(Location paramLocation) {
    n_onMyLocationClick(paramLocation);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/mono/com/google/android/gms/maps/GoogleMap_OnMyLocationClickListenerImplementor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */