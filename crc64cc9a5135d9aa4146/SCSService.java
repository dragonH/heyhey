package crc64cc9a5135d9aa4146;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class SCSService extends Service implements IGCUserPeer {
  public static final String __md_methods = "n_onBind:(Landroid/content/Intent;)Landroid/os/IBinder;:GetOnBind_Landroid_content_Intent_Handler\nn_onStartCommand:(Landroid/content/Intent;II)I:GetOnStartCommand_Landroid_content_Intent_IIHandler\nn_onDestroy:()V:GetOnDestroyHandler\n";
  
  private ArrayList refList;
  
  static {
    Runtime.register("SCSAPPForms.Droid.SCSService, SCS Mobile APP", SCSService.class, "n_onBind:(Landroid/content/Intent;)Landroid/os/IBinder;:GetOnBind_Landroid_content_Intent_Handler\nn_onStartCommand:(Landroid/content/Intent;II)I:GetOnStartCommand_Landroid_content_Intent_IIHandler\nn_onDestroy:()V:GetOnDestroyHandler\n");
  }
  
  public SCSService() {
    if (getClass() == SCSService.class)
      TypeManager.Activate("SCSAPPForms.Droid.SCSService, SCS Mobile APP", "", this, new Object[0]); 
  }
  
  private native IBinder n_onBind(Intent paramIntent);
  
  private native void n_onDestroy();
  
  private native int n_onStartCommand(Intent paramIntent, int paramInt1, int paramInt2);
  
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
  
  public IBinder onBind(Intent paramIntent) {
    return n_onBind(paramIntent);
  }
  
  public void onDestroy() {
    n_onDestroy();
  }
  
  public int onStartCommand(Intent paramIntent, int paramInt1, int paramInt2) {
    return n_onStartCommand(paramIntent, paramInt1, paramInt2);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/crc64cc9a5135d9aa4146/SCSService.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */