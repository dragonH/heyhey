package crc6434af9c19aa01b597;

import android.os.Bundle;
import com.google.android.gms.common.api.GoogleApiClient;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class GoogleApiClientConnectionCallbacksImpl implements IGCUserPeer, GoogleApiClient.ConnectionCallbacks {
  public static final String __md_methods = "n_onConnected:(Landroid/os/Bundle;)V:GetOnConnected_Landroid_os_Bundle_Handler:Android.Gms.Common.Apis.GoogleApiClient/IConnectionCallbacksInvoker, Xamarin.GooglePlayServices.Base\nn_onConnectionSuspended:(I)V:GetOnConnectionSuspended_IHandler:Android.Gms.Common.Apis.GoogleApiClient/IConnectionCallbacksInvoker, Xamarin.GooglePlayServices.Base\n";
  
  private ArrayList refList;
  
  static {
    Runtime.register("Android.Gms.Common.Apis.GoogleApiClientConnectionCallbacksImpl, Xamarin.GooglePlayServices.Base", GoogleApiClientConnectionCallbacksImpl.class, "n_onConnected:(Landroid/os/Bundle;)V:GetOnConnected_Landroid_os_Bundle_Handler:Android.Gms.Common.Apis.GoogleApiClient/IConnectionCallbacksInvoker, Xamarin.GooglePlayServices.Base\nn_onConnectionSuspended:(I)V:GetOnConnectionSuspended_IHandler:Android.Gms.Common.Apis.GoogleApiClient/IConnectionCallbacksInvoker, Xamarin.GooglePlayServices.Base\n");
  }
  
  public GoogleApiClientConnectionCallbacksImpl() {
    if (getClass() == GoogleApiClientConnectionCallbacksImpl.class)
      TypeManager.Activate("Android.Gms.Common.Apis.GoogleApiClientConnectionCallbacksImpl, Xamarin.GooglePlayServices.Base", "", this, new Object[0]); 
  }
  
  private native void n_onConnected(Bundle paramBundle);
  
  private native void n_onConnectionSuspended(int paramInt);
  
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
  
  public void onConnected(Bundle paramBundle) {
    n_onConnected(paramBundle);
  }
  
  public void onConnectionSuspended(int paramInt) {
    n_onConnectionSuspended(paramInt);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/crc6434af9c19aa01b597/GoogleApiClientConnectionCallbacksImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */