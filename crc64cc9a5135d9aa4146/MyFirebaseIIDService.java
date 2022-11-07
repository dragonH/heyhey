package crc64cc9a5135d9aa4146;

import com.google.firebase.iid.FirebaseInstanceIdService;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class MyFirebaseIIDService extends FirebaseInstanceIdService implements IGCUserPeer {
  public static final String __md_methods = "n_onTokenRefresh:()V:GetOnTokenRefreshHandler\n";
  
  private ArrayList refList;
  
  static {
    Runtime.register("SCSAPPForms.Droid.MyFirebaseIIDService, SCS Mobile APP", MyFirebaseIIDService.class, "n_onTokenRefresh:()V:GetOnTokenRefreshHandler\n");
  }
  
  public MyFirebaseIIDService() {
    if (getClass() == MyFirebaseIIDService.class)
      TypeManager.Activate("SCSAPPForms.Droid.MyFirebaseIIDService, SCS Mobile APP", "", this, new Object[0]); 
  }
  
  private native void n_onTokenRefresh();
  
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
  
  public void onTokenRefresh() {
    n_onTokenRefresh();
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/crc64cc9a5135d9aa4146/MyFirebaseIIDService.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */