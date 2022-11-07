package crc643f46942d9dd1fff9;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.WindowManager;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class FormsAppCompatActivity extends AppCompatActivity implements IGCUserPeer {
  public static final String __md_methods = "n_onBackPressed:()V:GetOnBackPressedHandler\nn_onConfigurationChanged:(Landroid/content/res/Configuration;)V:GetOnConfigurationChanged_Landroid_content_res_Configuration_Handler\nn_onOptionsItemSelected:(Landroid/view/MenuItem;)Z:GetOnOptionsItemSelected_Landroid_view_MenuItem_Handler\nn_onActivityResult:(IILandroid/content/Intent;)V:GetOnActivityResult_IILandroid_content_Intent_Handler\nn_onCreate:(Landroid/os/Bundle;)V:GetOnCreate_Landroid_os_Bundle_Handler\nn_onDestroy:()V:GetOnDestroyHandler\nn_onNewIntent:(Landroid/content/Intent;)V:GetOnNewIntent_Landroid_content_Intent_Handler\nn_onPause:()V:GetOnPauseHandler\nn_onRestart:()V:GetOnRestartHandler\nn_onResume:()V:GetOnResumeHandler\nn_onStart:()V:GetOnStartHandler\nn_onStop:()V:GetOnStopHandler\nn_onWindowAttributesChanged:(Landroid/view/WindowManager$LayoutParams;)V:GetOnWindowAttributesChanged_Landroid_view_WindowManager_LayoutParams_Handler\n";
  
  private ArrayList refList;
  
  static {
    Runtime.register("Xamarin.Forms.Platform.Android.FormsAppCompatActivity, Xamarin.Forms.Platform.Android", FormsAppCompatActivity.class, "n_onBackPressed:()V:GetOnBackPressedHandler\nn_onConfigurationChanged:(Landroid/content/res/Configuration;)V:GetOnConfigurationChanged_Landroid_content_res_Configuration_Handler\nn_onOptionsItemSelected:(Landroid/view/MenuItem;)Z:GetOnOptionsItemSelected_Landroid_view_MenuItem_Handler\nn_onActivityResult:(IILandroid/content/Intent;)V:GetOnActivityResult_IILandroid_content_Intent_Handler\nn_onCreate:(Landroid/os/Bundle;)V:GetOnCreate_Landroid_os_Bundle_Handler\nn_onDestroy:()V:GetOnDestroyHandler\nn_onNewIntent:(Landroid/content/Intent;)V:GetOnNewIntent_Landroid_content_Intent_Handler\nn_onPause:()V:GetOnPauseHandler\nn_onRestart:()V:GetOnRestartHandler\nn_onResume:()V:GetOnResumeHandler\nn_onStart:()V:GetOnStartHandler\nn_onStop:()V:GetOnStopHandler\nn_onWindowAttributesChanged:(Landroid/view/WindowManager$LayoutParams;)V:GetOnWindowAttributesChanged_Landroid_view_WindowManager_LayoutParams_Handler\n");
  }
  
  public FormsAppCompatActivity() {
    if (getClass() == FormsAppCompatActivity.class)
      TypeManager.Activate("Xamarin.Forms.Platform.Android.FormsAppCompatActivity, Xamarin.Forms.Platform.Android", "", this, new Object[0]); 
  }
  
  private native void n_onActivityResult(int paramInt1, int paramInt2, Intent paramIntent);
  
  private native void n_onBackPressed();
  
  private native void n_onConfigurationChanged(Configuration paramConfiguration);
  
  private native void n_onCreate(Bundle paramBundle);
  
  private native void n_onDestroy();
  
  private native void n_onNewIntent(Intent paramIntent);
  
  private native boolean n_onOptionsItemSelected(MenuItem paramMenuItem);
  
  private native void n_onPause();
  
  private native void n_onRestart();
  
  private native void n_onResume();
  
  private native void n_onStart();
  
  private native void n_onStop();
  
  private native void n_onWindowAttributesChanged(WindowManager.LayoutParams paramLayoutParams);
  
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
  
  public void onNewIntent(Intent paramIntent) {
    n_onNewIntent(paramIntent);
  }
  
  public boolean onOptionsItemSelected(MenuItem paramMenuItem) {
    return n_onOptionsItemSelected(paramMenuItem);
  }
  
  public void onPause() {
    n_onPause();
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
  
  public void onWindowAttributesChanged(WindowManager.LayoutParams paramLayoutParams) {
    n_onWindowAttributesChanged(paramLayoutParams);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/crc643f46942d9dd1fff9/FormsAppCompatActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */