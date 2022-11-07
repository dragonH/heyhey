package crc64e95e69e34d869711;

import android.location.Location;
import com.google.android.gms.location.LocationListener;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class LocationListener implements IGCUserPeer, LocationListener {
  public static final String __md_methods = "n_onLocationChanged:(Landroid/location/Location;)V:GetOnLocationChanged_Landroid_location_Location_Handler:Android.Gms.Location.ILocationListenerInvoker, Xamarin.GooglePlayServices.Location\n";
  
  private ArrayList refList;
  
  static {
    Runtime.register("Android.Gms.Location.LocationListener, Xamarin.GooglePlayServices.Location", LocationListener.class, "n_onLocationChanged:(Landroid/location/Location;)V:GetOnLocationChanged_Landroid_location_Location_Handler:Android.Gms.Location.ILocationListenerInvoker, Xamarin.GooglePlayServices.Location\n");
  }
  
  public LocationListener() {
    if (getClass() == LocationListener.class)
      TypeManager.Activate("Android.Gms.Location.LocationListener, Xamarin.GooglePlayServices.Location", "", this, new Object[0]); 
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


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/crc64e95e69e34d869711/LocationListener.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */