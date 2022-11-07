package mono.com.google.android.gms.maps;

import com.google.android.gms.maps.GoogleMap;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class GoogleMap_OnMyLocationButtonClickListenerImplementor implements IGCUserPeer, GoogleMap.OnMyLocationButtonClickListener {
  public static final String __md_methods = "n_onMyLocationButtonClick:()Z:GetOnMyLocationButtonClickHandler:Android.Gms.Maps.GoogleMap/IOnMyLocationButtonClickListenerInvoker, Xamarin.GooglePlayServices.Maps\n";
  
  private ArrayList refList;
  
  static {
    Runtime.register("Android.Gms.Maps.GoogleMap+IOnMyLocationButtonClickListenerImplementor, Xamarin.GooglePlayServices.Maps", GoogleMap_OnMyLocationButtonClickListenerImplementor.class, "n_onMyLocationButtonClick:()Z:GetOnMyLocationButtonClickHandler:Android.Gms.Maps.GoogleMap/IOnMyLocationButtonClickListenerInvoker, Xamarin.GooglePlayServices.Maps\n");
  }
  
  public GoogleMap_OnMyLocationButtonClickListenerImplementor() {
    if (getClass() == GoogleMap_OnMyLocationButtonClickListenerImplementor.class)
      TypeManager.Activate("Android.Gms.Maps.GoogleMap+IOnMyLocationButtonClickListenerImplementor, Xamarin.GooglePlayServices.Maps", "", this, new Object[0]); 
  }
  
  private native boolean n_onMyLocationButtonClick();
  
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
  
  public boolean onMyLocationButtonClick() {
    return n_onMyLocationButtonClick();
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/mono/com/google/android/gms/maps/GoogleMap_OnMyLocationButtonClickListenerImplementor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */