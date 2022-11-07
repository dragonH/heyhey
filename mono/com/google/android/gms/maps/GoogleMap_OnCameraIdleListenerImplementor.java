package mono.com.google.android.gms.maps;

import com.google.android.gms.maps.GoogleMap;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class GoogleMap_OnCameraIdleListenerImplementor implements IGCUserPeer, GoogleMap.OnCameraIdleListener {
  public static final String __md_methods = "n_onCameraIdle:()V:GetOnCameraIdleHandler:Android.Gms.Maps.GoogleMap/IOnCameraIdleListenerInvoker, Xamarin.GooglePlayServices.Maps\n";
  
  private ArrayList refList;
  
  static {
    Runtime.register("Android.Gms.Maps.GoogleMap+IOnCameraIdleListenerImplementor, Xamarin.GooglePlayServices.Maps", GoogleMap_OnCameraIdleListenerImplementor.class, "n_onCameraIdle:()V:GetOnCameraIdleHandler:Android.Gms.Maps.GoogleMap/IOnCameraIdleListenerInvoker, Xamarin.GooglePlayServices.Maps\n");
  }
  
  public GoogleMap_OnCameraIdleListenerImplementor() {
    if (getClass() == GoogleMap_OnCameraIdleListenerImplementor.class)
      TypeManager.Activate("Android.Gms.Maps.GoogleMap+IOnCameraIdleListenerImplementor, Xamarin.GooglePlayServices.Maps", "", this, new Object[0]); 
  }
  
  private native void n_onCameraIdle();
  
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
  
  public void onCameraIdle() {
    n_onCameraIdle();
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/mono/com/google/android/gms/maps/GoogleMap_OnCameraIdleListenerImplementor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */