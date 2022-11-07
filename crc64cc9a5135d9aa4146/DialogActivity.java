package crc64cc9a5135d9aa4146;

import android.app.Activity;
import android.os.Bundle;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class DialogActivity extends Activity implements IGCUserPeer {
  public static final String __md_methods = "n_onCreate:(Landroid/os/Bundle;)V:GetOnCreate_Landroid_os_Bundle_Handler\n";
  
  private ArrayList refList;
  
  static {
    Runtime.register("SCSAPPForms.Droid.DialogActivity, SCS Mobile APP", DialogActivity.class, "n_onCreate:(Landroid/os/Bundle;)V:GetOnCreate_Landroid_os_Bundle_Handler\n");
  }
  
  public DialogActivity() {
    if (getClass() == DialogActivity.class)
      TypeManager.Activate("SCSAPPForms.Droid.DialogActivity, SCS Mobile APP", "", this, new Object[0]); 
  }
  
  private native void n_onCreate(Bundle paramBundle);
  
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
  
  public void onCreate(Bundle paramBundle) {
    n_onCreate(paramBundle);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/crc64cc9a5135d9aa4146/DialogActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */