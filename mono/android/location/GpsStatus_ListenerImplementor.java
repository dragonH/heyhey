package mono.android.location;

import android.location.GpsStatus;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class GpsStatus_ListenerImplementor implements IGCUserPeer, GpsStatus.Listener {
  public static final String __md_methods = "n_onGpsStatusChanged:(I)V:GetOnGpsStatusChanged_IHandler:Android.Locations.GpsStatus/IListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n";
  
  private ArrayList refList;
  
  static {
    Runtime.register("Android.Locations.GpsStatus+IListenerImplementor, Mono.Android", GpsStatus_ListenerImplementor.class, "n_onGpsStatusChanged:(I)V:GetOnGpsStatusChanged_IHandler:Android.Locations.GpsStatus/IListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n");
  }
  
  public GpsStatus_ListenerImplementor() {
    if (getClass() == GpsStatus_ListenerImplementor.class)
      TypeManager.Activate("Android.Locations.GpsStatus+IListenerImplementor, Mono.Android", "", this, new Object[0]); 
  }
  
  private native void n_onGpsStatusChanged(int paramInt);
  
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
  
  public void onGpsStatusChanged(int paramInt) {
    n_onGpsStatusChanged(paramInt);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/mono/android/location/GpsStatus_ListenerImplementor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */