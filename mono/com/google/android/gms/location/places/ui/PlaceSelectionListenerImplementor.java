package mono.com.google.android.gms.location.places.ui;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class PlaceSelectionListenerImplementor implements IGCUserPeer, PlaceSelectionListener {
  public static final String __md_methods = "n_onError:(Lcom/google/android/gms/common/api/Status;)V:GetOnError_Lcom_google_android_gms_common_api_Status_Handler:Android.Gms.Location.Places.UI.IPlaceSelectionListenerInvoker, Xamarin.GooglePlayServices.Places\nn_onPlaceSelected:(Lcom/google/android/gms/location/places/Place;)V:GetOnPlaceSelected_Lcom_google_android_gms_location_places_Place_Handler:Android.Gms.Location.Places.UI.IPlaceSelectionListenerInvoker, Xamarin.GooglePlayServices.Places\n";
  
  private ArrayList refList;
  
  static {
    Runtime.register("Android.Gms.Location.Places.UI.IPlaceSelectionListenerImplementor, Xamarin.GooglePlayServices.Places", PlaceSelectionListenerImplementor.class, "n_onError:(Lcom/google/android/gms/common/api/Status;)V:GetOnError_Lcom_google_android_gms_common_api_Status_Handler:Android.Gms.Location.Places.UI.IPlaceSelectionListenerInvoker, Xamarin.GooglePlayServices.Places\nn_onPlaceSelected:(Lcom/google/android/gms/location/places/Place;)V:GetOnPlaceSelected_Lcom_google_android_gms_location_places_Place_Handler:Android.Gms.Location.Places.UI.IPlaceSelectionListenerInvoker, Xamarin.GooglePlayServices.Places\n");
  }
  
  public PlaceSelectionListenerImplementor() {
    if (getClass() == PlaceSelectionListenerImplementor.class)
      TypeManager.Activate("Android.Gms.Location.Places.UI.IPlaceSelectionListenerImplementor, Xamarin.GooglePlayServices.Places", "", this, new Object[0]); 
  }
  
  private native void n_onError(Status paramStatus);
  
  private native void n_onPlaceSelected(Place paramPlace);
  
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
  
  public void onError(Status paramStatus) {
    n_onError(paramStatus);
  }
  
  public void onPlaceSelected(Place paramPlace) {
    n_onPlaceSelected(paramPlace);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/mono/com/google/android/gms/location/places/ui/PlaceSelectionListenerImplementor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */