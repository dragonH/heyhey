package crc6480997b3ef81bf9b2;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.KeyEvent;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class ZxingActivity extends FragmentActivity implements IGCUserPeer {
  public static final String __md_methods = "n_onCreate:(Landroid/os/Bundle;)V:GetOnCreate_Landroid_os_Bundle_Handler\nn_onResume:()V:GetOnResumeHandler\nn_onRequestPermissionsResult:(I[Ljava/lang/String;[I)V:GetOnRequestPermissionsResult_IarrayLjava_lang_String_arrayIHandler\nn_onConfigurationChanged:(Landroid/content/res/Configuration;)V:GetOnConfigurationChanged_Landroid_content_res_Configuration_Handler\nn_onKeyDown:(ILandroid/view/KeyEvent;)Z:GetOnKeyDown_ILandroid_view_KeyEvent_Handler\n";
  
  private ArrayList refList;
  
  static {
    Runtime.register("ZXing.Mobile.ZxingActivity, ZXingNetMobile", ZxingActivity.class, "n_onCreate:(Landroid/os/Bundle;)V:GetOnCreate_Landroid_os_Bundle_Handler\nn_onResume:()V:GetOnResumeHandler\nn_onRequestPermissionsResult:(I[Ljava/lang/String;[I)V:GetOnRequestPermissionsResult_IarrayLjava_lang_String_arrayIHandler\nn_onConfigurationChanged:(Landroid/content/res/Configuration;)V:GetOnConfigurationChanged_Landroid_content_res_Configuration_Handler\nn_onKeyDown:(ILandroid/view/KeyEvent;)Z:GetOnKeyDown_ILandroid_view_KeyEvent_Handler\n");
  }
  
  public ZxingActivity() {
    if (getClass() == ZxingActivity.class)
      TypeManager.Activate("ZXing.Mobile.ZxingActivity, ZXingNetMobile", "", this, new Object[0]); 
  }
  
  private native void n_onConfigurationChanged(Configuration paramConfiguration);
  
  private native void n_onCreate(Bundle paramBundle);
  
  private native boolean n_onKeyDown(int paramInt, KeyEvent paramKeyEvent);
  
  private native void n_onRequestPermissionsResult(int paramInt, String[] paramArrayOfString, int[] paramArrayOfint);
  
  private native void n_onResume();
  
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
  
  public void onConfigurationChanged(Configuration paramConfiguration) {
    n_onConfigurationChanged(paramConfiguration);
  }
  
  public void onCreate(Bundle paramBundle) {
    n_onCreate(paramBundle);
  }
  
  public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent) {
    return n_onKeyDown(paramInt, paramKeyEvent);
  }
  
  public void onRequestPermissionsResult(int paramInt, String[] paramArrayOfString, int[] paramArrayOfint) {
    n_onRequestPermissionsResult(paramInt, paramArrayOfString, paramArrayOfint);
  }
  
  public void onResume() {
    n_onResume();
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/crc6480997b3ef81bf9b2/ZxingActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */