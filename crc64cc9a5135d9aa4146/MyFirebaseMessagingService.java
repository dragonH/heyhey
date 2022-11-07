package crc64cc9a5135d9aa4146;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class MyFirebaseMessagingService extends FirebaseMessagingService implements IGCUserPeer {
  public static final String __md_methods = "n_onMessageReceived:(Lcom/google/firebase/messaging/RemoteMessage;)V:GetOnMessageReceived_Lcom_google_firebase_messaging_RemoteMessage_Handler\n";
  
  private ArrayList refList;
  
  static {
    Runtime.register("SCSAPPForms.Droid.MyFirebaseMessagingService, SCS Mobile APP", MyFirebaseMessagingService.class, "n_onMessageReceived:(Lcom/google/firebase/messaging/RemoteMessage;)V:GetOnMessageReceived_Lcom_google_firebase_messaging_RemoteMessage_Handler\n");
  }
  
  public MyFirebaseMessagingService() {
    if (getClass() == MyFirebaseMessagingService.class)
      TypeManager.Activate("SCSAPPForms.Droid.MyFirebaseMessagingService, SCS Mobile APP", "", this, new Object[0]); 
  }
  
  private native void n_onMessageReceived(RemoteMessage paramRemoteMessage);
  
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
  
  public void onMessageReceived(RemoteMessage paramRemoteMessage) {
    n_onMessageReceived(paramRemoteMessage);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/crc64cc9a5135d9aa4146/MyFirebaseMessagingService.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */