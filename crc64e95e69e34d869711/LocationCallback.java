package crc64e95e69e34d869711;

import com.google.android.gms.location.LocationAvailability;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationResult;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class LocationCallback extends LocationCallback implements IGCUserPeer {
  public static final String __md_methods = "n_onLocationResult:(Lcom/google/android/gms/location/LocationResult;)V:GetOnLocationResult_Lcom_google_android_gms_location_LocationResult_Handler\nn_onLocationAvailability:(Lcom/google/android/gms/location/LocationAvailability;)V:GetOnLocationAvailability_Lcom_google_android_gms_location_LocationAvailability_Handler\n";
  
  private ArrayList refList;
  
  static {
    Runtime.register("Android.Gms.Location.LocationCallback, Xamarin.GooglePlayServices.Location", LocationCallback.class, "n_onLocationResult:(Lcom/google/android/gms/location/LocationResult;)V:GetOnLocationResult_Lcom_google_android_gms_location_LocationResult_Handler\nn_onLocationAvailability:(Lcom/google/android/gms/location/LocationAvailability;)V:GetOnLocationAvailability_Lcom_google_android_gms_location_LocationAvailability_Handler\n");
  }
  
  public LocationCallback() {
    if (getClass() == LocationCallback.class)
      TypeManager.Activate("Android.Gms.Location.LocationCallback, Xamarin.GooglePlayServices.Location", "", this, new Object[0]); 
  }
  
  private native void n_onLocationAvailability(LocationAvailability paramLocationAvailability);
  
  private native void n_onLocationResult(LocationResult paramLocationResult);
  
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
  
  public void onLocationAvailability(LocationAvailability paramLocationAvailability) {
    n_onLocationAvailability(paramLocationAvailability);
  }
  
  public void onLocationResult(LocationResult paramLocationResult) {
    n_onLocationResult(paramLocationResult);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/crc64e95e69e34d869711/LocationCallback.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */