package mono.com.google.android.gms.common.api;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class GoogleApiClient_OnConnectionFailedListenerImplementor implements IGCUserPeer, GoogleApiClient.OnConnectionFailedListener {
  public static final String __md_methods = "n_onConnectionFailed:(Lcom/google/android/gms/common/ConnectionResult;)V:GetOnConnectionFailed_Lcom_google_android_gms_common_ConnectionResult_Handler:Android.Gms.Common.Apis.GoogleApiClient/IOnConnectionFailedListenerInvoker, Xamarin.GooglePlayServices.Base\n";
  
  private ArrayList refList;
  
  static {
    Runtime.register("Android.Gms.Common.Apis.GoogleApiClient+IOnConnectionFailedListenerImplementor, Xamarin.GooglePlayServices.Base", GoogleApiClient_OnConnectionFailedListenerImplementor.class, "n_onConnectionFailed:(Lcom/google/android/gms/common/ConnectionResult;)V:GetOnConnectionFailed_Lcom_google_android_gms_common_ConnectionResult_Handler:Android.Gms.Common.Apis.GoogleApiClient/IOnConnectionFailedListenerInvoker, Xamarin.GooglePlayServices.Base\n");
  }
  
  public GoogleApiClient_OnConnectionFailedListenerImplementor() {
    if (getClass() == GoogleApiClient_OnConnectionFailedListenerImplementor.class)
      TypeManager.Activate("Android.Gms.Common.Apis.GoogleApiClient+IOnConnectionFailedListenerImplementor, Xamarin.GooglePlayServices.Base", "", this, new Object[0]); 
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


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/mono/com/google/android/gms/common/api/GoogleApiClient_OnConnectionFailedListenerImplementor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */