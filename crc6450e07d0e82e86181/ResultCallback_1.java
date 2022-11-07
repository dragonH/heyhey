package crc6450e07d0e82e86181;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.ResultCallback;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class ResultCallback_1 implements IGCUserPeer, ResultCallback {
  public static final String __md_methods = "n_onResult:(Lcom/google/android/gms/common/api/Result;)V:GetOnResult_Lcom_google_android_gms_common_api_Result_Handler:Android.Gms.Common.Apis.IResultCallbackInvoker, Xamarin.GooglePlayServices.Basement\n";
  
  private ArrayList refList;
  
  static {
    Runtime.register("Android.Gms.Common.Apis.ResultCallback`1, Xamarin.GooglePlayServices.Basement", ResultCallback_1.class, "n_onResult:(Lcom/google/android/gms/common/api/Result;)V:GetOnResult_Lcom_google_android_gms_common_api_Result_Handler:Android.Gms.Common.Apis.IResultCallbackInvoker, Xamarin.GooglePlayServices.Basement\n");
  }
  
  public ResultCallback_1() {
    if (getClass() == ResultCallback_1.class)
      TypeManager.Activate("Android.Gms.Common.Apis.ResultCallback`1, Xamarin.GooglePlayServices.Basement", "", this, new Object[0]); 
  }
  
  private native void n_onResult(Result paramResult);
  
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
  
  public void onResult(Result paramResult) {
    n_onResult(paramResult);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/crc6450e07d0e82e86181/ResultCallback_1.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */