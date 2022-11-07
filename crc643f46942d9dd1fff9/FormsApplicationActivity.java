package crc643f46942d9dd1fff9;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class FormsApplicationActivity extends Activity implements IGCUserPeer {
  public static final String __md_methods = "n_onBackPressed:()V:GetOnBackPressedHandler\nn_onConfigurationChanged:(Landroid/content/res/Configuration;)V:GetOnConfigurationChanged_Landroid_content_res_Configuration_Handler\nn_onOptionsItemSelected:(Landroid/view/MenuItem;)Z:GetOnOptionsItemSelected_Landroid_view_MenuItem_Handler\nn_onPrepareOptionsMenu:(Landroid/view/Menu;)Z:GetOnPrepareOptionsMenu_Landroid_view_Menu_Handler\nn_onActivityResult:(IILandroid/content/Intent;)V:GetOnActivityResult_IILandroid_content_Intent_Handler\nn_onCreate:(Landroid/os/Bundle;)V:GetOnCreate_Landroid_os_Bundle_Handler\nn_onDestroy:()V:GetOnDestroyHandler\nn_onPause:()V:GetOnPauseHandler\nn_onRestart:()V:GetOnRestartHandler\nn_onResume:()V:GetOnResumeHandler\nn_onStart:()V:GetOnStartHandler\nn_onStop:()V:GetOnStopHandler\n";
  
  private ArrayList refList;
  
  static {
    Runtime.register("Xamarin.Forms.Platform.Android.FormsApplicationActivity, Xamarin.Forms.Platform.Android", FormsApplicationActivity.class, "n_onBackPressed:()V:GetOnBackPressedHandler\nn_onConfigurationChanged:(Landroid/content/res/Configuration;)V:GetOnConfigurationChanged_Landroid_content_res_Configuration_Handler\nn_onOptionsItemSelected:(Landroid/view/MenuItem;)Z:GetOnOptionsItemSelected_Landroid_view_MenuItem_Handler\nn_onPrepareOptionsMenu:(Landroid/view/Menu;)Z:GetOnPrepareOptionsMenu_Landroid_view_Menu_Handler\nn_onActivityResult:(IILandroid/content/Intent;)V:GetOnActivityResult_IILandroid_content_Intent_Handler\nn_onCreate:(Landroid/os/Bundle;)V:GetOnCreate_Landroid_os_Bundle_Handler\nn_onDestroy:()V:GetOnDestroyHandler\nn_onPause:()V:GetOnPauseHandler\nn_onRestart:()V:GetOnRestartHandler\nn_onResume:()V:GetOnResumeHandler\nn_onStart:()V:GetOnStartHandler\nn_onStop:()V:GetOnStopHandler\n");
  }
  
  public FormsApplicationActivity() {
    if (getClass() == FormsApplicationActivity.class)
      TypeManager.Activate("Xamarin.Forms.Platform.Android.FormsApplicationActivity, Xamarin.Forms.Platform.Android", "", this, new Object[0]); 
  }
  
  private native void n_onActivityResult(int paramInt1, int paramInt2, Intent paramIntent);
  
  private native void n_onBackPressed();
  
  private native void n_onConfigurationChanged(Configuration paramConfiguration);
  
  private native void n_onCreate(Bundle paramBundle);
  
  private native void n_onDestroy();
  
  private native boolean n_onOptionsItemSelected(MenuItem paramMenuItem);
  
  private native void n_onPause();
  
  private native boolean n_onPrepareOptionsMenu(Menu paramMenu);
  
  private native void n_onRestart();
  
  private native void n_onResume();
  
  private native void n_onStart();
  
  private native void n_onStop();
  
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
  
  public void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent) {
    n_onActivityResult(paramInt1, paramInt2, paramIntent);
  }
  
  public void onBackPressed() {
    n_onBackPressed();
  }
  
  public void onConfigurationChanged(Configuration paramConfiguration) {
    n_onConfigurationChanged(paramConfiguration);
  }
  
  public void onCreate(Bundle paramBundle) {
    n_onCreate(paramBundle);
  }
  
  public void onDestroy() {
    n_onDestroy();
  }
  
  public boolean onOptionsItemSelected(MenuItem paramMenuItem) {
    return n_onOptionsItemSelected(paramMenuItem);
  }
  
  public void onPause() {
    n_onPause();
  }
  
  public boolean onPrepareOptionsMenu(Menu paramMenu) {
    return n_onPrepareOptionsMenu(paramMenu);
  }
  
  public void onRestart() {
    n_onRestart();
  }
  
  public void onResume() {
    n_onResume();
  }
  
  public void onStart() {
    n_onStart();
  }
  
  public void onStop() {
    n_onStop();
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/crc643f46942d9dd1fff9/FormsApplicationActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */