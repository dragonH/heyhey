package mono.com.google.android.gms.maps;

import android.location.Location;
import com.google.android.gms.maps.LocationSource;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class LocationSource_OnLocationChangedListenerImplementor implements IGCUserPeer, LocationSource.OnLocationChangedListener {
  public static final String __md_methods = "n_onLocationChanged:(Landroid/location/Location;)V:GetOnLocationChanged_Landroid_location_Location_Handler:Android.Gms.Maps.ILocationSourceOnLocationChangedListenerInvoker, Xamarin.GooglePlayServices.Maps\n";
  
  private ArrayList refList;
  
  static {
    Runtime.register("Android.Gms.Maps.ILocationSourceOnLocationChangedListenerImplementor, Xamarin.GooglePlayServices.Maps", LocationSource_OnLocationChangedListenerImplementor.class, "n_onLocationChanged:(Landroid/location/Location;)V:GetOnLocationChanged_Landroid_location_Location_Handler:Android.Gms.Maps.ILocationSourceOnLocationChangedListenerInvoker, Xamarin.GooglePlayServices.Maps\n");
  }
  
  public LocationSource_OnLocationChangedListenerImplementor() {
    if (getClass() == LocationSource_OnLocationChangedListenerImplementor.class)
      TypeManager.Activate("Android.Gms.Maps.ILocationSourceOnLocationChangedListenerImplementor, Xamarin.GooglePlayServices.Maps", "", this, new Object[0]); 
  }
  
  private native void n_onLocationChanged(Location paramLocation);
  
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
  
  public void onLocationChanged(Location paramLocation) {
    n_onLocationChanged(paramLocation);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/mono/com/google/android/gms/maps/LocationSource_OnLocationChangedListenerImplementor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */