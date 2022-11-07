package crc64cc9a5135d9aa4146;

import android.content.Intent;
import android.os.Bundle;
import crc643f46942d9dd1fff9.FormsApplicationActivity;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class MainActivity extends FormsApplicationActivity implements IGCUserPeer {
  public static final String __md_methods = "n_onCreate:(Landroid/os/Bundle;)V:GetOnCreate_Landroid_os_Bundle_Handler\nn_onRequestPermissionsResult:(I[Ljava/lang/String;[I)V:GetOnRequestPermissionsResult_IarrayLjava_lang_String_arrayIHandler\nn_onPause:()V:GetOnPauseHandler\nn_onResume:()V:GetOnResumeHandler\nn_onStart:()V:GetOnStartHandler\nn_onNewIntent:(Landroid/content/Intent;)V:GetOnNewIntent_Landroid_content_Intent_Handler\n";
  
  private ArrayList refList;
  
  static {
    Runtime.register("SCSAPPForms.Droid.MainActivity, SCS Mobile APP", MainActivity.class, "n_onCreate:(Landroid/os/Bundle;)V:GetOnCreate_Landroid_os_Bundle_Handler\nn_onRequestPermissionsResult:(I[Ljava/lang/String;[I)V:GetOnRequestPermissionsResult_IarrayLjava_lang_String_arrayIHandler\nn_onPause:()V:GetOnPauseHandler\nn_onResume:()V:GetOnResumeHandler\nn_onStart:()V:GetOnStartHandler\nn_onNewIntent:(Landroid/content/Intent;)V:GetOnNewIntent_Landroid_content_Intent_Handler\n");
  }
  
  public MainActivity() {
    if (getClass() == MainActivity.class)
      TypeManager.Activate("SCSAPPForms.Droid.MainActivity, SCS Mobile APP", "", this, new Object[0]); 
  }
  
  private native void n_onCreate(Bundle paramBundle);
  
  private native void n_onNewIntent(Intent paramIntent);
  
  private native void n_onPause();
  
  private native void n_onRequestPermissionsResult(int paramInt, String[] paramArrayOfString, int[] paramArrayOfint);
  
  private native void n_onResume();
  
  private native void n_onStart();
  
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
  
  public void onNewIntent(Intent paramIntent) {
    n_onNewIntent(paramIntent);
  }
  
  public void onPause() {
    n_onPause();
  }
  
  public void onRequestPermissionsResult(int paramInt, String[] paramArrayOfString, int[] paramArrayOfint) {
    n_onRequestPermissionsResult(paramInt, paramArrayOfString, paramArrayOfint);
  }
  
  public void onResume() {
    n_onResume();
  }
  
  public void onStart() {
    n_onStart();
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/crc64cc9a5135d9aa4146/MainActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */