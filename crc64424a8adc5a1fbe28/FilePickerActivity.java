package crc64424a8adc5a1fbe28;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import java.util.ArrayList;
import mono.android.IGCUserPeer;
import mono.android.Runtime;
import mono.android.TypeManager;

public class FilePickerActivity extends Activity implements IGCUserPeer {
  public static final String __md_methods = "n_onCreate:(Landroid/os/Bundle;)V:GetOnCreate_Landroid_os_Bundle_Handler\nn_onActivityResult:(IILandroid/content/Intent;)V:GetOnActivityResult_IILandroid_content_Intent_Handler\n";
  
  private ArrayList refList;
  
  static {
    Runtime.register("Plugin.FilePicker.FilePickerActivity, Plugin.FilePicker", FilePickerActivity.class, "n_onCreate:(Landroid/os/Bundle;)V:GetOnCreate_Landroid_os_Bundle_Handler\nn_onActivityResult:(IILandroid/content/Intent;)V:GetOnActivityResult_IILandroid_content_Intent_Handler\n");
  }
  
  public FilePickerActivity() {
    if (getClass() == FilePickerActivity.class)
      TypeManager.Activate("Plugin.FilePicker.FilePickerActivity, Plugin.FilePicker", "", this, new Object[0]); 
  }
  
  private native void n_onActivityResult(int paramInt1, int paramInt2, Intent paramIntent);
  
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
  
  public void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent) {
    n_onActivityResult(paramInt1, paramInt2, paramIntent);
  }
  
  public void onCreate(Bundle paramBundle) {
    n_onCreate(paramBundle);
  }
}


/* Location:              /home/dragonh/workspace/work/app/apk-reverse/dex-tools-2.1/target-dex2jar.jar!/crc64424a8adc5a1fbe28/FilePickerActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */