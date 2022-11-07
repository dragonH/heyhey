package crc6434af9c19aa01b597;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class GoogleApiClientOnConnectionFailedListenerImpl implements IGCUserPeer, GoogleApiClient.OnConnectionFailedListener {
  public static final String __md_methods = "n_onConnectionFailed:(Lcom/google/android/gms/common/ConnectionResult;)V:GetOnConnectionFailed_Lcom_google_android_gms_common_ConnectionResult_Handler:Android.Gms.Common.Apis.GoogleApiClient/IOnConnectionFailedListenerInvoker, Xamarin.GooglePlayServices.Base\n";
  
  private ArrayList refList;
  
  static {
    Runtime.register("Android.Gms.Common.Apis.GoogleApiClientOnConnectionFailedListenerImpl, Xamarin.GooglePlayServices.Base", GoogleApiClientOnConnectionFailedListenerImpl.class, "n_onConnectionFailed:(Lcom/google/android/gms/common/ConnectionResult;)V:GetOnConnectionFailed_Lcom_google_android_gms_common_ConnectionResult_Handler:Android.Gms.Common.Apis.GoogleApiClient/IOnConnectionFailedListenerInvoker, Xamarin.GooglePlayServices.Base\n");
  }
  
  public GoogleApiClientOnConnectionFailedListenerImpl() {
    if (getClass() == GoogleApiClientOnConnectionFailedListenerImpl.class)
      TypeManager.Activate("Android.Gms.Common.Apis.GoogleApiClientOnConnectionFailedListenerImpl, Xamarin.GooglePlayServices.Base", "", this, new Object[0]); 
  }
  
  private native void n_onConnectionFailed(ConnectionResult paramConnectionResult);
  
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
  
  public void onConnectionFailed(ConnectionResult paramConnectionResult) {
    n_onConnectionFailed(paramConnectionResult);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/crc6434af9c19aa01b597/GoogleApiClientOnConnectionFailedListenerImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */