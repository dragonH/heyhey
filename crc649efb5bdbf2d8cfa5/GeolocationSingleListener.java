package crc649efb5bdbf2d8cfa5;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class GeolocationSingleListener implements IGCUserPeer, LocationListener {
  public static final String __md_methods = "n_onLocationChanged:(Landroid/location/Location;)V:GetOnLocationChanged_Landroid_location_Location_Handler:Android.Locations.ILocationListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\nn_onProviderDisabled:(Ljava/lang/String;)V:GetOnProviderDisabled_Ljava_lang_String_Handler:Android.Locations.ILocationListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\nn_onProviderEnabled:(Ljava/lang/String;)V:GetOnProviderEnabled_Ljava_lang_String_Handler:Android.Locations.ILocationListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\nn_onStatusChanged:(Ljava/lang/String;ILandroid/os/Bundle;)V:GetOnStatusChanged_Ljava_lang_String_ILandroid_os_Bundle_Handler:Android.Locations.ILocationListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n";
  
  private ArrayList refList;
  
  static {
    Runtime.register("Plugin.Geolocator.GeolocationSingleListener, Plugin.Geolocator", GeolocationSingleListener.class, "n_onLocationChanged:(Landroid/location/Location;)V:GetOnLocationChanged_Landroid_location_Location_Handler:Android.Locations.ILocationListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\nn_onProviderDisabled:(Ljava/lang/String;)V:GetOnProviderDisabled_Ljava_lang_String_Handler:Android.Locations.ILocationListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\nn_onProviderEnabled:(Ljava/lang/String;)V:GetOnProviderEnabled_Ljava_lang_String_Handler:Android.Locations.ILocationListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\nn_onStatusChanged:(Ljava/lang/String;ILandroid/os/Bundle;)V:GetOnStatusChanged_Ljava_lang_String_ILandroid_os_Bundle_Handler:Android.Locations.ILocationListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n");
  }
  
  public GeolocationSingleListener() {
    if (getClass() == GeolocationSingleListener.class)
      TypeManager.Activate("Plugin.Geolocator.GeolocationSingleListener, Plugin.Geolocator", "", this, new Object[0]); 
  }
  
  private native void n_onLocationChanged(Location paramLocation);
  
  private native void n_onProviderDisabled(String paramString);
  
  private native void n_onProviderEnabled(String paramString);
  
  private native void n_onStatusChanged(String paramString, int paramInt, Bundle paramBundle);
  
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
  
  public void onProviderDisabled(String paramString) {
    n_onProviderDisabled(paramString);
  }
  
  public void onProviderEnabled(String paramString) {
    n_onProviderEnabled(paramString);
  }
  
  public void onStatusChanged(String paramString, int paramInt, Bundle paramBundle) {
    n_onStatusChanged(paramString, paramInt, paramBundle);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/crc649efb5bdbf2d8cfa5/GeolocationSingleListener.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */